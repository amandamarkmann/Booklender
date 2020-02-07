package se.lexicon.amanda.booklender.service;


import java.util.List;
import java.util.Optional;

import se.lexicon.amanda.booklender.dto.BookDto;


public interface BookService {
	
	List<BookDto>findByReserved(boolean reserved);
	
	List<BookDto>findByAvailable(boolean available);
	
	List<BookDto>findByTitle(String title);
	
	Optional<BookDto> findById(int bookId);
	
	List<BookDto> findAll();
	
	BookDto create(BookDto bookDto);
	
	BookDto update(BookDto bookDto);
	
	boolean delete(int bookId);
	
}

