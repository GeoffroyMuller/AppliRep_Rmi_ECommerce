package controleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ClientApp;
import interfaces.IMagasin;
import interfaces.IPanier;
import interfaces.IProduit;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class ControleurProduit implements Initializable{
	
	private IMagasin magasin;
	
	private IPanier panier;
	
	/**
	 * Id representant l'emplacement du produit du magasin
	 */
	private int idPlacement;
	
	/**
	 * Produit courant
	 */
	private IProduit produitCourant;
	
	/**
	 * Liste des produits présent dans le magasin
	 */
	private ArrayList<IProduit> listeProduits;

	/**
	 * Quantité du produit dans le panier
	 */
	private int qtProduit;
	
	/**
	 * Liste des quantités de chaque produit du panier
	 */
	private ArrayList<Integer> listeQuantites;
	
	@FXML
	private Label label_nomproduit;
	
	@FXML
	private Label label_description;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		magasin = ClientApp.getMagasinCourant();
		try {
			panier = ClientApp.getSessionClientCourant().recupererPanier();
		} catch (RemoteException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		idPlacement = ControleurMagasin.getNoProduitPanierCourant();
		actualiserQuantite();
		try {
			panier = ClientApp.getSessionClientCourant().recupererPanier();
		} catch (RemoteException | SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			ArrayList<IProduit> listeProduits = magasin.getListeProduit();
			produitCourant = listeProduits.get(idPlacement);
			chargerProduitCourant();
		} catch (RemoteException e) {
			alertErreur_Charge();
		}
	}
	
	@FXML
	public void ajouterAuPanier() {
		try {
			actualiserQuantite();
			System.out.println("-----------------"+produitCourant.getId());
			panier.ajouterProduit(produitCourant.getId());
			actualiserQuantite();
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Charge le produit courant
	 * @throws RemoteException
	 */
	public void chargerProduitCourant() throws RemoteException {
		label_nomproduit.setText(produitCourant.getNom());
		label_description.setText(produitCourant.getDescription());
	}
	
	/**
	 * affiche une alert specifique au produit
	 */
	public static void alertErreur_Charge() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");
		alert.setHeaderText("Chargement des produits impossible");
		alert.setContentText("Essayez de vous reconnecter.");
		alert.showAndWait();
	}
	
	/**
	 * Actualise les attribus de quantité
	 */
	private void actualiserQuantite() {
		try {
			listeQuantites = panier.getQuantiteProduit();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		qtProduit = listeQuantites.get(idPlacement);
	}

}
