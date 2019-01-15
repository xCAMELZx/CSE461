import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
	
public class Server implements RemoteRandom {

    public Server() {}
    
    
    public ArrayList rand1(int n) throws RemoteException{
    	ArrayList response = new ArrayList();
    	
    	Random rand = new Random();
    	
    	while(response.size() < n){
    		response.add((Integer.valueOf(rand.nextInt())));	
    	}
    	return response;
    	
    }
    
    public ArrayList rand2(int n, int lo, int hi) throws RemoteException{
    	
    	ArrayList response = new ArrayList();
    	
    	if(lo >= hi){
    		return response;
    	}
    	
    	Random rand = new Random();
    	
    	while(response.size() < n){
    		int x =rand.nextInt(hi);
    		if(x > lo){
    			response.add((Integer.valueOf(x)));
    		}
    	}
    	return response;
    	
    }
    	
    public static void main(String args[]) {
	
	try {
	  
		
		
		Server obj = new Server();
	    RemoteRandom stub = (RemoteRandom) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("RANDRMI", stub);
		

	    System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}

