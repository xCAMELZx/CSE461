
//package l3.randgen;
	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
	
public class Server implements randgen {
	
    public Server() {}

    public int [] myrandgen(int  rnum [], int j) {
	Random generator = new Random();
	for (int i=0; i<=10; i++)
		rnum[i]= generator.nextInt()/10000000;
		return rnum;
    }
	
	public int [] myrandgenwithbnd(int rnum[], int j, int lbnd, int ubnd){
	int i=0;
	int k=0;
	int[] nrnum= new int [20];
	while (i<=10)
	{	
		if ((rnum[i]>=lbnd)&&(rnum[i]<=ubnd))
		{
		nrnum[k]=rnum[i];
		k++;
		}
		i++;
	}

	
	return nrnum;
	}
	
    public static void main(String args[]) {
	
	try {
	    Server obj = new Server();
	    randgen stub = (randgen) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("randgen", stub);

	    System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
