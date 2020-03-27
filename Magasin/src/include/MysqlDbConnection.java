package include;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbConnection {
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lpglprojetrmi?serverTimezone=UTC";
	private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	static {
		try {
			Class.forName(MYSQL_DRIVER);
		} catch (Exception e) {
			System.out.println("Impossible de se connecter");
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(JDBC_URL, "root", "");
	}
}
