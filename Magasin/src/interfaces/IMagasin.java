package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.Client;
import modele.Produit;

public interface IMagasin extends Remote{
	
	public String getInfo() throws RemoteException;
	
	public Client connexionClient(String mail, String mdp) throws RemoteException, SQLException;
	
	public ArrayList<Produit> getProduits() throws RemoteException, SQLException;
	
	public String ajouterProduitPanier() throws RemoteException;
	
	public String supprimerProduitPanier() throws RemoteException;
	
	public String majQteProduitPanier() throws RemoteException;
	
	public String validerUnPanier() throws RemoteException;
	
	public String validerUneCommande() throws RemoteException;
	
	
}
