package lancement;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import implement.MagasinImpl;
import include.MysqlDbConnection;
import modele.Client;
import modele.Produit;

public class TestJDBC {
	public static void main(String[] args) throws SQLException, RemoteException {
		ArrayList<Produit> listeP = new ArrayList<Produit>();
		MagasinImpl m = new MagasinImpl();
		
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
		
			//		listeP = MagasinImpl.get
//		Connection c = MysqlDbConnection.getConnection();
//		Statement stmt = null;
//		String sql;
//		sql = "Select * from client";
//		stmt = c.createStatement();
//		ResultSet rs = stmt.executeQuery(sql);
//		while (rs.next())
//		{
//			int id = rs.getInt("id");
//			System.out.println("l'id de mon client est : "+id);
//		}
//		rs.close();
//		stmt.close();
//		c.close();
	}
}
