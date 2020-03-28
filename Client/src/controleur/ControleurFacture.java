package controleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import interfaces.IBanque;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class ControleurFacture implements Initializable{

	private IBanque ibanque;
	@FXML
	private Label label_ClientNomPrenom;
	
	@FXML
	private Label label_ClientNumRue;
	
	@FXML 
	private Label label_ClientCpVille;
	
	@FXML
	private Label label_ClientMail;
	
	@FXML
	private Label label_DateJour;
	
	@FXML 
	private Label label_Total;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public ControleurFacture() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void validerLaFacture() throws RemoteException, SQLException
	{
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Identifiants bancaires");
		dialog.setHeaderText("Vous allez effectuer un paiement");
		dialog.setContentText("Veuillez entrer votre identifiants bancaires:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    System.out.println("Votre identifiant : " + result.get());
		   
		    boolean solvabilite = ibanque.verifierSolvabilite(result.get(), 220.00);
		    System.out.println(solvabilite);
		    if (solvabilite == true)
		    {
		    	Alert alert = new Alert(AlertType.NONE);
		    	alert.setTitle("Félicitation");
		    	alert.setContentText("Votre paiement est accepté !");

		    	alert.showAndWait();
		    }
		    else
		    {
		    	Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Erreur");
		    	alert.setContentText("Votre banque a refusé le paiement");

		    	alert.showAndWait();
		    }
		}
	}

}
