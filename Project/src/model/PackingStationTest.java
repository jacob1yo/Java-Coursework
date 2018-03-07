package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackingStationTest {

	private PackingStation p1;
	
	@BeforeEach
	void setUp() throws Exception {
		p1 = new PackingStation();
	}

	@Test
	void testGetID() {
		p1.generateID();
		assertEquals("p1", p1.getID());
	}

}
