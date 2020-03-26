package implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MagasinDao;
import dao.ProduitDao;
import interfaces.IMagasin;
import interfaces.IProduit;

public class Magasin extends UnicastRemoteObject implements IMagasin{
	
	ArrayList<Produit> listeProduit = new ArrayList<Produit>();
	
	MagasinDao magasinDao = new MagasinDao();
	
	public Magasin() throws RemoteException, SQLException
	{
		this.listeProduit = magasinDao.getProduits();
	}

	public ArrayList<Produit> getListeProduit() throws RemoteException{
		return listeProduit;
	}

	public void setListeProduit(ArrayList<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

}
