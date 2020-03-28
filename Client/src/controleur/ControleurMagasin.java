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
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Panier;

public class ControleurMagasin implements Initializable{

	private IMagasin magasin;

	private IClient sessionClient;

	private Panier panier;

	private ArrayList<IProduit> listeProduits;

	private ControleurPanierProduit controleurPanierProduit;
	
	private ControleurFacture controleurFacture;

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
	
	@FXML
	private BorderPane bp_principal;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			magasin = ClientApp.getMagasinCourant();
			sessionClient = ClientApp.getSessionClientCourant();
			listeProduits = magasin.getListeProduit();
			panier = ClientApp.getPanierSessionClient(sessionClient);
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
		BorderPane nodeproduit;
		for(int i=0; i<listeProduits.size(); i++) {
			noProduitCourant = i;
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/vue/produit.fxml"));
			loader.setController(new ControleurProduit(this));
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
		listeProduits = panier.getListeDeProduit();

		AnchorPane nodeproduit;

		for(int i=0; i<listeProduits.size(); i++) {

			noProduitPanierCourant = i;
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/vue/produitPanier.fxml"));
			controleurPanierProduit = new ControleurPanierProduit(this, i,
					ClientApp.getSessionClientCourant().recupererPanier()
					.getListeDeProduit().get(i));
			loader.setController(controleurPanierProduit);
			nodeproduit = loader.load();
			list_panier.getItems().add(nodeproduit);
		}
	}

	/**
	 * Actualise la partie graphique du panier
	 * @throws IOException
	 * @throws SQLException
	 */
	@FXML
	public void actualiserPanierGraphique() throws IOException, SQLException {
		AnchorPane nodeproduit;
		ArrayList<IProduit>listeProduitsPanier = panier.getListeProduits();
		list_panier.getItems().clear();
		for(int i=0; i<listeProduitsPanier.size(); i++) {
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/vue/produitPanier.fxml"));
			controleurPanierProduit = new ControleurPanierProduit(this, i,listeProduitsPanier.get(i));
			loader.setController(controleurPanierProduit);
			nodeproduit = loader.load();
			list_panier.getItems().add(nodeproduit);
			list_panier.refresh();
		}
	}

	@FXML
	public void validerMonPanier() {
		Stage windows = new Stage();
		//bp_principal.setDisable(true);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/facture.fxml"));
			controleurFacture = new ControleurFacture();
			loader.setController(controleurPanierProduit);
			Parent root = loader.load();
			Scene scene = new Scene(root,600,700);
			scene.getStylesheets().add(getClass().getResource("/vue/style/principal.css").toExternalForm());


			windows.setScene(scene);
			windows.show();
		} catch(Exception e) {
			e.printStackTrace();
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

	public ControleurPanierProduit getControleurPanierProduit() {
		return controleurPanierProduit;
	}	

}

