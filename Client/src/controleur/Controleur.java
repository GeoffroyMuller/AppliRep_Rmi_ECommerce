package controleur;

import java.io.IOException;

import application.Client;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Controleur gerant la vue principal
 * @author Geoff-Portable
 */
public class Controleur {
	@FXML
	private TextField textfield_ip;
	@FXML
	private TextField textfield_port;
	@FXML
	private TabPane tab_onglets;
	@FXML
	private Tab tab_test;

	/**
	 * ip du serveur
	 */
	private static String IP = "0.0.0.0";
	
	/**
	 * port du serveur
	 */
	private static String PORT = "PORT";

	/**
	 * Connexion au serveur
	 */
	@FXML
	public void connexion(){
		IP = textfield_ip.getText();
		PORT = textfield_port.getText();
			try {
				if(IP.isEmpty()||PORT.isEmpty()) {
					throw new Exception();
				}
				Client.connection(IP, PORT);
				chargerMagasin();
			} catch (Exception e) {
				alertErreur_IpPort();
			}
		
	}
	
	/**
	 * Charge le fxml du magasin dans un nouvel onglet
	 */
	@FXML
	public void chargerMagasin() {
		ObservableList<Tab> list_tabs = tab_onglets.getTabs();
		list_tabs.add(new Tab(""+IP+" : "+PORT));
		FXMLLoader loader = new FXMLLoader(getClass()
				.getResource("/vue/magasin.fxml"));

		BorderPane node;
		try {
			node = loader.load();
			node.prefWidthProperty().bind(tab_onglets.widthProperty());
			node.prefHeightProperty().bind(tab_onglets.heightProperty());
			list_tabs.get(list_tabs.size()-1).setContent(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * affiche une alert specifique à l'ip et le port
	 */
	public static void alertErreur_IpPort() {
		Alert alert = new Alert(AlertType.WARNING); alert = new Alert(AlertType.WARNING);
		alert.setTitle("Attention");
		alert.setHeaderText("Connexion échouée");
		alert.setContentText("L'ip ou le port saisi est incorrect");
		alert.showAndWait();
	}
}


