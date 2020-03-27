package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import implement.Produit;

public interface IPanier extends Remote{

	/**
	 * Ajoute un produit ou modifie la quantité d'un produit dans un panier
	 * @param idProduit
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public void ajouterProduit(int idProduit) throws RemoteException, SQLException;
	
	/**
	 * Retire un produit ou modifie la quantité d'un produit dans un panier
	 * @param idProduit
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public void retirerProduit(int idProduit) throws RemoteException, SQLException;
	
	/**
	 * Calcul le montant d'un panier
	 * @return
	 * @throws RemoteException
	 */
	public double calculerMontantPanier() throws RemoteException;
	
	public ArrayList<Integer> getQuantiteProduit() throws RemoteException;

	public ArrayList<Produit> getListeDeProduit() throws RemoteException;
	
	public int getIdPanier() throws RemoteException;
	
}
