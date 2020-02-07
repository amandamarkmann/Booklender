package se.lexicon.amanda.booklender.service;

import java.util.List;
import java.util.Optional;

import se.lexicon.amanda.booklender.dto.LibraryUserDto;


public interface LibraryUserService {
	
	Optional<LibraryUserDto> findById(int userId);
	
	Optional<LibraryUserDto> findByEmail(String email);
	
	List<LibraryUserDto> findAll();
	
	LibraryUserDto create(LibraryUserDto libraryUserDto);
	
	LibraryUserDto update(LibraryUserDto libraryUserDto);
	
	boolean delete(int userId);

}
