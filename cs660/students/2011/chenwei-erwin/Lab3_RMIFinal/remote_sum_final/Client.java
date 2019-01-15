import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.lang.Integer;

public class Client 
{
	public static void main(String[] args) 
	{

		String hostname = null;
		int num1 = 0, num2 = 0;
	
		if(args.length == 3)
		{
	        	hostname = args[0];
          		num1 = Integer.valueOf(args[1]).intValue();
          		num2 = Integer.valueOf(args[2]).intValue();
		}
		else 
		{
			System.out.println("Invalid input: Please enter two numbers to be added together...  " );
			System.exit(0);
		}
		


		try 
		{
	    		Registry registry = LocateRegistry.getRegistry(hostname);
	    		RemoteSum stub = (RemoteSum) registry.lookup("SUMRMI");
	    		int server_response = stub.sum(num1, num2);
	    		System.out.println(num1 + " + "  + num2 + " = " + server_response);
		} 
		catch (Exception ex) 
		{
	    		System.err.println("Client exception: " + ex.toString());
	    		ex.printStackTrace();
		}
    	}
}

