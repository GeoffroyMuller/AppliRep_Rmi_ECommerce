package implement;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import interfaces.IBanque;

public class Banque extends UnicastRemoteObject implements IBanque{
	
	private static final long serialVersionUID = 1L;

	public Banque() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Utilise le serveur Banque pour vérifier la solvabilité d'un client
	 * @return : Un boolean
	 */
	@Override
	public boolean verifierSolvabilite(String identifiants, double montant) throws RemoteException, SQLException {
		boolean solvabiliteClient = false;
		// TODO Auto-generated method stub
		try {
			System.out.println("Appel au serveur Banque");
			Remote r = Naming.lookup("rmi://192.168.0.17/banque");
			solvabiliteClient = ((IBanque)r).verifierSolvabilite(identifiants, montant);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return solvabiliteClient;
	}
}
