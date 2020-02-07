package se.lexicon.amanda.booklender.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.amanda.booklender.models.Loan;

public interface LoanRepo extends CrudRepository<Loan, Long> {

	Optional<Loan> findByLoanId (long loanId);
	List<Loan> findByBookBookId (int bookId);
	List<Loan> findByLoanTakerUserId (int userId);
	
	List<Loan> findByTerminated (boolean terminated);
	
}
