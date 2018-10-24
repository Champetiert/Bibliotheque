package main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Book;
import model.Client;
import model.Gender;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Book book = new Book();
		book.setTitle("UML For Java Programmers");
		book.setAuthor("Robert Cecil Martin");
		em.persist(book);
		
		Book book1 = new Book();
		book1.setTitle("La philosophie dans le boudoire");
		book1.setAuthor("Le marquis de Sade");
		em.persist(book1);
		
		Book book2 = new Book();
		book2.setTitle("La Bible");
		book2.setAuthor("Dieu");
		em.persist(book2);
		
		Book book3 = new Book();
		book3.setTitle("kamasutra");
		book3.setAuthor("Indien");
		em.persist(book3);
		
		Client client = new Client();
		client.setFirstname("Thomas");
		client.setName("Champetier");
		client.setGender(Gender.M);
		client.setBestBook(book);
		ArrayList<Book> listLivre =new ArrayList<>();
		//listLivre.add(book);
		listLivre.add(book2);
		listLivre.add(book3);
		client.setListAchatLivre(listLivre);	
		em.persist(client);
		
		ArrayList<Book> listLivre1 =new ArrayList<>();
		Client client1 = new Client();
		client1.setFirstname("Stephane");
		client1.setName("Gallois");
		client1.setGender(Gender.M);
		listLivre1 =new ArrayList<>();
		listLivre1.add(book1);
		listLivre1.add(book2);
		client1.setListAchatLivre(listLivre1);	
		em.persist(client1);
		
		ArrayList<Book> listLivre2 =new ArrayList<>();
		Client client2 = new Client();
		client2.setFirstname("Cloé");
		client2.setName("Doe");
		client2.setGender(Gender.F);
		//listLivre2.add(book);
		listLivre2.add(book3);
		client2.setListAchatLivre(listLivre2);	
		em.persist(client2);
		
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		Client findClient = em.find(Client.class, client.getId());
		System.out.println(findClient.getId() + " " + findClient.getName() + " " + findClient.getFirstname()+" "+findClient.getListAchatLivre());
		em.close();
		
		em = emf.createEntityManager();
		TypedQuery<Client> query = em.createQuery(
			       "select distinct c " +
			       "from Client c " +
			       "inner join fetch c.listAchatLivre b " +
			       "where b.id =:id ", Client.class);
			query.setParameter("id", book2.getId());
			List<Client> listcli = query.getResultList();
			for(Client  cli: listcli) {
				System.out.println(cli);
			}
		em.close();
		
		em = emf.createEntityManager();
		TypedQuery<Book> query2 = em.createQuery(
			       "select distinct b " +
			       "from Client c " +
			       "inner join c.listAchatLivre b "
			       , Book.class);
			//query.setParameter("id", book2.getId());
			List<Book> listLivre12 = query2.getResultList();
			for(Book  livre: listLivre12) {
				System.out.println(livre);
			}
		em.close();
		

	}

}
