package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

public class Robot implements Entity {
	/**
	 * When calculating the distance, there is a twenty percent safety margin of battery power
	 * 
	 * @see #
	 * 
	 * The battery level of the robot
	 * @see #getBatteryLevel, #decreaseBatteryLevel
	 */
	private int safetyMargin, batteryLevel;
	
	/**
	 * Whether the robot is currently processing an order or not
	 * @see #processOrder
	 */
	private boolean orderStatus;
	/**
	 * The unique identifier of each robot
	 * @see #getID, #generateID
	 */
	private String uid;
	/**
	 * Stores the last number used for the ID
	 */
	private int lastNum;
	
	public Robot() {
		safetyMargin = 20;
		batteryLevel = 100;
		orderStatus = false;
		lastNum = 0;
		generateID();
	}
	
	/**
	 * The current battery level of the robot.
	 * @return <code>int</code> how much battery life of a robot is left. {@link #batteryLevel}
	 */
	public int getBatteryLevel() {
		return batteryLevel;
	}
	
	/**
	 * Decreases the battery of a robot after every tick depending on if it's carrying an order or not.
	 */
	public void decreaseBatteryLevel() {
		if(orderStatus == true) {
			batteryLevel = batteryLevel - 2;
		}
		else {
		batteryLevel--; 
		}
	}
	
	/**
	 * Decides whether a robot can take an order or not.
	 * @return <code>boolean</code> true if an order is accepted, otherwise false.
	 */
	public boolean processOrder() {
		
	}
	
	/**
	 * If a robot needs charging.
	 * @return <code>boolean</code> true if needs charging, otherwise false. 
	 * {@link #batteryLevel}
	 */
	public boolean needsCharging() {
		
		
		return false; // if statement needed
	}
	/**
	 *Implements the movement of a robot 
	 */
	public void move() {
		
	}
	/**
	 * Robots takes the items from the storage shelf.
	 */
	public void pickUpOrder() {
		
	}
	
	/**
	 * When a robot drops off items at a packing station and waits until it's packed.
	 */
	public void dropOrder() {
		
	}

	@Override
	public void generateID() {
		int num = lastNum++;
		uid = "r + num";
	}

	@Override
	public String getID() {
		return uid;
	}

	@Override
	public Robot compare(Robot r) {
		// TODO Auto-generated method stub
		return null;
	}
}
