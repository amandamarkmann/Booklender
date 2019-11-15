package se.lexicon.amanda.booklender.test.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.amanda.booklender.data.LoanRepo;
import se.lexicon.amanda.booklender.models.Book;
import se.lexicon.amanda.booklender.models.LibraryUser;
import se.lexicon.amanda.booklender.models.Loan;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class LoanRepoTest {

	
	@Autowired
	private LoanRepo testObject;
	private Loan testLoan;
	private LibraryUser testUser;
	private Book testBook;
	private long loanId;
	
	
	@Before
	public void setup() {
		
		testUser = new LibraryUser(LocalDate.parse("2019-11-11"), "Test Testsson", "test@test.se"); 
		testBook = new Book("Harry Potter", 30, BigDecimal.valueOf(10), "Cool stuff");
		testLoan = new Loan(testUser, testBook, LocalDate.parse("2019-11-15"));
		
		testLoan = testObject.save(testLoan);
		loanId = testLoan.getLoanId();
	}
	
	
	@Test
	public void test_save_loan() {
		assertNotNull(testLoan);
		assertTrue(testLoan.getLoanId() !=0);
		assertEquals(LocalDate.parse("2019-11-15"), testLoan.getLoanDate());
		assertEquals("Test Testsson", testUser.getName());
		assertEquals("test@test.se", testUser.getEmail());
		assertEquals("Harry Potter", testBook.getTitle());
	}
	
	
	@Test
	public void test_findAll() {
		assertNotNull(testObject.findAll());
	}
	
	@Test
	public void test_findById_loanId() {
		Optional<Loan> result = testObject.findByLoanId(loanId);
		assertTrue(result.isPresent());
		assertEquals(loanId, result.get().getLoanId());
	}
	
	@Test
	public void test_delete_by_loanId() {
		assertTrue(testObject.findByLoanId(loanId).isPresent());
		testObject.deleteById(loanId);
		assertFalse(testObject.findByLoanId(loanId).isPresent());
	}
	
	@Test
	public void test_findByTerminated_true() {
		testLoan.returnedBook();
		List<Loan> result = testObject.findByTerminated(true);
		int expectedSize = 1;
		assertEquals(result.size(), expectedSize);
		assertTrue(result.contains(testLoan)); 
	}
	
	@Test
	public void test_findByUserId() {
		Optional<Loan> result = testObject.findByLoanTakerUserId(testUser.getUserId());
		assertTrue(result.isPresent());
		assertEquals(testUser.getUserId(), result.get().getLoanTaker().getUserId());
	}
	
	
	@Test
	public void test_findByBookId() {
		Optional<Loan> result = testObject.findByBookBookId(testBook.getBookId());
		assertTrue(result.isPresent());
		assertEquals(testBook.getBookId(), result.get().getBook().getBookId());
	}
	
}
