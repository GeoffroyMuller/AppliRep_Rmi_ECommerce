package controleur;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Client;
import interfaces.IMagasin;
import interfaces.IProduit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ControleurMagasin implements Initializable{
	
	/**
	 * Numero du produit qui est en train d'étre chargé
	 */
	private static int noProduitCourant;

	@FXML
	private ListView list_produit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			chargerProduit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Charge les produits du magasin dans la vue
	 * @throws IOException
	 */
	@FXML
	public void chargerProduit() throws IOException {
		ArrayList<IProduit> listeProduits = Client.dernierMagasin().getListeProduit();

		BorderPane nodeproduit;

		for(int i=0; i<listeProduits.size(); i++) {
			noProduitCourant = i;
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/vue/produit.fxml"));
			nodeproduit = loader.load();
			list_produit.getItems().add(nodeproduit);
		}
	}
	
	public static int getNoProduitCourant() {
		return noProduitCourant;
	}
}

