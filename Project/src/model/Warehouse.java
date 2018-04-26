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
	 * Storages is a static variable, which has the same data as Storage Lists. Therefore, the data can be returned through a static method.
	 * 
	 * @see #addStorage #genID
	 */
	private ArrayList<StorageShelf> storages;

	/**
	 * The string representation of Robot UID (key) to the Charging Pods UID (value) are mapped and stored in this HashMap.
	 * 
	 * @see #addToRobotsChargePod #getRobotChargePod
	 */
	private HashMap<String, String> robotsChargePod = new HashMap<String, String>();

	private HashMap<String, Point> storagePoints = new HashMap<String, Point>();
	private HashMap<String, Point> packingPoints = new HashMap<String, Point>();
	private HashMap<String, Point> chargePoints = new HashMap<String, Point>();

	private ArrayList<String> configuration = new ArrayList<String>();
	private ArrayList<String> podRob = new ArrayList<String>();
	private ArrayList<String> shelves = new ArrayList<String>();
	private ArrayList<String> stations = new ArrayList<String>();

	private Order order;

	private static int next;

	public Warehouse() {
		robotList = new ArrayList<Robot>();
		chargeList = new ArrayList<ChargingPod>();
		robotPoints = new ArrayList<Point>();
		currentToNext = new HashMap<Point, Point>();
		storages = new ArrayList<StorageShelf>();
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
			robotList.get(i).updateBattery(batteryLevel);
			System.out.println(robotList.get(i).getID()); // delete this manual test after
		}

		ChargingPod chargePod = new ChargingPod(x, y);
		chargePod.setId(cuid);
		chargeList.add(chargePod);
		System.out.println("ChargeL: " + chargeList.size());
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
	 * 
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
			//robot.getDestinations().clear();
			//robot.recieveOrder(getDestinations());
			value =  costEstimation.distanceEstimator(robot.getRobotX(), robot.getRobotY(), robot.getID(), robot.getBatteryLevel(), robotsChargePod, chargePoints);
			if(value == true) {
				setAssigned();
				robot.orderDecision(getDestinations());
			}
			else {
				robot.orderDecision(robot.getStart());
			}
		}
		return value;
	}

	public void setAssigned() {
		ArrayList<ArrayList<String>> sentence = order.getDecision();
		order.addToAssigned(sentence.get(0));
		order.removeFromDecision(sentence.get(0));	//figure this shit out
	}

	public HashMap<Point, Point> move(int i) {
		Robot robot = robotList.get(i);
		HashMap<Point, Point> temp = new HashMap<Point, Point>();
		temp.put(robot.getRobotCoordinates(), robot.getRobotCoordinates());
		currentToNext = temp;
		if(robot.atChargePod() && !robot.getOrderStatus()) {
			return currentToNext;
		}
		else{
			PathFinding pathFinding = new PathFinding();
			Point destination = robot.getDestination();
			pathFinding.pathCalc(destination);
			currentToNext = pathFinding.getNewNodes();
			return currentToNext;
		}

		/*currentToNext = null;
		CostEstimationStrategy costEst = new CostEstimationStrategy(order, getPacking(), storagePoints);
		Robot robot = costEstmation(costEst);
		ArrayList<Point> destinations = getDestination(costEst);
		if(robot != null) {
			PathFinding pathFinding = new PathFinding();
			robot.orderDecision(destinations);
			robot.recieveOrder(destinations);
			Point destination = robot.getDestination();
			System.out.println("Warehouse dest: " + destination);
			pathFinding.pathCalc(destination);
			currentToNext = pathFinding.getNewNodes();
		}
		return currentToNext;*/
	}

	public void moveRobot(int i) {
		ArrayList<Point> robots = robotPoints();
		Point coordinates = robots.get(i);
		Point next = currentToNext.get(coordinates);
		Double x = next.getX();
		Double y = next.getY();
		robotList.get(i).setCoordinates(x.intValue(), y.intValue());
	}

	public String getRobotInfo() {
		String robotInfo = "";
		String robotID = "";
		String robotCharge = "";
		String robotCoordinates = "";
		getUpdatedRobotCoordinates();

		for (int i = 0; i < robotList.size(); i++) {
			robotID = robotList.get(i). getID() + " ";
			robotCharge = robotList.get(i).getBatteryLevel() + " ";
			robotCoordinates += /*robotList.get(i).getRobotCoordinates().getX() + ", " + robotList.get(i).getRobotCoordinates().getY() + " ";*/
					robotInfo += "Robot ID :" + robotID + "\n" + "Charge Rate :" + robotCharge + "\n" + "Coordinates :" + robotCoordinates + "\n" + "\n";	
		}
		return robotInfo;
	}

	public String getUpdatedRobotCoordinates() {
		String coordinates = "";
		for (Point value : currentToNext.values()) {
			String x = String.valueOf(value.getX());
			String y = String.valueOf(value.getY());
			coordinates += x + " " + y;
			//System.out.println(coordinates);
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
	 * Reads needed values from a SIM file
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

	public void readStorageData() {
		for(int i = 0; i < shelves.size(); i+=4) {
			Integer x = Integer.valueOf(shelves.get(i+2));
			Integer y = Integer.valueOf(shelves.get(i+3));
			String uid = shelves.get(i+1);
			addStorage(uid, x.intValue(), y.intValue());
		}
	}

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
	 * 
	 */
	public void addToRobotsChargePod() {
		System.out.println("robot size: " + robotList.size());
		for(int i = 0; i < robotList.size(); i++) {
			String robot = "r" + i;
			String charge = "c" + i;
			robotsChargePod.put(robot, charge);
			System.out.println("Looping: " + robotsChargePod.get("r0"));
		}
	}

	public HashMap<String, String> getRobotsChargePod() {
		return robotsChargePod;
	}

	public ArrayList<StorageShelf> getStorageShelfs(){
		return storages;
	}

	public ArrayList<PackingStation> getPackingStationList(){
		return packingList;
	}

	public ArrayList<StorageShelf> getStorageList(){ //may need to change from static
		return storageList;
	}

	public ArrayList<ChargingPod> getChargeList(){
		return chargeList;
	}

	public void addToStoragePoints() {
		System.out.println("addToStoragePoints called... " + storageList.size());
		for(StorageShelf s: storageList) {
			System.out.print("For loop called... ");
			storagePoints.put(s.getID(), s.getStorageCoordinates());
		}
	}
	public HashMap<String, Point> storagePoints() {
		return storagePoints;
	}

	public void addToPackingPoints() {
		for(PackingStation p : getPackingStationList()) {
			packingPoints.put(p.getID(), p.getPackingCoordinates());
		}
	}
	public HashMap<String, Point> packingPoints(){
		return packingPoints;
	}

	public void addToChargePoints() {
		for(ChargingPod c : getChargeList()) {
			chargePoints.put(c.getID(), c.getChargingCoordinates());
		}
	}

	public HashMap<String, Point> chargePoints(){
		return chargePoints;
	}

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

	public void addPoints() {
		addToStoragePoints();
		addToPackingPoints();
		addToChargePoints();
	}

	public void clearLists() {
		podRob.clear();
		shelves.clear();
		stations.clear();
		configuration.clear();
	}

	public Order getOrder() {
		return order;
	}

	public ArrayList<String> getConfiguration(){
		return configuration;
	}

	public ArrayList<String> getPodRob(){
		return podRob;
	}

	public ArrayList<String> getStorageShelves(){
		return shelves;
	}

	public ArrayList<String> getPackingStations(){
		return stations;
	}

	public ArrayList<Point> getDestinations(){
		ArrayList<Point> destinations = new ArrayList<Point>();
		ArrayList<String> newOrder = new ArrayList<String>();
		newOrder = order.getAssigned();
		for(int i = 2; i < newOrder.size(); i++) {
			destinations.add(storagePoints.get(newOrder.get(i)));
			destinations.add(getPacking().passOnPoint());
		}
		System.out.println("Cost estimation dest: " + destinations.toString());
		return destinations;
	}

	public boolean getOrderStatus(int index) {
		return robotList.get(index).getOrderStatus();
	}
}