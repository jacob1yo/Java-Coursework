package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Order;
import model.PackingStation;
import model.Robot;

class DeliveryDispatchTest {
	/**
	 * To see if packing stations dispatch deliveries when ready
	 * @throws Exception
	 */
	private PackingStation ps = new PackingStation(300, 400);
	private Order o = new Order();
	private Robot r = new Robot();
	private ArrayList<Point> destinations = new ArrayList<Point>();
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGivenOrder() {
		Point destination = new Point(300,400);
		destinations.add(destination);
		r.setCoordinates(300, 400);
		r.receiveOrder(destinations);
		assertEquals(false,r.getOrderStatus());
		
		
	}

}
