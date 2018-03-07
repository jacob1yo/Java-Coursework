package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChargingPodTest {

	private ChargingPod c1;
	
	@BeforeEach
	void setUp() throws Exception {
		c1 = new ChargingPod();
	}


	@Test
	void testGetID() {
		c1.generateID();
		assertEquals("c1", c1.getID());
	}

	@Test
	void testCompare() {
		fail("Not yet implemented");
	}

}
