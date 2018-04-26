package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Order;
import model.PackingStation;

class DeliveryDispatchTest {
	/**
	 * To see if packing stations dispatch deliveries when ready
	 * @throws Exception
	 */
	private PackingStation ps = new PackingStation(300, 400);
	private Order o = new Order();
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetNextOrder() {
		
	}

}
