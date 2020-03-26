package application;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.IClient;
import interfaces.IMagasin;
import interfaces.IPanier;
import interfaces.IProduit;

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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Fin du client");
	}
	
//	/**
//	 * Permet d'effectuer des test
//	 */
//	public static void testGetListe() {
//		System.out.println( "Lancement du client!" );
//		try {
//			Remote r = Naming.lookup("rmi://192.168.0.17/magasin");
//			ArrayList<IProduit> listeProduit = new ArrayList<IProduit>();
//			listeProduit = ((IMagasin)r).getListeProduit();
//			for (IProduit produit : listeProduit)
//			{
//				System.out.println(produit.getCouleur());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		System.out.println("Fin du client");
//	}
	
//	/**
//	 * Permet d'effectuer des test
//	 */
//	public static void testClient() {
//		System.out.println( "Lancement du client!" );
//		try {
//			Remote r = Naming.lookup("rmi://192.168.0.17/client");
//			IClient iclient = ((IClient)r).connexionClient("geo@gmail.com", "geo");
//			System.out.println(iclient.getId());
//			IPanier ipanier = iclient.recuperePanier(iclient.getId());
//			System.out.println(ipanier.getIdPanier());
//			ArrayList<IProduit> listeProduit = new ArrayList<IProduit>();
//			listeProduit = ipanier.getListeDeProduit();
//			for(IProduit produit : listeProduit)
//			{
//				System.out.println(produit.getNom());
//			}
//			ArrayList<IProduit> listeProduit = new ArrayList<IProduit>();
//			listeProduit = ((IMagasin)r).getListeProduit();
//			for (IProduit produit : listeProduit)
//			{
//				System.out.println(produit.getCouleur());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		System.out.println("Fin du client");
//	}
}