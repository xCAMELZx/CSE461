import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
	
public class Server implements RemoteSum 
{
	public Server() {}

    	public int sum(int num1, int num2) 
	{
		return num1 + num2;
    	}
	
    	public static void main(String args[]) 
	{
		try 
		{
	    		Server obj = new Server();
	    		RemoteSum stub = (RemoteSum) UnicastRemoteObject.exportObject(obj, 0);

	    		Registry registry = LocateRegistry.getRegistry();
	    		registry.bind("SUMRMI", stub);

	    		System.err.println("Server ready");
		} 
		catch (Exception ex) 
		{
	 	   	System.err.println("Server exception: " + ex.toString());
	    		ex.printStackTrace();
		}
    	}
}

