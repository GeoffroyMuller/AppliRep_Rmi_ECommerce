package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface IPanier extends Remote {
	
	public IPanier ajouterProduit(IPanier panier, IProduit produit) throws RemoteException, SQLException;
	
	public IPanier retirerProduit(IPanier panier, IProduit produit) throws RemoteException, SQLException;
	
	public double calculerMontantIPanier(ArrayList<IProduit> listeProduits) throws RemoteException;

	public ArrayList<IProduit> getListeDeProduit() throws RemoteException;
}
