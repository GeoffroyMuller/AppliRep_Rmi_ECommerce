package application;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import implement.Banque;
import implement.Client;
import implement.Magasin;

public class Main {
	
	private static int PORT = 1098;
	private static String IP = "127.0.0.1";

	public static void main(String[] args) throws SQLException {
		try {
			if(args.length > 0) {
				PORT = Integer.parseInt(args[0]);
			}
			
			LocateRegistry.createRegistry(PORT);
			
			System.out.println("Lancement du serveur Magasin");
			
			Magasin magasin = new Magasin();
			String url = "rmi://"+InetAddress.getLocalHost().getHostAddress()+":"+PORT+"/magasin";
			System.out.println(InetAddress.getLocalHost().getHostAddress()+":"+PORT);
			Naming.rebind(url, magasin);
			
			Client client = new Client();
			String urlClient = "rmi://"+InetAddress.getLocalHost().getHostAddress()+":"+PORT+"/client";
			System.out.println(InetAddress.getLocalHost().getHostAddress()+":"+PORT);
			Naming.rebind(urlClient, client);
			
			Banque banque = new Banque();
			String urlBanque = "rmi://"+InetAddress.getLocalHost().getHostAddress()+":"+PORT+"/banque";
			System.out.println(InetAddress.getLocalHost().getHostAddress()+":"+PORT);
			Naming.rebind(urlBanque, banque);
			
			System.out.println("Serveur lancé");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
