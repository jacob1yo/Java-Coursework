package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Robot;
import model.Warehouse;

class CollisionTest {
	private Warehouse warehouse;
	/**
	 * If robot collisions are reported.
	 * If two or more robots enter the same node, the simulation should end and report crash to user
	 */

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testCollide() {
		Robot r1 = new Robot();
		Robot r2 = new Robot();
		
		r1.setCoordinates(100, 100);
		r2.setCoordinates(100, 100);
		
	
		assertEquals(r1.getRobotCoordinates(), r2.getRobotCoordinates());
		
	}
	
	@Test
	void testNotCollide() {
		Robot r1 = new Robot();
		Robot r2 = new Robot();
		
		r1.setCoordinates(150, 100);
		r2.setCoordinates(200, 100);
		
	
		assertNotEquals(r1.getRobotCoordinates(), r2.getRobotCoordinates());
		
	}
		
	}
	


