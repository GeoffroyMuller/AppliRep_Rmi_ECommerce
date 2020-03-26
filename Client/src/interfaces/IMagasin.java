package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface IMagasin extends Remote{
	
public String getInfo() throws RemoteException;
	
	public ArrayList<IProduit> getListeProduit() throws RemoteException;
	
}
