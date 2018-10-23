package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.ModelLivre;
import object.Client;
import object.Client.Gender;
import object.ConnexionBDD;
import object.Livre;

public class main {

	public static void main(String[] args) {
		
		System.out.println(LocalDate.now());
		
		//ConnexionBDD.initialize();
		Client client1=new Client("Gallois", "stephane", Gender.M);
		Livre b1=new Livre("Le chat", "chat");
		//ModelLivre.delete(1);
		System.out.println(ModelLivre.readAll());
		/*Client.save(client1);
		System.out.println(Client.readAll());
		Client.update(1, client1);
		Client.delete(2);*/


	}

}
