package example.hello;	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
public class Server implements Hello {
    public Server() {}
    public String sayHello(String n, String max, String min) {
	return getRandomNumber(n,max, min);
    }
    public String getRandomNumber(String n, String max, String min) {
    	Random randomGenerator = new Random();
	int nn = Integer.parseInt(n);
	int nmax = Integer.parseInt(max);
	int nmin = Integer.parseInt(min);
	
	String result="";
        for (int i = 1; i <= nn; i++){
        	int randomInt = nmin + randomGenerator.nextInt(nmax-nmin);
        result=result +"\nrandom number "+ i + " is "+ randomInt +" "; 
    	}
	return result;
    }
    public static void main(String args[]) {	
	try {
	    Server obj = new Server();
	    Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("Hello", stub);
	    System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
