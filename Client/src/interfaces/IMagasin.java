package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMagasin extends Remote{
	
	public String getInfo() throws RemoteException;
	
	public String connexionClient() throws RemoteException;
	
	public String getProduits() throws RemoteException;
	
	public String ajouterProduitPanier() throws RemoteException;
	
	public String supprimerProduitPanier() throws RemoteException;
	
	public String majQteProduitPanier() throws RemoteException;
	
	public String validerUnPanier() throws RemoteException;
	
	
}
