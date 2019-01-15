import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteRandom extends Remote {
	ArrayList rand1(int n) throws RemoteException;
    
    ArrayList rand2(int n, int lo, int hi) throws RemoteException;
    
}

