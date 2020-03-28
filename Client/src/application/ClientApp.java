package application;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import interfaces.IClient;
import interfaces.IMagasin;
import interfaces.IPanier;
import modele.Panier;

/**
 * Client Singleton
 * @author Geoff-Portable
 */
public class ClientApp {

	/**
	 * Magasin courant
	 */
	private static IMagasin magasinCourant;

	/**
	 * Magasin courante
	 */
	private static IClient sessionClientCourant;

	private static ClientApp clientApp;

	private static ArrayList<IMagasin> listeMagasins;
	
	private static HashMap<IClient, Panier> listePaniers;

	/**
	 * Renvoie l'instance de client 
	 * @return
	 */
	public static ClientApp getInstance() {
		if(clientApp == null) {
			clientApp = new ClientApp();
		}
		return clientApp;
	}

	/**
	 * Permet de connecter le clientApp au magasin
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
		magasinCourant = obj;
		listeMagasins = new ArrayList<IMagasin>();
		listeMagasins.add(obj);
	}

	/**
	 * Permet de connecter le clientApp a la sessionClient (representation du client sur le serveur)
	 * @param ip du serveur
	 * @param port du serveur
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws SQLException
	 */
	public static void connexionSessionClient(String ip, String port) 
			throws MalformedURLException, RemoteException, NotBoundException, SQLException {
		IClient obj;
		obj = (IClient)Naming.lookup("rmi://"+ip+":"+port+"/client");
		sessionClientCourant = obj.connexionClient("geo@gmail.com", "geo");
		listePaniers = new HashMap<IClient, Panier>();
		listePaniers.put( sessionClientCourant, new Panier(sessionClientCourant.recupererPanier()));
	}

	public static IMagasin getMagasinCourant() {
		return magasinCourant;
	}

	public static IClient getSessionClientCourant() {
		return sessionClientCourant;
	}

	public static ArrayList<IMagasin> getListeMagasins() {
		return listeMagasins;
	}

	public static Panier getPanierSessionClient(IClient _client) {
		return listePaniers.get(_client);
	} 
	
	public static HashMap<IClient, Panier> getListePaniers() {
		return listePaniers;
	}

	/**
	 * Permet d'effectuer des test
	 */
	public static void testClient() {
		System.out.println( "Lancement du client!" );
		try {
			Remote r = Naming.lookup("rmi://192.168.0.17:1098/client");
			IClient ibanque = ((IClient)r).connexionClient("root", "root");
			IPanier ipanier = ibanque.recupererPanier();
			System.out.println(ipanier.calculerMontantPanier());
			ipanier.ajouterProduit(2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Fin du client");
	}
}