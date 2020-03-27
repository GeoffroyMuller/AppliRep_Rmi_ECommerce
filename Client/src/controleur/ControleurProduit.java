package controleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ClientApp;
import interfaces.IMagasin;
import interfaces.IProduit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class ControleurProduit implements Initializable{
	
	/**
	 * Produit courant
	 */
	private static IProduit produit;
	
	@FXML
	private Label label_nomproduit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		IMagasin magasin = ClientApp.dernierMagasin();
		try {
			ArrayList<IProduit> listeProduits = ClientApp.dernierMagasin().getListeProduit();
			produit = listeProduits.get(ControleurMagasin.getNoProduitCourant());
			chargerProduitCourant();
		} catch (RemoteException e) {
			alertErreur_Charge();
		}
	}
	
	@FXML
	public void ajouterAuPanier() {
		
	}
	
	/**
	 * Charge le produit courant
	 * @throws RemoteException
	 */
	public void chargerProduitCourant() throws RemoteException {
		label_nomproduit.setText(produit.getNom());
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
