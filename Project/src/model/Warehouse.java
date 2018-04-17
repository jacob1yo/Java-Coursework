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
	
	public void addStorage(int x, int y) {
		StorageShelf storage = new StorageShelf(x, y);
		storageList.add(storage);
		for(int i = 0; i < storageList.size(); i++) {
			System.out.println(storageList.get(i).getID()); //delete this manual test after
		}
	}
	
	public void addPacking(int x, int y) {
		PackingStation packing = new PackingStation(x, y);
		packingList.add(packing);
	}
	
	public void delete(int x, int y) {
		for(int i = 0; i < robotList.size(); i++) {
			if(robotList.get(i).getRobotX() == (double) x && robotList.get(i).getRobotY() == (double) y) {
				robotList.get(i).decreaseUID();
				robotList.remove(i);
			}
		}
		
		for(int i = 0; i < chargeList.size(); i++) {
			if(chargeList.get(i).getChargingX() == (double) x && chargeList.get(i).getChargingY() == (double) y) {
				chargeList.get(i).decreaseUID();
				chargeList.remove(i);
			}
		}
		
		for(int i = 0; i < storageList.size(); i++) {
			if(storageList.get(i).getStorageX() == (double) x && storageList.get(i).getStorageY() == (double) y) {
				storageList.get(i).decreaseUID();
				storageList.remove(i);
			}
		}
		
		for(int i = 0; i < packingList.size(); i++) {
			if(packingList.get(i).getPackingX() == (double) x && packingList.get(i).getPackingY() == (double) y) {
				packingList.get(i).decreaseUID();
				packingList.remove(i);
			}
		}
		
		
	}
	
	/*public void removeRobot() {
		if((robotList.size()-1)>0) {
			robotList.remove(robotList.size()-1);
		}
		else {
			robotList.remove(robotList.get(0));
		}
	}
	
	public void removeCharge() {
		if((chargeList.size()-1)>0) { 
			chargeList.remove(chargeList.size()-1);
		}
		else {
			chargeList.remove(chargeList.get(0));
		}
	}
	
	public void removeStorage() {
		storageList.remove(storageList.size()-1);
	}
	
	public void removePacking() {
		packingList.remove(packingList.size()-1);
	}*/
	
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
