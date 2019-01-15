package com.f.solarsystem;


import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.AttributeSet;

import com.f.body.Earth;
import com.f.body.Moon;
import com.f.body.Sky;
import com.f.body.Sun;
import com.f.gl.MatrixState;

public class MySurfaceView extends GLSurfaceView{

	public MySurfaceView(Context context) {
		super(context);
		setEGLContextClientVersion(2); 
		setRenderer(render);				  
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setEGLContextClientVersion(2); 
        setRenderer(render);				  
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	
	private float leftRight;
	private float frontBack;
	

	public void setLeftRight(float leftRight,float frontBack) {
		this.leftRight += leftRight;
		this.frontBack += frontBack;
		MatrixState.setCamera(0+this.frontBack,0,10f+this.leftRight,0f+this.frontBack,0f,-1.0f+this.leftRight,0f,1.0f,0.0f);      
	}

	private GLSurfaceView.Renderer render = new Renderer() {
		
		private Sun sun = null;
		private Sky sky1 = null;
		private Sky sky2 = null;
		private Moon moon = null;
		private Earth earth = null;
		
		private int textureIdSun;
		private int textureIdMoon;
		private int textureIdEarthDay;
		private int textureIdEarthNight;
		
		private float yRotateAngle = 0;
		
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			sun = new Sun(MySurfaceView.this,5);
			sky1 = new Sky(MySurfaceView.this,50,100);
			sky2 = new Sky(MySurfaceView.this,50,100);
			moon = new Moon(MySurfaceView.this,1.5f);
			earth = new Earth(MySurfaceView.this,3);
			textureIdSun=initTexture(R.drawable.sun);
			textureIdMoon=initTexture(R.drawable.moon); 
			textureIdEarthDay=initTexture(R.drawable.earth); 
			textureIdEarthNight=initTexture(R.drawable.earthn); 
					
			GLES20.glClearColor(0.0f,0.0f,0.0f, 1.0f);  
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            MatrixState.setLightLocation(0, 0, -50.0f);
            MatrixState.setInitStack();  
		}
		
		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			GLES20.glViewport(0, 0, width, height); 
			float ratio= (float) width / height;
            MatrixState.setProjectFrustum(-ratio*2, ratio*2, -1*2, 1*2, 8f, 200);
            MatrixState.setCamera(0,0,10f,0f,0f,-1.0f,0f,1.0f,0.0f);       
             
            
            GLES20.glEnable(GLES20.GL_CULL_FACE);  
		}
		
		@Override
		public void onDrawFrame(GL10 gl) {
			GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);   
			
			MatrixState.pushMatrix();
			//sun sky
			MatrixState.translate(0, 0, -45);
			MatrixState.rotate(yRotateAngle, 0, 1, 0);
			sky1.drawSelf(2);
			sky2.drawSelf(4);
			sun.drawSelf(textureIdSun);
			MatrixState.rotate(yRotateAngle, 0, 1, 0);
			//earth
			MatrixState.translate(20, 0, 0);
			MatrixState.rotate(yRotateAngle, 0, 1, 0);
			earth.drawSelf(textureIdEarthDay,textureIdEarthNight);
			//moon
			MatrixState.translate(5, 0, 0);
			MatrixState.rotate(yRotateAngle/3.0f, 0, 1, 0);
			moon.drawSelf(textureIdMoon);
			
			MatrixState.popMatrix();
			
			yRotateAngle=(yRotateAngle+0.5f)%360;
		}
	};
	
	private int initTexture(int drawableId)
	{
		/**
		 * ��ȡϵͳ����������ID
		 */
		int[] textures = new int[1];
		/**
		 *  ����1������������id������
		 *  ����2������id������
		 *  ����3��ƫ����
		 */
		GLES20.glGenTextures(1,textures,0);    
		int textureId=textures[0]; 
		//������ID���Ժ�ʹ�������ö�������������ID
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
		
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_CLAMP_TO_EDGE);
        
		Bitmap bitmap = getDrawableBitmap(drawableId);
		/**
		 * ʵ�ʼ�������ͼƬ
		 * ����1���������ͣ���OpenGL ES�б���ΪGL20.GL_TEXTURE_2D 
		 * ����2������Ĳ�Σ�0��ʾ����ͼ��㣬�������Ϊֱ����ͼ 
		 * ����3������ͼ��
		 * ����4������߿�ߴ�
		 */
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        return textureId;
	}
	
	private Bitmap getDrawableBitmap(int drawableId){
		InputStream is = this.getResources().openRawResource(drawableId);
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(is);
			return bitmap;
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
