package model;
import java.awt.Point;
import java.util.ArrayList;
public class Warehouse {

	public Point robotCoordinates;
	public Point storageCoordinates;
	public Point chargingCoordintates;
	public Point packingCoordinates;
	
	private ArrayList<Robot> robotList;
	private ArrayList<ChargingPod> chargeList;
	private ArrayList<StorageShelf> storageList;
	private ArrayList<PackingStation> packingList;

	public Warehouse(){
		/*
		this.chargingCoordintates=chargingCoordintates;
		this.robotCoordinates=robotCoordinates;
		this.storageCoordinates=storageCoordinates;
		this.packingCoordinates=packingCoordinates;
		*/
		robotList = new ArrayList<Robot>();
		chargeList = new ArrayList<ChargingPod>();
		storageList = new ArrayList<StorageShelf>();
		packingList = new ArrayList<PackingStation>();
	}
	
	public void addRobot(int x, int y, int batteryLevel, int chargeRate) {
		Robot robot = new Robot();
		robotList.add(robot);
		for(int i = 0; i < robotList.size(); i++) {
			robotList.get(i).updateBattery(batteryLevel);
		}
		
		ChargingPod chargePod = new ChargingPod();
		chargeList.add(chargePod);
		for(int i = 0; i < chargeList.size(); i++) {
			chargeList.get(i).updateChargeRate(chargeRate);
		}
	}	
	
	public void addStorage(int x, int y) {
		StorageShelf storage = new StorageShelf();
		storageList.add(storage);
	}
	
	public void addPacking(int x, int y) {
		PackingStation packing = new PackingStation();
		packingList.add(packing);
	}

}
