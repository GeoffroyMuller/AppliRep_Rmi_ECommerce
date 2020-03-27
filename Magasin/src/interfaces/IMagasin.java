package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import implement.Produit;

public interface IMagasin extends Remote{
		
	/**
	 * R�cup�re la liste des produits d'un magasin
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<Produit> getListeProduit() throws RemoteException;

}
