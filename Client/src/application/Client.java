package application;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import interfaces.IMagasin;
import interfaces.IProduit;

/**
 * Client Singleton
 * @author Geoff-Portable
 */
public class Client {

	private static Client client;
	
	private static ArrayList<IMagasin> listeMagasins;

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
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public static void connection(String ip, String port) 
			throws MalformedURLException, RemoteException, NotBoundException {
		IMagasin obj;
		obj = (IMagasin)Naming.lookup("rmi://"+ip+":"+port+"/magasin");
		listeMagasins = new ArrayList<IMagasin>();
		listeMagasins.add(obj);
	}
	
	public static IMagasin dernierMagasin() {
		ArrayList<IMagasin> listeMagasins = getListeMagasins();
		return listeMagasins.get(listeMagasins.size()-1);
	}

	public static ArrayList<IMagasin> getListeMagasins() {
		return listeMagasins;
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