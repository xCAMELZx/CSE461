package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Client {

    private Client() {}

    public static void main(String[] args) {

		String host = (args.length < 1) ? null : args[0];
		String N, Min, Max;
		N = "10";
		Min = "20";
		Max = "100";
	
		BufferedReader object = new BufferedReader(
			new InputStreamReader(System.in));
	
		try{
  			System.out.println("Enter first number to add");
  			Min = object.readLine();
  			System.out.println(" "+ Min);
		} catch (IOException e){
  			System.out.println("Out of range! " );
			System.exit(1);
		}
		try{
  			System.out.println("Enter second number to add");
  			Max = object.readLine();
  			System.out.println(" + "+ Max);
		} catch (IOException e){
  			System.out.println("Out of range! " );
			System.exit(1);
		}
		try {
	    		Registry registry = LocateRegistry.getRegistry(host);
	    		Hello stub = (Hello) registry.lookup("Hello");
	    		String response = stub.sayHello(N,Min,Max);
	    		System.out.println("response: " + response);
		} catch (Exception e) {
	    		System.err.println("Client exception: " + e.toString());
	    		e.printStackTrace();
		}
    }
}

 
