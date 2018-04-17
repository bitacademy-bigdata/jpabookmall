package example.com.jpabookmall;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import example.com.domain.Book;

public class App {
	public static void main(String[] args) {

		// 1. Entity manager factory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabookmall");

		// 2. Entity Manager 생성
		EntityManager em = emf.createEntityManager();

		// 3. Get TX
		EntityTransaction tx = em.getTransaction();

		// 4. TX Begins
		tx.begin();

		// 5. Business
		try {
			// insertTest( em );
			// insertAndUpdateTest( em );
			// findOneTest( em );
			// findListTest( em );
			deleteTest( em );
		} catch ( Exception e ) {
			tx.rollback();
		}

		// 6. TX Commit
		tx.commit();

		// 7. Entity Manager 종료
		em.close();

		// 8. Entity Manager Factory 종료
		emf.close();
	}
	
	public static void insertTest(EntityManager em){
		Book book = new Book();
		book.setTitle( "god of java" );
		book.setPrice( 20000 );
		
		em.persist(book);
	}
	
	public static void insertAndUpdateTest(EntityManager em) {
		Book book = new Book();
		book.setTitle( "곰브리치 세계사" );
		book.setPrice( 4000 );
		
		em.persist(book);
		
		book.setPrice( 40000 );
	}
	
	public static void findOneTest( EntityManager em ) {
		Book book = em.find( Book.class, 7L );
		System.out.println( book );
		book.setPrice(0);
	}
	
	public static void findListTest( EntityManager em ) {
		TypedQuery<Book> query = em.createQuery( "select a from Book a where a.price = 1000", Book.class );
		List<Book> list = query.getResultList();
		
		for( Book book : list ) {
			System.out.println( book );
		}
	}
	
	public static void deleteTest( EntityManager em ) {
		Book book = em.find( Book.class, 7L );
		em.remove( book );
	}
}