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
	private EditText solution;          
	private TextView operator;
	
	private CalculatorActivity mContext;
	Double operand1;
	Double operand2;
	String Oper1;
	String Oper2;
	String Operate;
	private String ipAddress = "192.168.0.12";
	private int port = 5000;

	
	   
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.main);
        input1 = (EditText) findViewById(R.id.input1);                  
         input2 = (EditText) findViewById(R.id.input2);                  
        solution = (EditText) findViewById(R.id.solution);                  
        operator = (TextView) findViewById(R.id.operator);                    
        /* We create anEvent in each button.*/                    
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
        		if ((input1.getText().length() == 0)                                                  
        				|| (input1.getText().toString() == " ")) {                                            
        			show = new AlertDialog.Builder(mContext).setTitle("Error")                                                          
        			.setMessage("Some inputs are empty")                                                          
        			.setPositiveButton("OK", null).show();  
        		}else if (operator.getText().equals("")) {                                            
        			show = new AlertDialog.Builder(mContext).setTitle("Error")                                                          
        			.setMessage("Operator is null").setPositiveButton("OK", null).show();
        		}else if (operator.getText().equals("+")) {
        			double result = new Double(input1.getText().toString())
        			+ new Double(input2.getText().toString());                                            
        			solution.setText(Double.toString(result));
        			operand1= new Double(input1.getText().toString());
        			operand2= new Double(input2.getText().toString());
        			operator.setText(operand1+"+"+operand2);
        			Oper1=operand1.toString()+ "+" + operand2.toString();
        			try{
            			String message=Oper1;
            			int server_port = 5000;
            			byte[] rec= new byte [100];
            			DatagramSocket s = new DatagramSocket();
            			InetAddress local = InetAddress.getByName("192.168.0.12");
            			int msg_length=message.length();
            			byte[] mess = message.getBytes();
            			DatagramPacket p = new DatagramPacket(mess, msg_length,local,server_port);
            			s.send(p);
            			//DatagramPacket p1 = new DatagramPacket(rec, rec.length);
            			//s.receive(p1);
            			//String t= new String(rec, 0, p1.getLength());
            			s.close();
            			//System.out.println("The Answer"+t);
            			//operator.setText(t);
            			//solution.setText(t);
            			
            			}
            			catch(Exception e){}	        

        		
        		} else if (operator.getText().equals("-")) {                                            
        			double result = new Double(input1.getText().toString())
        			- new Double(input2.getText().toString());                                            
        			solution.setText(Double.toString(result));
        			operand1= new Double(input1.getText().toString());
        			operand2= new Double(input2.getText().toString());
        			operator.setText(operand1+"-"+operand2);
        			Oper1=operand1.toString()+ "-" + operand2.toString();
        			try{
            			String message=Oper1;
            			int server_port = 5000;
            			byte[] rec= new byte [100];
            			DatagramSocket s = new DatagramSocket();
            			InetAddress local = InetAddress.getByName("192.168.0.12");
            			int msg_length=message.length();
            			byte[] mess = message.getBytes();
            			DatagramPacket p = new DatagramPacket(mess, msg_length,local,server_port);
            			s.send(p);
            			//DatagramPacket p1 = new DatagramPacket(rec, rec.length);
            			//s.receive(p1);
            			//String t= new String(rec, 0, p1.getLength());
            			s.close();
            			//System.out.println("The Answer"+t);
            			//operator.setText(t);
            			//solution.setText(t);
            			
            			}
            			catch(Exception e){}	        

        		} else if (operator.getText().equals("*")) {                                            
        			double result = new Double(input1.getText().toString())
        			* new Double(input2.getText().toString());                                            
        			solution.setText(Double.toString(result));
        			operand1= new Double(input1.getText().toString());
        			operand2= new Double(input2.getText().toString());
        			operator.setText(operand1+"*"+operand2);
        			Oper1=operand1.toString()+ "*" + operand2.toString();
        			/*try{
        			
        			
        			
        			byte[] message = new byte[1500];
        			byte[] sen1=new byte[100];
        			sen1=Oper1.getBytes();
        			//InetAddress local = InetAddress.getByName("192.168.1.2");
        			InetAddress IPAdress  = InetAddress.getByName("192.168.0.20");


        			DatagramPacket p = new DatagramPacket(sen1, sen1.length,IPAdress,port);
        			DatagramSocket s = new DatagramSocket(port);
                    s.send(p);
        			
        			s.close();
        			}
        			catch( Exception e )
        		      {
        				e.printStackTrace();
        		      }
        			try{
            			
            			String text;
            			
            			byte[] message = new byte[1500];
            			byte[] sen1=new byte[100];
            			message=Oper1.getBytes();
            			DatagramPacket p1 = new DatagramPacket(message, message.length);
            			DatagramSocket s = new DatagramSocket(4444);
            			s.receive(p1);
            			
            			text = new String(message, 0, p1.getLength());
            			operator.setText(text);
            			
            			s.close();
            			}
            			catch( Exception e )
            		      {
            				e.printStackTrace();
            		      }	*/
        			
        			try{
            			String message=Oper1;
            			int server_port = 5000;
            			byte[] rec= new byte [100];
            			DatagramSocket s = new DatagramSocket();
            			InetAddress local = InetAddress.getByName("192.168.0.12");
            			int msg_length=message.length();
            			byte[] mess = message.getBytes();
            			DatagramPacket p = new DatagramPacket(mess, msg_length,local,server_port);
            			s.send(p);
            			//DatagramPacket p1 = new DatagramPacket(rec, rec.length);
            			//s.receive(p1);
            			//String t= new String(rec, 0, p1.getLength());
            			s.close();
            			//System.out.println("The Answer"+t);
            			//operator.setText(t);
            			//solution.setText(t);
            			
            			}
            			catch(Exception e){}	        
            	}

        		 else if (operator.getText().equals("/")) {
        			double result = new Double(input1.getText().toString())
        			/ new Double(input2.getText().toString());                                              
        			solution.setText(Double.toString(result));
        			operand1= new Double(input1.getText().toString());
        			operand2= new Double(input2.getText().toString());
        			operator.setText(operand1 + "/" + operand2);
        			Oper1=operand1.toString()+ "/" + operand2.toString();
        			try{
            			String message=Oper1;
            			int server_port = 5000;
            			byte[] rec= new byte [100];
            			DatagramSocket s = new DatagramSocket();
            			InetAddress local = InetAddress.getByName("192.168.0.12");
            			int msg_length=message.length();
            			byte[] mess = message.getBytes();
            			DatagramPacket p = new DatagramPacket(mess, msg_length,local,server_port);
            			s.send(p);
            			//DatagramPacket p1 = new DatagramPacket(rec, rec.length);
            			//s.receive(p1);
            			//String t= new String(rec, 0, p1.getLength());
            			s.close();
            			//System.out.println("The Answer"+t);
            			//operator.setText(t);
            			//solution.setText(t);
            			
            			}
            			catch(Exception e){}	        

        		 }                            
        	}                    
        });               
        
    }
}