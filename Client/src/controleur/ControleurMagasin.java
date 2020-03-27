package controleur;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ClientApp;
import interfaces.IClient;
import interfaces.IMagasin;
import interfaces.IPanier;
import interfaces.IProduit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ControleurMagasin implements Initializable{

	private IMagasin magasin;

	private IClient sessionClient;

	/**
	 * Numero du produit qui est en train d'étre chargé
	 */
	private static int noProduitCourant;

	/**
	 * Numero du produit du panier qui est en train d'étre chargé
	 */
	private static int noProduitPanierCourant;

	@FXML
	private ListView<BorderPane> list_produit;

	@FXML
	private ListView<AnchorPane> list_panier;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			magasin = ClientApp.getMagasinCourant();
			sessionClient = ClientApp.getSessionClientCourant();

			chargerProduit();
			chargerPanier();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Charge les produits du magasin dans la vue
	 * @throws IOException
	 */
	@FXML
	public void chargerProduit() throws IOException {
		ArrayList<IProduit> listeProduits = magasin.getListeProduit();

		BorderPane nodeproduit;

		for(int i=0; i<listeProduits.size(); i++) {
			noProduitCourant = i;
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/vue/produit.fxml"));
			nodeproduit = loader.load();
			list_produit.getItems().add(nodeproduit);
		}

	}

	/**
	 * Charge le panier du client dans la vue
	 * @throws IOException
	 * @throws SQLException 
	 */
	@FXML
	public void chargerPanier() throws IOException, SQLException {
		IPanier panier = sessionClient.recupererPanier();
		ArrayList<IProduit> listeProduits = panier.getListeDeProduit();

		AnchorPane nodeproduit;

		for(int i=0; i<listeProduits.size(); i++) {

			noProduitPanierCourant = i;
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/vue/produitPanier.fxml"));
			nodeproduit = loader.load();
			list_panier.getItems().add(nodeproduit);
		}

	}

	public static int getNoProduitCourant() {
		return noProduitCourant;
	}

	public static int getNoProduitPanierCourant() {
		return noProduitPanierCourant;
	}

	public IMagasin getMagasin() {
		return magasin;
	}

	public IClient getSessionClient() {
		return sessionClient;
	}



}

