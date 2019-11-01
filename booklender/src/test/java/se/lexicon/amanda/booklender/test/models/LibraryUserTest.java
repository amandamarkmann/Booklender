package se.lexicon.amanda.booklender.test.models;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import se.lexicon.amanda.booklender.models.LibraryUser;


public class LibraryUserTest {

	
		private LibraryUser testUser;
	
		@Before
		public void setup() {
		testUser = new LibraryUser(LocalDate.parse("2019-10-30"), "Test Testsson", "test@testsson.se");
		}
	
		@Test
		public void test_create_LibraryUser_success() {		
		
			LocalDate expectedRegDate = LocalDate.parse("2019-10-30");
			String expectedName = "Test Testsson";
			String expectedEmail = "test@testsson.se";
			
			
			assertEquals(0,testUser.getUserId());
			assertEquals(expectedRegDate, testUser.getRegDate());
			assertEquals(expectedName, testUser.getName());	
			assertEquals(expectedEmail, testUser.getEmail());
		}
		
		@Test
		public void test_equals_and_hashcode_true() {
			LibraryUser copy = new LibraryUser(LocalDate.parse("2019-10-30"), "Test Testsson", "test@testsson.se");

			assertTrue(copy.equals(testUser));
			assertEquals(copy.hashCode(), testUser.hashCode());
		}
		
		@Test
		public void test_toString_contains_correct_information() {
			String toString = testUser.toString();
			
			assertTrue(toString.contains("2019-10-30"));
			assertTrue(toString.contains("Test Testsson"));
			assertTrue(toString.contains("test@testsson.se"));
	}
		
		
		
		
	}

