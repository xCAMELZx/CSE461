package com.f.gl;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.Matrix;

public final class MatrixState {

	private static float[] currMatrix = null ;//��ǰ�任����
	private static float[] mProjMatrix = new float[16];//4x4���� ͶӰ��
    private static float[] mCameraMatrix = new float[16];//�����λ�ó���9��������   

    
    private static FloatBuffer lightPositionBuffer = null;//��λ��λ�����ݻ�����
    private static FloatBuffer lightDirectionBuffer = null;//�����λ�����ݻ�����
    private static FloatBuffer cameraBuffer = null;//�����λ�����ݻ�����
    
    
    private static float[][] mStack=new float[10][16];
    private static int stackTop=-1;
    /**
     * ��ʼ����ǰ����
     */
    public static void setInitStack()
    {
    	currMatrix=new float[16];
    	Matrix.setRotateM(currMatrix, 0, 0, 1, 0, 0);
    }
    /**
     * ����ǰ����ѹջ
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
     * ����ǰ�����ջ
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
     * ����ǰ������xyz�����ƶ�
     * @param x 
     * @param y
     * @param z
     */
    public static void translate(float x,float y,float z)
    {
    	Matrix.translateM(currMatrix, 0, x, y, z);
    }
    /**
     * ����ǰ������xyz������תangle��
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
     * �����������λ�ã�Ŀ��㣬up������������Щ���ݹ���һ�����������
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
    		float cx,	//�����λ��x
    		float cy,   //�����λ��y
    		float cz,   //�����λ��z
    		float tx,   //�����Ŀ���x
    		float ty,   //�����Ŀ���y
    		float tz,   //�����Ŀ���z
    		float upx,  //�����UP����X����
    		float upy,  //�����UP����Y����
    		float upz   //�����UP����Z����		
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
        	 * �������λ�����ݷ��뻺��
        	 */
        	ByteBuffer llbb= ByteBuffer.allocateDirect(3*4);
            float[] cameraLocation={cx,cy,cz};//�����λ��
        	llbb.clear();
            llbb.order(ByteOrder.nativeOrder());//�����ֽ�˳��
            cameraBuffer=llbb.asFloatBuffer();
            cameraBuffer.put(cameraLocation);
            cameraBuffer.position(0);  
    }
    /**
     * ����͸��ͶӰ������������Щ���ݹ���һ��͸��ͶӰ����
     * ʹ�ô˺�������͸��ͶӰ����ʱ��Ӧ��֤left��bottom�ı����봰�ڵı���һ�¡�
     * 1.���㴰�ڵĿ�߱�  float ratio = (float) width / height;
     * 2. - left = right �� - bottom = top
     * 3. ʹ��right/top = ratio��
     * e.g. ��
     * float ratio = (float) width / height;
     * MatrixState.setProjectFrustum(-x*ratio, x*ratio, -x, x, zNear, zFar);
     * x:������������
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param near
     * @param far
     */
    public static void setProjectFrustum
    ( 
    	float left,		//near���left
    	float right,    //near���right
    	float bottom,   //near���bottom
    	float top,      //near���top
    	float near,		//near�����
    	float far       //far�����
    )
    {
    	Matrix.frustumM(mProjMatrix, 0, left, right, bottom, top, near, far);
    }
    
    /**
     * ��������ͶӰ������������Щ���ݹ���һ������ͶӰ����
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param near
     * @param far
     */
    public static void setProjectOrtho
    (
    	float left,		//near���left
    	float right,    //near���right
    	float bottom,   //near���bottom
    	float top,      //near���top
    	float near,		//near�����
    	float far       //far�����
    )
    {    	
    	Matrix.orthoM(mProjMatrix, 0, left, right, bottom, top, near, far);
    }
    
    /**
     * ��ǰ����x���������xͶӰ����(����ͶӰ�����͸��ͶӰ����) = �ܱ任����
     * @return ������ܱ任����
     */
    public static float[] getFinalMatrix()
    {	
    	float[] mMVPMatrix=new float[16];
    	Matrix.multiplyMM(mMVPMatrix, 0, mCameraMatrix, 0, currMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);        
        return mMVPMatrix;
    }
    
    /**
     * @return ����ĵ�ǰ��ģ����ͼ���󣬼��任����
     */
    public static float[] getMMatrix()
    {       
        return currMatrix;
    }

    /**
     * ���ö�λ��ƹ�λ�ã��������������lightPositionBuffer���壬�ȴ�������ɫ��
     * @param x
     * @param y
     * @param z
     */
    public static void setLightLocation(float x,float y,float z)
    {
    	float[] lightLocation={x,y,z};//��λ���Դλ��
    	ByteBuffer llbbL = ByteBuffer.allocateDirect(3*4);
    	llbbL.clear();
        llbbL.order(ByteOrder.nativeOrder());//�����ֽ�˳��
        lightPositionBuffer=llbbL.asFloatBuffer();
        lightPositionBuffer.put(lightLocation);
        lightPositionBuffer.position(0);
    }
    /**
     * ���ö����ƹ�λ�ã��������������lightDirectionBuffer���壬�ȴ�������ɫ��
     * @param x
     * @param y
     * @param z
     */
    public static void setLightDirection(float x,float y,float z){
    	float lightDirection[] = {x,y,z};
    	ByteBuffer llbbL = ByteBuffer.allocateDirect(3*4);
    	llbbL.clear();
        llbbL.order(ByteOrder.nativeOrder());//�����ֽ�˳��
        lightDirectionBuffer=llbbL.asFloatBuffer();
        lightDirectionBuffer.put(lightDirection);
        lightDirectionBuffer.position(0);
    }
    /**
     * @return ��λ��ƹ�λ�õ����ݻ�����
     */
    public static FloatBuffer getLightPositionBuffer(){
    	return lightPositionBuffer;
    }
    /**
     * @return �����ƹ�λ�õ����ݻ�����
     */
    public static FloatBuffer getLightDirectionBuffer(){
    	return lightDirectionBuffer;
    }
    /**
     * @return �����λ�õ����ݻ�����
     */
    public static FloatBuffer getCameraPosBuffer(){
    	return cameraBuffer;
    }
}
