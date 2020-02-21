package se.lexicon.amanda.booklender.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import se.lexicon.amanda.booklender.converter.EntityDtoConverter;
import se.lexicon.amanda.booklender.data.BookRepo;
import se.lexicon.amanda.booklender.data.LibraryUserRepo;
import se.lexicon.amanda.booklender.data.LoanRepo;
import se.lexicon.amanda.booklender.dto.LoanDto;
import se.lexicon.amanda.booklender.models.Loan;

@Service
public class LoanServiceImpl implements LoanService {

	private LoanRepo loanRepo;
	
	private LibraryUserRepo userRepo;
	
	private BookRepo bookRepo;
	
	private EntityDtoConverter converter;
	
	
	@Autowired
	public LoanServiceImpl(LoanRepo loanRepo, LibraryUserRepo userRepo, BookRepo bookRepo,
			EntityDtoConverter converter) {
		this.loanRepo = loanRepo;
		this.userRepo = userRepo;
		this.bookRepo = bookRepo;
		this.converter = converter;
	}



	@Override
	public Optional<LoanDto> findById(long loanId) {
		
		return Optional.of(converter.loanToDto(loanRepo.findByLoanId(loanId).get()));
	}

	@Override
	public List<LoanDto> findByBookId(int bookId) {
		
		return converter.loansToDtos(loanRepo.findByBookBookId(bookId));
	}

	@Override
	public List<LoanDto> findByUserId(int userId) {

		return converter.loansToDtos(loanRepo.findByLoanTakerUserId(userId));
		
	}

	@Override
	public List<LoanDto> findByTerminated(boolean terminated) {
		
		return converter.loansToDtos(loanRepo.findByTerminated(terminated));
	}

	@Override
	public List<LoanDto> findAll() {

		return converter.loansToDtos(loanRepo.findAll());
	}

	@Override
	public LoanDto create(LoanDto loanDto) {
		if(loanDto.getLoanId() != 0) {
			throw new IllegalArgumentException();
		}
		
		Loan loanEntity = converter.dtoToLoan(loanDto);
		
		
		loanEntity.setLoanTaker(userRepo.findById(loanEntity.getLoanTaker().getUserId()).get());
		
		loanEntity.setBook(bookRepo.findByBookId(loanEntity.getBook().getBookId()).get());
		
		
		
		loanEntity = loanRepo.save(loanEntity);
		
		
		return converter.loanToDto(loanEntity);
	}

	@Override
	public LoanDto update(LoanDto loanDto) throws IllegalArgumentException{
		
		if(loanDto.getLoanId() == 0) {
			throw new IllegalArgumentException("Loan has invalid id: " + loanDto.getLoanId());
		}
		
		Loan loan = loanRepo.findByLoanId(loanDto.getLoanId()).orElseThrow(IllegalArgumentException::new);
		
		if (loanDto.isTerminated()) {
			loan.returnedBook();
		}
		
		loanRepo.save(loan);
		
		
		return loanDto;
	}

	@Override
	public boolean delete(long loanId) {
	
		Loan loan = loanRepo.findByLoanId(loanId).orElseThrow(IllegalArgumentException::new);
		
		loanRepo.delete(loan);
		
		return !loanRepo.existsById(loanId);
		

	}

}
