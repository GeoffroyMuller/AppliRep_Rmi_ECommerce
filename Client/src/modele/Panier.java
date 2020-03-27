package modele;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import interfaces.IPanier;
import interfaces.IProduit;

public class Panier extends Observable{

	private IPanier panier;
	
	private int idPlace;

	private ArrayList<Integer> listeQuantites;
	
	private ArrayList<IProduit> listeProduits;

	public Panier(IPanier _panier) {
		panier = _panier;
		//idPlace = ClientApp.getSessionClientCourant().getId();
		listeQuantites = new ArrayList<Integer>();
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

	private void actualiserListePanier() {
		try {
			listeProduits = panier.getListeDeProduit();
			listeQuantites = panier.getQuantiteProduit();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
