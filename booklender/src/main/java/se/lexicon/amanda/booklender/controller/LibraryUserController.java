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

import se.lexicon.amanda.booklender.dto.LibraryUserDto;
import se.lexicon.amanda.booklender.service.LibraryUserService;

@RestController
public class LibraryUserController {

	private LibraryUserService libraryUserService;

	
	@Autowired
	public LibraryUserController(LibraryUserService libraryUserService) {
		this.libraryUserService = libraryUserService;
	}

	@GetMapping("api/library_users/{userId}")
	public ResponseEntity<Optional<LibraryUserDto>> findById(@PathVariable ("userId") int userId){
	
	return ResponseEntity.ok().body(libraryUserService.findById(userId));
	}
	
	@GetMapping("api/library_users/{email}")
	public ResponseEntity<Optional<LibraryUserDto>> findByEmail(@RequestParam (name = "email") String email){
		
		return ResponseEntity.ok().body(libraryUserService.findByEmail(email));
	}
	
	
	@GetMapping("api/library_users")
	public ResponseEntity<List<LibraryUserDto>> findAll(){
		
			return ResponseEntity.ok().body(libraryUserService.findAll());
		
	}

	
	@PostMapping("api/library_users")
	public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto libraryUserDto) {
		
		if(libraryUserDto == null) {
			throw new IllegalArgumentException();
		}
		
		return ResponseEntity.ok(libraryUserService.create(libraryUserDto));
		
		
	}
		
	@PutMapping("api/library_users")
	public ResponseEntity<LibraryUserDto> update(@RequestBody LibraryUserDto libraryUserDto) {
		
		if(libraryUserDto == null) {
			throw new IllegalArgumentException();
		}
		
		
		return ResponseEntity.ok(libraryUserService.update(libraryUserDto));
	}
	
	
	
}
	
