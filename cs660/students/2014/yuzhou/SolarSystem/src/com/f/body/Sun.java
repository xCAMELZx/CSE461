package com.f.body;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import android.opengl.GLES20;

import com.f.gl.MatrixState;
import com.f.gl.ShaderUtil;
import com.f.solarsystem.MySurfaceView;


public class Sun {

	private float R = 0;
	private int vCount = 0;

	private int mProgram;
	private int maTexCoorHandle;
	private int maPositionHandle;
	private int muMVPMatrixHandle;
	
	private FloatBuffer mVertexBuffer = null;
	private FloatBuffer mTexCoorBuffer = null;
	public Sun(MySurfaceView mv,float R)
	{
		this.R = R;
		initVertexData();
		initShader(mv);
	}

	private void initVertexData() {
		
		ArrayList<Float> alVertex = new ArrayList<Float>();
		final int angleSpan = 10;
		a:for(int vAngle = -90;vAngle<=90;vAngle+=angleSpan){
			for(int hAngle = 0;hAngle <=360 ;hAngle+=angleSpan){
		
				//1
				float x = (float) (R*Math.cos(Math.toRadians(vAngle))*Math.cos(Math.toRadians(hAngle)));
				float y = (float) (R*Math.cos(Math.toRadians(vAngle))*Math.sin(Math.toRadians(hAngle)));
				float z = (float) (R*Math.sin(Math.toRadians(vAngle)));
				alVertex.add(x);
				alVertex.add(y);
				alVertex.add(z);
				//2
				if(vAngle+angleSpan>90){
					break a;
				}
				float sx = (float) (R*Math.cos(Math.toRadians(vAngle+angleSpan))*Math.cos(Math.toRadians(hAngle)));
				float sy = (float) (R*Math.cos(Math.toRadians(vAngle+angleSpan))*Math.sin(Math.toRadians(hAngle)));
				float sz = (float) (R*Math.sin(Math.toRadians(vAngle+angleSpan)));
				alVertex.add(sx);
				alVertex.add(sy);
				alVertex.add(sz);
			}
		}
		vCount = alVertex.size()/3;
		float vertices[] = new float[vCount * 3];
		for (int i = 0; i < alVertex.size(); i++) {
			vertices[i] = alVertex.get(i);
		}
		//��������
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer = vbb.asFloatBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);
		//������������
	    float[] texCoor=generateTexCoor(angleSpan);
        ByteBuffer tbb = ByteBuffer.allocateDirect(texCoor.length*4);
        tbb.order(ByteOrder.nativeOrder());
        mTexCoorBuffer=tbb.asFloatBuffer();
        mTexCoorBuffer.put(texCoor);
        mTexCoorBuffer.position(0);  
	}
	private float[] generateTexCoor(int angleSpan) {
		final int bh =180/angleSpan;
		final int bw =360/angleSpan;
		final float sizew=1.0f/bw;//����
		final float sizeh=1.0f/bh;//����
		final List<Float> list=new ArrayList<Float>(); 
    	a:for(int i=0;i<=bh;i++)
    	{
    		for(int j=0;j<=bw;j++)
    		{
    			//1
    			float s=j*sizew;
    			float t=i*sizeh;
    			list.add(s);
    			list.add(t);
    			//2
    			if(i+1>bh){
    				break a;
    			}
    			float ct=(i+1)*sizeh;
    			float cs=(j)*sizew;
    			list.add(cs);
    			list.add(ct);
    		}
    	}
    	float result[] = new float[list.size()];
    	for(int i=0;i<list.size();i++){
    		result[i] = list.get(i);
    	}
    	return result;
	}

	private void initShader(MySurfaceView mv) {
		String vertexshader = ShaderUtil.loadFromAssetsFile("vertex_sun.sh",mv.getResources());
		String fragmentshader = ShaderUtil.loadFromAssetsFile("frag_sun.sh",mv.getResources());
		mProgram = ShaderUtil.createProgram(vertexshader, fragmentshader);
		
		maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
		muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
		maTexCoorHandle=GLES20.glGetAttribLocation(mProgram, "aTexCoor");  
	}

	public void drawSelf(int texid){
		
		GLES20.glUseProgram(mProgram);
		GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false,MatrixState.getFinalMatrix(), 0);
		GLES20.glVertexAttribPointer(maPositionHandle, 3, GLES20.GL_FLOAT,false, 3 * 4, mVertexBuffer);
	    GLES20.glVertexAttribPointer(maTexCoorHandle,2,GLES20.GL_FLOAT,false,2*4,mTexCoorBuffer); 
		GLES20.glEnableVertexAttribArray(maPositionHandle);
		GLES20.glEnableVertexAttribArray(maTexCoorHandle);  
		 //������
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texid);
        
		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, vCount);
	}
}
