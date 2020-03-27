package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface IPanier extends Remote {
	
	public void ajouterProduit(int idProduit) throws RemoteException, SQLException;
	
	public void retirerProduit(int idProduit) throws RemoteException, SQLException;
	
	public double calculerMontantPanier() throws RemoteException;

	public ArrayList<IProduit> getListeDeProduit() throws RemoteException;
	
	public int getIdPanier() throws RemoteException;
	
}
