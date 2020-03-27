package controleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import application.ClientApp;
import interfaces.IMagasin;
import interfaces.IPanier;
import interfaces.IProduit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * Controleur de la vue produitPanier. Gére les produits contenus dans le panier
 */
public class ControleurPanierProduit implements Initializable, Observer{

	private IPanier panier;

	/**
	 * Produit courant
	 */
	private IProduit produitCourant;

	/**
	 * Quantité du produit dans le panier
	 */
	private int qtProduit;
	
	/**
	 * Id representant l'emplacement du produit du panier courant
	 * cette valeur sera utilisées pour les actualisations des listes
	 */
	private int idPlacement;
	
	/**
	 * Liste des produits présent dans le panier
	 */
	private ArrayList<IProduit> listeProduits;

	/**
	 * Liste des quantités de chaque produit du panier
	 */
	private ArrayList<Integer> listeQuantites;
	
	@FXML
	private Label label_nomproduit;

	@FXML
	private TextField tf_quantite;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			panier = ClientApp.getSessionClientCourant().recupererPanier();
			idPlacement = ControleurMagasin.getNoProduitPanierCourant();
			
			try {
				listeQuantites = panier.getQuantiteProduit();
				listeProduits = panier.getListeDeProduit();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			produitCourant = listeProduits.get(idPlacement);
			qtProduit = listeQuantites.get(idPlacement);
			chargerProduitCourant();

		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void moinsUn() {
		try {
			actualiserQuantite();
			System.out.println("avmoins:"+qtProduit);
			panier.retirerProduit(produitCourant.getId());
			actualiserQuantite();
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void plusUn() {
		try {
			actualiserQuantite();
			System.out.println("avplus:"+qtProduit);
			panier.ajouterProduit(produitCourant.getId());
			actualiserQuantite();
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void retirerDuPanier() {

	}
	
	/**
	 * Charge le produit courant
	 * @throws RemoteException
	 */
	public void chargerProduitCourant() throws RemoteException {
		label_nomproduit.setText(produitCourant.getNom());
		tf_quantite.setText(""+qtProduit);

	}
	
	/**
	 * Actualise les attribus de quantité
	 */
	private void actualiserQuantite() {
		try {
			listeQuantites = panier.getQuantiteProduit();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		qtProduit = listeQuantites.get(idPlacement);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
