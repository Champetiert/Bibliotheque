package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import object.Livre;
import object.Livre;
import object.ConnexionBDD;

public class ModelLivre {
	
	

	
	public static void save(Livre livre) {
		Connection conn = ConnexionBDD.connectionBDD();
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Livre(title,author)Values(?,?);")) {
			stmt.setString(1, livre.getTitle());
			stmt.setString(2, livre.getAuthor());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ConnexionBDD.closeConnBdd(conn);
	}
	
	public static ArrayList<Livre> readAll() {
		ArrayList<Livre> listlivre = new ArrayList<Livre>();
		Connection conn = ConnexionBDD.connectionBDD();

		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM Livre");
			while (resultSet.next()) {
				listlivre.add(new Livre(resultSet.getLong("id"), resultSet.getString("title"),
						resultSet.getString("author")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnexionBDD.closeConnBdd(conn);
		return listlivre;
	}

	public static void delete(int id) {
		Connection conn = ConnexionBDD.connectionBDD();
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Livre WHERE id=?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnexionBDD.closeConnBdd(conn);
	}

	public static void update(int id, Livre livre) {
		Connection conn = ConnexionBDD.connectionBDD();
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE Livre SET title=?,author=?;")) {
			stmt.setString(1, livre.getTitle());
			stmt.setString(2, livre.getTitle());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ConnexionBDD.closeConnBdd(conn);
	}
}
