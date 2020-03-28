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

	/**
	 * Constructeur
	 * @param _controleurMagasin Controleur createur
	 * @param _idPlacement place occupé dans le panier
	 * @param _produitCourant produit a attitrer this
	 */
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
	public void moinsUn() throws SQLException {
		try {
			panier.retirerProduit(produitCourant.getId());
			controleurMagasin.actualiserPanierGraphique();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void plusUn() throws SQLException {
		try {
			panier.ajouterProduit(produitCourant.getId());

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void retirerDuPanier() throws SQLException {
		try {
			panier.retirerProduitEntier(produitCourant.getId());
			controleurMagasin.actualiserPanierGraphique();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Charge le produit courant
	 * @throws SQLException 
	 * @throws RemoteException
	 */
	public void chargerProduitCourant(){
		try {
			label_nomproduit.setText(produitCourant.getNom());
			try {
				tf_quantite.setText(""+panier.getQuantites(idPlacement));
			} catch (IndexOutOfBoundsException e) { }
		} catch (RemoteException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update par l'Observable
	 */
	@Override
	public void update(Observable o, Object arg) {
		panier = (Panier)o;

		chargerProduitCourant();

	}
}
