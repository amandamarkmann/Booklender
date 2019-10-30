package se.lexicon.amanda.booklender.test.models;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import se.lexicon.amanda.booklender.models.Book;
import se.lexicon.amanda.booklender.models.LibraryUser;
import se.lexicon.amanda.booklender.models.Loan;

public class LoanTest {

	private Loan testLoan;
	
	private Book testBook;
	
	private LibraryUser testUser;
	
	
	@Before
	public void setup() {
		
		testBook = new Book("Harry Potter", 31, BigDecimal.valueOf(10), "Test description");
		
		testUser = new LibraryUser(LocalDate.parse("2019-10-29"), "Test Testsson", "test@testsson.se");
		
		testLoan = new Loan (testUser, testBook, LocalDate.parse("2019-10-30"));
	
	}
	
	@Test
	public void test_create_loan_success() {
		
		LocalDate expectedLoanDate = LocalDate.parse("2019-10-30");
		
		assertEquals(0, testLoan.getLoanId());
		assertEquals(testUser, testLoan.getLoanTaker());
		assertEquals(testBook, testLoan.getBook());
		assertEquals(expectedLoanDate, testLoan.getLoanDate());
		assertFalse(testLoan.getBook().isAvailable());
		assertFalse(testLoan.isTerminated());
		}
	
	
	
	//test loan is terminated
	//test isOverdue
	//test getFine
	//test extendLoan
}
