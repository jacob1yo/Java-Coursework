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
	 * 
	 * 
	 */
	private int safetyMargin, batteryLevel;
	
	/**
	 * Whether the robot is currently processing an order or not
	 * @see #processOrder
	 */
	private boolean orderStatus;
	
	public Robot() {
		safetyMargin = 20;
		batteryLevel = 100;
		orderStatus = false;
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
