package com.f.solarsystem;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Rocker extends View {

	private Paint paint = null;
	private Bitmap rocker_in = null;
	private Bitmap rocker_out = null;
	private OnRockerMoveListener onRockerMoveListener = null;
	
	private float xCenter = 200; 
	private float yCenter = 200;
	
	private float xOffset = 0;
	private float yOffset = 0;
	
	private int rocker_in_Width;
	private int rocker_in_Height;
	private int rocker_out_Width;
	private int rocker_out_Height;
	
	
	public void setLocation(float xCenter,float yCenter){
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		invalidate();
	}
	public void setRockerSize(int rockerSize){
		rocker_in = resizeImage(rocker_in,rockerSize/3,rockerSize/3);
		rocker_out = resizeImage(rocker_out,rockerSize,rockerSize);
		rocker_in_Width = rocker_in.getWidth();
		rocker_in_Height = rocker_in.getHeight();
		rocker_out_Width = rocker_out.getWidth();
		rocker_out_Height = rocker_out.getHeight();
		invalidate();
	}
	public Rocker(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		rocker_in = getBitmap(getResources(),"rocker_in.png");
		rocker_out = getBitmap(getResources(),"rocker_out.png");
		
		paint = new Paint();
		paint.setAntiAlias(true);
		setRockerSize(250);
	}
	public interface OnRockerMoveListener{
		public void rockerMove(View v);
		public void rockerMove(float xOffset,float yOffset);
	}
	public void setOnRockerMoveListener(OnRockerMoveListener onRockerMoveListener){
		this.onRockerMoveListener = onRockerMoveListener;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap(rocker_in,xCenter-rocker_in_Width/2+xOffset,yCenter-rocker_in_Height/2+yOffset, paint);
		canvas.drawBitmap(rocker_out, xCenter-rocker_out_Width/2, yCenter-rocker_out_Height/2, paint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(onRockerMoveListener != null){
			float fx = event.getX();
			float fy = event.getY();
			float r = (fx-xCenter)*(fx-xCenter)+(fy-yCenter)*(fy-yCenter);
			if(r<=(rocker_out_Width/2)*(rocker_out_Width/2)){
				
				xOffset = fx - xCenter;
				yOffset = fy - yCenter;
				
				onRockerMoveListener.rockerMove(this);
				onRockerMoveListener.rockerMove(xOffset,yOffset);
				invalidate();
			}
		}
		if(event.getAction()==MotionEvent.ACTION_UP){
			xOffset=0;
			yOffset=0;
			invalidate();
		}
		return true;
	}
	
	private Bitmap getBitmap(Resources res, String fname) {
		Bitmap result = null;
		try {
			InputStream in = res.getAssets().open(fname);
			int ch = 0;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((ch = in.read()) != -1) {
				baos.write(ch);
			}
			byte[] buff = baos.toByteArray();
			baos.close();
			in.close();
			result = BitmapFactory.decodeByteArray(buff, 0, buff.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private Bitmap resizeImage(Bitmap bitmap, int w, int h) {
		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;
		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;
		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);
		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);
		return resizedBitmap;
	}

}
