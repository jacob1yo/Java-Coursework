package model;

import java.awt.Point;
import java.util.ArrayList;

import gui.MainController;

/**
 * This class contains the implementation of the Robot methods.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 *         
 * @version 1.0
 */

public class Robot implements Entity  {

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
	protected final double SAFETY_MARGIN = 0.2;

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

	private ArrayList<Point> order;

	private Point nextDestination;

	private Point nextNode;

	private int index;

	private boolean carrying;

	private Point start;

	private int waitTime;

	private int originalBatteryLevel;

	/**
	 * The Point coordinates where the Robot is placed on the grid.
	 * 
	 * @see #setCoordinates #getRobotCoordinates #getRobotX #getRobotY
	 */
	protected Point robotCoordinates;

	/**
	 * Robot Constructor. Creates a <code>Robot</code> and sets a safety margin of 20% and a <code>boolean</code>
	 * value set to false automatically for the Order Status.
	 */	
	public Robot() {
		orderStatus = false;
		nextDestination = new Point();
		order = new ArrayList<Point>();
		//nextNode = new Point();
		int index =  0;
		carrying = false;
		start = new Point();
		waitTime = 0;
	}

	public void setStart(int x, int y) {
		start = new Point(x, y);
		nextDestination = start;
	}

	public void initializeOrder() {
		if(!orderStatus) {
			//recieveOrder();
		}
		//Call method to go to charge pod and charge
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

	public void charging(int chargeRate) {
		if(getRobotCoordinates().equals(start)) {
			if(batteryLevel < originalBatteryLevel) {
				batteryLevel += chargeRate;
			}
			System.out.println("Battery level: " + batteryLevel);
		}
	}

	/**
	 * Decreases the battery of a robot after every tick depending on if it's
	 * carrying an order or not.
	 */
	public void decreaseBatteryLevel() {
		if (carrying == true) {
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

	public ArrayList<Point> setDestinationStart() {
		ArrayList<Point> finalDest = new ArrayList<Point>();
		finalDest.add(start);
		return finalDest;
	}

	public void setBatteryCap(int batteryLevel) {
		originalBatteryLevel = batteryLevel;
	}

	/**
	 * 
	 */
	public void recieveOrder(ArrayList<Point> destination) {
		order = destination;
		order.add(start);
		index = 0;
		System.out.println("Robot order: " + order.toString());
	}

	/**
	 * Decides whether a robot can take an order or not.
	 * @return <code>boolean</code> True if an order is accepted, otherwise false.
	 */
	public void orderDecision(ArrayList<Point> destination) {
		ArrayList<Point> chargePod = new ArrayList<Point>();
		chargePod.add(start);
		if(!destination.equals(chargePod)) {
			orderStatus = true;
			System.out.println("Order accepted. " + destination);
		}
		recieveOrder(destination);
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
	public void pickUpItems() {
		if(atShelf()) {
			carrying = true;
		}
	}

	/**
	 * Returns true if robot needs to start or continue waiting at a packing station
	 * @return
	 */
	public boolean waitAtPacking() {
		if(waitTime > 0) {
			waitTime--;
			return true;
		}
		return false;
	}
	
	public void setWaitTime(int time) {
		waitTime = time;
	}

	public boolean atLocation() {
		if(getRobotCoordinates().equals(nextDestination)) {
			return true;
		}
		return false;
	}

	public Point nextInPath() {
		if(atLocation()) {
			if(index < order.size()) {
				pickUpItems();
				atPacking();
				finishOrder(index);
				nextDestination = order.get(index);
				index++;
			}
		}
		return nextDestination;
	}

	/*public Point updateDestination() {
		nextDestination = nextInPath();
		return nextDestination;
	}*/

	public Point getDestination() {
		return nextInPath();
	}

	/**
	 * When a robot drops off items at a packing station and waits until it's packed.
	 */
	public void dropOrder() {
		if(getRobotCoordinates() == order.get(order.size() - 2)) {
			carrying = false;
		}
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
	public void setId(String newUid) {
		uid = newUid;
	}

	public boolean getOrderStatus() {
		return orderStatus;
	}

	public double pythagoras(double x, double y, double X, double Y) {
		return Math.sqrt(Math.pow((X - x), 2) + Math.pow((Y - y),2));	
	}

	public ArrayList<Point> getDestinations(){
		return order;
	}

	public void finishOrder(int index) {
		if(index == (order.size() - 1)) {
			if(getRobotCoordinates().equals(order.get(order.size() - 2))) {
				System.out.println("orderStatus is set to false");
				orderStatus = false;
			}
		}
	}

	public boolean atPacking() {
		for(int i = 1; i < order.size(); i += 2) {
			if(getRobotCoordinates().equals(order.get(i))) {
				carrying = false;
				return true;
			}
		}
		return false;
	}
	
	public boolean atShelf() {
		for(int i = 0; i < order.size() - 1; i += 2) {
			if(getRobotCoordinates().equals(order.get(i))) {
				carrying = true;
				return true;
			}
		}
		return false;
	}

	public boolean atChargePod() {
		if(getRobotCoordinates().equals(start)) {
			return true;
		}
		return false;
	}



}
