package se.lexicon.amanda.booklender.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.amanda.booklender.models.Book;

public interface BookRepo extends CrudRepository<Book, Integer> {

	List<Book> findByReserved (boolean reserved);
	List<Book> findByAvailable (boolean available);
	List<Book> findByTitle (String title);
	
	Optional<Book> findByBookId (int bookId);
}
