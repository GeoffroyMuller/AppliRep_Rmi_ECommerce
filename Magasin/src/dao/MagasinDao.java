package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import implement.Produit;
import include.MysqlDbConnection;

public class MagasinDao {
	/**
	 * Récupère les produits d'un magasin
	 * @return
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public ArrayList<Produit> recupererProduits() throws RemoteException, SQLException {
		ArrayList<Produit> listeProduits = new ArrayList<Produit>();
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "Select * from produits";
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			listeProduits.add(new Produit(rs.getInt("idProduits"), rs.getString("nomProduit"), rs.getInt("unitStock"), rs.getDouble("prixUnit"), 
					rs.getInt("qteParUnit"), rs.getInt("taille"), rs.getDouble("poids"), rs.getString("couleur"), rs.getInt("produitDispo"), 
					rs.getDouble("remise"), rs.getString("produitDesc"), rs.getInt("note")));
		}
		
		rs.close();
		stmt.close();
		c.close();
		return listeProduits;
	}
}
