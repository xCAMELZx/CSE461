import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
	
public class Server implements RemoteSum {

    public Server() {}


    public int sum(int a, int b) {
	return a + b;
    }
	
    public static void main(String args[]) {
	
	try {
	    Server obj = new Server();
	    RemoteSum stub = (RemoteSum) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("SUMRMI", stub);

	    System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}

