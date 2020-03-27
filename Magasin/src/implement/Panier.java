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
	
	ArrayList<Integer> listeQuantite = new ArrayList<Integer>();
	
	ArrayList<Produit> listeDeProduit = new ArrayList<Produit>();
	
	private PanierDao panierDao = new PanierDao(); 
	
	public Panier() throws RemoteException
	{
	}		
	
	public Panier(int idPanier) throws RemoteException, SQLException
	{
		this.idPanier = idPanier;
		this.listeDeProduit = panierDao.recupererLesProduits(this.getIdPanier());
		this.listeQuantite = panierDao.recupererLesQuantite(this.getIdPanier());
		this.montantPanier = this.calculerMontantPanier();
	}	
	
	public void ajouterProduit(int idPanier, int idProduit) throws RemoteException, SQLException
	{
		int idProduitExistant = 0;
		int quantite = 0;
		int i = 0;
		for (Produit produit : this.listeDeProduit)
		{
			if (produit.getId() == idProduit)
			{
				idProduitExistant = produit.getId();
				quantite = listeQuantite.get(i);
			}
			i++;
		}
		if (idProduitExistant == idProduit)
		{
			panierDao.ajouterQuantite(idPanier, idProduit, quantite);
			this.listeDeProduit = panierDao.recupererLesProduits(this.getIdPanier());
			this.listeQuantite = panierDao.recupererLesQuantite(this.getIdPanier());
		}
		else
		{
			panierDao.ajouterProduit(idPanier, idProduit);
			this.listeDeProduit = panierDao.recupererLesProduits(this.getIdPanier());
			this.listeQuantite = panierDao.recupererLesQuantite(this.getIdPanier());
		}
	}
	
	public void retirerProduit(int idPanier, int idProduit) throws RemoteException, SQLException
	{
		int quantite = 0;
		int i = 0;
		for (Produit produit : this.listeDeProduit)
		{
			if (produit.getId() == idProduit)
			{
				quantite = listeQuantite.get(i);
			}
			i++;
		}
		if (quantite > 1)
		{
			panierDao.retirerQuantite(idPanier, idProduit, quantite);
			this.listeDeProduit = panierDao.recupererLesProduits(this.getIdPanier());
			this.listeQuantite = panierDao.recupererLesQuantite(this.getIdPanier());
		}
		else
		{
			panierDao.retirerProduit(idPanier, idProduit);
			this.listeDeProduit = panierDao.recupererLesProduits(this.getIdPanier());
			this.listeQuantite = panierDao.recupererLesQuantite(this.getIdPanier());
		}
	}
	
//	public Panier ajouterUnProduitListe(Panier panier, Produit produit) 
//	{
//		listeDeProduit.add(produit);
//		this.montantPanier += produit.prixUnit;
//		return this;
//	}
//	
//	public Panier retirerUnProduitListe(Panier panier, Produit produit)
//	{
//		listeDeProduit.remove(produit);
//		this.montantPanier -= produit.prixUnit;
//		return this;
//	}
	
	public double calculerMontantPanier() throws RemoteException
	{
		double montantPanier = 0;
		int i = 0;
		for(Produit produit : this.listeDeProduit)
		{
			montantPanier += produit.getPrixUnit()*this.listeQuantite.get(i);
			i++;
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