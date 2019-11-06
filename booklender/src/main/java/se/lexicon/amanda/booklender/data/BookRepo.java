package se.lexicon.amanda.booklender.data;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.amanda.booklender.models.Book;

public interface BookRepo extends CrudRepository<Book, Integer> {

}
