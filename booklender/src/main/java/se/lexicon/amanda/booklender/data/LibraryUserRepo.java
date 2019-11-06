package se.lexicon.amanda.booklender.data;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.amanda.booklender.models.LibraryUser;

public interface LibraryUserRepo extends CrudRepository<LibraryUser, Integer> {

}
