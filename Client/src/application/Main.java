package application;

import java.rmi.Naming;
import java.rmi.Remote;

import interfaces.IMagasin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application{
	@Override
	public void start(Stage windows) {
		//	public static void main( String[] args) {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/principal.fxml"));
//			Parent root = loader.load();
//			Scene scene = new Scene(root,1000,600);
//			scene.getStylesheets().add(getClass().getResource("/vue/style/principal.css").toExternalForm());
//
//			windows.setScene(scene);
//			windows.show();
			System.out.println("Lancement du client");

			Remote r = Naming.lookup("rmi://192.168.0.17/test");
			System.out.println(r);
			String bonjor = ((IMagasin) r).getProduits();
			System.out.println("resultat de la methode : "+bonjor);
			
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
