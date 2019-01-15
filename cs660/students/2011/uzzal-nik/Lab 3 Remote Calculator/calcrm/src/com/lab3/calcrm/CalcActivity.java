package com.lab3.calcrm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CalcActivity extends Activity {
	


    public String message;
    private EditText result;
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;	
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;	
	private Button btn9;
	private Button btnmul;
	private Button btndiv;
	private Button btnplu;
	private Button btnmin;	
	private Button btnequ;
	private Button btnres;
	public String int1;
	public String int2="oo";
	public String sign;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
 
        result= (EditText) findViewById(R.id.result);
        btn0=(Button) findViewById(R.id.btn0);
        btn1=(Button) findViewById(R.id.btn1);
        btn2=(Button) findViewById(R.id.btn2);
        btn3=(Button) findViewById(R.id.btn3);
        btn4=(Button) findViewById(R.id.btn4);
        btn5=(Button) findViewById(R.id.btn5);
        btn6=(Button) findViewById(R.id.btn6);
        btn7=(Button) findViewById(R.id.btn7);
        btn8=(Button) findViewById(R.id.btn8);
        btn9=(Button) findViewById(R.id.btn9);
        btnmul=(Button) findViewById(R.id.btnmul);
        btnmin=(Button) findViewById(R.id.btnmin);
        btnplu=(Button) findViewById(R.id.btnplu);
        btndiv=(Button) findViewById(R.id.btndiv);
        btnequ=(Button) findViewById(R.id.btnequ);
        btnres=(Button) findViewById(R.id.btnres);
        
        btnres.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		result.setText("");
        	}
        	});

        btn0.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="0";
        		result.setText(all);
        	}
        	});
        
        btn1.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="1";
        		result.setText(all);
        	}
        	});
        

        
        btn2.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="2";
        		result.setText(all);
        	}
        	});
        
 
        
        btn3.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="3";
        		result.setText(all);
        	}
        	});
        
        
        btn4.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="4";
        		result.setText(all);
        	}
        	});
        
        btn5.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="5";
        		result.setText(all);
        	}
        	});
        
        btn6.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="6";
        		result.setText(all);
        	}
        	});
        
        
        btn7.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="7";
        		result.setText(all);
        	}
        	});
        
        btn8.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="8";
        		result.setText(all);
        	}
        	});
        
        
        btn9.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		String all=result.getText().toString();
        		all+="9";
        		result.setText(all);
        	}
        	});
        
        btnmul.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		int1=result.getText().toString();
        		sign="mul";
        		result.setText("");
        	}
        	});
  
        btndiv.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		int1=result.getText().toString();
        		sign="div";
        		result.setText("");
        	}
        	});
        
        btnplu.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		int1=result.getText().toString();
        		sign="plus";
        		result.setText("");
        	}
        	});
        btnmin.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		int1=result.getText().toString();
        		sign="min";
        		result.setText("");
        	}
        	});
        
        btnequ.setOnClickListener(new OnClickListener() 
        {
        	public void onClick(View arg0) 
        		{
        		int2=result.getText().toString();
        		int2=int1+"-"+sign+"-"+int2+"-"+sign;
        	    try 
        	    {
        	    	Thread.sleep(500);
        	    } 
        	    catch (InterruptedException e) 
        	    { }
        		try{
        			String message=int2;
        			int server_port = 4444;
        			byte[] rec= new byte [100];
        			DatagramSocket s = new DatagramSocket();
        			InetAddress local = InetAddress.getByName("139.182.36.176");
        			int msg_length=message.length();
        			byte[] mess = message.getBytes();
        			DatagramPacket p = new DatagramPacket(mess, msg_length,local,server_port);
        			s.send(p);
        			DatagramPacket p1 = new DatagramPacket(rec, rec.length);
        			s.receive(p1);
        			String t= new String(rec, 0, p1.getLength());
        			s.close();
        			result.setText(t);
        			}
        			catch(Exception e){}	        
        	}
        });
    }	
}