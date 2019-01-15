//UDPServer.java

import java.net.*;
import java.io.*;


class server 
{
   public static void main(String args[]) throws Exception
      {
      	
      	 byte[] receive_data = new byte[1024];
         byte[] send_data = new byte[1024];
         
         int recv_port;
         
         DatagramSocket server_socket = new DatagramSocket(5000);
         
         System.out.println ("UDPServer Waiting for client on port 5000");
         
         
         while(true)
         {
          
          DatagramPacket receive_packet = new DatagramPacket(receive_data,
                                              receive_data.length);
                                              
                  server_socket.receive(receive_packet);
                  
                  String data = new String(receive_packet.getData(),0,0,receive_packet.getLength());
                  
                  InetAddress IPAddress = receive_packet.getAddress();
                  
                  recv_port = receive_packet.getPort();
                  
                  if (data.equals("q") || data.equals("Q"))
                  break;
                  
                  else
                  
                  System.out.println("( " + IPAddress + " , " + recv_port 
                  + " ) said :" + data );
                  
                  String input1="";
                  String input2="";
                  char operator='+';
                  //private void handleString(String data){
                	  if (data.length() > 0 ){
                	  	for (int i=0;i<data.length()-1;i++){
                	  		if (data.charAt(i)=='+' || data.charAt(i)=='-' || data.charAt(i)=='*' || data.charAt(i)=='/'){
                	  			input1=(data.substring(0,i));
                	  			input2=(data.substring(i+1,data.length()));
                	  			operator=(data.charAt(i));			
                	  		}
                	  	}
                	  }else{
                	  	System.out.println("empty data");
                	  }
                	//  }
                	  double num1 = Double.parseDouble(input1);
                	  double num2 = Double.parseDouble(input2);
                	  double num=0;
                	  
          				if (operator=='+') {
          					num = num1+num2;
          				}
          				else if (operator=='-') {
          					num = num1-num2;
        				}
          				else if (operator=='*') {
          					num = num1*num2;
          				}
          				else{
          					num = num1/num2;
          				}
                	  
                	  System.out.println("( " + input1 + Character.toString(operator)+ input2 
                              + " ) is : " + num);
                              

                	      try {
                	        String host1 = "192.168.0.12";
                	        int port1 = 5000;
                	        byte[] message1 = Double.toString(num).getBytes();

                	        // Get the internet address of the specified host
                	        InetAddress address1 = InetAddress.getByName(host1);

                	        // Initialize a datagram packet with data and address
                	        DatagramPacket packet1 = new DatagramPacket(message1, message1.length,
                	            address1, port1);

                	        // Create a datagram socket, send the packet through it, close it.
                	        DatagramSocket dsocket1 = new DatagramSocket();
                	        dsocket1.send(packet1);
                	        dsocket1.close();
                	      } catch (Exception e) {
                	        System.err.println(e);
                	      }
                  
         }
      }
}