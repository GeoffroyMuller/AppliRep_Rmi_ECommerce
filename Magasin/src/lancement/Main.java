package lancement;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import implement.InfoImpl;
import include.MysqlDbConnection;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection c = MysqlDbConnection.getConnection();
		Statement stmt = null;
		String sql;
		sql = "Select * from client";
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			int id = rs.getInt("id");
			System.out.println("l'id de mon client est : "+id);
		}
		rs.close();
		stmt.close();
		c.close();
		System.out.println("yo");
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Hello world");
			
			InfoImpl info = new InfoImpl();
			String url = "rmi://"+InetAddress.getLocalHost().getHostAddress()+"/test";
			System.out.println("Enregistrement de l'objet avec l'url : "+url);
			Naming.rebind(url, info);
			
			System.out.println("Serveur lancé");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
