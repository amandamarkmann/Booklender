package se.lexicon.amanda.booklender.test.dto;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import se.lexicon.amanda.booklender.dto.BookDto;
import se.lexicon.amanda.booklender.dto.LibraryUserDto;
import se.lexicon.amanda.booklender.dto.LoanDto;


public class LoanDtoTest {

	private LoanDto testLoan;
	
	private BookDto testBook;
	
	private LibraryUserDto testUser;
	
	
	@Before
	public void setup() {
		
		testBook = new BookDto(1, "Harry Potter", true, false, 31, BigDecimal.valueOf(10), "Test description");
		
		testUser = new LibraryUserDto(1, LocalDate.parse("2019-10-29"), "Test Testsson", "test@testsson.se");
		
		testLoan = new LoanDto(1, testUser, testBook, LocalDate.parse("2019-10-30"), false);
	
	}
	
	@Test
	public void test_create_loan_success() {
		
		LocalDate expectedLoanDate = LocalDate.parse("2019-10-30");
		
		assertEquals(1, testLoan.getLoanId());
		assertEquals(testUser, testLoan.getLoanTaker());
		assertEquals(testBook, testLoan.getBook());
		assertEquals(expectedLoanDate, testLoan.getLoanDate());
		assertTrue(testLoan.getBook().isAvailable());
		assertFalse(testLoan.isTerminated());
		}
	
	
}
