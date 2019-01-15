package com.packtpub.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CalculatorActivity extends Activity {
	private EditText input1;
	private EditText input2;
	
	private TextView operator; 
	private TextView result;
	
	private String ipAddress = "192.168.0.11";
	private int port = 4444;
	private String delimiter =",";
	
	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		input1 = (EditText) findViewById(R.id.input1);
		input2 = (EditText) findViewById(R.id.input2);
		operator = (TextView) findViewById(R.id.operator);
		result = (TextView) findViewById(R.id.result);
		Button plusButton = (Button) findViewById(R.id.plus);
		Button minusButton = (Button) findViewById(R.id.min);
		Button prodButton = (Button) findViewById(R.id.mul);
		Button divButton = (Button) findViewById(R.id.div);
		Button equalButton = (Button) findViewById(R.id.equal);
		Button oneButton = (Button) findViewById(R.id.one);
		Button twoButton = (Button) findViewById(R.id.two);
		Button threeButton = (Button) findViewById(R.id.three);
		Button fourButton = (Button) findViewById(R.id.four);
		Button fiveButton = (Button) findViewById(R.id.five);
		Button sixButton = (Button) findViewById(R.id.six);
		Button sevenButton = (Button) findViewById(R.id.seven);
		Button eightButton = (Button) findViewById(R.id.eight);
		Button nineButton = (Button) findViewById(R.id.nine);
		Button zeroButton = (Button) findViewById(R.id.zero);

		oneButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("1");
			}
		});
		twoButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("2");
			}
		});
		threeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("3");
			}
		});
		fourButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("4");
			}
		});
		fiveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("5");
			}
		});
		sixButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("6");
			}
		});
		sevenButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("7");
			}
		});
		eightButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("8");
			}
		});
		nineButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("9");
			}
		});
		zeroButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				input1.append("0");
			}
		});

		plusButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				operator.setText("+");
			}
		});
		minusButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				operator.setText("-");
			}
		});
		prodButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				operator.setText("*");
			}
		});
		divButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				operator.setText("/");
			}
		});

		equalButton.setOnClickListener(new OnClickListener() {
			private AlertDialog show;

			public void onClick(View arg0) {
					
				
				String message = 
		 			input1.getText().toString() 
	 				+ delimiter + operator.getText().toString() 
	 				+ delimiter + 
	 				input2.getText().toString();
				
				 try{
					 
						byte[] rec= new byte [100];
						DatagramSocket s = new DatagramSocket();
						InetAddress local = InetAddress.getByName(ipAddress);
						int msg_length=message.length();
						byte[] mess = message.getBytes();
						DatagramPacket p = new DatagramPacket(mess, msg_length,local,port);
						s.send(p);
						DatagramPacket p1 = new DatagramPacket(rec, rec.length);
						s.receive(p1);
						String t= new String(rec, 0, p1.getLength());
						s.close();
	
					    result.setText(t);
					
					
					//solution.setText(t);
					
					
				
				}
			catch(Exception e){	}
				 

			
			}});

	}
}