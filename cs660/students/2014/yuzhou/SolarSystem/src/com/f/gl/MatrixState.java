package com.f.gl;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.Matrix;

public final class MatrixState {

	private static float[] currMatrix = null ;//当前变换矩阵
	private static float[] mProjMatrix = new float[16];//4x4矩阵 投影用
    private static float[] mCameraMatrix = new float[16];//摄像机位置朝向9参数矩阵   

    
    private static FloatBuffer lightPositionBuffer = null;//定位光位置数据缓冲区
    private static FloatBuffer lightDirectionBuffer = null;//定向灯位置数据缓冲区
    private static FloatBuffer cameraBuffer = null;//摄像机位置数据缓冲区
    
    
    private static float[][] mStack=new float[10][16];
    private static int stackTop=-1;
    /**
     * 初始化当前矩阵
     */
    public static void setInitStack()
    {
    	currMatrix=new float[16];
    	Matrix.setRotateM(currMatrix, 0, 0, 1, 0, 0);
    }
    /**
     * 将当前矩阵压栈
     */
    public static void pushMatrix()
    {
    	stackTop++;
    	for(int i=0;i<16;i++)
    	{
    		mStack[stackTop][i]=currMatrix[i];
    	}
    }
    /**
     * 将当前矩阵出栈
     */
    public static void popMatrix()
    {
    	for(int i=0;i<16;i++)
    	{
    		currMatrix[i]=mStack[stackTop][i];
    	}
    	stackTop--;
    }
    /**
     * 将当前矩阵沿xyz方向移动
     * @param x 
     * @param y
     * @param z
     */
    public static void translate(float x,float y,float z)
    {
    	Matrix.translateM(currMatrix, 0, x, y, z);
    }
    /**
     * 将当前矩阵沿xyz方向旋转angle度
     * @param angle
     * @param x
     * @param y
     * @param z
     */
    public static void rotate(float angle,float x,float y,float z)
    {
    	Matrix.rotateM(currMatrix,0,angle,x,y,z);
    }
    /**
     * 设置摄像机的位置，目标点，up向量，即用这些数据构建一个摄像机矩阵
     * @param cx
     * @param cy
     * @param cz
     * @param tx
     * @param ty
     * @param tz
     * @param upx
     * @param upy
     * @param upz
     */
    public static void setCamera
    (
    		float cx,	//摄像机位置x
    		float cy,   //摄像机位置y
    		float cz,   //摄像机位置z
    		float tx,   //摄像机目标点x
    		float ty,   //摄像机目标点y
    		float tz,   //摄像机目标点z
    		float upx,  //摄像机UP向量X分量
    		float upy,  //摄像机UP向量Y分量
    		float upz   //摄像机UP向量Z分量		
    )
    {
        	Matrix.setLookAtM
            (
            		mCameraMatrix, 
            		0, 
            		cx,
            		cy,
            		cz,
            		tx,
            		ty,
            		tz,
            		upx,
            		upy,
            		upz
            );
        	/**
        	 * 将摄像机位置数据放入缓冲
        	 */
        	ByteBuffer llbb= ByteBuffer.allocateDirect(3*4);
            float[] cameraLocation={cx,cy,cz};//摄像机位置
        	llbb.clear();
            llbb.order(ByteOrder.nativeOrder());//设置字节顺序
            cameraBuffer=llbb.asFloatBuffer();
            cameraBuffer.put(cameraLocation);
            cameraBuffer.position(0);  
    }
    /**
     * 设置透视投影参数，即用这些数据构建一个透视投影矩阵
     * 使用此函数设置透视投影矩阵时，应保证left与bottom的比例与窗口的比例一致。
     * 1.计算窗口的宽高比  float ratio = (float) width / height;
     * 2. - left = right ， - bottom = top
     * 3. 使得right/top = ratio。
     * e.g. ：
     * float ratio = (float) width / height;
     * MatrixState.setProjectFrustum(-x*ratio, x*ratio, -x, x, zNear, zFar);
     * x:坐标缩放因子
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param near
     * @param far
     */
    public static void setProjectFrustum
    ( 
    	float left,		//near面的left
    	float right,    //near面的right
    	float bottom,   //near面的bottom
    	float top,      //near面的top
    	float near,		//near面距离
    	float far       //far面距离
    )
    {
    	Matrix.frustumM(mProjMatrix, 0, left, right, bottom, top, near, far);
    }
    
    /**
     * 设置正交投影参数，即用这些数据构建一个正交投影矩阵
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param near
     * @param far
     */
    public static void setProjectOrtho
    (
    	float left,		//near面的left
    	float right,    //near面的right
    	float bottom,   //near面的bottom
    	float top,      //near面的top
    	float near,		//near面距离
    	float far       //far面距离
    )
    {    	
    	Matrix.orthoM(mProjMatrix, 0, left, right, bottom, top, near, far);
    }
    
    /**
     * 当前矩阵x摄像机矩阵x投影矩阵(正交投影矩阵或透视投影矩阵) = 总变换矩阵
     * @return 物体的总变换矩阵
     */
    public static float[] getFinalMatrix()
    {	
    	float[] mMVPMatrix=new float[16];
    	Matrix.multiplyMM(mMVPMatrix, 0, mCameraMatrix, 0, currMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);        
        return mMVPMatrix;
    }
    
    /**
     * @return 物体的当前的模型视图矩阵，即变换矩阵
     */
    public static float[] getMMatrix()
    {       
        return currMatrix;
    }

    /**
     * 设置定位光灯光位置，并将其坐标放入lightPositionBuffer缓冲，等待传入着色器
     * @param x
     * @param y
     * @param z
     */
    public static void setLightLocation(float x,float y,float z)
    {
    	float[] lightLocation={x,y,z};//定位光光源位置
    	ByteBuffer llbbL = ByteBuffer.allocateDirect(3*4);
    	llbbL.clear();
        llbbL.order(ByteOrder.nativeOrder());//设置字节顺序
        lightPositionBuffer=llbbL.asFloatBuffer();
        lightPositionBuffer.put(lightLocation);
        lightPositionBuffer.position(0);
    }
    /**
     * 设置定向光灯光位置，并将其坐标放入lightDirectionBuffer缓冲，等待传入着色器
     * @param x
     * @param y
     * @param z
     */
    public static void setLightDirection(float x,float y,float z){
    	float lightDirection[] = {x,y,z};
    	ByteBuffer llbbL = ByteBuffer.allocateDirect(3*4);
    	llbbL.clear();
        llbbL.order(ByteOrder.nativeOrder());//设置字节顺序
        lightDirectionBuffer=llbbL.asFloatBuffer();
        lightDirectionBuffer.put(lightDirection);
        lightDirectionBuffer.position(0);
    }
    /**
     * @return 定位光灯光位置的数据缓冲区
     */
    public static FloatBuffer getLightPositionBuffer(){
    	return lightPositionBuffer;
    }
    /**
     * @return 定向光灯光位置的数据缓冲区
     */
    public static FloatBuffer getLightDirectionBuffer(){
    	return lightDirectionBuffer;
    }
    /**
     * @return 摄像机位置的数据缓冲区
     */
    public static FloatBuffer getCameraPosBuffer(){
    	return cameraBuffer;
    }
}
