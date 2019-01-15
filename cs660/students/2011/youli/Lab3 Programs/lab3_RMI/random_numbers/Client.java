import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.lang.Integer;

public class Client 
{
	private static void printList(String message, ArrayList list)
	{
		System.out.println(message);

		for (int i=0; i<list.size(); i++) 
		{
			System.out.println(list.get(i));
		}
	}
	
    	public static void main(String args[]) 
	{
		String hostname = null;
		int number_of_random_values = 0;
		int lowerbound =0;
		int upperbound =0;

		if(args.length == 4)
		{
          		hostname = args[0];
          		number_of_random_values = Integer.valueOf(args[1]).intValue();
          		lowerbound = Integer.valueOf(args[2]).intValue();
          		upperbound = Integer.valueOf(args[3]).intValue();
		}
		else if(args.length == 2)
		{
		 	hostname = args[0];
         		number_of_random_values = Integer.valueOf(args[1]).intValue();
		}
		else 
		{
			System.out.println("Invalid input: Please enter either the number or random numbers (and optional) upper and lower bounds " );
			System.out.println(args.length);
			for (int i = 0; i < args.length; i++)
			{
				System.out.println(args[i]);
			}
			System.out.println("Invalid input: Please enter either the number or random numbers (and optional) upper and lower bounds " );
			System.exit(0);
		}
		
		try 
		{
			if(number_of_random_values == 0)
			{
				System.out.println("Error: You have enter zero random values" );
				System.exit(0);
			}	
			ArrayList list;
	
			Registry registry = LocateRegistry.getRegistry(hostname);
	    		RemoteRandom stub = (RemoteRandom) registry.lookup("RANDRMI");
	    
			if(lowerbound > 0 && upperbound >0)
			{
				list = stub.rand2(number_of_random_values, lowerbound, upperbound);
				printList("The " + number_of_random_values + " random numbers between " 
						 + lowerbound + " and " + upperbound + " are:", list);
			}
			else
			{
				list = stub.rand1(number_of_random_values);
				printList("The " + number_of_random_values + " random numbers are:", list);
			}
		} 
		catch (Exception ex) 
		{
	    		System.err.println("Client exception: " + ex.toString());
	    		ex.printStackTrace();
		}
    	}
}

