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
	 * Produit courant
	 */
	private IProduit produitCourant;
	
	@FXML
	private Label label_nomproduit;
	
	@FXML
	private Label label_description;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		magasin = ClientApp.getMagasinCourant();
		try {
			System.out.println("------------"+ClientApp.getSessionClientCourant().getId());
			panier = ClientApp.getSessionClientCourant().recuperePanier();
		} catch (RemoteException | SQLException e1) {
			System.out.println("--******************-");
		}
		
		try {
			ArrayList<IProduit> listeProduits = magasin.getListeProduit();
			produitCourant = listeProduits.get(ControleurMagasin.getNoProduitCourant());
			chargerProduitCourant();
		} catch (RemoteException e) {
			alertErreur_Charge();
		}
	}
	
	@FXML
	public void ajouterAuPanier() {
		try {
			System.out.println("-----------------"+produitCourant.getId());
			panier.ajouterProduit(produitCourant.getId());
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
	
	
}
