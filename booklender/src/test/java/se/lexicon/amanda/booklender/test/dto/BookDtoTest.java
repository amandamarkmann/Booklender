package se.lexicon.amanda.booklender.test.dto;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import se.lexicon.amanda.booklender.dto.BookDto;

public class BookDtoTest {
	
	private BookDto testBook;
	
	@Before
	public void setup() {
	
		testBook = new BookDto(1, "Harry Potter", true, false, 31, BigDecimal.valueOf(10), "Test description");
	}
	
	@Test
	public void test_create_book_success() {
		
		int expectedMaxLoanDays = 31;
		BigDecimal expectedFinePerDay = BigDecimal.valueOf(10);
		String expectedDescription = "Test description";
		String expectedTitle = "Harry Potter";
		
		
		assertEquals(1, testBook.getBookId());
		assertEquals(expectedTitle, testBook.getTitle());
		assertEquals(expectedMaxLoanDays, testBook.getMaxLoanDays());
		assertEquals(expectedFinePerDay, testBook.getFinePerDay());
		assertEquals(expectedDescription, testBook.getDescription());
		assertTrue(testBook.isAvailable());
		assertFalse(testBook.isReserved());
	
	}
	

}
