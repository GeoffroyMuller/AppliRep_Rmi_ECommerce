package controleur;

import java.io.IOException;
import java.rmi.ConnectException;

import application.ClientApp;
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
	/*
	 * Convention : les variables @FXML utilisent des "_", 
	 * les autres utilise principalement le camelcase
	 */
	@FXML
	private TextField textfield_ip_port;
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

			try {
				recupereIpPort();
				ClientApp.connection(IP, PORT);
				ClientApp.connexionSessionClient(IP, PORT);
				chargerMagasin();
			} catch (ConnectException e) {
				alertConnexionEchouee("Le serveur distant n'est pas accessible");
			} catch (Exception e) {
				e.printStackTrace();
				alertConnexionEchouee("L'ip ou le port saisi est incorrect :"
						+"\n  format attendu : <ip serveur>:<port serveur>"
						+"\n  exemple : 192.168.43.1:1099");
			}
		
	}
	
	/**
	 * Charge le fxml du magasin dans un nouvel onglet
	 */
	private void chargerMagasin() {
		ObservableList<Tab> list_tabs = tab_onglets.getTabs();
		list_tabs.add(new Tab(""+IP+" : "+PORT));
		FXMLLoader loader = new FXMLLoader(getClass()
				.getResource("/vue/magasin.fxml"));

		BorderPane node;
		try {
			node = loader.load();
			node.getStylesheets().add(getClass().getResource("/vue/style/principal.css").toExternalForm());
			node.prefWidthProperty().bind(tab_onglets.widthProperty());
			node.prefHeightProperty().bind(tab_onglets.heightProperty());
			list_tabs.get(list_tabs.size()-1).setContent(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Recupere l'ip et le port entrés dans le textfield de la vue principal
	 * @throws Exception lorsque le format <ip serveur>:<port serveur> n'est pas respecté
	 */
	public void recupereIpPort() throws Exception{
		String[] ipPort = textfield_ip_port.getText().trim().split(":");
		
		if(ipPort.length!=2) {
			throw new Exception();
		}else {
			if(ipPort[0].isEmpty()||ipPort[1].isEmpty()) {
				throw new Exception();
			}
		}
		IP = ipPort[0];
		PORT = ipPort[1];
	}

	/**
	 * affiche une alert specifique à l'ip et le port
	 */
	public void alertConnexionEchouee(String msg) {
		Alert alert = new Alert(AlertType.WARNING); alert = new Alert(AlertType.WARNING);
		alert.setTitle("Attention");
		alert.setHeaderText("Connexion échouée");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}


