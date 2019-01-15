
//package l3.randgen;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface randgen extends Remote {
   int [] myrandgen(int rnum[], int j) throws RemoteException;
   int [] myrandgenwithbnd(int rnum[], int j, int lbnd, int ubnd) throws RemoteException;
}
