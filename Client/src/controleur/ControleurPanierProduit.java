package controleur;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import application.ClientApp;
import interfaces.IClient;
import interfaces.IMagasin;
import interfaces.IPanier;
import interfaces.IProduit;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.Panier;
import javafx.scene.control.Alert.AlertType;

/**
 * Controleur de la vue produitPanier. Gére les produits contenus dans le panier
 */
public class ControleurPanierProduit implements Initializable, Observer{
	
	private int idPlacement;
	
	private IClient sessionClient;
	
	private Panier panier;
	
	private ControleurMagasin controleurMagasin;
	
	/**
	 * Produit courant
	 */
	private IProduit produitCourant;

	@FXML
	private Label label_nomproduit;

	@FXML
	private TextField tf_quantite;

	
	 public ControleurPanierProduit(
			 ControleurMagasin _controleurMagasin, int _idPlacement, IProduit _produitCourant) {
		 controleurMagasin = _controleurMagasin;
		 idPlacement = _idPlacement;
		 produitCourant = _produitCourant;

	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sessionClient = controleurMagasin.getSessionClient();
		panier = ClientApp.getPanierSessionClient(sessionClient);
		panier.addObserver(this);

		chargerProduitCourant();
	}

	@FXML
	public void moinsUn() {
		try {
			panier.retirerProduit(produitCourant.getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void plusUn() {
		try {
			panier.ajouterProduit(produitCourant.getId());

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@FXML
	public void retirerDuPanier() {
		try {
			panier.retirerProduitEntier(produitCourant.getId());
			System.out.println("retirer> produit "
			+"[id:"+produitCourant.getId()+", nom: "+produitCourant.getNom()+"]"+"à l'emplacement : "+idPlacement);
			controleurMagasin.actualiserPanierGraphique();
			System.out.println("*-----------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Charge le produit courant
	 * @throws RemoteException
	 */
	public void chargerProduitCourant(){
		try {
			label_nomproduit.setText(produitCourant.getNom());
			//tf_quantite.setText(""+panier.getMapProduitQt().get(produitCourant));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		panier = (Panier)o;
		chargerProduitCourant();
	}
}
