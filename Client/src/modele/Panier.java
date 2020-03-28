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

	public Panier(IPanier _panier) throws RemoteException {
		panier = _panier;
		mapProduitQt = new HashMap<IProduit, Integer>();
		actualiserListePanier();
	}

	public void ajouterProduit(int idProduit) {
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
	public void retirerProduit(int idProduit) {
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
	public void retirerProduitEntier(int idProduit) {
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

	private void actualiserListePanier() {
		try {
			listeProduits = panier.getListeDeProduit();
			listeQuantites = panier.getQuantiteProduit();
			for (int i = 0; i < listeProduits.size(); i++) {
				mapProduitQt.put(
						listeProduits.get(i),
						listeQuantites.get(i));
			}
			System.out.println("----------------");
			for (int i = 0; i < listeProduits.size(); i++) {
				System.out.println("list : "+listeProduits.get(i).getNom());
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getListeQuantites() {
		actualiserListePanier();
		return listeQuantites;
	}

	public ArrayList<IProduit> getListeProduits() {
		actualiserListePanier();
		return listeProduits;
	}

	public HashMap<IProduit, Integer> getMapProduitQt() {
		actualiserListePanier();
		return mapProduitQt;
	}
}
