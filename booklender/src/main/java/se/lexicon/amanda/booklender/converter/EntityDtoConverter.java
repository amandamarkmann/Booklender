package se.lexicon.amanda.booklender.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import se.lexicon.amanda.booklender.dto.BookDto;
import se.lexicon.amanda.booklender.dto.LibraryUserDto;
import se.lexicon.amanda.booklender.dto.LoanDto;
import se.lexicon.amanda.booklender.models.Book;
import se.lexicon.amanda.booklender.models.EntityFactory;
import se.lexicon.amanda.booklender.models.LibraryUser;
import se.lexicon.amanda.booklender.models.Loan;

@Component
public class EntityDtoConverter extends EntityFactory {

	public BookDto bookToDto(Book book) {
		BookDto dto = new BookDto();
		dto.setBookId(book.getBookId());
		dto.setTitle(book.getTitle());
		dto.setMaxLoanDays(book.getMaxLoanDays());
		dto.setFinePerDay(book.getFinePerDay());
		dto.setDescription(book.getDescription());
		dto.setAvailable(book.isAvailable());
		dto.setReserved(book.isReserved());
		return dto;
	}
	
	public List<BookDto> booksToDtos(Iterable<Book> iterable){
		List<BookDto> dtos = new ArrayList<>();
		for(Book book : iterable) {
			dtos.add(bookToDto(book)); 
			}
		return dtos;
		}

	public Book dtoToBook(BookDto dto) {
		Book book = createBook(dto.getBookId(), dto.getTitle(), dto.getMaxLoanDays(), dto.getFinePerDay(), dto.getDescription(), dto.isAvailable(), dto.isReserved());
		return book;
	}
	
	
	public List<Book> dtosToBooks(List<BookDto> dtos){
		List<Book> books = new ArrayList<>();
		for(BookDto dto : dtos) {
			books.add(dtoToBook(dto));
		}
		return books;
	}

	public LibraryUserDto userToDto(LibraryUser user) {
		LibraryUserDto dto = new LibraryUserDto();
		dto.setUserId(user.getUserId());
		dto.setRegDate(user.getRegDate());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		return dto;
	}
	
	public List<LibraryUserDto> usersToDtos(Iterable<LibraryUser> iterable){
		List<LibraryUserDto> dtos = new ArrayList<>();
		for(LibraryUser user : iterable) {
			dtos.add(userToDto(user));
					}
		return dtos;
		}
	
	public LibraryUser dtoToUser(LibraryUserDto dto) {
		LibraryUser user = createLibraryUser(dto.getUserId(), dto.getRegDate(), dto.getName(), dto.getEmail());
		return user;
	}
	
	public List<LibraryUser> dtosToUsers(List<LibraryUserDto> dtos){
		List<LibraryUser> users = new ArrayList<>();
		for(LibraryUserDto dto : dtos) {
			users.add(dtoToUser(dto));
			}
		return users;
		}
	
	public LoanDto loanToDto(Loan loan) {
		LoanDto dto = new LoanDto();
		dto.setLoanId(loan.getLoanId());
		dto.setLoanTaker(userToDto(loan.getLoanTaker()));
		dto.setBook(bookToDto(loan.getBook()));
		dto.setLoanDate(loan.getLoanDate());
		return dto;
	}
	
	public List<LoanDto> loansToDtos(Iterable<Loan> iterable){
		List<LoanDto> dtos = new ArrayList<>();
		for(Loan loan : iterable) {
			dtos.add(loanToDto(loan));
				}
		return dtos;
		}
	
	
	public Loan dtoToLoan(LoanDto dto) {
		Loan loan = createLoan(dto.getLoanId(), dtoToUser(dto.getLoanTaker()), dtoToBook(dto.getBook()), dto.getLoanDate());
		return loan;
	}
	

	public List<Loan> dtosToLoans(List<LoanDto> dtos){
		List<Loan> loans = new ArrayList<>();
		for(LoanDto dto : dtos) {
			loans.add(dtoToLoan(dto));
			}
		return loans;
		}

	}