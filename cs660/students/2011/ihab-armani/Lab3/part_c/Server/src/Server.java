import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Server
{
	public static void main(String args[]) throws Exception
	{
		System.out.println("Server Ready");
		while(true)
			{
				try
				{
				double result2=0.0;
				String text;
				int server_port = 4444;
				byte[] message = new byte[1500];
				byte[] sen1=new byte[100];
				DatagramPacket p = new DatagramPacket(message, message.length);
				DatagramSocket s = new DatagramSocket(server_port);
				s.receive(p);
				text = new String(message, 0, p.getLength());
				String[] temp;
				String delimiter = ",";
				temp = text.split(delimiter); 

				System.out.println("Receiving message: " + text);
				
				if(temp[1].equals("+"))
				{
					result2 = new Double(temp[0]) + new Double(temp[2]);
				}
           
				if(temp[1].equals("-"))
				{
					System.out.println("Operation: " + temp[0] + " - " + temp[2] );
					result2 = new Double(temp[0])- new Double(temp[2]);
				}
           
				if(temp[1].equals("/"))
				{
					System.out.println("Operation: " + temp[0] + " / " + temp[2] );
					result2 = new Double(temp[0]) / new Double(temp[2]);
				}
           
				if(temp[1].equals("*"))
				{
					System.out.println("Operation: " + temp[0] + " * " + temp[2] );
					result2 = new Double(temp[0]) * new Double(temp[2]);
				}

				String sen=  Double.toString(result2);
				InetAddress IPAddress=p.getAddress();
				int port =p.getPort();
				sen1=sen.getBytes();
				DatagramPacket p1 = new DatagramPacket(sen1, sen1.length, IPAddress, port);
				System.out.println("Sending message: " + result2);
				s.send(p1);
				s.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
			}
	}
}
		