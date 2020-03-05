package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Information extends Remote{
	public String getInfo() throws RemoteException;
}
