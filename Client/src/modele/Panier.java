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
	
	private int idPlace;

	private ArrayList<Integer> listeQuantites;
	
	private ArrayList<IProduit> listeProduits;
	
	private HashMap<IProduit, Integer> mapProduitQt;

	public Panier(IPanier _panier) throws RemoteException, SQLException {
		panier = _panier;
		mapProduitQt = new HashMap<IProduit, Integer>();
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
			for (int i = 0; i < listeProduits.size(); i++) {
				mapProduitQt.put(
						listeProduits.get(i),
						listeQuantites.get(i));
				System.out.println("------------"+listeQuantites.get(i));
			}
			for (int i = 0; i < listeProduits.size(); i++) {
				System.out.println("list : "+listeProduits.get(i).getNom());
			}

		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getListeQuantites() throws SQLException {
		actualiserListePanier();
		return listeQuantites;
	}

	public ArrayList<IProduit> getListeProduits() throws SQLException {
		actualiserListePanier();
		return listeProduits;
	}

	public HashMap<IProduit, Integer> getMapProduitQt() throws SQLException {
		actualiserListePanier();
		return mapProduitQt;
	}
}
