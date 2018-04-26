package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ChargingPod;
import model.CostEstimationStrategy;
import model.Order;
import model.PackingStation;
import model.Robot;
import model.StorageShelf;

class RobotPowerFailureTest {
	private Robot r = new Robot();
	private StorageShelf ss = new StorageShelf(2, 2);
	private PackingStation ps = new PackingStation(0, 2);
	private ChargingPod cp = new ChargingPod(2, 0);
	private CostEstimationStrategy ce;
	private HashMap robotsChargePod = new HashMap<String, String>();
	private HashMap chargePoints = new HashMap<String, Point>();
	private ArrayList<ChargingPod> chargeList = new ArrayList<ChargingPod>();
	private ArrayList<StorageShelf> storageList = new ArrayList<StorageShelf>();
	private HashMap<String, Point> storagePoints = new HashMap<String, Point>();
	private ArrayList<Robot> robotList = new ArrayList<Robot>();
	private Order order = new Order();
	private ArrayList<String> orderList = new ArrayList<String>();
	/**
	 * If power failures are reported during simulation
	 * 
	 */

	@BeforeEach
	void setUp() throws Exception {
		chargeList.add(cp);
		robotList.add(r);

		r.generateID(0);
		ps.generateID(0);
		cp.generateID(0);
		ss.generateID(0);

		r.setStart(2, 0);
		r.setBatteryCap(20);
		r.updateBattery(20);

		r.setCoordinates(100, 100);
		for (ChargingPod c : chargeList) {
			chargePoints.put(c.getID(), c.getChargingCoordinates());
		}
		for (int i = 0; i < chargeList.size(); i++) {
			String robot = "r" + i;
			String chargePod = "c" + i;
			robotsChargePod.put(robot, chargePod);
		}

		for (StorageShelf ss : storageList) {
			storagePoints.put(ss.getID(), ss.getStorageCoordinates());
		}

	}

	@Test
	void testCostEst() {
		orderList.add("order");
		orderList.add("9");
		orderList.add("ss0");
		order.addToDecision(orderList);
		ce = new CostEstimationStrategy(order, ps, chargePoints);
		boolean value = ce.distanceEstimator(r.getRobotX(), r.getRobotY(), r.getID(), r.getBatteryLevel(), robotsChargePod, chargePoints);
		
		assertEquals(true, value);

	}

}
