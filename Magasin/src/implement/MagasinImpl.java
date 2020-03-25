package implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import include.MysqlDbConnection;
import interfaces.IMagasin;
import modele.Client;
import modele.Produit;

public class MagasinImpl extends UnicastRemoteObject implements IMagasin{
	
	private static final long serialVersionUID = 2674880711467464646L;
	
	public MagasinImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getInfo() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Invocation de la méthode getInformation()");
		return "bonjour";
	}

	@Override
	public ArrayList<Produit> getProduits() throws RemoteException, SQLException {
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

	/**
	 * Methode qui ajoute un produit dans un panier
	 * @return : panier
	 */
	@Override
	public String ajouterProduitPanier() throws RemoteException {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client connexionClient(String mail, String mdp) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<Object> listeChamps = new ArrayList<Object>();
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "Select * from client where mail = '"+mail+"' and mdp = '"+mdp+"'";
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.next();
		Client cli = new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("rue"), rs.getInt("cp"), rs.getInt("numRue")
				, rs.getInt("age"), rs.getString("mail"), rs.getString("mdp"));		
		
		rs.close();
		stmt.close();
		c.close();
		return cli;
	}

	@Override
	public String validerUnPanier() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String supprimerProduitPanier() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String majQteProduitPanier() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String validerUneCommande() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
