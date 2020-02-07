package se.lexicon.amanda.booklender.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.lexicon.amanda.booklender.converter.EntityDtoConverter;
import se.lexicon.amanda.booklender.data.BookRepo;
import se.lexicon.amanda.booklender.dto.BookDto;
import se.lexicon.amanda.booklender.models.Book;


@Service
public class BookServiceImpl implements BookService {

	private BookRepo bookRepo;
	private EntityDtoConverter converter;
	
	
	@Autowired
	public BookServiceImpl(BookRepo bookRepo, EntityDtoConverter converter) {
		this.bookRepo = bookRepo;
		this.converter = converter;
	}
	
	@Override
	public List<BookDto> findByReserved(boolean reserved) {
		
		return converter.booksToDtos(bookRepo.findByReserved(reserved));
				}


	@Override
	public List<BookDto> findByAvailable(boolean available) {

		return converter.booksToDtos(bookRepo.findByAvailable(available));
	}

	@Override
	public List<BookDto> findByTitle(String title) {
		
		return converter.booksToDtos(bookRepo.findByTitle(title));
	}

	@Override
	public Optional<BookDto> findById(int bookId) {

		return Optional.of(converter.bookToDto(bookRepo.findById(bookId).get()));
	}

	@Override
	public List<BookDto> findAll() {
		return converter.booksToDtos(bookRepo.findAll());
	}

	@Override
	public BookDto create(BookDto bookDto) throws IllegalArgumentException{
		if(bookDto.getBookId() != 0) {
			throw new IllegalArgumentException("Invalid Book ID need to  be 0");
		}
		
		Book book = converter.dtoToBook(bookDto);
		
		book = bookRepo.save(book);
		
		return converter.bookToDto(book);
		
	}

	@Override
	public BookDto update(BookDto bookDto) throws IllegalArgumentException{
		
		if(bookDto.getBookId() == 0) {
				throw new IllegalArgumentException("Book has invalid id: " + bookDto.getBookId());
			}
			
			Book book = bookRepo.findByBookId(bookDto.getBookId()).orElseThrow(IllegalArgumentException::new);
			
			if (bookDto.isAvailable()) {
				book.setAvailable(true);
			}
			
			if(bookDto.isReserved()) {
				book.setReserved(true);
			}
			
			book.setTitle(bookDto.getTitle());
			
			book.setFinePerDay(bookDto.getFinePerDay());
			
			book.setMaxLoanDays(bookDto.getMaxLoanDays());
			
			book.setDescription(bookDto.getDescription());
			
			bookRepo.save(book);
			
			
			return bookDto;
		
	}

	@Override
	public boolean delete(int bookId) {
		
		Book book = bookRepo.findById(bookId).orElseThrow(IllegalArgumentException::new);
		
		bookRepo.delete(book);
		
		return !bookRepo.existsById(bookId);
		
	}

}
