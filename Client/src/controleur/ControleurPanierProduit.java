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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleurPanierProduit implements Initializable{
	
	private IPanier panier;
	
	/**
	 * Produit courant
	 */
	private IProduit produitCourant;
	
	@FXML
	private Label label_nomproduit;
	
	@FXML
	private TextField tf_quantite;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			panier = ClientApp.getSessionClientCourant().recuperePanier();
			
			ArrayList<IProduit> listeProduits = panier.getListeDeProduit();
			produitCourant = listeProduits.get(ControleurMagasin.getNoProduitPanierCourant());
			chargerProduitCourant();
		} catch (RemoteException | SQLException e) {
			
		}
	}

	@FXML
	public void moinsUn() {
		try {
			panier.retirerProduit(produitCourant.getId());
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void plusUn() {
		try {
			panier.ajouterProduit(produitCourant.getId());
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void retirerDuPanier() {
		
	}

	/**
	 * Charge le produit courant
	 * @throws RemoteException
	 */
	public void chargerProduitCourant() throws RemoteException {
		label_nomproduit.setText(produitCourant.getNom());
		
	}
	
}
