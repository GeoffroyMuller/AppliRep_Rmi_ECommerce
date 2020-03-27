package modele;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

import interfaces.IPanier;
import interfaces.IProduit;

public class Panier extends Observable{

	private IPanier panier;
	
	private int idPlacement;
	
	/**
	 * Quantit� du produit dans le panier
	 */
	private int qtProduit;
	
	/**
	 * Liste des produits pr�sent dans le panier
	 */
	private ArrayList<IProduit> listeProduits;

	/**
	 * Liste des quantit�s de chaque produit du panier
	 */
	private ArrayList<Integer> listeQuantites;
	
	
	private void actualiserQuantite() {
		try {
			listeQuantites = panier.getQuantiteProduit();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		qtProduit = listeQuantites.get(idPlacement);
	}
}
