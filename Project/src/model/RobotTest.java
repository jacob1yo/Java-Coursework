package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RobotTest {
	private Robot r1;
	private Robot r2;

	@BeforeEach
	void setUp() throws Exception {
		r1 = new Robot();
		r2 = new Robot();
	}
	@Test
	void testGetBatteryLevel() {
		assertEquals(100,r1.getBatteryLevel());
	}

	@Test
	void testDecreaseBatteryLevel() {
		r2.decreaseBatteryLevel();
		assertEquals(99,r2.getBatteryLevel());
	}

	@Test
	void getFreeRobot() {
		r1.pickUpOrder();
		assertNotNull(r2,r2.getID());
		
	}

	@Test
	void testNeedsCharging() {
		
	}

	@Test
	void testMove() {
		fail("Not yet implemented");
	}

	@Test
	void testPickUpOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testDropOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testGetID() {
		r1.generateID();
		assertEquals("r1",r1.getID());
		
	}

	@Test
	void testCompare() {
		fail("Not yet implemented");
	}

}
