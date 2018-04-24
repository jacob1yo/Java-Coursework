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

	private ArrayList<Point> order;

	private Point nextDestination;

	private Point nextNode;

	private int index;

	private boolean carrying;

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
		nextDestination = getRobotCoordinates();
		order = new ArrayList<Point>();
		nextNode = new Point();
		int index =  0;
		carrying = false;
	}
	
	public void initializeOrder() {
		recieveOrder();
		/*while(!orderStatus) {
			recieveOrder();
		}*/
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
		order = CostEstimationStrategy.getDestinations();
		order.add(Order.chargePoints().get(Warehouse.getRobotsChargePod().get(getID())));
	}

	/**
	 * Decides whether a robot can take an order or not.
	 * @return <code>boolean</code> True if an order is accepted, otherwise false.
	 */
	public void orderDecision() {
		if(CostEstimationStrategy.getDecision()) {
			orderStatus = true;
		}
		else {
			orderStatus = false;
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
	 * Robots takes the items from the storage shelf.
	 */
	public void pickUpItems() {
		if(getRobotCoordinates() == nextDestination) {
			carrying = true;
			updateDestination();
		}
	}

	public Point nextInPath() {
		nextNode = order.get(index);
		index++;
		return nextNode;
	}

	public void updateDestination() {
		nextDestination = nextInPath();
	}
	
	public Point getDestination() {
		return nextDestination;
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
	public boolean compare(Entity r) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void setId(String newUid) {
		uid = newUid;
	}
}
