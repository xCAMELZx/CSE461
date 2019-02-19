import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.lang.*;
import java.io.*;
import java.util.Random;
import java.util.Arrays;

public class RandomNumberServer {
	public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(4455);
			System.out.println("Server Started...");
				while(true){
					new Thread(new ClientConnection(serverSocket.accept())).start();
				}	
		}catch(IOException e){e.printStackTrace();}
	}
}
class ClientConnection implements Runnable{
	private Socket socket;
	public ClientConnection(Socket socket){
			this.socket = socket;
	}
 @Override
 public void run(){
	try{
		DataInputStream dIn = new DataInputStream(socket.getInputStream());
		DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
		String message = dIn.readUTF();
		System.out.println("Client Request : " + message);
		String[] input = message.split(" ");
		String result = generateRandom(Integer.parseInt(input[0]),Integer.parseInt(input[1]), Integer.parseInt(input[2]));
		System.out.println("Server Response : " + result);
		dOut.writeUTF(result);
		dOut.flush();
		dOut.close();
		socket.close();
	}catch(IOException e){e.printStackTrace();}
 }
 public static String generateRandom(int num, int min, int max ) {
		int range = (max - min) + 1;
		String response ="";
			for(int i=0;i<num;i++) {
				response += Integer.toString((int)(Math.random() * range) + min) + " ";
			}
	return response;
 }
}
