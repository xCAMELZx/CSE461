import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteRandom extends Remote 
{
	ArrayList rand1(int number_of_random_values) throws RemoteException;
      	ArrayList rand2(int number_of_random_values, int lower_bound, int upper_bound) throws RemoteException;
}

