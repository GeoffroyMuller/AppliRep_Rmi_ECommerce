package implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import dao.ClientDao;
import interfaces.IClient;

public class Client extends UnicastRemoteObject implements IClient{

	private static final long serialVersionUID = 1L;
	
	int id;
	String nom;
	String prenom;
	String rue;
	int cp;
	int numRue;
	int age;
	String mail;
	String mdp;
	int idPanier;
	
	ClientDao clientDao = new ClientDao();
	
	public Client() throws RemoteException
	{
		
	}
	
	public Client(int id, String nom, String prenom, String rue, int cp, int numRue, int age, String mail, String mdp, int idPanier) throws RemoteException
	{
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.rue = rue;
		this.cp = cp;
		this.numRue = numRue;
		this.age = age;
		this.mail = mail;
		this.mdp = mdp;
		this.idPanier = idPanier;
	}
	
	public Client connexionClient() throws RemoteException, SQLException
	{
		return clientDao.connexionClient(mail, mdp);
	}
	
	public Client connexionClient(String mail, String mdp) throws RemoteException, SQLException
	{
		return clientDao.connexionClient(mail, mdp);
	}
	
	public Panier recuperePanier() throws RemoteException, SQLException
	{
		return clientDao.recupererPanier(this.id);
	}
	
	public int getId() throws RemoteException {
		return id;
	}
	public void setId(int id) throws RemoteException {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public int getNumRue() {
		return numRue;
	}
	public void setNumRue(int numRue) {
		this.numRue = numRue;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
