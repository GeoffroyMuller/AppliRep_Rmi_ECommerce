package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface IPanier extends Remote {
	
	public void ajouterProduit(int idProduit) throws RemoteException, SQLException;
	
	public void retirerProduit(int idProduit) throws RemoteException, SQLException;
	
	public void retirerToutesQuantites( int idProduit) throws SQLException, RemoteException; 
	
	/**
	 * Vide entièrement un panier
	 * @param idPanier
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public void viderPanier(int idPanier) throws SQLException, RemoteException;
	
	public double calculerMontantPanier() throws RemoteException;
	
	public ArrayList<Integer> getQuantiteProduit() throws RemoteException, SQLException;

	public ArrayList<IProduit> getListeDeProduit() throws RemoteException, SQLException;
	
	public int getIdPanier() throws RemoteException;
	
}
