package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import include.MysqlDbConnection;
import modele.Client;
import modele.Commande;
import modele.Panier;
import modele.Produit;

public class PanierDao {
	public void create(Panier panier, Produit produit) {
		// TODO Auto-generated method stub

	}

	public void update(Panier panier) {
		// TODO Auto-generated method stub

	}

	public void delete(Panier panier) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Produit> read(Panier panier) throws SQLException {
		// TODO Auto-generated method stub
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
}
