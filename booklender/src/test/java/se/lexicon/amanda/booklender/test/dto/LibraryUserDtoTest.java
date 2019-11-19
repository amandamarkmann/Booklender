package se.lexicon.amanda.booklender.test.dto;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import se.lexicon.amanda.booklender.dto.LibraryUserDto;



public class LibraryUserDtoTest {
	
	private LibraryUserDto testUser;
	
	@Before
	public void setup() {
	testUser = new LibraryUserDto(1, LocalDate.parse("2019-10-30"), "Test Testsson", "test@testsson.se");
	}

	@Test
	public void test_create_LibraryUser_success() {		
	
		LocalDate expectedRegDate = LocalDate.parse("2019-10-30");
		String expectedName = "Test Testsson";
		String expectedEmail = "test@testsson.se";
		
		
		assertEquals(1,testUser.getUserId());
		assertEquals(expectedRegDate, testUser.getRegDate());
		assertEquals(expectedName, testUser.getName());	
		assertEquals(expectedEmail, testUser.getEmail());
	}
	
	

}
