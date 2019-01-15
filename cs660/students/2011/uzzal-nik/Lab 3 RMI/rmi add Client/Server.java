

	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


	
public class Server implements add2 {
	
    public Server() {}

    public int addthis2(int i, int j) {
	return i+j;
    }
	
    public static void main(String args[]) {
	
	try {
	    Server obj = new Server();
	    add2 stub = (add2) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("add2", stub);

	    System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
