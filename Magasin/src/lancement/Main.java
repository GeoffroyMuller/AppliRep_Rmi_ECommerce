package lancement;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import implement.Magasin;
import implement.Produit;
import include.MysqlDbConnection;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
//		
		System.out.println("yo");
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Hello world");
			
			Magasin info = new Magasin();
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
