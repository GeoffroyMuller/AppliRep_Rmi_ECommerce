package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ControleurMagasin implements Initializable{
	@FXML
	private ListView list_produit;
	@FXML
	public void chargerProduit() {
		FXMLLoader loader = new FXMLLoader(getClass()
				.getResource("/vue/produit.fxml"));

		BorderPane nodeproduit;
		try {
			nodeproduit = loader.load();
			//nodeproduit.prefWidthProperty().bind(list_produit.prefWidthProperty());
			list_produit.getItems().add(nodeproduit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		chargerProduit();
	}
}

