package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import implement.Client;
import implement.Panier;

public interface IClient extends Remote{

	public IClient connexionClient(String mail, String mdp) throws RemoteException, SQLException;
	
	public IPanier recuperePanier() throws RemoteException, SQLException;
	
	public int getId() throws RemoteException;
	
	public void setId(int idClient) throws RemoteException;
	
}