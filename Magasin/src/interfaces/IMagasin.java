package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import implement.Client;
import implement.Panier;
import implement.Produit;

public interface IMagasin extends Remote{
		
	public ArrayList<Produit> getListeProduit() throws RemoteException;

}
