import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.lang.Integer;

public class Client {
    
       public static void main(String[] args) {

	String host=null;
	int a=0,b=0;

	if(args.length > 2){
          host=args[0];
          a=Integer.valueOf(args[1]).intValue();
          b=Integer.valueOf(args[2]).intValue();
	}


	try {
	    Registry registry = LocateRegistry.getRegistry(host);
	    RemoteSum stub = (RemoteSum) registry.lookup("SUMRMI");
	    int response = stub.sum(a, b);
	    System.out.println(a + " + "  + b + " = " + response);
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}

