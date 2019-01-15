package com.f.body;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

import com.f.gl.MatrixState;
import com.f.gl.ShaderUtil;
import com.f.solarsystem.MySurfaceView;

public class Sky {

	private float R = 0;
	private int vCount = 50;
	private int mProgram;
	private int maPositionHandle;
	private int uPointSizeHandle;
	private int muMVPMatrixHandle;

	private FloatBuffer mVertexBuffer = null;
	
	public Sky(MySurfaceView mv,float R,int count)
	{
		this.R=R;
		this.vCount = count;
		initVertexData();
		intShader(mv);
	}
	private void initVertexData() {
		float vertexs[]=new float[vCount*3];
		for(int i=0;i<vCount;i++){
			double vAngle = (Math.random()*180-90);
			double hAngle = (Math.random()*360);
			float x = (float) (R*Math.cos(Math.toRadians(vAngle))*Math.cos(Math.toRadians(hAngle)));
			float y = (float) (R*Math.cos(Math.toRadians(vAngle))*Math.sin(Math.toRadians(hAngle)));
			float z = (float) (R*Math.sin(Math.toRadians(vAngle)));
			vertexs[i*3] = x;
			vertexs[i*3+1] = y;
			vertexs[i*3+2] = z;
		}
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertexs.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer  = vbb.asFloatBuffer();
        mVertexBuffer.put(vertexs);
        mVertexBuffer.position(0);
	}
	private void intShader(MySurfaceView mv) {
		String vertexshader = ShaderUtil.loadFromAssetsFile("vertex_sky.sh", mv.getResources());
	    String fragmentshader = ShaderUtil.loadFromAssetsFile("frag_sky.sh", mv.getResources());  
        mProgram = ShaderUtil.createProgram(vertexshader, fragmentshader);
        maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");        
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix"); 
        uPointSizeHandle = GLES20.glGetUniformLocation(mProgram, "uPointSize"); 
	}
	public void drawSelf(int size){
//		MatrixState.rotate(1/5.0f, 0, 1, 0);
		GLES20.glUseProgram(mProgram); 
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, MatrixState.getFinalMatrix(), 0);  
        GLES20.glUniform1f(uPointSizeHandle, size);  //将顶点尺寸传入着色器程序
        GLES20.glVertexAttribPointer( maPositionHandle,3,GLES20.GL_FLOAT, false,3*4, mVertexBuffer);   
        //允许顶点位置数据数组
        GLES20.glEnableVertexAttribArray(maPositionHandle);         
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, vCount); //绘制星星点    

	}
}
