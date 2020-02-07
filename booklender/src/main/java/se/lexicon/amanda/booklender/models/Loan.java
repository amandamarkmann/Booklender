package se.lexicon.amanda.booklender.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loanId;
	
	
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	)
	@JoinColumn(name = "user_id")
	private LibraryUser loanTaker;
	
	
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	)
	@JoinColumn(name = "book_id")
	private Book book;
	
	
	private LocalDate loanDate;

	private boolean terminated;
	

	public Loan(long loanId, LibraryUser loanTaker, Book book, LocalDate loanDate) {
		this(loanTaker, book, loanDate);
		this.loanId = loanId;
	}

	public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate) {
		this.loanTaker = loanTaker;
		this.book = book;
		this.loanDate = loanDate;
		this.terminated = false;
	}
	
	
	public Loan() {}

	public LibraryUser getLoanTaker() {
		return loanTaker;
	}

	public void setLoanTaker(LibraryUser loanTaker) {
		this.loanTaker = loanTaker;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		if(!book.isAvailable()) {
			throw new IllegalArgumentException("Book is not available");
		}
		book.setAvailable(false);
		
		this.book = book;
	}
	
	
	public boolean isTerminated() {
		return terminated;
	}

	public void returnedBook() {
		this.book.setAvailable(true);
		this.terminated = true;
	}
	

	public long getLoanId() {
		return loanId;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}
	

	public boolean isOverdue() {
		
		LocalDate lastDay = loanDate.plusDays(book.getMaxLoanDays());
		
		boolean overdue = LocalDate.now().isAfter(lastDay);
		return overdue;
	}
	
	public BigDecimal getFine() {		
		Period period = Period.between(loanDate.plusDays(book.getMaxLoanDays()), LocalDate.now());
		
		int daysOverdue = period.getDays();
		
		BigDecimal fine = BigDecimal.ZERO;
		if(daysOverdue > 0) {
			fine = BigDecimal.valueOf(daysOverdue * book.getFinePerDay().longValue());
		}
		return fine;
	}
	
	public boolean extendLoan(LocalDate newLoanDate) {
		if(book.isReserved() || isOverdue()) {
			return false;
		}
		if(loanDate.plusDays(book.getMaxLoanDays()).isAfter(newLoanDate) || loanDate.plusDays(book.getMaxLoanDays()).equals(newLoanDate)) {
		
		this.loanDate=newLoanDate;
		return true;
		}
		return false;		
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, loanDate, loanId, loanTaker, terminated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		return Objects.equals(book, other.book) && Objects.equals(loanDate, other.loanDate) && loanId == other.loanId
				&& Objects.equals(loanTaker, other.loanTaker) && terminated == other.terminated;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Loan [loanId=");
		builder.append(loanId);
		builder.append(", loanTaker=");
		builder.append(loanTaker);
		builder.append(", book=");
		builder.append(book);
		builder.append(", loanDate=");
		builder.append(loanDate);
		builder.append(", terminated=");
		builder.append(terminated);
		builder.append("]");
		return builder.toString();
	}
}
