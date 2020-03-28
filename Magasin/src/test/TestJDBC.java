package test;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ClientDao;
import dao.MagasinDao;
import dao.PanierDao;
import implement.Client;
import implement.Magasin;
import implement.Panier;
import implement.Produit;
import include.MysqlDbConnection;
import interfaces.IBanque;

/**
 * Une classe qui permet d'effectuer des tests sur la bdd
 *
 */
public class TestJDBC {
	public static void main(String[] args) throws SQLException, RemoteException {
		ArrayList<Produit> listeP = new ArrayList<Produit>();
		Panier panier = new Panier(1);
		PanierDao pDao = new PanierDao();
		Produit produit = new Produit();
		MagasinDao magasinDao = new MagasinDao();
		ClientDao clientdao = new ClientDao();
		
		pDao.viderPanier(panier.getIdPanier());
		
		
		/**
		 * METHODE DE CONNEXION 
		 * @return UN BOOLEAN POUR LA SOLVABILITE
		 * VALIDE
		 */
//		try {
//			Remote r = Naming.lookup("rmi://192.168.0.17/banque");
//			System.out.println(r);
//			boolean solva = ((IBanque)r).verifierSolvabilite("GEO123GEO", 240.00);
//			System.out.println(solva);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		/**
		 * METHODE DE CONNEXION 
		 * @return : UN CLIENT
		 * VALIDE
		 */
//		Client c = m.connexionClient("stephane@gmail.com", "stephane");
//		System.out.println(c.getAge());
		
		
		/**
		 * METHODE QUI RECUPERE LES PRODUITS
		 * @return : LA LISTE DES PRODUITS DU MAGASIN
		 * VALIDE
		 */
//		listeP = magasinDao.getProduits();
//		for (Produit p : listeP)
//		{
//			System.out.println(p.toString());
//		} 
//		panier.setListeDeProduit(listeP);
//		panier.ajouterProduit(panier, produit);
		
		/**
		 * @return LA LISTE DE PRODUIT D'UN PANIER
		 * VALIDE
		 */
//		listeP = pDao.read(panier);
//		for (Produit p : listeP)
//		{
//			System.out.println(p.toString());
//		} 
		
		/**
		 * METHODE QUI AJOUTE UN PRODUIT A UN PANIER
		 * @return BOOLEAN
		 * VALIDE
		 */
//		produit.setId(2);
//		pDao.create(panier, produit);
		
		/**
		 * METHODE QUI SUPPRIME UN PRODUIT A UN PANIER
		 * @return BOOLEAN
		 * VALIDE
		 */
//		produit.setId(2);
//		pDao.delete(panier, produit);
		
		/**
		 * METHODE QUI CALCUL LE MONTANT D'UN PANIER
		 * @return LE MONTANT
		 * VALIDE
		 */
//		double montant = pDao.montantPanier(listeP);
//		System.out.println("le montant du panier est : "+montant);
		
		/**
		 * METHODE QUI VERIFIE SI UN CLIENT EST SOLVABLE
		 * @return BOOLEAN
		 */
//		banque.verifierSolvabilite("ETI123ETI", montant);
	}
}
