package application;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import implement.Client;
import implement.Magasin;
import implement.Panier;
import implement.Produit;
import include.MysqlDbConnection;
import interfaces.IClient;

public class Main {
	
	private static int PORT = 1099;
	private static String IP = "127.0.0.1";

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
//		
		System.out.println("yo");
		try {
			LocateRegistry.createRegistry(PORT);
			
			System.out.println("Hello world");
			
			Magasin magasin = new Magasin();
			String url = "rmi://"+InetAddress.getLocalHost().getHostAddress()+":"+PORT+"/magasin";
			System.out.println("Enregistrement de l'objet avec l'url : "+url);
			Naming.rebind(url, magasin);
			
			Client client = new Client();
			String urlClient = "rmi://"+InetAddress.getLocalHost().getHostAddress()+":"+PORT+"/client";
			System.out.println("Enregistrement de l'objet avec l'url : "+urlClient);
			Naming.rebind(urlClient, client);
			
			System.out.println("Serveur lancé");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
