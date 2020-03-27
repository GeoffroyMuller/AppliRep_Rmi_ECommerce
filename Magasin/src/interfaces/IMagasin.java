package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import implement.Produit;

public interface IMagasin extends Remote{
		
	/**
	 * Récupère la liste des produits d'un magasin
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<Produit> getListeProduit() throws RemoteException;

}
