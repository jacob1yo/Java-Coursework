package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import gui.MainController;

/**
 * This class contains the implementation of the Robot methods.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 *         
 * @version 1.0
 */

public class Robot extends Warehouse implements Entity  {

	/**
	 * The value of the battery level of Robot objects.
	 * 
	 * @see #orderDecision #getBatteryLevel #decreaseBatteryLevel #updateBattery #orderDecision
	 */
	protected int batteryLevel;
	
	/**
	 * The value of the safety margin for each robots battery level
	 * 
	 * @see #orderDecision
	 */	
	protected double safetyMargin;

	/**
	 * Whether the robot is currently processing an order or not
	 * 
	 * @see #decreaseBatteryLevel #orderDecision #pickUpItems #dropOrder
	 */
	private boolean orderStatus;
	
	/**
	 * The unique identifier of each robot
	 * 
	 * @see #generateID, #setId
	 */
	private String uid;
	
	/**
	 * Stores the last number used for the ID
	 * 
	 * @see #resetID
	 */
	private static int lastNum = 0;
	
	/**
	 * The Point coordinates where the Robot is placed on the grid.
	 * 
	 * @see #setCoordinates #getRobotCoordinates #getRobotX #getRobotY
	 */
	protected Point robotCoordinates;
	
	/**
	 * Storage Shelf Constructor. Creates a <code>Robot</code> and sets a safety margin of 20%. It also has a <code>boolean</code>
	 * set to false automatically for the Order Status.
	 */	
	public Robot() {
		safetyMargin = 0.2;
		orderStatus = false;
	}
		
	/**
	 * The current battery level of the robot.
	 * 
	 * @return <code>int</code> How much battery life of a robot is left.
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
	 * Updates the battery level of the robot.
	 * 
	 * @param batteryLevel a {@link batteryLevel} containing value of the battery level for the robot.
	 */
	public void updateBattery(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	
	/**
	 * 
	 */
	public void recieveOrder() {
		
	}
	
	/**
	 * Decides whether a robot can take an order or not.
	 * @return <code>boolean</code> True if an order is accepted, otherwise false.
	 */
	public boolean orderDecision() {
		if (orderStatus == false) {
			if(CostEstimationStrategy.distanceEstimator() < (safetyMargin*batteryLevel)+(batteryLevel)) {
				Order.removeFromUnassigned(PackingStation.passOnIndex());
				Order.addToAssigned(PackingStation.getNextOrder());
				PackingStation.incrementIndex();
				move();
				orderStatus= true;
			}
			else {
				orderStatus= false;
			}
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
	 * 
	 * @param r a Robot object.
	 * @param ss a Storage Shelf object.
	 */
	public void pickUpItems(Robot r, StorageShelf ss) {

		if (orderDecision()) {
			orderStatus = true;
		} else
			orderStatus = false;
		// to be continued

	}

	/**
	 * When a robot drops off items at a packing station and waits until it's packed.
	 * 
	 * @param r a Robot object.
	 * @param ps a Packing Station object.
	 */
	public void dropOrder(Robot r, PackingStation ps) {
		//some code here
		orderStatus = true;
	}
	
	/**
	 * Sets the coordinate values for the robot and uses the X and Y int values to create a point on the grid.
	 * 
	 * @param x a int value. Used for the X coordinate.
	 * @param y a int value. Used for the Y coordinate.
	 */
	public void setCoordinates(int x, int y) {
		robotCoordinates = new Point(x, y);
	}

	/**
	 * Gets the X and Y coordinates of the robot.
	 * 
	 * @return <code>Point</code> The coordinate value.
	 */
	public Point getRobotCoordinates() {
		return robotCoordinates;
	}

	/**
	 * Gets the Y coordinate of the robot.
	 * @return <code>double</code> The Y coordinate value.
	 */
	public double getRobotY() {
		return robotCoordinates.getY();
	}

	/**
	 * Gets the X coordinate of the robot.
	 * @return <code>double</code> The X coordinate value.
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

	/**
	 * Gets the location of each robot from the {@link MainController class} and stores it in an ArrayList. 
	 * 
	 * @return Returns a <code>ArrayList<Point></code>. ArrayList of Point Objects.
	 */
	public ArrayList<Point> getRobotSpaces() {
		return MainController.getRobotSpaces();

	}

	/**
	 * Gets the free spaces on the of free spaces on the grid {@link MainController class} and stores it in an ArrayList. 
	 * 
	 * @param numCols an int value representing the number of columns in the current grid.
	 * @param numRows an int value representing the number of rows in the current grid.
	 * 
	 * @return Returns a <code>ArrayList<Point></code>. ArrayList of Point Objects.
	 */
	public ArrayList<Point> getFreeSpacePoints(int numCols, int numRows) {
		return MainController.getFreeSpaces(numCols, numRows);
	}

	@Override
	public void generateID(int id) {
		int num = id;
		uid = "r" + num;
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
	@Override
	public void setId(String newUid) {
		uid = newUid;
	}
}
