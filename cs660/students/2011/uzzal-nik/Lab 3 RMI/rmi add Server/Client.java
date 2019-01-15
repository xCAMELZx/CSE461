


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

	String host = (args.length < 1) ? null : args[0];
	try {
	    Registry registry = LocateRegistry.getRegistry(host);
	    add2 stub = (add2) registry.lookup("add2");
	    int response = stub.addthis2(2, 3);
	    System.out.println("Adding 2 and 3");
	    System.out.println("Result of the Addition: " + response);
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
