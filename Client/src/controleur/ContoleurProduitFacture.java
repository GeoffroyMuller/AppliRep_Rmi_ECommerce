package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * Controle la vue d'un produit dans la facture
 * @author Geoff-Portable
 *
 */
public class ContoleurProduitFacture implements Initializable {
	private String nom, quantite, prixUnit, total;
	@FXML
	private Label label_facture_produit;

	/**
	 * Constructeur
	 * @param nom
	 * @param quantite
	 * @param prixUnit
	 * @param total
	 */
	public ContoleurProduitFacture(String nom, String quantite, String prixUnit, String total) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.quantite = quantite;
		this.prixUnit = prixUnit;
		this.total = total;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		label_facture_produit.setText("Produit : "+nom+
				" | Quantite : "+quantite+
				" | prixUnit : "+prixUnit+" €"+
				" | Total : "+total+" €");
	}
	
}
