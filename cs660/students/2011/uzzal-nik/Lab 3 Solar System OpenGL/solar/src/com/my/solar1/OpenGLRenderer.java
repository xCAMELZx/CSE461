package com.my.solar1;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;

public class OpenGLRenderer implements Renderer {
private Sphere sp;
private butsqr bs;
private float angle=0f, angle1=0f, angle2=0f, angle3=0f,
angle4=0f, angle5=0f, angle6=0f, angle7=0f, angle8=0f, angle9=0f;



	public OpenGLRenderer() {
		sp = new Sphere();
		bs = new butsqr();
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//float lightAmbient[]={0.2f, 0f, 0f,1f};
		float lightAmbient[]={0.2f, 0.2f, 0.2f, 0.5f};
		float lightDiffuse[]={1.0f, 1.0f, 0f, 1.0f};
		float mat_specular[]={1.0f,0f,0f, 1.0f};
		float mat_shininess[]={1f};//{5.0f};
		float matAmbient[]={255,255,0,1f};
		//float matAmbient[]={251,176,64,1f};
		float matDiffuse[]={0,0,0.8f,0};
		float lightPosition[]={-1, -0.2f, 0, 1};
		float lightPosition1[]={0, 1.2f, 0, 1};		 
		float lightPosition2[]={-1f, 1.0f, -0.20f, 1};	//-0.5
		gl.glShadeModel(GL10.GL_SMOOTH);
		//GLU.glColorMaterial( gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT_AND_DIFFUSE );
	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, mat_specular, 0);
		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SHININESS, mat_shininess, 0);
		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT, matAmbient, 0);
	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, matDiffuse, 0);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
		
		gl.glEnable(GL10.GL_LIGHTING);
	//	gl.glEnable(GL10.GL_LIGHT0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPosition, 0);
		
		
		//gl.glEnable(GL10.GL_LIGHT1);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, lightAmbient, 0);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, lightDiffuse, 0);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, lightPosition1, 0);
		
		gl.glEnable(GL10.GL_LIGHT2);
		gl.glLightfv(GL10.GL_LIGHT2, GL10.GL_AMBIENT, lightAmbient, 0);
		gl.glLightfv(GL10.GL_LIGHT2, GL10.GL_DIFFUSE, lightDiffuse, 0);
		gl.glLightfv(GL10.GL_LIGHT2, GL10.GL_POSITION, lightPosition2, 0);
		
		
		/*gl.glEnable(GL10.GL_LIGHT0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPosition, 0);*/
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		gl.glDisable(GL10.GL_DITHER);
		

	}
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);;
		
		/*
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -6);
		gl.glRotatef(angle, 1, 1, 0);
		sp.draw(gl);	
		
		*/
		
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, -25, -5, 0, 0, -5, 1, 1, 0);
		gl.glTranslatef(0, 0, -5);
		//gl.glTranslatef(0, 0, -15);
		gl.glScalef(1.4f, 1.4f, 1.4f);
		gl.glRotatef(45, 1, 1, 0);
	
		//sun
		gl.glPushMatrix();
		//gl.glRotatef(-angle, 0, 0, 1);
		gl.glColor4f(1f, 1f, 0.0f, 1.0f);
		sp.draw(gl);	
		gl.glPopMatrix();
		//gl.glDisable(GL10.GL_LIGHT0);
		//gl.glDisable(GL10.GL_LIGHTING);
		
		//1. Mercury
		
		gl.glPushMatrix();
		gl.glRotatef(-angle1, 0, 0, 1);
		gl.glTranslatef(2, 0, 0);
		gl.glScalef(.35f, .35f, .35f);
		gl.glColor4f(1f, 1f, 0.6f, 1.0f);
		sp.draw(gl);		
		gl.glPopMatrix();

		//2. Venus
		gl.glPushMatrix();
		gl.glRotatef(-angle2, 0, 0, 1);
		gl.glTranslatef(2.5f, 1.5f, 0);
		gl.glScalef(.35f, .35f, .35f);
		sp.draw(gl);
	//	gl.glColor4f(1f, 0.5f, 1.0f, 1.0f);
		gl.glPopMatrix();
		
		//3. Earth
		gl.glPushMatrix();
		gl.glRotatef(-angle3, 0, 0, 1);
		gl.glTranslatef(5, 0, 0);
		gl.glScalef(.35f, .35f, .35f);
		sp.draw(gl);

		
		// 3a. Moon
		gl.glPushMatrix();
		gl.glRotatef(-angle3, 0, 0, 1);
		gl.glTranslatef(2, 0, 0);
		gl.glScalef(.5f, .5f, .5f);
		gl.glRotatef(angle*10, 0, 0, 1);
		sp.draw(gl);

		gl.glPopMatrix();
	    gl.glPopMatrix();
	  // gl.glPopMatrix();
 
		//4. Mars
		gl.glPushMatrix();
		gl.glRotatef(-angle4, 0, 0, 1);
		gl.glTranslatef(6, 2, 0);
		gl.glScalef(.35f, .35f, .35f);
		sp.draw(gl);
		gl.glPopMatrix();
		
		//5. Jupitar
		gl.glPushMatrix();
		gl.glRotatef(-angle5, 0, 0, 1);
		gl.glTranslatef(8, 0, 0);
		gl.glScalef(.8f, .8f, .8f);
		sp.draw(gl);
		gl.glPopMatrix();
				    
		//6. Saturn
		gl.glPushMatrix();
		gl.glRotatef(-angle6, 0, 0, 1);
		gl.glTranslatef(9, 2, 0);
		gl.glScalef(.5f, .5f, .5f);
		sp.draw(gl);
		gl.glPopMatrix();
	
		//7.Uranus 
		gl.glPushMatrix();
		gl.glRotatef(-angle7, 0, 0, 1);
		gl.glTranslatef(10.5f, 1, 0);
		gl.glScalef(.4f, .4f, .4f);
		sp.draw(gl);
		gl.glPopMatrix();
			
		//8.Naptun 
		gl.glPushMatrix();
		gl.glRotatef(-angle8, 0, 0, 1);
		gl.glTranslatef(12, 1, 0);
		gl.glScalef(.37f, .37f, .37f);
		sp.draw(gl);
		gl.glPopMatrix();
	
		//9.Pluto
		gl.glPushMatrix();
		gl.glRotatef(-angle9, 0, 0, 1);
		gl.glTranslatef(13.5f, 1, 0);
		gl.glScalef(.27f,.27f, .27f);
		sp.draw(gl);
		gl.glPopMatrix();

		// Increse the angle.
		angle++;
		angle9=angle9+1.2f;
		angle8=angle8+1.4f;
		angle7=angle7+1.6f;
		angle6=angle6+1.8f;
		angle5=angle5+2f;
		angle4=angle4+2.2f;
		angle3=angle3+2.4f;
		angle2=angle2+2.6f;
		angle1=angle1+2.8f;
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
	
		
		//gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	
	

}
