package controleur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.ClientApp;
import interfaces.IBanque;
import interfaces.IClient;
import interfaces.IPanier;
import interfaces.IProduit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

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
	
	@FXML
	private ListView<AnchorPane> list_produit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			label_clientNomPrenom.setText(this.sessionClient.getNom()+" "+this.sessionClient.getPrenom());
			label_ClientNumRue.setText(this.sessionClient.getNumRue()+" "+this.sessionClient.getRue());
			label_ClientCpVille.setText(""+this.sessionClient.getCp());
			label_ClientMail.setText(this.sessionClient.getMail());
			label_Total.setText(panier.calculerMontantPanier()+"�");
			chargerListeProduit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructeur
	 * @param listeProduits
	 * @param sessionClient
	 * @throws RemoteException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public ControleurFacture(IClient sessionClient) throws RemoteException, SQLException, InterruptedException {
		this.sessionClient = sessionClient;
		this.panier = sessionClient.recupererPanier();
		this.listeProduits = panier.getListeDeProduit();
		this.listeQuantite = panier.getQuantiteProduit();
	}
	
	/**
	 * Charge les produits dans la facture
	 * @throws IOException
	 */
	public void chargerListeProduit() throws IOException {
		AnchorPane nodeproduit;

		for (int i = 0; i < listeProduits.size(); i++) {
			
			double montant = listeProduits.get(i)
					.getPrixUnit()*listeQuantite.get(i);
			
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/vue/produitFacture.fxml"));
			
			ContoleurProduitFacture contoleurProduitFacture =
					new ContoleurProduitFacture(
							listeProduits.get(i).getNom(),
							""+listeQuantite.get(i),
							""+listeProduits.get(i).getPrixUnit(),
							""+montant);
			loader.setController(contoleurProduitFacture);
			nodeproduit = loader.load();
			
			list_produit.getItems().add(nodeproduit);
			System.out.println("-----------"+list_produit);
		}
	}
	
	/**
	 * Valide la facture
	 * @throws RemoteException
	 * @throws SQLException
	 */
	@FXML
	public void validerLaFacture() throws RemoteException, SQLException
	{
		try {
			Remote r = Naming.lookup("rmi://"+InetAddress.getLocalHost().getHostAddress()+":"+Controleur.PORT+"/banque");
			ibanque = ((IBanque)r);
			System.out.println(ibanque);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		boolean solvabilite;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Identifiants bancaires");
		dialog.setHeaderText("Vous allez effectuer un paiement");
		dialog.setContentText("Veuillez entrer votre identifiants bancaires:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    System.out.println("Votre identifiant : " + result.get());
		   
		    System.out.println(panier.calculerMontantPanier());
		    System.out.println(ibanque);
		    solvabilite = ibanque.verifierSolvabilite(result.get(), panier.calculerMontantPanier());
		    System.out.println(solvabilite);
		    if (solvabilite == true)
		    {
		    	panier.viderPanier(panier.getIdPanier());
		    	Alert alert = new Alert(AlertType.INFORMATION);
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
