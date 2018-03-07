package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StorageShelfTest {
	private StorageShelf s1;
		
	@BeforeEach
	void setUp() throws Exception {
		s1 = new StorageShelf();
	}


	@Test
	void testGetID() {
		s1.generateID();
		assertEquals("s1", s1.getID());
	}

	@Test
	void testCompare() {
		fail("Not yet implemented");
	}

}
