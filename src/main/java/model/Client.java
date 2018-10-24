package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Client {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String firstname;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@ManyToMany
	private List<Book> listAchatLivre;
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", firstname=" + firstname + ", gender=" + gender + "]\n";
	}

	@ManyToOne
	private Book bestBook;

	public List<Book> getListAchatLivre() {
		return listAchatLivre;
	}

	public void setListAchatLivre(List<Book> listAchatLivre) {
		this.listAchatLivre = listAchatLivre;
	}

	public Book getBestBook() {
		return bestBook;
	}

	public void setBestBook(Book bestBook) {
		this.bestBook = bestBook;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	

}
