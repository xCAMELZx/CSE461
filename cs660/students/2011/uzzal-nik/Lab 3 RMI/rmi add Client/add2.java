


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface add2 extends Remote {
    int addthis2(int i, int j) throws RemoteException;
}
