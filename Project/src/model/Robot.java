package model;

public class Robot {
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
}
