package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IClient extends Remote{

	public IClient connexionClient(String mail, String mdp) throws RemoteException, SQLException;
	
	public IPanier recupererPanier() throws RemoteException, SQLException;
	
	public int getId() throws RemoteException;
	
	public void setId(int idClient) throws RemoteException;
	
	public String getNom() throws RemoteException;
	
	public String getPrenom() throws RemoteException;
	
	public int getCp() throws RemoteException;
	
	public int getNumRue()  throws RemoteException;
	
	public String getRue() throws RemoteException;
	
	public String getMail() throws RemoteException;
}
