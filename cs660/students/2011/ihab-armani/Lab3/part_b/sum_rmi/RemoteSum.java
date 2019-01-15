import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSum extends Remote {
    int sum(int a, int b) throws RemoteException;
}

