package implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PanierDao;
import interfaces.IPanier;

public class Panier extends UnicastRemoteObject implements IPanier {

	private static final long serialVersionUID = 1L;
	
	private int idPanier;
	
	double montantPanier;
	
	ArrayList<Produit> listeDeProduit = new ArrayList<Produit>();
	
	private PanierDao panierDao = new PanierDao(); 
	
	public Panier() throws RemoteException
	{
	}		
	
	public Panier(int idPanier) throws RemoteException, SQLException
	{
		this.idPanier = idPanier;
		this.listeDeProduit = panierDao.recupererLesProduits(this.getIdPanier());
		this.montantPanier = this.calculerMontantPanier();
	}	
	
	public void ajouterProduit(int idPanier, int idProduit) throws RemoteException, SQLException
	{
		panierDao.ajouterProduit(idPanier, idProduit);
	}
	
	public void retirerProduit(int idPanier, int idProduit) throws RemoteException, SQLException
	{
		panierDao.retirerProduit(idPanier, idProduit);
	}
	
	public Panier ajouterUnProduitListe(Panier panier, Produit produit) 
	{
		listeDeProduit.add(produit);
		this.montantPanier += produit.prixUnit;
		return this;
	}
	
	public Panier retirerUnProduitListe(Panier panier, Produit produit)
	{
		listeDeProduit.remove(produit);
		this.montantPanier -= produit.prixUnit;
		return this;
	}
	
	public double calculerMontantPanier() throws RemoteException
	{
		double montantPanier = 0;
		for(Produit produit : this.listeDeProduit)
		{
			montantPanier += produit.getPrixUnit();
		}
		return montantPanier;
	}

	public int getIdPanier() throws RemoteException {
		return idPanier;
	}
	
	public double getMontantPanier() {
		return montantPanier;
	}

	public void setMontantPanier(double montantPanier) {
		this.montantPanier = montantPanier;
	}

	public ArrayList<Produit> getListeDeProduit() throws RemoteException{
		return listeDeProduit;
	}

	public void setListeDeProduit(ArrayList<Produit> listeDeProduit) {
		this.listeDeProduit = listeDeProduit;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}
	

}
