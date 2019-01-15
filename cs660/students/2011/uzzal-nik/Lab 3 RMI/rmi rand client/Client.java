
//package l3.randgen;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

	String host = (args.length < 1) ? null : args[0];
	try {
	    Registry registry = LocateRegistry.getRegistry(host);
	    randgen stub = (randgen) registry.lookup("randgen");
		int[] randnum = new int [20];
		int[] response = new int [20];
		int n = 10;
		int lowbnd=0;
		int upperbnd=10000;
		
	    response = stub.myrandgen(randnum, n);
	    //stub.myrandgen(randnum, n);
	    //System.out.println("response: " + response);
		System.out.println("Random numbers are: ");
		for (int i=0; i<=10; i++)
		System.out.println(" " + response[i]);
		
		System.out.println("Random number between 0 to 10000:");
		
		response = stub.myrandgenwithbnd(response, n, lowbnd, upperbnd);
		for (int i=0; i<=10; i++)
		{
			if (response[i]>=1)
			System.out.println(" " + response[i]);
		}
		
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
