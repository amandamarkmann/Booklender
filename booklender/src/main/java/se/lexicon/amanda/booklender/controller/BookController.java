package se.lexicon.amanda.booklender.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.amanda.booklender.dto.BookDto;
import se.lexicon.amanda.booklender.service.BookService;

@RestController
public class BookController {
	
	private BookService bookService;

	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("api/books/{bookId}")
	public ResponseEntity<Optional<BookDto>> findById(@PathVariable ("bookId") int bookId){
	
	return ResponseEntity.ok().body(bookService.findById(bookId));
	}
	
	
	@GetMapping("api/books/find")
	public ResponseEntity<List<BookDto>> find(
			@RequestParam(defaultValue = "all", name = "type") String type,
			@RequestParam(required = false, name = "value") String value) {
		
		if(type.equalsIgnoreCase("all") && value == null) {
			return ResponseEntity.ok(bookService.findAll());
		}
		else if(type.equalsIgnoreCase("title")) {
			return ResponseEntity.ok(bookService.findByTitle(value));
		}
		else if(type.equalsIgnoreCase("available")) {
			return ResponseEntity.ok(bookService.findByAvailable(Boolean.valueOf(value)));
		}
		else if(type.equalsIgnoreCase("reserved")) {
			return ResponseEntity.ok(bookService.findByReserved(Boolean.valueOf(value)));
		}
		else {
			throw new IllegalArgumentException();
		}
	}
		
	
	
	
	
	@PostMapping("api/books")
	public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
		
		if(bookDto == null) {
			throw new IllegalArgumentException();
		}
		
		return ResponseEntity.ok(bookService.create(bookDto));
		
		
	}
		
	@PutMapping("api/books")
	public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto) {
		
		if(bookDto == null) {
			throw new IllegalArgumentException();
		}
		
		return ResponseEntity.ok(bookService.update(bookDto));
	}
	
}
