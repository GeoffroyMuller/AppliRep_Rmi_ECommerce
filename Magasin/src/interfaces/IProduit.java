package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import implement.Produit;

public interface IProduit extends Remote{
	
	public int getId() throws RemoteException;

	public String getNom() throws RemoteException;
	
	public int getStock() throws RemoteException;
	
	public double getPrixUnit() throws RemoteException;
	
	public int getQteUnit() throws RemoteException;
	
	public int getTaille() throws RemoteException;
	
	public double getPoids() throws RemoteException;
	
	public String getCouleur() throws RemoteException;
	
	public int getProduitDispo() throws RemoteException;
	
	public double getRemise() throws RemoteException;
	
	public String getDescription() throws RemoteException;
	
	public int getNote() throws RemoteException;
}
