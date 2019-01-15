import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSum extends Remote {
    int sum(int num1, int num2) throws RemoteException;
}

