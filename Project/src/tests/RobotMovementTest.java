package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Robot;
import model.Warehouse;

class RobotMovementTest {
	private Robot r1 = new Robot();
	private Robot r2 = new Robot();
	private Warehouse w = new Warehouse();
	private HashMap<Point, Point> currentToNext = new HashMap<Point, Point>();
	private ArrayList<Robot> robotList = new ArrayList<Robot>();
	/**
	 * current to next gives next location and current loaction which is a hashmap
	 * 
	 * current to next takes in 
	 * 
	 * a new path finding object is made...
	 * 
	 * pathfinding.pathCalc() is called which takes a parameter of point, which is the destionation of the robot
	 * 
	 * pathfinding.getNewNodes() - returns a hashmap of its current location and next location
	 * currentToNext = getNewNodes
	 * 
	 * destination passed into pathCalc is taken from robot 
	 */
	
	/**
	 * To see if robots moved as expected
	 * @throws Exception
	 */

	@BeforeEach
	void setUp() throws Exception {
		r1.setCoordinates(0, 0);
		r2.setCoordinates(1,0);
		robotList.add(r1);
		robotList.add(r2);
		
		
	}

	@Test
	void testMove() {
		Point node = new Point(2,2);
		Robot r = robotList.get(0);
		HashMap<Point, Point> temp = new HashMap<Point, Point>();
		temp.put(r.getRobotCoordinates(), node );
		currentToNext = temp;
		assertEquals(currentToNext.get(r.getRobotCoordinates()),node);
		
		
	}

}
