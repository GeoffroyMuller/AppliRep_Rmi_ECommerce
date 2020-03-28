package modele;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import interfaces.IPanier;
import interfaces.IProduit;

public class Panier extends Observable{

	private IPanier panier;

	private ArrayList<Integer> listeQuantites;
	
	private ArrayList<IProduit> listeProduits;

	public Panier(IPanier _panier) throws RemoteException, SQLException {
		panier = _panier;
		actualiserListePanier();
	}

	public void ajouterProduit(int idProduit) throws SQLException {
		try {
			panier.ajouterProduit(idProduit);
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualiserListePanier();
		setChanged();
		notifyObservers();
	}
	public void retirerProduit(int idProduit) throws SQLException {
		try {
			panier.retirerProduit(idProduit);
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualiserListePanier();
		setChanged();
		notifyObservers();
	}
	public void retirerProduitEntier(int idProduit) throws SQLException {
		try {
			panier.retirerToutesQuantites(idProduit);
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualiserListePanier();
		setChanged();
		notifyObservers();
	}

	private void actualiserListePanier() throws SQLException {
		try {
			listeProduits = panier.getListeDeProduit();
			listeQuantites = panier.getQuantiteProduit();
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getQuantites(int index) throws SQLException, RemoteException {
		actualiserListePanier();
		return listeQuantites.get(index); //listeQuantites.get(index);
	}
	
	public ArrayList<Integer> getListeQuantites() throws SQLException {
		actualiserListePanier();
		return listeQuantites;
	}

	public ArrayList<IProduit> getListeProduits() throws SQLException {
		actualiserListePanier();
		return listeProduits;
	}

}
