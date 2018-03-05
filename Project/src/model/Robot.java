package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

public class Robot implements Entity {
	private int safetyMargin, batteryLevel;
	private boolean orderStatus;
	
	public Robot() {
		safetyMargin = 20;
		batteryLevel = 100;
		orderStatus = false;
	}
	
	public int getBatteryLevel() {
		return batteryLevel;
	}
	
	public void decreaseBatteryLevel() {
		
	}
	
	public void processOrder() {
		
	}
	
	public boolean needsCharging() {
		
	}
	
	public void move() {
		
	}
	
	public void pickUpOrder() {
		
	}
	
	public void dropOrder() {
		
	}

	@Override
	public void generateID() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Robot compare(Robot r) {
		// TODO Auto-generated method stub
		return null;
	}
}
