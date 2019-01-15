package com.my.solar1;



import android.app.Activity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class solarActivity extends Activity {
	private GLSurfaceView view;
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
 		//GLSurfaceView view = new GLSurfaceView(this);
 		view = new GLSurfaceView(this);
   		view.setRenderer(new OpenGLRenderer());
   		//setContentView(R.layout.main);
   		setContentView(view);
   		
    }
    
    @Override
    public boolean onTouchEvent(final MotionEvent evt) {
       float currentX = evt.getX();
       float currentY = evt.getY();

       if (currentX<=200 && currentY>=770)
       {
    	   view.onPause();
       }

       else
    	   view.onResume();
  
       return true;  // Event handled
    }
 
    
 
}