package implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import interfaces.IProduit;

public class Produit extends UnicastRemoteObject implements IProduit{

	private static final long serialVersionUID = 1L;
	
	int id;
	String nom;
	int stock;
	double prixUnit;
	int qteUnit;
	int taille;
	double poids;
	String couleur;
	int produitDispo;
	double remise;
	String description;
	int note;
	
	public Produit() throws RemoteException
	{
		
	}

	public Produit(int id, String nom, int stock, double prixUnit, int qteUnit, int taille, double poids,
			String couleur, int produitDispo, double remise, String description, int note) throws RemoteException {
		super();
		this.id = id;
		this.nom = nom;
		this.stock = stock;
		this.prixUnit = prixUnit;
		this.qteUnit = qteUnit;
		this.taille = taille;
		this.poids = poids;
		this.couleur = couleur;
		this.produitDispo = produitDispo;
		this.remise = remise;
		this.description = description;
		this.note = note;
	}

	public String toString()
	{
		return nom;
		
	}
	
	public int getId() throws RemoteException{
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() throws RemoteException{
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getStock() throws RemoteException{
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrixUnit() throws RemoteException{
		return prixUnit;
	}

	public void setPrixUnit(double prixUnit) {
		this.prixUnit = prixUnit;
	}

	public int getQteUnit() throws RemoteException{
		return qteUnit;
	}

	public void setQteUnit(int qteUnit) {
		this.qteUnit = qteUnit;
	}

	public int getTaille() throws RemoteException{
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public double getPoids() throws RemoteException{
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public String getCouleur() throws RemoteException{
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getProduitDispo() throws RemoteException{
		return produitDispo;
	}

	public void setProduitDispo(int produitDispo) {
		this.produitDispo = produitDispo;
	}

	public double getRemise() throws RemoteException{
		return remise;
	}

	public void setRemise(double remise) {
		this.remise = remise;
	}

	public String getDescription() throws RemoteException{
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNote() throws RemoteException{
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}


}
