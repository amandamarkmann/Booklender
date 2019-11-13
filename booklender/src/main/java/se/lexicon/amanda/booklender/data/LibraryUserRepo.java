package se.lexicon.amanda.booklender.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.amanda.booklender.models.LibraryUser;

public interface LibraryUserRepo extends CrudRepository<LibraryUser, Integer> {
	
	Optional<LibraryUser> findByUserId(int userId);
	Optional<LibraryUser> findByEmail(String email);

}
