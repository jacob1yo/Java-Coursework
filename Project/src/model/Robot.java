package model;

import java.awt.Point;
import java.util.ArrayList;

import gui.MainController;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 *
 */

public class Robot extends Warehouse implements Entity  {

	/**
	 * The x coordinate of a robot on the grid
	 */
	/**
	 * The y coordinate of a robot on the grid
	 */
	/*private int robotX;


	protected int robotY;*/

	/**
	 * When calculating the distance, there is a twenty percent safety margin of
	 * battery power
	 * 
	 * @see #orderDecision
	 * 
	 *      The battery level of the robot
	 * @see #getBatteryLevel, #decreaseBatteryLevel
	 */
	private int batteryLevel;
	private double safetyMargin;

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
	private static int lastNum = 0;

	protected Point robotCoordinates;

	//add fields, explain how this might change

	public Robot() {
		safetyMargin = 0.2;
		orderStatus = false;
		generateID();
	}
	/*
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
	 * 
	 */
	public void updateBattery(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	/**
	 * Decides whether a robot can take an order or not.
	 * @return <code>boolean</code> true if an order is accepted, otherwise false.
	 */
	public boolean  orderDecision() {
		if (orderStatus == false) {
			if(CostEstimationStrategy.distanceToSteps() < (safetyMargin*batteryLevel)+(batteryLevel)) {
				move();
				return true;
			}
			else {
				return false;
			}

			//meaning a robot is free
			// sub-class method (Cost est strategy and Path finding strategy for batt level requirement)
			//	if (//subclass){
			/* if it returns true
			 * carry out the distance stuff
			 * invoke move method
			 * return true
			 * else
			 *return false
			 */

		}
		return orderStatus;
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

	public void setCoordinates(int x, int y) {
		robotCoordinates = new Point(x, y);
	}

	/**
	 * Gets the X n' Y co-ordinates of the robot.
	 * @return <code>Point</code> The co-ordinate value.
	 */
	public Point getRobotCoordinates() {
		return robotCoordinates;
	}

	/**
	 * Gets the Y co-ordinate of the robot.
	 * @return <code>int</code> The co-ordinate value.
	 */
	public double getRobotY() {
		return robotCoordinates.getY();
	}

	/**
	 * Gets the x co-ordinate of the robot.
	 * @return <code>int</code> The co-ordinate value.
	 */
	public double getRobotX() {
		return robotCoordinates.getX();
	}

	/**
	 * Rests the lastNum field to 0.
	 */
	public void resetID() {
		lastNum = 0;
	}

	public ArrayList<Point> getRobotSpaces() {
		return MainController.getRobotSpaces();

	}

	public ArrayList<Point> getFreeSpacePoints(int numCols, int numRows) {
		return MainController.getFreeSpaces(numCols, numRows);
	}

	@Override
	public void generateID() {
		int num = lastNum++;
		uid = "r" + num;
	}
	
	public void changeId(String newUid) {
		uid = newUid;
		
		
	}

	@Override
	public String getID() {
		return uid;
	}

	@Override
	public boolean compare(Entity r) {
		// TODO Auto-generated method stub
		return true;
	}
}
