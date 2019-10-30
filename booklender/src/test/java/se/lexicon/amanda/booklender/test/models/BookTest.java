package se.lexicon.amanda.booklender.test.models;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import se.lexicon.amanda.booklender.models.Book;



public class BookTest {

	
	private Book testBook;
	
	
	@Before
	public void setup() {
	
		testBook = new Book(1, "Harry Potter", 31, BigDecimal.valueOf(10), "Test description");
	}
	
	@Test
	public void test_create_book_success() {
		
		int expectedMaxLoanDays = 31;
		BigDecimal expectedFinePerDay = BigDecimal.valueOf(10);
		String expectedDescription = "Test description";
		String expectedTitle = "Harry Potter";
		
		
		assertTrue(testBook.getBookId() == 1);
		assertEquals(expectedTitle, testBook.getTitle());
		assertEquals(expectedMaxLoanDays, testBook.getMaxLoanDays());
		assertEquals(expectedFinePerDay, testBook.getFinePerDay());
		assertEquals(expectedDescription, testBook.getDescription());
		assertTrue(testBook.isAvailable());
		assertFalse(testBook.isReserved());
	
	}
	
	@Test
	public void test_equals_and_hashcode_true() {
		Book copy = new Book("Harry Potter", 31, BigDecimal.valueOf(10), "Test description");

		assertTrue(copy.equals(testBook));
		assertEquals(copy.hashCode(), testBook.hashCode());
	}
	
	@Test
	public void test_toString_contains_correct_information() {
		String toString = testBook.toString();
		
		assertTrue(
				toString.contains("Harry Potter") &&
				toString.contains("31") &&
				toString.contains("10") &&
				toString.contains("Test description")
			);
}
}
