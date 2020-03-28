package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IBanque extends Remote {

	/**
	 * Verifie la solvabilité d'un client
	 */
	public boolean verifierSolvabilite(String identifiants, double montant) throws RemoteException, SQLException;
	
}
