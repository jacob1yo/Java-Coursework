package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 *
 */

public class Robot implements Entity {
	
	/**
	 * The x coordinate of a robot on the grid
	 */
	private int robotX;
	
	/**
	 * The y coordinate of a robot on the grid
	 */
	private int robotY;
	
	/**
	 * When calculating the distance, there is a twenty percent safety margin of
	 * battery power
	 * 
	 * @see #orderDecision
	 * 
	 *      The battery level of the robot
	 * @see #getBatteryLevel, #decreaseBatteryLevel
	 */
	private int safetyMargin, batteryLevel;

	/**
	 * Whether the robot is currently processing an order or not
	 * 
	 * @see #processOrder
	 */
	private boolean orderStatus;
	/**
	 * The unique identifier of each robot
	 * 
	 * @see #getID, #generateID
	 */
	private String uid;
	/**
	 * Stores the last number used for the ID
	 */
	private int lastNum;
	
	//add fields, explain how this might change

	public Robot() {
		safetyMargin = 20;
		batteryLevel = 100;
		orderStatus = false;
		lastNum = 0;
		generateID();
	}

	/**
	 * The current battery level of the robot.
	 * 
	 * @return <code>int</code> how much battery life of a robot is left.
	 *         {@link #batteryLevel}
	 */
	public int getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * Decreases the battery of a robot after every tick depending on if it's
	 * carrying an order or not.
	 */
	public void decreaseBatteryLevel() {
		if (orderStatus == true) {
			batteryLevel = batteryLevel - 2;
		} else {
			batteryLevel--;
		}
	}

	/**
	 * Decides whether a robot can take an order or not.
	 * @return <code>boolean</code> true if an order is accepted, otherwise false.
	 */
	public boolean orderDecision() {
		if (orderStatus == false) { //meaning a robot is free
			// sub-class method (Cost est strategy and Path finding strategy for batt level requirement)
			if (//subclass){
					/* if it returns true
					 * carry out the distance stuff
					 * invoke move method
					 * return true
			
			return false
					 */
		}
	}

	/**
	 * If a robot needs charging.
	 * 
	 * @return <code>boolean</code> true if needs charging, otherwise false.
	 *         {@link #batteryLevel}
	 */
	public boolean needsCharging() {

		return false; // if statement needed
	}

	/**
	 * Implements the movement of a robot
	 */
	public void move() {
		/**
		 * A* Determines it's movement, left, right etc etc Movement would be in it's
		 * own class
		 */

	}

	/**
	 * Robots takes the items from the storage shelf.
	 */
	public void pickUpItems(Robot r, StorageShelf ss) {

		if (orderDecision()) {
			orderStatus = true;
		} else
			orderStatus = false;
		// to be continued

	}

	/**
	 * When a robot drops off items at a packing station and waits until it's
	 * packed.
	 */
	public void dropOrder(Robot r, PackingStation ps) {

	}
	
	/**
	 * Gets the X co-ordinate of the robot.
	 * @return <code>int</code> The co-ordinate value.
	 */
	public int getRobotX() {
	}
	
	/**
	 * Gets the Y co-ordinate of the robot.
	 * @return <code>int</code> The co-ordinate value.
	 */
	public int getRobotY() {
	}

	/**
	 * Gets X co-ordinate of a shelf.
	 * @return <code>int</code> The X co-ordinate.
	 */
	public int getShelfX() {}
	
	/**
	 * Gets Y co-ordinate of a shelf.
	 * @return <code>int</code> The Y co-ordinate.
	 */
	public int getShelfY() {}
	
	/**
	 * Gets X co-ordinate of a packing station.
	 * @return <code>int</code> The X co-ordinate.
	 */
	public int getPackingX() {}
	
	/**
	 * Gets X co-ordinate of a packing station.
	 * @return <code>int</code> The Y co-ordinate.
	 */
	public int getPackingX() {}
	
	@Override
	public void generateID() {
		int num = lastNum++;
		uid = "r" + num;
	}

	@Override
	public String getID() {
		return uid;
	}

	@Override
	public boolean compare(Robot r) {
		// TODO Auto-generated method stub
		return true;
	}
}
