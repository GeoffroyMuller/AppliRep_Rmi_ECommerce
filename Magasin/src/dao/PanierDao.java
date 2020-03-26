package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import implement.Client;
import implement.Commande;
import implement.Panier;
import implement.Produit;
import include.MysqlDbConnection;

public class PanierDao {
	public Panier ajouterProduit(Panier panier, Produit produit) throws SQLException, RemoteException {
		panier.ajouterUnProduitListe(panier, produit);
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "insert into constituer (`idPanier`, `idProduit`) values ("+panier.getIdPanier()+", "+produit.getId()+")";
		stmt = c.createStatement();
		int rs = stmt.executeUpdate(sql);	
		stmt.close();
		c.close();
		return panier;
	}

	public void update(Panier panier) {
		
	}

	public Panier retirerProduit(Panier panier, Produit produit) throws SQLException, RemoteException {
		panier.retirerUnProduitListe(panier, produit);
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "delete from constituer where constituer.idPanier = "+panier.getIdPanier()+" and constituer.idProduit = "+produit.getId();
		stmt = c.createStatement();
		int rs = stmt.executeUpdate(sql);	
		stmt.close();
		c.close();
		System.out.println("OK");
		return panier;
	}

	public ArrayList<Produit> recupererLesProduits(Panier panier) throws SQLException, RemoteException {
		List<Integer> listeProduitsPanier = new ArrayList<Integer>();
		List<Produit> listeProduits = new ArrayList<Produit>();
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "Select idProduit from constituer where idPanier = "+panier.getIdPanier();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			listeProduitsPanier.add(rs.getInt("idProduit"));
			
		}
		
		for (int i=0; i<listeProduitsPanier.size(); i++)
		{
			String sqlProduits = "Select * from produits where idProduits = "+listeProduitsPanier.get(i);
			stmt = c.createStatement();
			ResultSet rsProduits = stmt.executeQuery(sqlProduits);
			while (rsProduits.next())
			{
				listeProduits.add(new Produit(rsProduits.getInt("idProduits"), rsProduits.getString("nomProduit"), rsProduits.getInt("unitStock"), rsProduits.getDouble("prixUnit"), 
						rsProduits.getInt("qteParUnit"), rsProduits.getInt("taille"), rsProduits.getDouble("poids"), rsProduits.getString("couleur"), rsProduits.getInt("produitDispo"), 
						rsProduits.getDouble("remise"), rsProduits.getString("produitDesc"), rsProduits.getInt("note")));
			}
			rsProduits.close();
		}
		rs.close();
		
		stmt.close();
		c.close();
		return (ArrayList<Produit>) listeProduits;
	}
	
	public double montantPanier(ArrayList<Produit> listeProduits) throws RemoteException
	{
		double montant = 0;
		for (Produit produit : listeProduits)
		{
			montant += produit.getPrixUnit();
		}
		return montant;
	}
}
