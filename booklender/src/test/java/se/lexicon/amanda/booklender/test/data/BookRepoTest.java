package se.lexicon.amanda.booklender.test.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import se.lexicon.amanda.booklender.data.BookRepo;
import se.lexicon.amanda.booklender.models.Book;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class BookRepoTest {
	
	@Autowired
	private BookRepo testObject;
	private Book testBook;
	private int bookId;

	@Before
	public void setup() {
		testBook = new Book("Harry Potter", 30, BigDecimal.valueOf(10), "Cool stuff");
		testBook = testObject.save(testBook);
		bookId = testBook.getBookId();
	}
	
	
	@Test
	public void test_save_book() {
		
		assertNotNull(testBook);
		assertTrue(testBook.getBookId() !=0);
		assertEquals("Harry Potter", testBook.getTitle());
	}
	
	
	@Test
	public void test_findAll() {
		assertNotNull(testObject.findAll());
	}
	
	
	@Test
	public void test_findById_bookId() {
		Optional<Book> result = testObject.findByBookId(bookId);
		assertTrue(result.isPresent());
		assertEquals(bookId, result.get().getBookId());
}
	
	
	@Test
	public void test_delete_by_bookId() {
		assertTrue(testObject.findByBookId(bookId).isPresent());
		testObject.deleteById(bookId);
		assertFalse(testObject.findByBookId(bookId).isPresent());
	}
	
	@Test
	public void test_findByReserved() {
		testBook.setReserved(true);
		List<Book> result = testObject.findByReserved(true);
		int expectedSize = 1;
		assertEquals(result.size(), expectedSize);
		assertTrue(result.contains(testBook)); 
	}
		
	@Test
	public void test_findByReserved_false() {
		testBook.setReserved(false);
		List<Book> result = testObject.findByReserved(true);
		int expectedSize = 0;
		assertEquals(result.size(), expectedSize);
		assertFalse(result.contains(testBook));
	}
	
	@Test
	public void test_findByAvailable() {
		testBook.setAvailable(true);
		List<Book> result = testObject.findByAvailable(true);
		int expectedSize = 1;
		assertEquals(result.size(), expectedSize);
		assertTrue(result.contains(testBook));
	}
	
	@Test
	public void test_findByAvailable_false() {
		testBook.setAvailable(false);
		List<Book> result = testObject.findByAvailable(true);
		int expectedSize = 0;
		assertEquals(result.size(), expectedSize);
		assertFalse(result.contains(testBook));
	}
	
	@Test
	public void test_findByTitle() {
		List<Book> result = testObject.findByTitle("Harry Potter");
		int expectedSize = 1;
		assertEquals(result.size(), expectedSize);
		assertTrue(result.contains(testBook));
	}
	
	}
