package com.f.solarsystem;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.f.solarsystem.Rocker.OnRockerMoveListener;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_main);
		
		final MySurfaceView mysurfaceview = (MySurfaceView)findViewById(R.id.mySurfaceView);
		Rocker rocker = (Rocker)findViewById(R.id.rocker);
		rocker.setRockerSize(250);
		rocker.setLocation(150, 350);
		rocker.setOnRockerMoveListener(new OnRockerMoveListener() {
			
			@Override
			public void rockerMove(float xOffset, float yOffset) {
				System.out.println("xOffset="+xOffset/100.0f+",yOffset="+yOffset/100.0f);
				mysurfaceview.setLeftRight(xOffset/100.0f, yOffset/100.0f);
			}
			
			@Override
			public void rockerMove(View v) {
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
