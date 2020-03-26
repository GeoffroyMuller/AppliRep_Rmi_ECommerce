package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import implement.Client;
import implement.Panier;

public interface IClient extends Remote{

	public Client connexionClient(String mail, String mdp) throws RemoteException, SQLException;
	
	public Panier recuperePanier(Client client) throws RemoteException, SQLException;
	
}
