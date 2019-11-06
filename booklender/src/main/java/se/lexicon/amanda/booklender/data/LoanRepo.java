package se.lexicon.amanda.booklender.data;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.amanda.booklender.models.Loan;

public interface LoanRepo extends CrudRepository<Loan, Long> {

}
