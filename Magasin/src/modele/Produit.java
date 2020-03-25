package modele;

public class Produit {

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
	
	public Produit()
	{
		
	}
	
	public Produit(int id, String nom, int stock, double prixUnit, int qteUnit, int taille, double poids,
			String couleur, int produitDispo, double remise, String description, int note) {
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
}
