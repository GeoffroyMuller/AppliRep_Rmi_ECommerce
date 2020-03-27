package controleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import application.ClientApp;
import interfaces.IClient;
import interfaces.IMagasin;
import interfaces.IPanier;
import interfaces.IProduit;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.Panier;
import javafx.scene.control.Alert.AlertType;

/**
 * Controleur de la vue produitPanier. Gére les produits contenus dans le panier
 */
public class ControleurPanierProduit implements Initializable, Observer{

	private IClient sessionClient;
	
	private Panier panier;
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
			sessionClient = ClientApp.getSessionClientCourant();
			panier = ClientApp.getListePaniers().get(sessionClient);
			panier.addObserver(this);
			produitCourant = ClientApp.getMagasinCourant()
					.getListeProduit().get(ControleurMagasin.getNoProduitPanierCourant());
			
			chargerProduitCourant();

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void moinsUn() {
		try {
			panier.retirerProduit(produitCourant.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void plusUn() {
		try {
			panier.ajouterProduit(produitCourant.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
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
	public void chargerProduitCourant(){
		try {
			label_nomproduit.setText(produitCourant.getNom());
			tf_quantite.setText(""+panier.getListeQuantites()
			.get(produitCourant.getId()-1));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		panier = (Panier)o;
		chargerProduitCourant();
	}
}
