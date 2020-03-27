package implement;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import interfaces.IBanque;

public class Banque extends UnicastRemoteObject implements IBanque{

	public Banque() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verifierSolvabilite(String identifiants, double montant) throws RemoteException, SQLException {
		boolean solva = false;
		// TODO Auto-generated method stub
		try {
			Remote r = Naming.lookup("rmi://192.168.0.17/banque");
			System.out.println(r);
			solva = ((IBanque)r).verifierSolvabilite(identifiants, montant);
			System.out.println(solva);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return solva;
	}
}
