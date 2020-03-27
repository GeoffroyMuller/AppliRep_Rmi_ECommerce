package application;
	
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

import implement.Banque;

public class Main{
	public static void main(String[] args) throws SQLException {
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Serveur de la banque");
			Banque banque = new Banque();
			String urlBanque = "rmi://"+InetAddress.getLocalHost().getHostAddress()+"/banque";
			System.out.println("Enregistrement de l'objet avec l'url : "+urlBanque);
			Naming.rebind(urlBanque, banque);
			System.out.println("Serveur lancé");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
