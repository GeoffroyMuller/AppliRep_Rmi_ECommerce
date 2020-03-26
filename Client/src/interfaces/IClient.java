package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IClient extends Remote{

	public IClient connexionClient(String mail, String mdp) throws RemoteException, SQLException;
	
	public IPanier recuperePanier(IClient client) throws RemoteException, SQLException;
	
}
