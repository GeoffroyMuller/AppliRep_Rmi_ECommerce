package application;
	
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

import implement.Banque;

public class Main{
	private static int PORT = 1099;
	private static String IP = "127.0.0.1";
	public static void main(String[] args) throws SQLException {
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Lancement du serveur de la banque");
			Banque banque = new Banque();
			String urlBanque = "rmi://"+InetAddress.getLocalHost().getHostAddress()+":"+PORT+"/banque";
			System.out.println(urlBanque);
			Naming.rebind(urlBanque, banque);
			System.out.println("Serveur lancé");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
