package implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MagasinDao;
import interfaces.IMagasin;

public class Magasin extends UnicastRemoteObject implements IMagasin{
	
	private static final long serialVersionUID = 1L;

	ArrayList<Produit> listeProduit = new ArrayList<Produit>();
	
	MagasinDao magasinDao = new MagasinDao();
	
	public Magasin() throws RemoteException, SQLException
	{
		this.listeProduit = magasinDao.recupererProduits();
	}

	public ArrayList<Produit> getListeProduit() throws RemoteException{
		return listeProduit;
	}

	public void setListeProduit(ArrayList<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

}
