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
	private static ArrayList<StorageShelf> storages;

	public Warehouse() {
		robotList = new ArrayList<Robot>();
		chargeList = new ArrayList<ChargingPod>();
		storageList = new ArrayList<StorageShelf>();
		packingList = new ArrayList<PackingStation>();
		robotPoints = new ArrayList<Point>();
		hashmap = new HashMap<Point, Point>();
		storages = new ArrayList<StorageShelf>();
	}

	/**
	 * Creates a new robot and corresponding charging pod, and adds both to their
	 * own ArrayList.
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
				storages = storageList;
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
		Manhattan manhattan = new Manhattan();
		Point destination = new Point(4, 4);
		manhattan.manhattanCalc(destination);
		if (!robotList.isEmpty()) {
			hashmap = manhattan.getNewNodes();
			// hashmap = robotList.get(0).move();
			hashmap = manhattan.getNewNodes();
			System.out.println("hashmap size: " + hashmap.size());
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
		System.out.println(x);
			Integer y = Integer.valueOf(Order.getPodRob().get(i+4));
		System.out.println(y);
			addRobot(x.intValue(), y.intValue(), readBatteryLevel(), readChargeRate());
		}
	}
	
	public void readStorageData() {
		for(int i = 0; i < Order.getStorageShelves().size(); i+=4) {
			Integer x = Integer.valueOf(Order.getStorageShelves().get(i+2));
			Integer y = Integer.valueOf(Order.getStorageShelves().get(i+3));
			addStorage(x.intValue(), y.intValue());
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
	 * Allows displaying of Robot on screen
	 */
	public void showRobot() {
		for (int i = 2; i < Order.getPodRob().size(); i += 5) { // If the uid doesn't correspond with the one stated in
			// sim file, change uid so robot is placed in right
			// position
			Robot r = new Robot();
			if (r.getID().contains(Order.getPodRob().get(i))) {
				robotList.add(i, r);		//modify to add robots coordinates?
			} else {
				r.changeId(Order.getPodRob().get(i));
				robotList.add(r);

			}
		}
		for (int j = 1; j < Order.getPodRob().size(); j += 5) {
			Integer x = Integer.valueOf(Order.getPodRob().get(j + 2));
			Integer y = Integer.valueOf(Order.getPodRob().get(j + 3));
			ChargingPod cp = new ChargingPod(x.intValue(), y.intValue());

			if (cp.getID().contains(Order.getPodRob().get(j))) {
				chargeList.add(j, cp);
			} else {
				cp.changeId(Order.getPodRob().get(j));
				chargeList.add(cp);
			}	

		}
	}

	public void showPackingStations() {
		for (int i = 1; i < Order.getPackingStations().size(); i+=4) {
			Integer x = Integer.valueOf(Order.getPackingStations().get(i + 1));
			Integer y = Integer.valueOf(Order.getPodRob().get(i + 2));
			PackingStation ps = new PackingStation(x.intValue(), y.intValue());

			if (ps.getID().contains(Order.getPackingStations().get(i))) {
				packingList.add(i, ps);
			} else {
				ps.changeId(Order.getPackingStations().get(i));
				packingList.add(ps);
			}
		}
	}

	public void showStorageShelves() {
		for (int i  = 1; i < Order.getStorageShelves().size(); i+=4 ) {
			Integer x = Integer.valueOf(Order.getStorageShelves().get(i + 1));
			Integer y = Integer.valueOf(Order.getStorageShelves().get(i + 2));
			StorageShelf ss = new StorageShelf(x.intValue(), y.intValue());

			if (ss.getID().contains(Order.getStorageShelves().get(i))) {
				storageList.add(i, ss);
			} else {
				ss.changeId(Order.getStorageShelves().get(i));
				storageList.add(ss);
			}
		}
	}

	public static ArrayList<StorageShelf> getStorageShelfs(){
		return storages;
	}
}