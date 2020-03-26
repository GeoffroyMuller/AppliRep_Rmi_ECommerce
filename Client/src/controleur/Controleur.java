package controleur;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Controleur {
	@FXML
	private TextField textfield_ip;
	@FXML
	private TextField textfield_port;
	@FXML
	private TabPane tab_onglets;
	@FXML
	private Tab tab_test;

	
	@FXML
	public void connexion(){
		/*Client.connection(
				textfield_ip.getText(),
				textfield_port.getText());*/
		
		tab_onglets.getTabs().add(new Tab("TEST"));
		FXMLLoader loader = new FXMLLoader(getClass()
				.getResource("/vue/magasin.fxml"));
		BorderPane node;
		try {
			node = loader.load();
			node.prefWidthProperty().bind(tab_onglets.widthProperty());
			node.prefHeightProperty().bind(tab_onglets.heightProperty());
			tab_test.setContent(node);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("connexion");
	}
}
	

