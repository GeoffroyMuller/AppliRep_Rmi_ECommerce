package application;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.IBanque;
import interfaces.IClient;
import interfaces.IMagasin;
import interfaces.IPanier;
import interfaces.IProduit;

/**
 * Client Singleton
 * @author Geoff-Portable
 */
public class ClientApp {

	private static ClientApp clientApp;
	
	private static IClient sessionClient;
	
	private static ArrayList<IMagasin> listeMagasins;

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
		sessionClient = obj.connexionClient("geo@gmail.com", "geo");
	}
	
	public static IMagasin dernierMagasin() {
		ArrayList<IMagasin> listeMagasins = getListeMagasins();
		return listeMagasins.get(listeMagasins.size()-1);
	}

	public static IClient getSessionClient() {
		return sessionClient;
	}
	
	public static IPanier getPanier() throws RemoteException, SQLException {
		return sessionClient.recuperePanier();
	}

	public static ArrayList<IMagasin> getListeMagasins() {
		return listeMagasins;
	}
	
	
	
	/**
	 * Permet d'effectuer des test
	 */
	public static void testClient() {
		System.out.println( "Lancement du client!" );
		try {
			Remote r = Naming.lookup("rmi://192.168.0.17:1098/client");
			IClient ibanque = ((IClient)r).connexionClient("root", "root");
			IPanier ipanier = ibanque.recuperePanier();
			System.out.println(ipanier.calculerMontantPanier());
			ipanier.ajouterProduit(2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Fin du client");
	}
}