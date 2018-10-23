package object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Client {
	private Long id;
	private String lastname;
	private String firstname;
	private Gender gender;
	private Livre livreprefere;

	public enum Gender {
		M, F
	}

	public Client(String lastname, String firstname, Gender gender) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
	}

	public Client(Long id, String lastname, String firstname, Gender gender) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
	}

	public Client(Long id, String lastname, String firstname, Gender gender, Livre livreprefere) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
		this.livreprefere = livreprefere;
	}

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

	@Override
	public String toString() {
		return lastname + "," + firstname + "," + gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Livre getLivreprefere() {
		return livreprefere;
	}

	public void setLivreprefere(Livre livreprefere) {
		this.livreprefere = livreprefere;
	}

}
