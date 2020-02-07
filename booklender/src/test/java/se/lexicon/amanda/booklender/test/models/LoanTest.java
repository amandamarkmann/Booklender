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
		assertTrue(testLoan.getBook().isAvailable());
		assertFalse(testLoan.isTerminated());
		}
	
	@Test
	public void test_returned_book_loanIsTerminated_and_bookIsAvailable() {
		testLoan.returnedBook();
		
		assertTrue(testLoan.isTerminated());
		assertTrue(testBook.isAvailable());
	}
	
	@Test
	public void test_loan_is_overdue() {
		
		Loan testLoan = new Loan(testUser, testBook, LocalDate.now().minusDays(32));
		
		assertTrue(testLoan.isOverdue());
	}
	
	@Test
	public void test_loan_is_not_overdue() {
		
		Loan testLoan = new Loan(testUser, testBook, LocalDate.now().minusDays(29));
		
		assertFalse(testLoan.isOverdue());
	}
	
	@Test
	public void test_will_get_fine_of_30() {
		BigDecimal expectedFine = BigDecimal.valueOf(30);
		
		LocalDate threeDaysOverdue = LocalDate.now().minusDays(34);
		
		Loan overdueLoan = new Loan(testUser, testBook, threeDaysOverdue);
		
		assertEquals(expectedFine, overdueLoan.getFine());
		
	}
	
	@Test
	public void test_will_not_get_fine() {
		BigDecimal expectedFine = BigDecimal.ZERO;
		
		LocalDate noDaysOverdue = LocalDate.now().minusDays(26);
		
		Loan notOverdueLoan = new Loan (testUser, testBook, noDaysOverdue);
		
		assertEquals(expectedFine, notOverdueLoan.getFine());
		
	}
	
	@Test
	public void test_extendLoan_is_possible() {
		
		Loan wantToExtendLoan = new Loan(testUser, testBook, LocalDate.now().minusDays(2));
		
		assertTrue(wantToExtendLoan.extendLoan(LocalDate.now()));
		assertEquals(LocalDate.now(), wantToExtendLoan.getLoanDate());
	}
	
	
	@Test
	public void test_extendLoan_return_false_while_overdue() {
		
		LocalDate threeDaysOverdue = LocalDate.now().minusDays(34);
		
		Loan overdueLoan = new Loan(testUser, testBook, threeDaysOverdue);
		
		assertFalse(overdueLoan.extendLoan(threeDaysOverdue));
	}
	
	
	@Test
	public void test_extendLoan_return_false_if_book_is_reserved() {
		
		testBook.setReserved(true);
		
		assertFalse(testLoan.extendLoan(LocalDate.now()));
	}

	
	@Test
	public void test_equals_and_hashcode_true() {
		Loan copy = new Loan (testUser, testBook, LocalDate.parse("2019-10-30"));

		assertTrue(copy.equals(testLoan));
		assertEquals(copy.hashCode(), testLoan.hashCode());
	}
	
	
	@Test
	public void test_toString_contains_correct_information() {
		String toString = testLoan.toString();
		
		assertTrue(toString.contains("Test Testsson"));
		assertTrue(toString.contains("Harry Potter"));
		assertTrue(toString.contains("2019-10-30"));
}
	
}
