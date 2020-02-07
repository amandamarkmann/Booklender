package se.lexicon.amanda.booklender.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EntityFactory {

	protected Book createBook(int bookId, String title, int maxLoanDays, BigDecimal finePerDay, String description) {
		return new Book(bookId, title, maxLoanDays, finePerDay, description);
	}
	
	protected LibraryUser createLibraryUser(int userId, LocalDate regDate, String name, String email) {
		return new LibraryUser(userId, regDate, name, email);
	}
	
	protected Loan createLoan(long loanId, LibraryUser loanTaker, Book book, LocalDate loanDate) {
		return new Loan(loanId, loanTaker, book, loanDate);
	}
	
}
