package se.lexicon.amanda.booklender.test.data;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.amanda.booklender.data.LibraryUserRepo;
import se.lexicon.amanda.booklender.models.LibraryUser;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class LibraryUserRepoTest {

	
	@Autowired
	private LibraryUserRepo testObject;
	private LibraryUser testUser;
	private int userId;
	private String email;

	
	@Before
	public void setup() {
	
		testUser = new LibraryUser(LocalDate.parse("2019-11-11"), "Test Testsson", "test@test.se");
		testUser = testObject.save(testUser);
		userId = testUser.getUserId();
		email = testUser.getEmail();
	}
	
	
	@Test
	public void test_save_user() {
		
		assertNotNull(testUser);
		assertTrue(testUser.getUserId() !=0);
		assertEquals(LocalDate.parse("2019-11-11"), testUser.getRegDate());
		assertEquals("Test Testsson", testUser.getName());
		assertEquals("test@test.se", testUser.getEmail());
	}
	
	@Test
	public void test_findAll() {
		assertNotNull(testObject.findAll());
	}
	
	@Test
	public void test_findById_userId() {
		Optional<LibraryUser> result = testObject.findByUserId(userId);
		assertTrue(result.isPresent());
		assertEquals(userId, result.get().getUserId());
}
	
	
	@Test
	public void test_delete_by_userId() {
		assertTrue(testObject.findByUserId(userId).isPresent());
		testObject.deleteById(userId);
		assertFalse(testObject.findByUserId(userId).isPresent());
	}
	
	@Test
	public void test_findByEmail() {
		Optional<LibraryUser> result = testObject.findByEmail(email);
		assertTrue(result.isPresent());
		assertEquals(email, result.get().getEmail());
	}
	
}
