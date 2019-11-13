package se.lexicon.amanda.booklender.test.data;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.amanda.booklender.data.LoanRepo;
import se.lexicon.amanda.booklender.models.Book;
import se.lexicon.amanda.booklender.models.LibraryUser;
import se.lexicon.amanda.booklender.models.Loan;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class LoanRepoTest {

	
	@Autowired
	private LoanRepo testObject;
	private Loan testLoan;
	private LibraryUser testUser;
	private Book testBook;
	
	
}
