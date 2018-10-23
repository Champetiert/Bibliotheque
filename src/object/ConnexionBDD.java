package object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnexionBDD {
	private static String url = "jdbc:postgresql://localhost:5432/Bibliotheque";

	public static Connection connectionBDD() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, Login.getLog(), Login.getPsw());
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return conn;
	}

	
	public static void initialize() {
		Connection conn = ConnexionBDD.connectionBDD();
		try (Statement stmt = conn.createStatement()){
		stmt.executeUpdate("DROP TABLE IF EXISTS AchatLivre; "
				+ "DROP TABLE IF EXISTS Client ;"
				+ "DROP TABLE IF EXISTS Livre ;");
			
		stmt.executeUpdate("CREATE TABLE Livre(id SERIAL PRIMARY KEY, title VARCHAR(30),author VARCHAR(30) )");	
		stmt.executeUpdate("CREATE TABLE Client(id SERIAL PRIMARY KEY,"
				+ "lastname VARCHAR(30),"
				+ "firstname VARCHAR(30),"
				+ "gender VARCHAR(1),"
				+ "livrePrefere INTEGER, "
				+ "FOREIGN KEY(livrePrefere)REFERENCES Livre(id))"); 
		stmt.executeUpdate("CREATE TABLE AchatLivre(id_Client INTEGER, id_Livre INTEGER,"
		 +
		 "FOREIGN KEY(id_Livre)REFERENCES Livre(id),FOREIGN KEY(id_Client)REFERENCES Client(id) )"
		 );
		}catch (Exception e) {
			e.printStackTrace();
		}
		ConnexionBDD.closeConnBdd(conn);
	}
	
	
	public static void closeConnBdd(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}