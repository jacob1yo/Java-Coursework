package model;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contains the implementation of the Warehouse methods.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 * 
 * @version 1.0
 */

public class Warehouse {

	/**
	 * Robot objects used in the simulation, are stored in this
	 * <code>ArrayList</code> of <code>Robot</code>.
	 * 
	 * @see #addRobot #genId #delete #removeAll #check #checkRobot #costEst
	 *      #robotPoints #move #moveRobot #getRobotInfo #getRobotList
	 *      #getOrderStatus #addToRobotsChargePod
	 */
	private ArrayList<Robot> robotList;

	/**
	 * Charging Pod objects used in the simulation, are stored in this
	 * <code>ArrayList</code> of <code>ChargingPod</code>.
	 * 
	 * @see #addRobot #genID #delete #removeAll #check #chargingPodPoints
	 *      #getChargeList #move
	 */
	private ArrayList<ChargingPod> chargeList;

	/**
	 * Storage Shelf objects used in the simulation, are stored in this
	 * <code>ArrayList</code> of <code>StorageShelf</code>.
	 * 
	 * @see #addStorage #genId #delete #removeAll #check #storageShelfPoints
	 *      #getStorageList
	 * @see #addToStoragePoints
	 */
	private ArrayList<StorageShelf> storageList = new ArrayList<StorageShelf>();

	/**
	 * Packing Station objects used in the simulation, are stored in this
	 * <code>ArrayList</code> of <code>PackingStation</code>.
	 * 
	 * @see #addPacking #genId #delete #removeAll #check #packingStationPoints
	 *      #getPackingID #getPackingStationsList
	 * @see #getPacking
	 */
	private ArrayList<PackingStation> packingList = new ArrayList<PackingStation>();

	/**
	 * Robot point coordinates used in the simulation, are stored in this
	 * <code>ArrayList</code> of <code>Point</code>.
	 * 
	 * @see #robotPoints #getRobotPoints
	 */
	private ArrayList<Point> robotPoints;

	/**
	 * Robots current Point coordinate and next Point coordinate, are stored in this
	 * <code>HashMap</code> of <code>Point, Point</code>.
	 * 
	 * @see #move #moveRobot #getUpdatedRobotCoordinates
	 */
	private HashMap<Point, Point> currentToNext;

	/**
	 * The <code>string</code> representation of Robot UID <code>key</code> to the
	 * Charging Pods UID <code>value</code> are mapped and stored in this
	 * <code>HashMap</code>.
	 * 
	 * @see #addToRobotsChargePod #getRobotChargePod #costEst
	 */
	private HashMap<String, String> robotsChargePod = new HashMap<String, String>();

	/**
	 * The coordinates of each Storage Shelfs <code>value</code> is stored in this
	 * <code>HashMap</code>, which is accessed by the Storage Shelfs UID
	 * <code>key</code>.
	 * 
	 * @see #addToStoragePoints #costEst #getDestinations #storagePoints
	 */
	private HashMap<String, Point> storagePoints = new HashMap<String, Point>();

	/**
	 * The coordinates of each Packing Stations <code>value</code> is stored in this
	 * <code>HashMap</code>, which is accessed by the Packing Stations UID
	 * <code>key</code>.
	 * 
	 * @see #addToPackingPoints #packingPoints
	 */
	private HashMap<String, Point> packingPoints = new HashMap<String, Point>();

	/**
	 * The coordinates of each Charging Pods <code>value</code> is stored in this
	 * <code>HashMap</code>, which is accessed by the Charging Pods UID
	 * <code>key</code>.
	 * 
	 * @see #addToChargePoints #chargePoints #costEst
	 */
	private HashMap<String, Point> chargePoints = new HashMap<String, Point>();

	/**
	 * The set up of the Warehouse simulation, reads from the "SIM" file are stored
	 * in this <code>ArrayList</code>. Stored in a <code>String</code> format.
	 * 
	 * @see #clearLists #fillLists #getConfiguration #readBatteryLevel
	 *      #readChargeRate
	 */
	private ArrayList<String> configuration = new ArrayList<String>();

	/**
	 * All the Robot and Charging Pods, that need to be created in the GUI from the
	 * "SIM" file are stored in this <code>ArrayList</code>. Stored in a
	 * <code>String</code> format.
	 * 
	 * @see #clearLists #fillLists #getPodRob #readRobotData
	 */

	private ArrayList<String> podRob = new ArrayList<String>();

	/**
	 * All the Storage Shelves, that need to be created in the GUI from the "SIM"
	 * file are stored in this <code>ArrayList</code>. Stored in a
	 * <code>String</code> format.
	 * 
	 * @see #clearLists #fillLists #getStorageShelves #readStorageData
	 */
	private ArrayList<String> shelves = new ArrayList<String>();

	/**
	 * All the Packing Stations, that need to be created in the GUI from the "SIM"
	 * file are stored in this <code>ArrayList</code>. Stored in a
	 * <code>String</code> format.
	 * 
	 * @see #clearLists #fillLists #getPackingStations #readPackingData
	 */
	private ArrayList<String> stations = new ArrayList<String>();

	/**
	 * Creates an instance of the Order class.
	 * 
	 * @see #costEst #fillLists #getOrder
	 */
	private Order order;

	/**
	 * Used to get the coordinates of the Packing Station.
	 * 
	 * @see #getPacking
	 */
	private static int next;

	/**
	 * Contains the number of ticks each Robot has to spend at their respective
	 * packing station, to complete and order.
	 * 
	 * @see #costEst #setWaitTime
	 */
	private int waitTime;

	/**
	 * True if the Robot is waiting at the Storage Shelf.
	 * 
	 * @see #move
	 */
	private boolean waited;

	/**
	 * Containing the order from the <code>ArrayList</code> from Cost Estimation
	 * Strategy. Stored in a <code>String</code> format.
	 * 
	 * @see #costEst #getDestinations
	 */
	private ArrayList<String> newOrder = new ArrayList<String>();

	/**
	 * Contains the number of completed orders.
	 * 
	 * @see #costEst #getCompleted
	 */
	private int completed;

	/**
	 * Warehouse Constructor.
	 * 
	 * Initialises and creates all the <code>ArrayList</code> and
	 * <code>HashMap</code>. Assigns the next order and sets {@link #next} value to
	 * 0.
	 */
	public Warehouse() {
		robotList = new ArrayList<Robot>();
		chargeList = new ArrayList<ChargingPod>();
		robotPoints = new ArrayList<Point>();
		currentToNext = new HashMap<Point, Point>();
		order = new Order();
		next = 0;
	}

	/**
	 * Creates a new robot and corresponding charging pod, and adds both to their
	 * own <code>ArrayList</code>.
	 * 
	 * @param ruid a <code>String</code>. Representing the UID of the Robot.
	 * @param cuid a <code>String</code>. Representing the UID of the Charging Pod.
	 * @param x a <code>int</code> value. Representing the X coordinate of the
	 *            Robot.
	 * @param y a <code>int</code> value. Representing the Y coordinate of the
	 *            Robot.
	 * @param batteryLevel a <code>int</code> value. Representing the value of the battery
	 *            level of the Robot.
	 * @param chargeRate a <code>int</code> value. Representing the value of the charge
	 *            rate for the Robot.
	 */
	public void addRobot(String ruid, String cuid, int x, int y, int batteryLevel, int chargeRate) {
		Robot robot = new Robot();
		robot.setStart(x, y);
		robot.setCoordinates(x, y);
		robot.setId(ruid);
		robotList.add(robot);
		for (int i = 0; i < robotList.size(); i++) {
			robotList.get(i).setBatteryCap(batteryLevel);
			robotList.get(i).updateBattery(batteryLevel);
		}

		ChargingPod chargePod = new ChargingPod(x, y);
		chargePod.setId(cuid);
		chargeList.add(chargePod);
		for (int i = 0; i < chargeList.size(); i++) {
			chargeList.get(i).updateChargeRate(chargeRate);
		}
	}

	/**
	 * Creates a new storage shelf and adds it to an <code>ArrayList</code>.
	 * 
	 * @param uid a <code>String</code> value. Representing the UID of the storage
	 *            shelf.
	 * @param x a <code>int</code> value. Representing the X coordinate of the
	 *            Storage shelf.
	 * @param y a <code>int</code> value. Representing the Y coordinate of the
	 *            Storage shelf.
	 */
	public void addStorage(String uid, int x, int y) {
		StorageShelf storage = new StorageShelf(x, y);
		storage.setId(uid);
		storageList.add(storage);
	}

	/**
	 * Creates a new packing station and adds it to an ArrayList.
	 * 
	 * @param uid a <code>String</code> value. Representing the UID of the Packing
	 *            station.
	 * @param x a <code>int</code> value. Representing the X coordinate of the
	 *            Packing station.
	 * @param y a <code>int</code> value. Representing the Y coordinate of the
	 *            Packing station.
	 */
	public void addPacking(String uid, int x, int y) {
		PackingStation packing = new PackingStation(x, y);
		packing.setId(uid);
		packingList.add(packing);
	}

	/**
	 * Iterates and generates the UID of each entity, based on the entities present
	 * in the Warehouse and stores each entity into their respective
	 * <code>ArrayList</code>.
	 * 
	 */
	public void genId() {
		ArrayList<Robot> robIds = new ArrayList<Robot>();
		for (int i = 0; i < robotList.size(); i++) {
			if (robotList.get(i).getID() != null) {
				robIds.add(robotList.get(i));
			}
			if (robotList.get(i).getID() == null) {
				robotList.get(i).generateID(robIds.size());
			}
		}
		ArrayList<ChargingPod> chIds = new ArrayList<ChargingPod>();
		for (int i = 0; i < chargeList.size(); i++) {
			if (chargeList.get(i).getID() != null) {
				chIds.add(chargeList.get(i));
			}
			if (chargeList.get(i).getID() == null) {
				chargeList.get(i).generateID(chIds.size());
			}
		}
		ArrayList<StorageShelf> ssIds = new ArrayList<StorageShelf>();
		for (int i = 0; i < storageList.size(); i++) {
			if (storageList.get(i).getID() != null) {
				ssIds.add(storageList.get(i));
			}
			if (storageList.get(i).getID() == null) {
				storageList.get(i).generateID(ssIds.size());
			}
		}
		ArrayList<PackingStation> psIds = new ArrayList<PackingStation>();
		for (int i = 0; i < packingList.size(); i++) {
			if (packingList.get(i).getID() != null) {
				psIds.add(packingList.get(i));
			}
			if (packingList.get(i).getID() == null) {
				packingList.get(i).generateID(psIds.size());
			}
		}
	}

	/**
	 * Deletes an item from entity lists depending on the coordinates given.
	 * 
	 * @param x a <code>int</code> value. Representing the X coordinate of the
	 *            entity.
	 * @param y a <code>int</code> value. Representing the Y coordinate of the
	 *            entity.
	 */
	public void delete(int x, int y) {
		for (int i = 0; i < robotList.size(); i++) {
			if (robotList.get(i).getRobotX() == (double) x && robotList.get(i).getRobotY() == (double) y) {
				robotList.remove(i);
				for (int j = i; j < robotList.size(); j++) {
					robotList.get(j).generateID(j);
				}
			}
		}
		for (int i = 0; i < chargeList.size(); i++) {
			if (chargeList.get(i).getChargingX() == (double) x && chargeList.get(i).getChargingY() == (double) y) {
				chargeList.remove(i);
				for (int j = i; j < chargeList.size(); j++) {
					chargeList.get(j).generateID(j);
				}
			}
		}
		for (int i = 0; i < storageList.size(); i++) {
			if (storageList.get(i).getStorageX() == (double) x && storageList.get(i).getStorageY() == (double) y) {
				storageList.remove(i);
				for (int j = i; j < storageList.size(); j++) {
					storageList.get(j).generateID(j);
				}
			}
		}

		for (int i = 0; i < packingList.size(); i++) {
			if (packingList.get(i).getPackingX() == (double) x && packingList.get(i).getPackingY() == (double) y) {
				packingList.remove(i);
				for (int j = i; j < packingList.size(); j++) {
					packingList.get(j).generateID(j);
				}
			}
		}
	}

	/**
	 * Clears all the <code>ArrayList</code> of entities if they contain any items.
	 */
	public void removeAll() {
		if (!robotList.isEmpty()) {
			robotList.clear();
		}
		if (!chargeList.isEmpty()) {
			chargeList.clear();
		}
		if (!storageList.isEmpty()) {
			storageList.clear();
		}
		if (!packingList.isEmpty()) {
			packingList.clear();
		}
	}

	/**
	 * Checks whether another entity is already in the position given.
	 * 
	 * @param x a <code>int</code> value. Representing the X coordinate of the
	 *            entity.
	 * @param y a <code>int</code> value. Representing the Y coordinate of the
	 *            entity.
	 * 
	 * @return Returns a <code>boolean</code> value. True if there is no entity in
	 *         the given position, false otherwise.
	 */
	public boolean check(int x, int y) {
		for (int i = 0; i < robotList.size(); i++) {
			if (robotList.get(i).getRobotX() == (double) x && robotList.get(i).getRobotY() == (double) y) {
				return false;
			}
		}

		for (int i = 0; i < chargeList.size(); i++) {
			if (chargeList.get(i).getChargingX() == (double) x && chargeList.get(i).getChargingY() == (double) y) {
				return false;
			}
		}

		for (int i = 0; i < storageList.size(); i++) {
			if (storageList.get(i).getStorageX() == (double) x && storageList.get(i).getStorageY() == (double) y) {
				return false;
			}
		}

		for (int i = 0; i < packingList.size(); i++) {
			if (packingList.get(i).getPackingX() == (double) x && packingList.get(i).getPackingY() == (double) y) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks to see if there is a Robot in an adjacent node.
	 * 
	 * @param x a <code>int</code> value. Representing the X coordinate of the
	 *            Robot.
	 * @param y a <code>int</code> value. Representing the Y coordinate of the
	 *            Robot.
	 * 
	 * @return Returns <code>boolean</code> value. True if there is a Robot there.
	 *         False otherwise.
	 */
	public boolean checkRobot(int x, int y) {
		for (int i = 0; i < robotList.size(); i++) {
			if (robotList.get(i).getRobotX() == (double) x && robotList.get(i).getRobotY() == (double) y) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets an <code>ArrayList</code> of coordinates of every robot.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>Points</code>. The
	 *         coordinate values of the Robot.
	 */
	public ArrayList<Point> robotPoints() {
		ArrayList<Point> robots = new ArrayList<Point>();
		for (int i = 0; i < robotList.size(); i++) {
			robots.add(robotList.get(i).getRobotCoordinates());
		}
		robotPoints = robots;
		return robots;
	}

	public ArrayList<Point> getRobotPoints() {
		return robotPoints;
	}

	/**
	 * Gets an <code>ArrayList</code> of <code>Point</code> coordinates of every
	 * charging pod.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>Points</code>. The
	 *         coordinate values of the Charging Pod.
	 */
	public ArrayList<Point> chargingPodPoints() {
		ArrayList<Point> chargingPods = new ArrayList<Point>();
		for (int i = 0; i < chargeList.size(); i++) {
			chargingPods.add(chargeList.get(i).getChargingCoordinates());
		}
		return chargingPods;
	}

	/**
	 * Gets an <code>ArrayList</code> of <code>Point</code> coordinates of every
	 * Packing Station.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>Points</code>. The
	 *         coordinate values of the Packign Station.
	 */
	public ArrayList<Point> packingStationPoints() {
		ArrayList<Point> packingStations = new ArrayList<Point>();
		for (int i = 0; i < packingList.size(); i++) {
			packingStations.add(packingList.get(i).getPackingCoordinates());
		}
		return packingStations;
	}

	/**
	 * Gets an <code>ArrayList</code> of <code>Point</code> coordinates of every
	 * Storage Shelf.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>Points</code>. The
	 *         coordinate values of the Storage Shelf.
	 */
	public ArrayList<Point> storageShelfPoints() {
		ArrayList<Point> storageShelfs = new ArrayList<Point>();
		for (int i = 0; i < storageList.size(); i++) {
			storageShelfs.add(storageList.get(i).getStorageCoordinates());
		}
		return storageShelfs;
	}

	/**
	 * Gets an <code>ArrayList</code> of <code>Point</code> coordinates for the free
	 * points in the grid.
	 * 
	 * @param numCols a <code>int</code> value. Representing the number of columns in
	 *            the grid.
	 * @param numRows a <code>int</code> value. Representing the number of rows in the
	 *            grid.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>Points</code>. The
	 *         coordinate values of the free spaces.
	 */
	public ArrayList<Point> freeSpacePoints(int numCols, int numRows) {
		ArrayList<Point> spaces = new ArrayList<Point>();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (checkRobot(i, j)) {
					Point point = new Point(i, j);
					spaces.add(point);
				}
			}
		}
		return spaces;
	}

	/**
	 * Returns the Packing Station the Robot has to go to.
	 * 
	 * @return Returns a <code>PackingStation</code>. The location of the packing
	 *         station.
	 */
	public PackingStation getPacking() {
		PackingStation packing = null;
		if (next < packingList.size()) {
			packing = packingList.get(next);
			next++;
		}
		if (next >= packingList.size()) {
			next = 0;
		}
		return packing;
	}

	/**
	 * Gets the <code>String</code> representation of {@link #completed}.
	 * 
	 * @return Returns <code>String</code> representation of {@link #completed}.
	 */
	public String getCompleted() {
		String complete = Integer.toString(completed);
		return complete;
	}

	/**
	 * Calls on the CostEstimationStrategy class {@link #distanceEstimator} method.
	 * To see if the robot can take an order or not.
	 * 
	 * @param i an <code>int</code> value. Used to get the robot of the
	 *            <code>ArrayList</code>.
	 * 
	 * @return Returns a <code>boolean</code> value. True if robot can move, false
	 *         otherwise.
	 */
	public boolean costEst(int i) {
		Robot robot = robotList.get(i);
		if (robot.completeOrder()) {
			completed++;
			System.out.println("Warehouse completedOrders: " + completed);
		}
		boolean value = true;
		System.out.println("CostEst being EXECUTED... " + robot.getOrderStatus());
		if (!robot.getOrderStatus()) {
			CostEstimationStrategy costEstimation = new CostEstimationStrategy(order, getPacking(), storagePoints);
			value = costEstimation.distanceEstimator(robot.getRobotX(), robot.getRobotY(), robot.getID(),
					robot.getBatteryLevel(), robotsChargePod, chargePoints);
			newOrder = costEstimation.getSentence();
			if (value == true) {
				order.completedSentence();
				robot.orderDecision(getDestinations());
				robot.setWaitTime(waitTime);
			} else {
				System.out.println("SET COMPLETED CALLED");
				robot.orderDecision(robot.setDestinationStart());
			}
		}
		return value;
	}

	/**
	 * 
	 * The current location is the <code>key</code> and the <code>value</code> is
	 * the destination used to tell the Robot where to move.
	 * 
	 * @param i an <code>int</code> value. Used to get the robot of the
	 *            <code>ArrayList</code>.
	 * 
	 * @return Returns {@link Warehouse#currentToNext} a <code>HashMap</code> of
	 *         where the Robot should move to next, according to its position and
	 *         the location of the destination.
	 */
	public HashMap<Point, Point> move(int i) {
		Robot robot = robotList.get(i);
		HashMap<Point, Point> temp = new HashMap<Point, Point>();
		temp.put(robot.getRobotCoordinates(), robot.getRobotCoordinates());
		currentToNext = temp;
		if (robot.atPacking() && robot.waitAtPacking()) {
			return currentToNext;
		} 
		else if (robot.atChargePod() && !robot.getOrderStatus()) {
			robot.charging(chargeList.get(i).getChargeRate());
			return currentToNext;
		}
		else if (robot.atShelf() && waited == false && robot.getOrderStatus()) {
			waited = true;
			return currentToNext;
		}
		else {
			waited = false;
			PathFinding pathFinding = new PathFinding();
			Point destination = robot.getDestination();
			pathFinding.pathCalc(destination, robotPoints());
			currentToNext = pathFinding.getNewNodes();
			robot.decreaseBatteryLevel();
			System.out.println("Battery level: " + robot.getBatteryLevel());
			return currentToNext;
		}
	}

	/**
	 * Changes the position of the robot, according to where it should go.
	 * 
	 * @param i an <code>int</code> value. Used to get the robot of the
	 *            <code>ArrayList</code>.
	 */
	public void moveRobot(int i) {
		ArrayList<Point> robots = robotPoints();
		Point coordinates = robots.get(i);
		Point next = currentToNext.get(coordinates);
		Double x = next.getX();
		Double y = next.getY();
		robotList.get(i).setCoordinates(x.intValue(), y.intValue());
	}

	/**
	 * Gets the information about the Robots coordinates, charge rate and battery
	 * level.
	 * 
	 * @return Returns a <code>String</code>. Gets the current state of each robot
	 *         entity, to display on the GUI.
	 */
	public String getRobotInfo() {
		String robotInfo = "";
		String robotID = "";
		String robotCharge = "";
		String robotCoordinates = "";
		getUpdatedRobotCoordinates();

		for (int i = 0; i < robotList.size(); i++) {
			robotID = robotList.get(i).getID() + " ";
			robotCharge = robotList.get(i).getBatteryLevel() + " ";
			robotInfo += "Robot ID :" + robotID + "\n" + "Charge Rate :" + robotCharge + "\n" + "Coordinates :" + "\n"
					+ "\n" + robotCoordinates;
		}
		return robotInfo;
	}

	/**
	 * Updates the robots coordinates, so that the updated coordinates are displayed
	 * in the GUI.
	 * 
	 * @return Returns a <code>String</code>. Representing the updated coordinates
	 *         of the robot.
	 */
	public String getUpdatedRobotCoordinates() {
		String coordinates = "";
		for (Point value : currentToNext.values()) {
			String x = String.valueOf(value.getX());
			String y = String.valueOf(value.getY());
			coordinates += x + " " + y;
		}
		return coordinates;

	}

	/**
	 * Gets the packing station UIDs out of an <code>ArrayList</code>.
	 * 
	 * @return Returns a <code>String</code>. Representing the UID of the Packing
	 *         Stations.
	 */
	public String getPackingID() {
		String packingID = "";
		for (int i = 0; i < packingList.size(); i++) {
			packingID += packingList.get(i).getID() + " \n";
		}
		return packingID;
	}

	/**
	 * Reads the needed values from a SIM file to create the necessary Robots.
	 */
	public void readRobotData() {
		for (int i = 0; i < podRob.size(); i += 5) {
			Integer x = Integer.valueOf(podRob.get(i + 3));
			Integer y = Integer.valueOf(podRob.get(i + 4));
			String ruid = podRob.get(i + 2);
			String cuid = podRob.get(i + 1);
			addRobot(ruid, cuid, x.intValue(), y.intValue(), readBatteryLevel(), readChargeRate());
		}
	}

	/**
	 * Reads the needed values from a SIM file to create the necessary Storage
	 * Shelfs.
	 */
	public void readStorageData() {
		for (int i = 0; i < shelves.size(); i += 4) {
			Integer x = Integer.valueOf(shelves.get(i + 2));
			Integer y = Integer.valueOf(shelves.get(i + 3));
			String uid = shelves.get(i + 1);
			addStorage(uid, x.intValue(), y.intValue());
		}
	}

	/**
	 * Reads the needed values from a SIM file to create the necessary Packing
	 * Stations.
	 */
	public void readPackingData() {
		for (int i = 0; i < stations.size(); i += 4) {
			Integer x = Integer.valueOf(stations.get(i + 2));
			Integer y = Integer.valueOf(stations.get(i + 3));
			String uid = stations.get(i + 1);
			addPacking(uid, x.intValue(), y.intValue());
		}
	}

	/**
	 * Reads battery level from a SIM file.
	 * 
	 * @return Returns a <code>int</code> value. Representing the battery capacity.
	 */
	public int readBatteryLevel() {
		Integer capacity = Integer.valueOf(configuration.get(7));
		return capacity.intValue();
	}

	/**
	 * Reads charge rate from a SIM file.
	 * 
	 * @return Returns a <code>int</code> value. Representing the charge rate.
	 */
	public int readChargeRate() {
		Integer chargeRate = Integer.valueOf(configuration.get(9));
		return chargeRate.intValue();
	}

	/**
	 * Uses the <code>HashMap</code> that maps the Robot UID to the correct Charging
	 * Pod UID, based on the number of robots present in the Warehouse.
	 */
	public void addToRobotsChargePod() {
		for (int i = 0; i < robotList.size(); i++) {
			String robot = "r" + i;
			String charge = "c" + i;
			robotsChargePod.put(robot, charge);
		}
	}

	/**
	 * Gets the <code>HashMap</code> created from the
	 * {@link #addToRobotsChargePod()} method.
	 * 
	 * @return Returns {@link #robotsChargePod} a <code>HashMap</code>. Representing
	 *         the Robots corresponding Charging Pod.
	 */
	public HashMap<String, String> getRobotsChargePod() {
		return robotsChargePod;
	}

	/**
	 * Gets the {@link #packingList} <code>ArrayList</code>.
	 * 
	 * @return Returns {@link #packingList} a <code>ArrayList</code>. Representing
	 *         Packing Stations present in the Warehouse.
	 */
	public ArrayList<PackingStation> getPackingStationList() {
		return packingList;
	}

	/**
	 * Gets the {@link #storageList} <code>ArrayList</code>.
	 * 
	 * @return Returns {@link #storageList} a <code>ArrayList</code>. Representing
	 *         Storage Shelves present in the Warehouse.
	 */
	public ArrayList<StorageShelf> getStorageList() {
		return storageList;
	}

	/**
	 * Gets the {@link #chargeList} <code>ArrayList</code>.
	 * 
	 * @return Returns {@link #chargeList} a <code>ArrayList</code>. Representing
	 *         Charging Pods present in the Warehouse.
	 */
	public ArrayList<ChargingPod> getChargeList() {
		return chargeList;
	}

	/**
	 * Add the coordinates to {@link #storagePoints} <code>HashMap</code> that
	 * contains Storage Shelves. The Storage Shelfs UID is the <code>key</code> and
	 * the <code>value</code> of the coordinate of the Storage Shelf.
	 */
	public void addToStoragePoints() {
		for (StorageShelf s : storageList) {
			storagePoints.put(s.getID(), s.getStorageCoordinates());
		}
	}

	/**
	 * Gets the {@link #storagePoints} <code>ArrayList</code>.
	 * 
	 * @return Returns {@link #storagePoints} a <code>ArrayList</code>. Representing
	 *         Storage Shelves present in the Warehouse.
	 */
	public HashMap<String, Point> storagePoints() {
		return storagePoints;
	}

	/**
	 * Add the coordinates to {@link #packingPoints} <code>HashMap</code> that
	 * contains Packing Stations. The Storage Shelfs UID is the <code>key</code> and
	 * the <code>value</code> of the coordinate of the Packing station.
	 */
	public void addToPackingPoints() {
		for (PackingStation p : getPackingStationList()) {
			packingPoints.put(p.getID(), p.getPackingCoordinates());
		}
	}

	/**
	 * Gets the {@link #packingPoints} <code>HashMap</code>.
	 * 
	 * @return Returns {@link #packingPoints} a <code>HashMap</code>. Representing
	 *         packing stations coordinates in the Warehouse.
	 */
	public HashMap<String, Point> packingPoints() {
		return packingPoints;
	}

	/**
	 * Add the coordinates to {@link #chargePoints} <code>HashMap</code> that
	 * contains Charing pods. The charging pod UID is the <code>key</code> and the
	 * <code>value</code> of the coordinates of the charging pod.
	 * 
	 */
	public void addToChargePoints() {
		for (ChargingPod c : getChargeList()) {
			chargePoints.put(c.getID(), c.getChargingCoordinates());
		}
	}

	/**
	 * Gets the {@link #chargePoints} <code>HashMap</code>.
	 * 
	 * @return Returns {@link #chargePoints} a <code>HashMap</code>. Representing
	 *         charging pod coordinates in the Warehouse.
	 */
	public HashMap<String, Point> chargePoints() {
		return chargePoints;
	}

	/**
	 * Fills the respective ArrayLists of type <code>String</code>, from the data in
	 * the "SIM" file, using a Scanner. With each word in the line, taking a slot in
	 * the <code>ArrayList</code>.
	 */
	public void fillLists() {
		try {
			Scanner scanner = new Scanner(order.getFile());
			clearLists();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if (line.contains("podRobot")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length; i++) {
						podRob.add(temp[i]);
					}

				} else if (line.contains("shelf")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length; i++) {
						shelves.add(temp[i]);
					}

				} else if (line.contains("station")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length; i++) {
						stations.add(temp[i]);
					}
				} else if (!line.contains("podRob") && !line.contains("shelf") && !line.contains("station")
						&& !line.contains("order")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length; i++) {
						configuration.add(temp[i]);
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		order.fillLists();
	}

	/**
	 * Populates the respective <code>HashMaps</code> containing the coordinates of
	 * their respective entities.
	 */
	public void addPoints() {
		addToStoragePoints();
		addToPackingPoints();
		addToChargePoints();
	}

	/**
	 * Clears the content of the <code>ArrayLists</code>, of the data from the "SIM"
	 * file.
	 */
	public void clearLists() {
		podRob.clear();
		shelves.clear();
		stations.clear();
		configuration.clear();
	}

	/**
	 * Gets an order.
	 * 
	 * @return order a <code>Order</code>. Returns an instance of the order class.
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Gets the configuration of the simulation.
	 * 
	 * @return Returns configuration a <code>ArrayList</code>. The ArrayList
	 *         contains <code>String</code>, of how the simulation should be set-up
	 *         according to the "SIM" file.
	 */
	public ArrayList<String> getConfiguration() {
		return configuration;
	}

	/**
	 * Gets all the Robot and Charging Pods from the <code>ArrayList</code>.
	 * 
	 * @return Returns podRob a <code>ArrayList</code>. The ArrayList contains
	 *         <code>String</code>, of the Robots and Charging Pods it should create
	 *         according to the "SIM" file.
	 */
	public ArrayList<String> getPodRob() {
		return podRob;
	}

	/**
	 * Gets all the storage shelves.
	 * 
	 * @return Returns shelves a <code>ArrayList</code>. The ArrayList contains
	 *         <code>String</code>, of the Storage Shelves it should create
	 *         according to the "SIM" file.
	 */
	public ArrayList<String> getStorageShelves() {
		return shelves;
	}

	/**
	 * Gets all the packing stations.
	 * 
	 * @return Returns stations a <code>ArrayList</code>. The ArrayList contains
	 *         <code>String</code>, of the Packing Stations it should create
	 *         according to the "SIM" file.
	 */
	public ArrayList<String> getPackingStations() {
		return stations;
	}

	/**
	 * Determines the {@link #waitTime} of the Robot.
	 * 
	 * @param Returns time a <code>String</code>. Sets the time a Robot must spend
	 *            delivering items to the Packing Station according to the order
	 *            from the "SIM" file. Assigns {@link #waitTime} an <code>int</code>
	 *            value.
	 */
	public void setWaitTime(String time) {
		Integer wait = Integer.parseInt(time);
		waitTime = wait.intValue();
	}

	/**
	 * Returns an ArrayList of <code>Point</code>, of each destination the Robot
	 * must go to, to complete an order.
	 * 
	 * @return
	 */
	public ArrayList<Point> getDestinations() {
		ArrayList<Point> destinations = new ArrayList<Point>();
		setWaitTime(newOrder.get(1));
		for (int i = 2; i < newOrder.size(); i++) {
			destinations.add(storagePoints.get(newOrder.get(i)));
			destinations.add(getPacking().passOnPoint());
		}
		System.out.println("COST estimation dest: " + newOrder.toString());
		return destinations;
	}

	/**
	 * Gets the orderStatus of each Robot in the <code>ArrayList</code>.
	 * 
	 * @param index an <code>int</code>. Used to take information out of the
	 *            <code>ArrayList</code>.
	 * 
	 * @return Returns a <code>boolean</code> value. True signals if the Robot is
	 *         carrying out an order and false if it is not.
	 */
	public boolean getOrderStatus(int index) {
		return robotList.get(index).getOrderStatus();
	}

	/**
	 * Gets all the robots in the simulation.
	 *
	 * @return Returns {@link #robotList} <code>ArrayList</code>. The ArrayList of
	 *         the Robots present in the Warehouse.
	 */
	public ArrayList<Robot> getRobotList() {
		return robotList;
	}
}