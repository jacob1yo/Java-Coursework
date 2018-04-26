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
	 * If two or more robots enter the same node, the siulation should end and report crash to user
	 */

	@BeforeEach
	void setUp() throws Exception {
			
		
	}

	@Test
	void checkList() {
		ArrayList<Robot> robotList = new ArrayList<Robot>();
		
		Robot r1 = new Robot();
		Robot r2 = new Robot();
		
		warehouse.getRobotList().add(r1);
		warehouse.getRobotList().add(r2);
		
		robotList.equals(warehouse.getRobotList());
		
		assertEquals(robotList.size(), warehouse.getRobotList().size());
		
		
	}

}
