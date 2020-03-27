package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IBanque extends Remote{
	
	/**
	 * Permet de vérifier la solvabilité d'un client auprès d'une Banque
	 * @param identifiants
	 * @param montant
	 * @return
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public boolean verifierSolvabilite(String identifiants, double montant) throws RemoteException, SQLException;
	
}
