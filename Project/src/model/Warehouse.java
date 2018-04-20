package model;

import java.awt.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse {

	private ArrayList<Robot> robotList;
	private ArrayList<ChargingPod> chargeList;
	private ArrayList<StorageShelf> storageList;
	private ArrayList<PackingStation> packingList;
	private static ArrayList<Point> robotPoints;
	private HashMap<Point, Point> hashmap;

	public Warehouse() {
		robotList = new ArrayList<Robot>();
		chargeList = new ArrayList<ChargingPod>();
		storageList = new ArrayList<StorageShelf>();
		packingList = new ArrayList<PackingStation>();
		robotPoints = new ArrayList<Point>();
		hashmap = new HashMap<Point, Point>();
	}

	/**
	 * Creates a new robot and corresponding charging pod, and adds both to their
	 * own ArrayList
	 * 
	 * @param x
	 * @param y
	 * @param batteryLevel
	 * @param chargeRate
	 */
	public void addRobot(int x, int y, int batteryLevel, int chargeRate) {
		Robot robot = new Robot();
		robot.setCoordinates(x, y);
		robotList.add(robot);
		for (int i = 0; i < robotList.size(); i++) {
			robotList.get(i).updateBattery(batteryLevel);
			System.out.println(robotList.get(i).getID()); // delete this manual test after
		}

		ChargingPod chargePod = new ChargingPod(x, y);
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
	public void addStorage(int x, int y) {
		StorageShelf storage = new StorageShelf(x, y);
		storageList.add(storage);
		for (int i = 0; i < storageList.size(); i++) {
			System.out.println(storageList.get(i).getID()); // delete this manual test after
		}
	}

	/**
	 * Creates a new packing station and adds it to an ArrayList
	 * 
	 * @param x
	 * @param y
	 */
	public void addPacking(int x, int y) {
		PackingStation packing = new PackingStation(x, y);
		packingList.add(packing);
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
				robotList.get(i).resetID();
				robotList.remove(i);
				for (int n = 0; n < robotList.size(); n++) {
					robotList.get(n).generateID();
				}
			}
		}
		for (int i = 0; i < chargeList.size(); i++) {
			if (chargeList.get(i).getChargingX() == (double) x && chargeList.get(i).getChargingY() == (double) y) {
				chargeList.get(i).resetID();
				chargeList.remove(i);
				for (int n = 0; n < chargeList.size(); n++) {
					chargeList.get(n).generateID();
				}
			}
		}
		for (int i = 0; i < storageList.size(); i++) {
			if (storageList.get(i).getStorageX() == (double) x && storageList.get(i).getStorageY() == (double) y) {
				storageList.get(i).resetID();
				storageList.remove(i);
				for (int n = 0; n < storageList.size(); n++) {
					storageList.get(n).generateID();
				}
			}
		}

		for (int i = 0; i < packingList.size(); i++) {
			if (packingList.get(i).getPackingX() == (double) x && packingList.get(i).getPackingY() == (double) y) {
				packingList.get(i).resetID();
				packingList.remove(i);
				for (int n = 0; n < packingList.size(); n++) {
					packingList.get(n).generateID();
				}
			}
		}
	}

	/**
	 * Clears all the lists of entities if they contain any items
	 */
	public void removeAll() {
		if (!robotList.isEmpty()) {
			robotList.get(0).resetID();
			robotList.clear();
		}
		if (!chargeList.isEmpty()) {
			chargeList.get(0).resetID();
			chargeList.clear();
		}
		if (!storageList.isEmpty()) {
			storageList.get(0).resetID();
			storageList.clear();
		}
		if (!packingList.isEmpty()) {
			packingList.get(0).resetID();
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
	
	/**
	 * Reads needed values from a SIM file
	 */
	public void readRobotCoordinates() {
		for (int i = 0; i < Order.getPodRob().size(); i+=3) {
			Integer x = Integer.valueOf(Order.getPodRob().get(i));
			System.out.println(x);
			for (int j = 0; j < Order.getPodRob().size(); j+=4) {
				Integer y = Integer.valueOf(Order.getPodRob().get(j));
				System.out.println(y);
				addRobot(x.intValue(), y.intValue(), readBatteryLevel(), readChargeRate());
			}
		}
		
	}
	
	/**
	 * Reads battery level from a SIM file
	 * @return
	 */
	public int readBatteryLevel() {
		Integer capacity =  Integer.valueOf(Order.getConfiguration().get(7));
		return capacity.intValue();
	}
	
	/**
	 * Reads charge rate from a SIM file
	 */
	public int readChargeRate() {
		Integer chargeRate = Integer.valueOf(Order.getConfiguration().get(9));
		return chargeRate.intValue();
	}
	

	public static ArrayList<Point> getRobotPoints(){
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
	 * Get the an ArrayList of coordinates of every space that is available for a robot to move to
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
		Manhattan manhattan = new Manhattan();
		Point destination = new Point(4, 4);
		manhattan.manhattanCalc(destination);
		if (!robotList.isEmpty()) {
			//hashmap = robotList.get(0).move();
			return hashmap;
		}
		return null;
	}

	public void moveRobot(int i) {
		ArrayList<Point> robots = robotPoints();
		Point coordinates = robots.get(i);
		Point next = hashmap.get(coordinates);
		Double x = next.getX();
		Double y = next.getY();
		robotList.get(i).setCoordinates(x.intValue(), y.intValue());
	}

}
