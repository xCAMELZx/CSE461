import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
	
public class Server implements RemoteRandom 
{

    	public Server() {}
    
    	public ArrayList rand1(int number_of_random_values) throws RemoteException
	{
    		ArrayList server_response = new ArrayList();
    	
    		Random rand = new Random();
    	
    		while(server_response.size() < number_of_random_values)
		{
	    		server_response.add((Integer.valueOf(rand.nextInt())));	
	    	}
	    	return server_response;
    	}
    
    	public ArrayList rand2(int number_of_random_values, int lowerbound, int upperbound) throws RemoteException
	{
	   	ArrayList server_response = new ArrayList();
    	
    		if(lowerbound >= upperbound)
		{
	    		return server_response;
    		}
    	
    		Random rand = new Random();
    	
    		while(server_response.size() < number_of_random_values)
		{
    			int random_number =rand.nextInt(upperbound);
    			
			if(random_number > lowerbound)
			{
	    			server_response.add((Integer.valueOf(random_number)));
    			}
	    	}
	    	return server_response;
	}
    	

	public static void main(String args[]) 
	{
		try 
		{
	  		Server object = new Server();
	  		RemoteRandom stub = (RemoteRandom) UnicastRemoteObject.exportObject(object, 0);

	    		Registry registry = LocateRegistry.getRegistry();
	    		registry.bind("RANDRMI", stub);

	    		System.err.println("Server ready");
		} 
		catch (Exception ex) 
		{
			System.err.println("Server exception: " + ex.toString());
		    	ex.printStackTrace();
		}
    	}
}

