package se.lexicon.amanda.booklender.service;

import java.util.List;
import java.util.Optional;

import se.lexicon.amanda.booklender.dto.LoanDto;

public interface LoanService {

	Optional<LoanDto> findById(long loanId);
	
	List<LoanDto> findByBookId(int bookId);
	
	List<LoanDto> findByUserId(int userId);
	
	List<LoanDto> findByTerminated(boolean terminated);
	
	List<LoanDto> findAll();
	
	LoanDto create(LoanDto loanDto);
	
	LoanDto update(LoanDto loanDto);
	
	boolean delete(long loanId);

	
}
