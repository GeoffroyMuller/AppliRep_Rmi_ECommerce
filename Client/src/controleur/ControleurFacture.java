package controleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import interfaces.IBanque;
import interfaces.IClient;
import interfaces.IPanier;
import interfaces.IProduit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class ControleurFacture {

	ArrayList<IProduit> listeProduits;
	
	ArrayList<Integer> listeQuantite;
	
	IClient sessionClient;
	
	IPanier panier;
	
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
	
	public ControleurFacture(ArrayList<IProduit> listeProduits, IClient sessionClient) throws RemoteException, SQLException {

		this.listeProduits = listeProduits;
		this.sessionClient = sessionClient;
		this.panier = sessionClient.recupererPanier();
		this.listeQuantite = panier.getQuantiteProduit();

		label_ClientNomPrenom.setText(sessionClient.getNom()+" "+sessionClient.getPrenom());
		label_ClientNumRue.setText(sessionClient.getNumRue()+" "+sessionClient.getRue());
		label_ClientCpVille.setText(""+sessionClient.getCp());
		label_ClientMail.setText(sessionClient.getMail());
		label_DateJour.setText(LocalTime.now().toString());
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
