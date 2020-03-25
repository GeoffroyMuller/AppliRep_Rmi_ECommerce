package lancement;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.PanierDao;
import implement.BanqueImpl;
import implement.MagasinImpl;
import include.MysqlDbConnection;
import modele.Client;
import modele.Panier;
import modele.Produit;

public class TestJDBC {
	public static void main(String[] args) throws SQLException, RemoteException {
		ArrayList<Produit> listeP = new ArrayList<Produit>();
		MagasinImpl m = new MagasinImpl();
		Panier panier = new Panier(1);
		PanierDao pDao = new PanierDao();
		Produit produit = new Produit();
		BanqueImpl banque = new BanqueImpl();
		
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
//		listeP = m.getProduits();
//		for (Produit p : listeP)
//		{
//			System.out.println(p.toString());
//		} 
		
		/**
		 * @return LA LISTE DE PRODUIT D'UN PANIER
		 * VALIDE
		 */
		listeP = pDao.read(panier);
		for (Produit p : listeP)
		{
			System.out.println(p.toString());
		} 
		
//		/**
//		 * METHODE QUI AJOUTE UN PRODUIT A UN PANIER
//		 * @return BOOLEAN
//		 * VALIDE
//		 */
//		produit.setId(2);
//		pDao.create(panier, produit);
		
//		/**
//		 * METHODE QUI SUPPRIME UN PRODUIT A UN PANIER
//		 * @return BOOLEAN
//		 * VALIDE
//		 */
//		produit.setId(2);
//		pDao.delete(panier, produit);
		
//		/**
//		 * METHODE QUI CALCUL LE MONTANT D'UN PANIER
//		 * @return LE MONTANT
//		 * VALIDE
//		 */
		double montant = pDao.montantPanier(listeP);
		System.out.println("le montant du panier est : "+montant);
		
		/**
		 * METHODE QUI VERIFIE SI UN CLIENT EST SOLVABLE
		 * @return BOOLEAN
		 */
		banque.verifierSolvabilite("ETI123ETI", montant);
	}
}
