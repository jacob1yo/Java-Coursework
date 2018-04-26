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
	 * Robot objects used in the simulation, are stored in this ArrayList.
	 * 
	 * @see #addRobot #genId #delete #removeAll #check #checkRobot #robotPoints #move #moveRobot 
	 * @see #getRobotInfo #getRobotID #addToRobotsChargePod
	 */
	private ArrayList<Robot> robotList;

	/**
	 * Charging Pod objects used in the simulation, are stored in this ArrayList.
	 * 
	 * @see #addRobot #genID #delete #removeAll #check #chargingPodPoints #getChargeList
	 */
	private ArrayList<ChargingPod> chargeList; //if gui messes up, change back from static

	/**
	 * Storage Shelf objects used in the simulation, are stored in this ArrayList.
	 * 
	 * @see #addStorage #genID #delete #removeAll #check #storageShelfPoints #getStorageList
	 */
	private ArrayList<StorageShelf> storageList = new ArrayList<StorageShelf>();

	/**
	 * Packing Station objects used in the simulation, are stored in this ArrayList.
	 * 
	 * @see #addPacking #genID #delete #removeAll #check #packingStationPoints #getPackingID #getPackingStations
	 */
	private ArrayList<PackingStation> packingList = new ArrayList<PackingStation>();

	/**
	 * Robot point coordinates used in the simulation, are stored in this ArrayList.
	 * 
	 * @see #robotPoints
	 */
	private ArrayList<Point> robotPoints;

	/**
	 * Robots current Point coordinate and next Point coordinate, are stored in this HashMap.
	 * 
	 * @see #move #moveRobot
	 */
	private HashMap<Point, Point> currentToNext;

	/**
	 * The string representation of Robot UID (key) to the Charging Pods UID (value) are mapped and stored in this HashMap.
	 * 
	 * @see #addToRobotsChargePod #getRobotChargePod
	 */
	private HashMap<String, String> robotsChargePod = new HashMap<String, String>();

	/**
	 * The coordinates of each Storage Shelfs (value) is stored in this HashMap, which is accessed by the Storage Shelfs UID (key).
	 * 
	 * @see
	 */
	private HashMap<String, Point> storagePoints = new HashMap<String, Point>();
	
	/**
	 * The coordinates of each Packing Stations (value) is stored in this HashMap, which is accessed by the Packing Stations UID (key).
	 * 
	 * @see
	 */
	private HashMap<String, Point> packingPoints = new HashMap<String, Point>();
	
	/**
	 * The coordinates of each Charging Pods (value) is stored in this HashMap, which is accessed by the Charging Pods UID (key).
	 * 
	 * @see
	 */
	private HashMap<String, Point> chargePoints = new HashMap<String, Point>();

	/**
	 * The set up of the Warehouse simulation, read from the sim file are stored in this ArrayList.
	 * 
	 * @see
	 */
	private ArrayList<String> configuration = new ArrayList<String>();
	
	/**
	 * All the Robot and Charging Pods, that need to be created in the GUI from the "SIM" file are stored in this ArrayList. Stored in a <code>String</code> format.
	 * 
	 * @see
	 */
	
	private ArrayList<String> podRob = new ArrayList<String>();
	
	/**
	 * All the Storage Shelves, that need to be created in the GUI from the "SIM" file are stored in this ArrayList. Stored in a <code>String</code> format.
	 * 
	 * @see
	 */
	private ArrayList<String> shelves = new ArrayList<String>();
	
	/**
	 * All the Packing Stations, that need to be created in the GUI from the "SIM" file are stored in this ArrayList. Stored in a <code>String</code> format.
	 * 
	 * @see
	 */
	private ArrayList<String> stations = new ArrayList<String>();

	/**
	 * Creates an instance of the Order class.
	 * 
	 * @see
	 */
	private Order order;

	/**
	 * 
	 */
	private static int next;

	/**
	 * Contains the number of ticks each Robot has to spend at their repective packing station, to complete and order.
	 * 
	 * @see
	 */
	private int waitTime;

	/**
	 * 
	 */
	private boolean waited;

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
	 * own ArrayList.
	 * 
	 * @param ruid
	 * @param cuid
	 * @param x
	 * @param y
	 * @param batteryLevel
	 * @param chargeRate
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
	 * Creates a new storage shelf and adds it to an ArrayList
	 * 
	 * @param x
	 * @param y
	 */
	public void addStorage(String uid, int x, int y) {
		StorageShelf storage = new StorageShelf(x, y);
		storage.setId(uid);
		storageList.add(storage);
	}

	/**
	 * Creates a new packing station and adds it to an ArrayList
	 * 
	 * @param x
	 * @param y
	 */
	public void addPacking(String uid, int x, int y) {
		PackingStation packing = new PackingStation(x, y);
		packing.setId(uid);
		packingList.add(packing);
	}

	/**
	 * Generates the UID of each entity, based on the entities present in the Warehouse.
	 * 
	 * @see
	 */
	public void genId() {
		ArrayList<Robot> robIds = new ArrayList<Robot>();
		for(int i = 0; i < robotList.size(); i++){
			if(robotList.get(i).getID() != null) {
				robIds.add(robotList.get(i));
			}
			if(robotList.get(i).getID() == null) {
				robotList.get(i).generateID(robIds.size());
			}
		}
		ArrayList<ChargingPod> chIds = new ArrayList<ChargingPod>();
		for(int i = 0; i < chargeList.size(); i++) {
			if(chargeList.get(i).getID() != null) {
				chIds.add(chargeList.get(i));
			}
			if(chargeList.get(i).getID() == null) {
				chargeList.get(i).generateID(chIds.size());
			}
		}
		ArrayList<StorageShelf> ssIds = new ArrayList<StorageShelf>();
		for(int i = 0; i < storageList.size(); i++) {
			if(storageList.get(i).getID() != null) {
				ssIds.add(storageList.get(i));
			}
			if(storageList.get(i).getID() == null) {
				storageList.get(i).generateID(ssIds.size());
			}
		}
		ArrayList<PackingStation> psIds = new ArrayList<PackingStation>();
		for(int i = 0; i < packingList.size(); i++) {
			if(packingList.get(i).getID() != null) {
				psIds.add(packingList.get(i));
			}
			if(packingList.get(i).getID() == null) {
				packingList.get(i).generateID(psIds.size());
			}
		}
	}

	/**
	 * Deletes an item from entity lists depending on the coordinates given
	 * 
	 * @param x
	 * @param y
	 */
	public void delete(int x, int y) {
		for (int i = 0; i < robotList.size(); i++) {
			if (robotList.get(i).getRobotX() == (double) x && robotList.get(i).getRobotY() == (double) y) {
				robotList.remove(i);
				for(int j = i; j < robotList.size(); j++) {
					robotList.get(j).generateID(j);
				}
			}
		}
		for (int i = 0; i < chargeList.size(); i++) {
			if (chargeList.get(i).getChargingX() == (double) x && chargeList.get(i).getChargingY() == (double) y) {
				chargeList.remove(i);
				for(int j = i; j < chargeList.size(); j++) {
					chargeList.get(j).generateID(j);
				}
			}
		}
		for (int i = 0; i < storageList.size(); i++) {
			if (storageList.get(i).getStorageX() == (double) x && storageList.get(i).getStorageY() == (double) y) {
				storageList.remove(i);
				for(int j = i; j < storageList.size(); j++) {
					storageList.get(j).generateID(j);
				}
			}
		}

		for (int i = 0; i < packingList.size(); i++) {
			if (packingList.get(i).getPackingX() == (double) x && packingList.get(i).getPackingY() == (double) y) {
				packingList.remove(i);
				for(int j = i; j < packingList.size(); j++) {
					packingList.get(j).generateID(j);
				}
			}
		}
	}

	/**
	 * Clears all the lists of entities if they contain any items
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
	 * Checks whether another entity is already in the position given
	 * 
	 * @param x
	 * @param y
	 * @return <code>boolean</code> True if there is no entity in the given
	 *         position, false otherwise
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
	 * 
	 * @param x
	 * @param y
	 * @return
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
	 * Get the an ArrayList of coordinates of every robot
	 * 
	 * @return <code>ArrayList<Point></code>
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
	 * Get the an ArrayList of coordinates of every charging pod
	 * 
	 * @return <code>ArrayList<Point></code>
	 */
	public ArrayList<Point> chargingPodPoints() {
		ArrayList<Point> chargingPods = new ArrayList<Point>();
		for (int i = 0; i < chargeList.size(); i++) {
			chargingPods.add(chargeList.get(i).getChargingCoordinates());
		}
		return chargingPods;
	}

	/**
	 * Get the an ArrayList of coordinates of every packing station
	 * 
	 * @return <code>ArrayList<Point></code>
	 */
	public ArrayList<Point> packingStationPoints() {
		ArrayList<Point> packingStations = new ArrayList<Point>();
		for (int i = 0; i < packingList.size(); i++) {
			packingStations.add(packingList.get(i).getPackingCoordinates());
		}
		return packingStations;
	}

	/**
	 * Get the an ArrayList of coordinates of every storage shelf
	 * 
	 * @return <code>ArrayList<Point></code>
	 */
	public ArrayList<Point> storageShelfPoints() {
		ArrayList<Point> storageShelfs = new ArrayList<Point>();
		for (int i = 0; i < storageList.size(); i++) {
			storageShelfs.add(storageList.get(i).getStorageCoordinates());
		}
		return storageShelfs;
	}

	/**
	 * Get the an ArrayList of coordinates of every space that is available for a
	 * robot to move to
	 * 
	 * @return <code>ArrayList<Point></code>
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
	 * 
	 * @return 
	 */
	public PackingStation getPacking() {
		PackingStation packing = null;
		if(next < packingList.size()) {
			packing = packingList.get(next);
			next++;
		}
		if(next >= packingList.size()) {
			next = 0;
		}
		return packing;
	}

	/*public Robot costEstmation(CostEstimationStrategy costEst) {
		for(int i = 0; i < robotList.size(); i++) {
			Robot robot = robotList.get(i);
			boolean result = costEst.distanceEstimator(robot.getRobotX(), robot.getRobotY(), robot.getID(), robot.getBatteryLevel(), robotsChargePod, chargePoints);
			if(result) {
				return robot;
			}
		}
		return null;
	}

	public ArrayList<Point> getDestination(CostEstimationStrategy costEst){
		return costEst.getDestinations();
	}*/

	/**
	 * Returns true if robot can move, false otherwise
	 * @param i
	 * @return
	 */
	public boolean costEst(int i) {
		Robot robot = robotList.get(i);
		boolean value = true;
		System.out.println("CostEst being EXECUTED... " + robot.getOrderStatus());
		if(!robot.getOrderStatus()) {
			CostEstimationStrategy costEstimation = new CostEstimationStrategy(order, getPacking(), storagePoints);
			value =  costEstimation.distanceEstimator(robot.getRobotX(), robot.getRobotY(), robot.getID(), robot.getBatteryLevel(), robotsChargePod, chargePoints);
			if(value == true) {
				setAssigned();
				robot.orderDecision(getDestinations());
				robot.setWaitTime(waitTime);
			}
			else {
				System.out.println("SET COMPLETED CALLED");
				setCompleted();
				System.out.println("CHECK: " + order.getAssigned().toString());
				robot.orderDecision(robot.getStart());
			}
		}
		return value;
	}

	public void setCompleted() {
		ArrayList<ArrayList<String>> sentence = order.getDecision();
		order.addToCompleted(sentence.get(0));
		order.removeFromAssigned(sentence.get(0));
	}

	public void setAssigned() {
		ArrayList<ArrayList<String>> sentence = order.getDecision();
		order.addToAssigned(sentence.get(0));
		order.removeFromDecision(sentence.get(0));	//figure this shit out
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public HashMap<Point, Point> move(int i) {
		Robot robot = robotList.get(i);
		HashMap<Point, Point> temp = new HashMap<Point, Point>();
		temp.put(robot.getRobotCoordinates(), robot.getRobotCoordinates());
		currentToNext = temp;
		if(robot.atPacking() && robot.waitAtPacking()) {
			return currentToNext;
		}
		else {
			if(robot.atChargePod() && !robot.getOrderStatus()) {
				robot.charging(chargeList.get(i).getChargeRate());
				return currentToNext;
			}
			else if(robot.atShelf() && waited == false) {
				waited = true;
				return currentToNext;
			}
			else{
				waited = false;
				PathFinding pathFinding = new PathFinding();
				Point destination = robot.getDestination();
				pathFinding.pathCalc(destination);
				currentToNext = pathFinding.getNewNodes();
				robot.decreaseBatteryLevel();
				System.out.println("Battery level: " + robot.getBatteryLevel());
				return currentToNext;
			}
		}
	}

	/**
	 * Changes the position of the robot, according to where it should go.
	 * @param i
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
	 * Returns a <code>String</String> that gets the current state of each robot entity, to display on the GUI.
	 * @return
	 */
	public String getRobotInfo() {
		String robotInfo = "";
		String robotID = "";
		String robotCharge = "";
		String robotCoordinates = "";
		getUpdatedRobotCoordinates();

		for (int i = 0; i < robotList.size(); i++) {
			robotID = robotList.get(i). getID() + " ";
			robotCharge = robotList.get(i).getBatteryLevel() + " ";
			robotInfo += "Robot ID :" + robotID + "\n" + "Charge Rate :" + robotCharge + "\n" + "Coordinates :" + "\n" + "\n" + robotCoordinates;	
		}
		return robotInfo;
	}

	/**
	 * Updates the robots coordinates, so that the updated coordinates are displayed in the GUI. 
	 * @return
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

	/* 
	 * Currently unused
	 */
	public String getRobotID() {
		String robotID = ""; 
		for (int i = 0; i < robotList.size(); i++) {
			robotID += robotList.get(i).getID() + " \n";
		}
		return robotID;
	}



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
		for(int i = 0; i < podRob.size(); i+=5) {
			Integer x = Integer.valueOf(podRob.get(i+3));
			Integer y = Integer.valueOf(podRob.get(i+4));
			String ruid = podRob.get(i+2);
			String cuid = podRob.get(i+1);
			addRobot(ruid, cuid, x.intValue(), y.intValue(), readBatteryLevel(), readChargeRate());
		}
	}

	/**
	 * Reads the needed values from a SIM file to create the necessary Storage Shelfs.
	 */
	public void readStorageData() {
		for(int i = 0; i < shelves.size(); i+=4) {
			Integer x = Integer.valueOf(shelves.get(i+2));
			Integer y = Integer.valueOf(shelves.get(i+3));
			String uid = shelves.get(i+1);
			addStorage(uid, x.intValue(), y.intValue());
		}
	}

	/**
	 * Reads the needed values from a SIM file to create the necessary Packing Stations.
	 */
	public void readPackingData() {
		for(int i = 0; i < stations.size(); i+=4) {
			Integer x = Integer.valueOf(stations.get(i+2));
			Integer y = Integer.valueOf(stations.get(i+3));
			String uid = stations.get(i+1);
			addPacking(uid, x.intValue(), y.intValue());
		}
	}

	/**
	 * Reads battery level from a SIM file
	 * 
	 * @return
	 */
	public int readBatteryLevel() {
		Integer capacity = Integer.valueOf(configuration.get(7));
		return capacity.intValue();
	}

	/**
	 * Reads charge rate from a SIM file
	 */
	public int readChargeRate() {
		Integer chargeRate = Integer.valueOf(configuration.get(9));
		return chargeRate.intValue();
	}

	/**
	 * Add to the <code>HashMap</code> that maps the Robot UID to the correct Charging Pod UID, based on the number of robots present in the Warehouse.
	 */
	public void addToRobotsChargePod() {
		for(int i = 0; i < robotList.size(); i++) {
			String robot = "r" + i;
			String charge = "c" + i;
			robotsChargePod.put(robot, charge);
		}
	}

	/**
	 * Returns the HashMap of a Robots corresponding Charging Pod.
	 * @return robotsChargePod <code>HashMap</code>
	 */
	public HashMap<String, String> getRobotsChargePod() {
		return robotsChargePod;
	}

	/**
	 * Returns an ArrayList of the Packing Stations present in the Warehouse.
	 * @return packingList <code>ArrayList</code>
	 */
	public ArrayList<PackingStation> getPackingStationList(){
		return packingList;
	}

	/**
	 * Returns an ArrayList of the Storage Shelves present in the Warehouse.
	 * @return storageList <code>ArrayList</code>
	 */
	public ArrayList<StorageShelf> getStorageList(){ 
		return storageList;
	}

	/**
	 * Returns an ArrayList of the Charging Pods present in the Warehouse.
	 * @return chargeList <code>ArrayList</code>
	 */
	public ArrayList<ChargingPod> getChargeList(){
		return chargeList;
	}

	/**
	 * Add to the HashMap that contains the coordinates of each Storage Shelf. The Storage Shelfs UID is the key and the value if the coordinate of the Storage Shelf.
	 */
	public void addToStoragePoints() {
		for(StorageShelf s: storageList) {
			storagePoints.put(s.getID(), s.getStorageCoordinates());
		}
	}
	
	/**
	 * Returns a HashMap of the coordinates of the Storage Shelves.
	 * @return storagePoints <code>HashMap</code>
	 */
	public HashMap<String, Point> storagePoints() {
		return storagePoints;
	}

	/**
	 * Add to the HashMap that contains the coordinates of each Packing Station. The Packing Stations UID is the key and the value if the coordinate of the Packing Station.
	 */
	public void addToPackingPoints() {
		for(PackingStation p : getPackingStationList()) {
			packingPoints.put(p.getID(), p.getPackingCoordinates());
		}
	}
	
	/**
	 * Returns a HashMap of the coordinates of the Packing Stations.
	 * @return packingPoints <code>HashMap</code>
	 */
	public HashMap<String, Point> packingPoints(){
		return packingPoints;
	}

	/**
	 * Add to the HashMap that contains the coordinates of each Charging Pod. The Charging Pods UID is the key and the value if the coordinate of the Charging Pod.
	 */
	public void addToChargePoints() {
		for(ChargingPod c : getChargeList()) {
			chargePoints.put(c.getID(), c.getChargingCoordinates());
		}
	}

	/**
	 * Returns a HashMap of the coordinates of the Charging podss
	 * @return chargePoints <code>HashMap</code>
	 */
	public HashMap<String, Point> chargePoints(){
		return chargePoints;
	}

	/**
	 * Fills the respective ArrayLists of type <code>String</code>, from the data in the "SIM" file, using a Scanner. With each word in the line, taking a slot in the ArrayList.
	 */
	public void fillLists() {
		try {
			Scanner scanner = new Scanner(order.getFile());
			clearLists();
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if(line.contains("podRobot")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length;i++) {
						podRob.add(temp[i]);
					}

				}
				else if(line.contains("shelf")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length;i++) {
						shelves.add(temp[i]);
					}

				}
				else if(line.contains("station")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length;i++) {
						stations.add(temp[i]);
					}
				}
				else if(!line.contains("podRob") && !line.contains("shelf") && !line.contains("station") && !line.contains("order")) {
					String[] temp = line.split(" ");
					for(int i = 0; i < temp.length; i++) {
						configuration.add(temp[i]);
					}
				}
			} scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		order.fillLists();
	}

	/**
	 * Populates the respective HashMaps containing the coordinates of their respective entities.
	 */
	public void addPoints() {
		addToStoragePoints();
		addToPackingPoints();
		addToChargePoints();
	}

	/**
	 * Clears the content of the ArrayLists, of the data from the "SIM" file.
	 */
	public void clearLists() {
		podRob.clear();
		shelves.clear();
		stations.clear();
		configuration.clear();
	}

	/**
	 * Returns an instance of the order class.
	 * @return order <code>Order</code>
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * Returns the ArrayList contains <code>String</code>, of how the simulation should be set-up according to the "SIM" file. 
	 * @return configuration <code>ArrayList</code>
	 */
	public ArrayList<String> getConfiguration(){
		return configuration;
	}

	/**
	 * Returns the ArrayList contains <code>String</code>, of the Robots and Charging Pods it should create according to the "SIM" file.
	 * @return podRob <code>ArrayList</code>
	 */
	public ArrayList<String> getPodRob(){
		return podRob;
	}

	/**
	 * Returns the ArrayList contains <code>String</code>, of the Storage Shelves it should create according to the "SIM" file.
	 * @return shelves <code>ArrayList</code>
	 */
	public ArrayList<String> getStorageShelves(){
		return shelves;
	}

	/**
	 * Returns the ArrayList contains <code>String</code>, of the Packing Stations it should create according to the "SIM" file.
	 * @return stations <code>ArrayList</code>
	 */
	public ArrayList<String> getPackingStations(){
		return stations;
	}

	/**
	 * Sets the time a Robot must spend delivering items to the Packing Station according to the order from the "SIM" file.
	 * @param time
	 */
	public void setWaitTime(String time) {
		Integer wait = Integer.parseInt(time);
		waitTime = wait.intValue();
	}
	
	/**
	 * Returns an ArrayList of <code>Point</code>, of each destination the Robot must go to, to complete an order.
	 * @return
	 */
	public ArrayList<Point> getDestinations(){
		ArrayList<Point> destinations = new ArrayList<Point>();
		ArrayList<String> newOrder = new ArrayList<String>();
		newOrder = order.getAssigned();
		setWaitTime(newOrder.get(1));
		for(int i = 2; i < newOrder.size(); i++) {
			destinations.add(storagePoints.get(newOrder.get(i)));
			destinations.add(getPacking().passOnPoint());
		}
		System.out.println("COST estimation dest: " + newOrder.toString());
		return destinations;
	}

	/**
	 * Returns the order status of the Robot, which signals if the Robot is carrying out an order or not.
	 * @param index
	 * @return <code>boolean</code> orderStatus.
	 */
	public boolean getOrderStatus(int index) {
		return robotList.get(index).getOrderStatus();
	}
	
	/**
	 * Returns an ArrayList of the Robots present in the Warehouse. 
	 * @return robotList <code>ArrayList</code>
	 */
	public ArrayList<Robot> getRobotList() {
		return robotList;
	}
}