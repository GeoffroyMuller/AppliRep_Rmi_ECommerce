package include;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Erreur pendant le chargement du pilote");
		}
		Connection cnxDirect = null;
		try {
			cnxDirect=DriverManager.getConnection("jdbc:mysql://localhost:3306/lpglprojetrmi?serverTimezone=UTC","root","");
			System.out.println(cnxDirect);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur pendant la connexion");
		}
		testLectureSeul(cnxDirect);
	}
	
	public static void testLectureSeul(Connection cnx) 
	{
		boolean etat;
		try {
			etat = cnx.isReadOnly();
			if(cnx.isReadOnly() != etat)
			{
				System.out.println("le mode lecture seule est pris en charge par ce pilote");
			}
			else
			{
				System.out.println("le mode lecture seule n\'est pas pris en charge par ce pilote");
			}
			cnx.setReadOnly(etat);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
