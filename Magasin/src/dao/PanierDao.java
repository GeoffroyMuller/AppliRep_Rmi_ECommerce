package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import implement.Panier;
import implement.Produit;
import include.MysqlDbConnection;

public class PanierDao {
	
	/**
	 * Ajoute un produit dans un panier
	 * @param idPanier
	 * @param idProduit
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public void ajouterProduit(int idPanier, int idProduit) throws SQLException, RemoteException {
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "insert into constituer (`idPanier`, `idProduit`, `quantiteProduit`) values ("+idPanier+", "+idProduit+", "+1+")";
		stmt = c.createStatement();
		stmt.executeUpdate(sql);	
		stmt.close();
		c.close();
	}
	
	/**
	 * Modifie la quantité d'un produit dans un panier
	 * @param idPanier
	 * @param idProduit
	 * @param quantite
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public void ajouterQuantite(int idPanier, int idProduit, int quantite) throws SQLException, RemoteException {
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		quantite += 1;
		String sql = "update constituer set quantiteProduit = "+quantite+" where constituer.idPanier = "+idPanier+" and constituer.idProduit = "+idProduit;
		stmt = c.createStatement();
		stmt.executeUpdate(sql);	
		stmt.close();
		c.close();
	}

	public void update(Panier panier) {
		
	}

	/**
	 * Retire un produit d'un panier
	 * @param idPanier
	 * @param idProduit
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public void retirerProduit(int idPanier, int idProduit) throws SQLException, RemoteException {
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "delete from constituer where constituer.idPanier = "+idPanier+" and constituer.idProduit = "+idProduit;
		stmt = c.createStatement();
		stmt.executeUpdate(sql);	
		stmt.close();
		c.close();
	}
	
	/**
	 * Retire toutes les quantités d'un produit
	 * @param idPanier
	 * @param idProduit
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public void retirerToutesQuantites(int idPanier, int idProduit) throws SQLException, RemoteException {
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "delete from constituer where constituer.idPanier = "+idPanier+" and constituer.idProduit = "+idProduit;
		stmt = c.createStatement();
		stmt.executeUpdate(sql);	
		stmt.close();
		c.close();
	}
	
	/**
	 * Modifie la quantité d'un produit dans un panier
	 * @param idPanier
	 * @param idProduit
	 * @param quantite
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public void retirerQuantite(int idPanier, int idProduit, int quantite) throws SQLException, RemoteException {
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		quantite -= 1;
		String sql = "update constituer set quantiteProduit = "+quantite+" where constituer.idPanier = "+idPanier+" and constituer.idProduit = "+idProduit;
		stmt = c.createStatement();
		stmt.executeUpdate(sql);	
		stmt.close();
		c.close();
	}

	/**
	 * Récupère la liste de produits d'un panier
	 * @param idPanier
	 * @return
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public ArrayList<Produit> recupererLesProduits(int idPanier) throws SQLException, RemoteException {
		List<Integer> listeProduitsPanier = new ArrayList<Integer>();
		List<Produit> listeProduits = new ArrayList<Produit>();
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "Select idProduit from constituer where idPanier = "+idPanier;
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
	
	/**
	 * Récupère les quantités de chaque produit dans le panier
	 * @param idPanier
	 * @return
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public ArrayList<Integer> recupererLesQuantite(int idPanier) throws SQLException, RemoteException {
		List<Integer> listeQuantitePanier = new ArrayList<Integer>();
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "Select quantiteProduit from constituer where idPanier = "+idPanier;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			listeQuantitePanier.add(rs.getInt("quantiteProduit"));
			
		}
		rs.close();
		stmt.close();
		c.close();
		return (ArrayList<Integer>) listeQuantitePanier;
	}
	
}
