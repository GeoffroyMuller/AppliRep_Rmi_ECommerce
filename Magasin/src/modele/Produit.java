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
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrixUnit() {
		return prixUnit;
	}

	public void setPrixUnit(double prixUnit) {
		this.prixUnit = prixUnit;
	}

	public int getQteUnit() {
		return qteUnit;
	}

	public void setQteUnit(int qteUnit) {
		this.qteUnit = qteUnit;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getProduitDispo() {
		return produitDispo;
	}

	public void setProduitDispo(int produitDispo) {
		this.produitDispo = produitDispo;
	}

	public double getRemise() {
		return remise;
	}

	public void setRemise(double remise) {
		this.remise = remise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}
}
