package application;

import java.rmi.Naming;
import java.rmi.Remote;

import interfaces.Information;

/**
 * Client Singleton
 * @author Geoff-Portable
 */
public class Client {

	private static Client client;

	/**
	 * Renvoie l'instance de client 
	 * @return
	 */
	public static Client getInstance() {
		if(client == null) {
			client = new Client();
		}
		return client;
	}

	/**
	 * Permet de connecter le client
	 * @param ip du serveur
	 * @param port du serveur
	 */
	public static void connection(String ip, String port) {
		System.out.println( "Lancement du client!" );
		try {
			Remote r = Naming.lookup("rmi://"+ip+":"+port+"/TestRMI");
			System.out.println(r);
			if (r instanceof Information)
			{
				String s = ((Information) r).getInfo();
				System.out.println("chaine renvoyee = "+s);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Fin du client");
	}
}