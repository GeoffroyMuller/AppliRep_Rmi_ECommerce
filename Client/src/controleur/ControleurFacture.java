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

public class ControleurFacture implements Initializable{

	ArrayList<IProduit> listeProduits;
	
	ArrayList<Integer> listeQuantite;
	
	IClient sessionClient;
	
	IPanier panier;
	
	private IBanque ibanque;
	@FXML
	private Label label_clientNomPrenom;
	
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
		System.out.println(label_clientNomPrenom);
		try {
			System.out.println(this.sessionClient.getNom());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}try {
			System.out.println(this.sessionClient.getPrenom());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			label_clientNomPrenom.setText(this.sessionClient.getNom()+" "+this.sessionClient.getPrenom());
			label_ClientNumRue.setText(this.sessionClient.getNumRue()+" "+this.sessionClient.getRue());
			label_ClientCpVille.setText(""+this.sessionClient.getCp());
			label_ClientMail.setText(this.sessionClient.getMail());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ControleurFacture(ArrayList<IProduit> listeProduits, IClient sessionClient) throws RemoteException, SQLException, InterruptedException {
		this.listeProduits = listeProduits;
		this.sessionClient = sessionClient;
		this.panier = sessionClient.recupererPanier();
		this.listeQuantite = panier.getQuantiteProduit();
		Thread.sleep(2000);
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
		   
		    boolean solvabilite = ibanque.verifierSolvabilite(result.get(), panier.calculerMontantPanier());
		    System.out.println(solvabilite);
		    if (solvabilite == true)
		    {
		    	Alert alert = new Alert(AlertType.NONE);
		    	alert.setTitle("F�licitation");
		    	alert.setContentText("Votre paiement est accept� !");

		    	alert.showAndWait();
		    }
		    else
		    {
		    	Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Erreur");
		    	alert.setContentText("Votre banque a refus� le paiement");

		    	alert.showAndWait();
		    }
		}
	}

}
