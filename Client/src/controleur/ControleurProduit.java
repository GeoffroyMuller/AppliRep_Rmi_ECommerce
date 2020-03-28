package controleur;

import java.io.IOException;
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
import interfaces.IProduit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import modele.Panier;

public class ControleurProduit implements Initializable, Observer{

	private IClient sessionClient;

	private IMagasin magasin;

	private Panier panier;

	private ControleurMagasin controleurMagasin;

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

	@FXML
	private Label label_nomproduit;

	@FXML
	private Label label_description;

	public ControleurProduit(ControleurMagasin _controleurMagasin) {
		controleurMagasin = _controleurMagasin;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {

			magasin = ClientApp.getMagasinCourant();
			sessionClient = ClientApp.getSessionClientCourant();
			listeProduits = magasin.getListeProduit();

			panier = ClientApp.getListePaniers().get(sessionClient);
			panier.addObserver(this);

			produitCourant = listeProduits.get(ControleurMagasin.getNoProduitCourant());
			listeProduits = magasin.getListeProduit();
			chargerProduitCourant();
		} catch (RemoteException e) {
			alertErreur_Charge();
		}
	}

	@FXML
	public void ajouterAuPanier() throws SQLException {
		try {
			panier.ajouterProduit(produitCourant.getId());
			controleurMagasin.actualiserPanierGraphique();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	@Override
	public void update(Observable o, Object arg) {
		panier = (Panier)o;
	}

}
