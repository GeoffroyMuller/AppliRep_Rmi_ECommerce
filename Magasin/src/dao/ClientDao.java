package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import implement.Client;
import implement.Panier;
import include.MysqlDbConnection;

public class ClientDao {
	
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
	
	public Panier recupererPanier(int idClient) throws RemoteException, SQLException
	{
		ArrayList<Object> listeChamps = new ArrayList<Object>();
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "Select idPanier from client where id = "+idClient;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		Panier panier = new Panier(rs.getInt("idPanier"));		
		rs.close();
		stmt.close();
		c.close();
		return panier;
	}
	
	public void create(Client client) {
		// TODO Auto-generated method stub

	}

	public void update(Client client) {
		// TODO Auto-generated method stub

	}

	public void delete(Client client) {
		// TODO Auto-generated method stub

	}

	public Client read() {
		// TODO Auto-generated method stub
		return null;
	}
}

