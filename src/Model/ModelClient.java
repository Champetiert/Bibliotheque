package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import object.Client;
import object.ConnexionBDD;
import object.Client.Gender;

public class ModelClient {

	
	public static void save(Client client) {
		Connection conn = ConnexionBDD.connectionBDD();
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Client(lastname,firstname,gender)Values(?,?,?);")) {
			stmt.setString(1, client.getLastname());
			stmt.setString(2, client.getFirstname());
			stmt.setString(3, client.getGender().name());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ConnexionBDD.closeConnBdd(conn);
	}
	
	public static ArrayList<Client> readAll() {
		ArrayList<Client> listclient = new ArrayList<Client>();
		Connection conn = ConnexionBDD.connectionBDD();

		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM Client");
			while (resultSet.next()) {
				listclient.add(new Client(resultSet.getLong("id"), resultSet.getString("lastname"),
						resultSet.getString("firstname"), Gender.valueOf(resultSet.getString("gender"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnexionBDD.closeConnBdd(conn);
		return listclient;
	}

	public static void delete(int id) {
		Connection conn = ConnexionBDD.connectionBDD();
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Client WHERE id=?")) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnexionBDD.closeConnBdd(conn);
	}

	public static void update(int id, Client client) {
		Connection conn = ConnexionBDD.connectionBDD();
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE Client SET lastname=?,firstname=?,gender=?;")) {
			stmt.setString(1, client.getLastname());
			stmt.setString(2, client.getFirstname());
			stmt.setString(3, client.getGender().name());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ConnexionBDD.closeConnBdd(conn);
	}
}
