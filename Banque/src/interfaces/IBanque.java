package interfaces;

import java.rmi.Remote;
import java.sql.SQLException;

public interface IBanque extends Remote {

	public boolean verifierSolvabilite(String identifiants, double montant) throws SQLException;
	
}
