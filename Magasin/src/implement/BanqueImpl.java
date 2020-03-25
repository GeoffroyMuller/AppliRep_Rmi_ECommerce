package implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import include.MysqlDbConnection;
import interfaces.IBanque;
import modele.Produit;

public class BanqueImpl extends UnicastRemoteObject implements IBanque{

	public BanqueImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifierSolvabilite(String identifiants, double montant) throws SQLException
	{
		double solve = 0;
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql = "Select * from client_banque where identifiantsBancaires = '"+identifiants+"'";
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			solve = rs.getDouble("solde");
		}
		
		rs.close();
		stmt.close();
		c.close();
		if (solve > montant)
		{
			System.out.println("le solde est ok");
			return true;
		}
		else
		{
			System.out.println("le solde n'est pas ok");
			return false;
		}
			
	}

}
