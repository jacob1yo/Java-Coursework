package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.CostEstimationStrategy;
import model.Order;
import model.PackingStation;
import model.Robot;

class CostEstimationStrategyTest {
	private Robot robot;
	private Order order;
	private PackingStation packingStation;
	private HashMap robotCharge;
	private HashMap storagePoints;
	private CostEstimationStrategy costEst;
	
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@Test
	void testdistanceEstimator() {
		robot = new Robot();
		order = new Order();
		packingStation = new PackingStation(100,100);
		robotCharge = new HashMap<String, String>();
		storagePoints = new HashMap<String, Point>();
		costEst = new CostEstimationStrategy(order, packingStation, storagePoints); 
		assertEquals(false, costEst.distanceEstimator(100.0, 100.0, "r0", 100, robotCharge , storagePoints ));
	}
}
