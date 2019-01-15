import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.lang.Integer;

public class Client {
    
	
	private static void printList(String msg, ArrayList list){
		System.out.println(msg);
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
    public static void main(String[] args) {

	String host=null;
	int n=0,lo=0,hi=0;

	if(args.length > 3){
          host=args[0];
          n=Integer.valueOf(args[1]).intValue();
          lo=Integer.valueOf(args[2]).intValue();
          hi=Integer.valueOf(args[3]).intValue();
	}
	else if(args.length > 1){
		 host=args[0];
         n=Integer.valueOf(args[1]).intValue();
	}


	try {
		
		if(n == 0){
			System.out.println("Invalid input" );
			System.exit(0);
		}
		
		
		ArrayList list;
	
		
		Registry registry = LocateRegistry.getRegistry(host);
	    RemoteRandom stub = (RemoteRandom) registry.lookup("RANDRMI");
	    
		if(lo > 0 && hi >0){
			list = stub.rand2(n, lo, hi);
			printList("The " + n + " random numbers between " + lo + " and " + hi + " are:", list);
			
		}
		else{
			list = stub.rand1(n);
			printList("The " + n + " random numbers are:", list);
			
			
		}
		
		
	    
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}

