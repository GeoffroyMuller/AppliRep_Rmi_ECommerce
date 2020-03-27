package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ControleurFacture implements Initializable{

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

}
