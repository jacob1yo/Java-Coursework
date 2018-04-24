package model;

import java.awt.Point;

import java.util.ArrayList;
import java.util.HashMap;

/**
* This class contains the implementation of the Warehouse methods.
* 
* @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
*         Vivek Bhukhan, Christos Dolopikos.
*         
* @version 1.0
*/

public class Warehouse {

	private ArrayList<Robot> robotList;
	static ArrayList<ChargingPod> chargeList; //if gui messes up, change back from static
	static ArrayList<StorageShelf> storageList = new ArrayList<StorageShelf>();
	static ArrayList<PackingStation> packingList = new ArrayList<PackingStation>();
	private static ArrayList<Point> robotPoints;
	private HashMap<Point, Point> currentToNext;
	private static ArrayList<StorageShelf> storages;
	private static HashMap<String, String> robotsChargePod = new HashMap<String, String>();

	public Warehouse() {
		robotList = new ArrayList<Robot>();
		chargeList = new ArrayList<ChargingPod>();
		robotPoints = new ArrayList<Point>();
		currentToNext = new HashMap<Point, Point>();
		storages = new ArrayList<StorageShelf>();
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

	public static ArrayList<Point> getRobotPoints() {
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

	public HashMap<Point, Point> move() {
		currentToNext = null;
		for(int i = 0; i < robotList.size(); i++) {
			PathFinding pathFinding = new PathFinding();
			robotList.get(i).orderDecision();
			//robotList.get(i).initializeOrder();
			Point destination = robotList.get(i).getDestination();
			System.out.println("Robot dest: " + destination);
			pathFinding.pathCalc(destination);
			currentToNext = pathFinding.getNewNodes();
		}
		return currentToNext;
		
		/*PathFinding pathFinding = new PathFinding();
		Point destination = new Point(4, 4);
		pathFinding.pathCalc(destination);
		if (!robotList.isEmpty()) {
			currentToNext = pathFinding.getNewNodes();
			// currentToNext = robotList.get(0).move();
			currentToNext = pathFinding.getNewNodes();
			System.out.println("currentToNext size: " + currentToNext.size());
			return currentToNext;
		}
		return null;*/
	}

	public void moveRobot(int i) {
		ArrayList<Point> robots = robotPoints();
		Point coordinates = robots.get(i);
		Point next = currentToNext.get(coordinates);
		Double x = next.getX();
		Double y = next.getY();
		robotList.get(i).setCoordinates(x.intValue(), y.intValue());
	}
	
	public void readOrders() {
		for(int i = 0; i < robotList.size(); i++) {
			robotList.get(i).recieveOrder();
		}
	}
	
	public String getRobotInfo() {
		String robotInfo = "";
		String robotID = "";
		String robotCharge = "";
		String robotCoordinates = "";
		
		for (int i = 0; i < robotList.size(); i++) {
			robotID = robotList.get(i). getID() + " ";
			robotCharge = robotList.get(i).getBatteryLevel() + " ";
			robotCoordinates = robotList.get(i).getRobotCoordinates().getX() + ", " + robotList.get(i).getRobotCoordinates().getY() + " ";
			
			robotInfo += "Robot ID :" + robotID + "\n" + "Charge Rate :" + robotCharge + "\n" + "Coordinates :" + robotCoordinates + "\n" + "\n";	
		}
			return robotInfo;
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
		for(int i = 0; i < Order.getPodRob().size(); i+=5) {
			Integer x = Integer.valueOf(Order.getPodRob().get(i+3));
			Integer y = Integer.valueOf(Order.getPodRob().get(i+4));
			String ruid = Order.getPodRob().get(i+2);
			String cuid = Order.getPodRob().get(i+1);
			addRobot(ruid, cuid, x.intValue(), y.intValue(), readBatteryLevel(), readChargeRate());
		}
	}
	
	public void readStorageData() {
		for(int i = 0; i < Order.getStorageShelves().size(); i+=4) {
			Integer x = Integer.valueOf(Order.getStorageShelves().get(i+2));
			Integer y = Integer.valueOf(Order.getStorageShelves().get(i+3));
			String uid = Order.getStorageShelves().get(i+1);
			addStorage(uid, x.intValue(), y.intValue());
		}
	}
	
	public void readPackingData() {
		for(int i = 0; i < Order.getPackingStations().size(); i+=4) {
			Integer x = Integer.valueOf(Order.getPackingStations().get(i+2));
			Integer y = Integer.valueOf(Order.getPackingStations().get(i+3));
			String uid = Order.getPackingStations().get(i+1);
			addPacking(uid, x.intValue(), y.intValue());
		}
	}

	/**
	 * Reads battery level from a SIM file
	 * 
	 * @return
	 */
	public int readBatteryLevel() {
		Integer capacity = Integer.valueOf(Order.getConfiguration().get(7));
		return capacity.intValue();
	}

	/**
	 * Reads charge rate from a SIM file
	 */
	public int readChargeRate() {
		Integer chargeRate = Integer.valueOf(Order.getConfiguration().get(9));
		return chargeRate.intValue();
	}
	
	/**
	 * 
	 */
	public void addToRobotsChargePod() {
		for(int i = 0; i < robotList.size(); i++) {
			String robot = "r" + i;
			String charge = "c" + i;
			robotsChargePod.put(robot, charge);
		}
	}
	
	public static HashMap<String, String> getRobotsChargePod() {
		return robotsChargePod;
	}
	
	public static ArrayList<StorageShelf> getStorageShelfs(){
		return storages;
	}
	
	public static ArrayList<PackingStation> getPackingStations(){
		return packingList;
	}
	
	public static ArrayList<StorageShelf> getStorageList(){ //may need to change from static
		return storageList;
	}
	
	public static ArrayList<ChargingPod> getChargeList(){
		return chargeList;
	}
}