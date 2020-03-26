package application;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import interfaces.IMagasin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application{

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage windows) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/principal.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("/vue/style/principal.css").toExternalForm());


			windows.setScene(scene);
			windows.show();
//			Client.testGetListe();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
