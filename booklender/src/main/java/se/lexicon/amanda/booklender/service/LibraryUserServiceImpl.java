package se.lexicon.amanda.booklender.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.lexicon.amanda.booklender.converter.EntityDtoConverter;
import se.lexicon.amanda.booklender.data.LibraryUserRepo;
import se.lexicon.amanda.booklender.dto.LibraryUserDto;
import se.lexicon.amanda.booklender.models.LibraryUser;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

	private LibraryUserRepo userRepo;
	private EntityDtoConverter converter;
	
	@Autowired
	public LibraryUserServiceImpl(LibraryUserRepo userRepo, EntityDtoConverter converter) {
		this.userRepo = userRepo;
		this.converter = converter;
	}

	@Override
	public Optional<LibraryUserDto> findById(int userId) {
		
		return Optional.of(converter.userToDto(userRepo.findById(userId).get()));
	}

	@Override
	public Optional<LibraryUserDto> findByEmail(String email) {
		
		return Optional.of(converter.userToDto(userRepo.findByEmail(email).get()));
	}

	@Override
	public List<LibraryUserDto> findAll() {

		return converter.usersToDtos(userRepo.findAll());
	}

	@Override
	public LibraryUserDto create(LibraryUserDto libraryUserDto) throws IllegalArgumentException{
		
		if(libraryUserDto.getUserId() != 0) {
			throw new IllegalArgumentException("Invalid UserID nedd to be 0");
		}
		
		LibraryUser user = converter.dtoToUser(libraryUserDto);
		
		user = userRepo.save(user);
		
		return converter.userToDto(user);
		
		
	}

	@Override
	public LibraryUserDto update(LibraryUserDto libraryUserDto) throws IllegalArgumentException {
		
		if(libraryUserDto.getUserId() == 0) {
			throw new IllegalArgumentException("User has invalid id: " + libraryUserDto.getUserId());
		}
		
		LibraryUser user = userRepo.findByUserId(libraryUserDto.getUserId()).orElseThrow(IllegalArgumentException::new);
		
		user.setEmail(libraryUserDto.getEmail());
		
		user.setName(libraryUserDto.getName());
		
		userRepo.save(user);
		
		
		return libraryUserDto;
		
		
	}

	@Override
	public boolean delete(int userId) {
		
		LibraryUser user = userRepo.findById(userId).orElseThrow(IllegalArgumentException::new);
		
		userRepo.delete(user);
		
		return !userRepo.existsById(userId);
	}

}
