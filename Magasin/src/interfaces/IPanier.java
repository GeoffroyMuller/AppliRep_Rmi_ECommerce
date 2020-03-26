package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import implement.Panier;
import implement.Produit;

public interface IPanier extends Remote{

	public Panier ajouterProduit(Panier panier, Produit produit) throws RemoteException, SQLException;
	
	public Panier retirerProduit(Panier panier, Produit produit) throws RemoteException, SQLException;
	
	public double calculerMontantPanier(ArrayList<Produit> listeProduits) throws RemoteException;

	public ArrayList<Produit> getListeDeProduit() throws RemoteException;

}
