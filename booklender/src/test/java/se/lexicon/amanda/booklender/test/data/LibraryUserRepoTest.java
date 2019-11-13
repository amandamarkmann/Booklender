package se.lexicon.amanda.booklender.test.data;


import static org.junit.Assert.*;

import java.time.LocalDate;

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

	
	@Before
	public void setup() {
	
		testUser = new LibraryUser(LocalDate.parse("2019-11-11"), "Test Testsson", "test@test.se");
		testUser = testObject.save(testUser);
	}
	
	
	@Test
	public void test_save_user() {
		
		assertNotNull(testUser);
		assertTrue(testUser.getUserId() !=0);
		assertEquals(LocalDate.parse("2019-11-11"), testUser.getRegDate());
		assertEquals("Test Testsson", testUser.getName());
		assertEquals("test@test.se", testUser.getEmail());
	}
	
}
