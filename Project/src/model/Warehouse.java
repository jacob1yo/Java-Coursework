package model;

import java.util.ArrayList;

public class Warehouse {

	private ArrayList<Robot> robotList;
	private ArrayList<ChargingPod> chargeList;
	private ArrayList<StorageShelf> storageList;
	private ArrayList<PackingStation> packingList;


	public Warehouse(){
		robotList = new ArrayList<Robot>();
		chargeList = new ArrayList<ChargingPod>();
		storageList = new ArrayList<StorageShelf>();
		packingList = new ArrayList<PackingStation>();
	}

	/*
	 * Creates a new robot and corresponding charging pod, and adds both to their own arraylist
	 */
	public void addRobot(int x, int y, int batteryLevel, int chargeRate) {
		Robot robot = new Robot(x, y);
		robotList.add(robot);
		for(int i = 0; i < robotList.size(); i++) {
			robotList.get(i).updateBattery(batteryLevel);
			System.out.println(robotList.get(i).getID()); //delete this manual test after
		}

		ChargingPod chargePod = new ChargingPod(x, y);
		chargeList.add(chargePod);
		for(int i = 0; i < chargeList.size(); i++) {
			chargeList.get(i).updateChargeRate(chargeRate);
		}
	}	

	/*
	 * Creates a new storage shelf and adds it to an arraylist
	 */
	public void addStorage(int x, int y) {
		StorageShelf storage = new StorageShelf(x, y);
		storageList.add(storage);
		for(int i = 0; i < storageList.size(); i++) {
			System.out.println(storageList.get(i).getID()); //delete this manual test after
		}
	}

	/*
	 * Creates a new packing station and adds it to an arraylist
	 */
	public void addPacking(int x, int y) {
		PackingStation packing = new PackingStation(x, y);
		packingList.add(packing);
	}

	/*
	 * Deletes an item from entity lists depending on the coordinates given
	 */
	public void delete(int x, int y) {
			for(int i = 0; i < robotList.size(); i++) {
				if(robotList.get(i).getRobotX() == (double) x && robotList.get(i).getRobotY() == (double) y) {
					robotList.get(i).resetID();
					robotList.remove(i);
					for(int n = 0; n < robotList.size(); n++) {
						robotList.get(n).generateID();
					}
				}
		}
			for(int i = 0; i < chargeList.size(); i++) {
				if(chargeList.get(i).getChargingX() == (double) x && chargeList.get(i).getChargingY() == (double) y) {
					chargeList.get(i).resetID();
					chargeList.remove(i);
					for(int n = 0; n < chargeList.size(); n++) {
						chargeList.get(n).generateID();
					}
				}
		}
			for(int i = 0; i < storageList.size(); i++) {
				if(storageList.get(i).getStorageX() == (double) x && storageList.get(i).getStorageY() == (double) y) {
					storageList.get(i).resetID();
					storageList.remove(i);
					for(int n = 0; n < storageList.size(); n++) {
						storageList.get(n).generateID();
					}
				}
		}

			for(int i = 0; i < packingList.size(); i++) {
				if(packingList.get(i).getPackingX() == (double) x && packingList.get(i).getPackingY() == (double) y) {
					packingList.get(i).resetID();
					packingList.remove(i);
					for(int n = 0; n < packingList.size(); n++) {
						packingList.get(n).generateID();
					}
				}
			}
	}

	/*
	 * Clears all the lists of entities if they contain any items
	 */
	public void removeAll() {
		if(!robotList.isEmpty()) {
			robotList.get(0).resetID();
		}
		if(!chargeList.isEmpty()) {
			chargeList.get(0).resetID();
		}
		if(!storageList.isEmpty()) {
			storageList.get(0).resetID();
		}
		if(!packingList.isEmpty()) {
			packingList.get(0).resetID();
		}
		robotList.clear();
		chargeList.clear();
		storageList.clear();
		packingList.clear();
	}

	/*
	 * Checks whether another entity is already in the position given
	 *  
	 *  @return <code>boolean</code> True if there is no entity in the given position, false otherwise
	 */
	public boolean check(int x, int y) {
		for(int i = 0; i < robotList.size(); i++) {
			if(robotList.get(i).getRobotX() == (double) x && robotList.get(i).getRobotY() == (double) y) {
				return false;
			}
		}

		for(int i = 0; i < chargeList.size(); i++) {
			if(chargeList.get(i).getChargingX() == (double) x && chargeList.get(i).getChargingY() == (double) y) {
				return false;
			}
		}

		for(int i = 0; i < storageList.size(); i++) {
			if(storageList.get(i).getStorageX() == (double) x && storageList.get(i).getStorageY() == (double) y) {
				return false;
			}
		}

		for(int i = 0; i < packingList.size(); i++) {
			if(packingList.get(i).getPackingX() == (double) x && packingList.get(i).getPackingY() == (double) y) {
				return false;
			}
		}

		return true;
	}

}
