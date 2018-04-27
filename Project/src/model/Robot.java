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

public class Robot implements Entity {

	/**
	 * The value of the battery level of Robot objects.
	 * 
	 * @see #charging #decreaseBatteryLevel #getBatteryLevel #updateBattery CostEstimationStrategy#distanceEstimator
	 */
	protected int batteryLevel;

	/**
	 * The value of the safety margin for each robots battery level.
	 * 
	 * @see CostEstimationStrategy#distanceEstimator
	 */
	protected final double SAFETY_MARGIN = 0.2;

	/**
	 * The <code>boolean</code> value representing whether the robot is currently
	 * processing an order or not
	 * 
	 * @see #finishOrder #getOrderStatus #orderDecision
	 */
	private boolean orderStatus;

	/**
	 * The <code>String</code> unique identifier of each robot.
	 * 
	 * @see #generateID #getID #setId
	 */
	private String uid;

	/**
	 * ArrayList storing <code>Point</code> coordinates of the Shelves, Packing
	 * Stations and Charging Pods.
	 * 
	 * @see #atPacking #atShelf #dropOrder #finishOrder #getDestinations #nextInPath #receiveOrder
	 */
	private ArrayList<Point> order;

	/**
	 * <code>Point</code> coordinates of the next destination which the robot needs
	 * to go to.
	 * 
	 * @see #atLocation #nextInPath #setStart
	 */
	private Point nextDestination;

	/**
	 * The <code>int</code> value of an index of a <code>ArrayList</code>.
	 * 
	 * @see #nextInPath #receiveOrder
	 */
	private int index;

	/**
	 * True or false <code>boolean</code> value when the robot is carrying a storage
	 * shelf.
	 * 
	 * @see #atPacking #atShelf #decreaseBatteryLevel #dropOrder #pickUpItems
	 */
	private boolean carrying;

	/**
	 * The starting <code>Point</code> coordinate where the robot and its
	 * corresponding charging pod is placed on the grid.
	 * 
	 * @see #atChargePod #charging #orderDecision #receiveOrder #setDestinationStart #setStart
	 */
	private Point start;

	/**
	 * The <code>int</code> value of how long the Robot should wait for at the
	 * Packing Station.
	 * 
	 * @see #setWaitTime #waitAtPacking
	 */
	private int waitTime;

	/**
	 * The <code>int</code> value of the Robots original battery level.
	 * 
	 * @see #charging #setBatteryCap
	 */
	private int originalBatteryLevel;

	/**
	 * The <code>Point</code> coordinates where the Robot is placed on the grid.
	 * 
	 * @see #getRobotCoordinates #getRobotX #getRobotY #setCoordinates
	 */
	protected Point robotCoordinates;
	
	/**
	 * Contains <code>boolean</code> to determine if a robot has completed an {@link #order}.
	 */
	private boolean completed;

	/**
	 * Robot Constructor.
	 * 
	 * Creates a <code>Robot</code> object and sets both the <code>boolean</code>
	 * value for Order Status and the carrying <code>boolean</code> value to false
	 * automatically. Wait time is initialised to 0.
	 */
	public Robot() {
		orderStatus = false;
		nextDestination = new Point();
		order = new ArrayList<Point>();
		index = 0;
		carrying = false;
		start = new Point();
		waitTime = 0;
	}

	/**
	 * Sets the starting X and Y coordinate values which is then assigned to
	 * <code>start</code> as the starting <code>Point</code> coordinate for the
	 * Robot.
	 * 
	 * @param x a <code>int</code> value. Used to set the X coordinate.
	 * @param y a <code>int</code> value. Used to set the Y coordinate.
	 */
	public void setStart(int x, int y) {
		start = new Point(x, y);
		nextDestination = start;
	}

	/**
	 * The current battery level of the robot.
	 * 
	 * @return <code>int</code> Returns the {@link #batteryLevel} of a robot.
	 */
	public int getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * Charges the robots {@link #batteryLevel} when the robot is on its Charging
	 * Pod node.
	 * 
	 * @param chargeRate a <code>int</code> value. Representing the rate which the robot's
	 *            {@link #batteryLevel} is being charged.
	 */
	public void charging(int chargeRate) {
		if (getRobotCoordinates().equals(start)) {
			if (batteryLevel < originalBatteryLevel) {
				batteryLevel += chargeRate;
			}
			System.out.println("Battery level: " + batteryLevel);
		}
	}

	/**
	 * Decreases the value of the {@link #batteryLevel} of a robot by 1 or 2, after
	 * every tick depending on if it's carrying an order or not.
	 * 
	 */
	public void decreaseBatteryLevel() {
		if (carrying == true) {
			batteryLevel = batteryLevel - 2;
		} else {
			batteryLevel--;
		}
	}

	/**
	 * Updates the battery level of the robot and assigns it
	 * 
	 * @param batteryLevel the {@link batteryLevel}. The updated value of the battery level
	 *            for the robot.
	 */
	public void updateBattery(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	/**
	 * Stores the <code>Point</code> coordinates in an ArrayList, for the robots
	 * Charging Pod where the robot initially started from.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>Point</code> coordinates.
	 *         Used by the robot to go back its {@link #start} position.
	 */
	public ArrayList<Point> setDestinationStart() {
		ArrayList<Point> finalDest = new ArrayList<Point>();
		finalDest.add(start);
		return finalDest;
	}

	/**
	 * Sets the robots {@link #batteryLevel} no higher than its original value.
	 * 
	 * @param batteryLevel the {@link #batteryLevel}. Original battery level assigned to
	 *            battery level to prevent value being higher than the robots
	 *            original value.
	 */
	public void setBatteryCap(int batteryLevel) {
		originalBatteryLevel = batteryLevel;
	}

	/**
	 * Contains an ArrayList of <code>Point</code> coordinates for each place the
	 * Robot has to visit in order to complete the order.
	 * 
	 * @param destination the <code>ArrayList</code> of <code>Point</code> coordinates.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>Point</code> coordinates.
	 *         Used by the robot to go back its {@link #start} position.
	 */
	public void receiveOrder(ArrayList<Point> destination) {
		order = destination;
		order.add(start);
		index = 0;
		System.out.println("Robot order: " + order.toString());
	}

	/**
	 * Decides whether a robot can take an order or not.
	 * 
	 * @param destination a <code>ArrayList</code> of <code>Point</code> coordinates.
	 * 
	 * @return Returns a <code>boolean</code> value. If an order is accepted
	 *         {@link #orderStatus} set to True, otherwise false.
	 */
	public void orderDecision(ArrayList<Point> destination) {
		ArrayList<Point> chargePod = new ArrayList<Point>();
		chargePod.add(start);
		if (!destination.equals(chargePod)) {
			orderStatus = true;
			System.out.println("Order accepted. " + destination);
		}
		receiveOrder(destination);
	}

	/**
	 * Robots takes the items needed for the order it has from the storage shelf.
	 * The {@link #carrying} boolean value set to <code>true</code> when Robot is
	 * {@link #carrying} an shelf.
	 */
	public void pickUpItems() {
		if (atShelf()) {
			carrying = true;
		}
	}

	/**
	 * Checks to see if the robot is at any location such as a Storage shelf,
	 * Packing Station or Charging Pod.
	 * 
	 * @return Returns <code>boolean</code> values. True if robot coordinates equals
	 *         {@link #nextDestination} <code>Point</code> coordinate. Else returns
	 *         false when it is not at its {@link #nextDestination}.
	 */
	public boolean atLocation() {
		if (getRobotCoordinates().equals(nextDestination)) {
			return true;
		}
		return false;
	}

	/**
	 * The method sets the wait time of the robot at a packing station.
	 * 
	 * @return Returns <code>boolean</code> values. True if {@link #waitTime} is
	 *         greater than 0. Else returns false, when the robot doesn't need to
	 *         wait at the packing station.
	 */
	public boolean waitAtPacking() {
		if (waitTime > 0) {
			waitTime--;
			return true;
		}
		return false;
	}

	/**
	 * Sets the value of {@link #waitTime} field for the robot depending on the
	 * order.
	 * 
	 * @param time a <code>int</code> value. The time parameter assigned to
	 *            {@link #waitTime}, determining how long the waiting period for the
	 *            robot is.
	 */
	public void setWaitTime(int time) {
		waitTime = time;
	}

	/**
	 * When the robot reaches {@link #atLocation()}, this method checks the length
	 * {@link #order} ArrayList through each index, for another order.
	 * 
	 * @return Returns {@link #nextDestination} <code>Point</code> coordinate. The
	 *         destination of the next place in the simulation the robot needs to
	 *         go.
	 */
	public Point nextInPath() {
		completed = false;
		if (atLocation()) {
			if (index < order.size()) {
				pickUpItems();
				atPacking();
				finishOrder(index);
				nextDestination = order.get(index);
				index++;
			}
		}
		return nextDestination;
	}

	/**
	 * This method gets the next destination for the robot to go, through calling
	 * the {@link #nextInPath()} method.
	 * 
	 * @return Returns the {@link #nextInPath()} method so the robot knows where to
	 *         go next in the simulation.
	 */
	public Point getDestination() {
		return nextInPath();
	}

	/**
	 * The robot drops off items at a packing station. Then sets Returns a
	 * <code>boolean</code> value of {@link #carrying} to false when the robot drops
	 * off the shelf.
	 */
	public void dropOrder() {
		if (getRobotCoordinates() == order.get(order.size() - 2)) {
			carrying = false;
		}
	}

	/**
	 * Sets the coordinate values for the robot and uses the X and Y int values to
	 * create a new <code>Point</code> on the grid.
	 * 
	 * @param x a <code>int</code> value. Used for the X coordinate.
	 * @param y a <code>int</code> value. Used for the Y coordinate.
	 */
	public void setCoordinates(int x, int y) {
		robotCoordinates = new Point(x, y);
	}

	/**
	 * Gets the X and Y coordinates of the robot.
	 * 
	 * @return Returns a <code>Point</code>. The Robots coordinate value.
	 */
	public Point getRobotCoordinates() {
		return robotCoordinates;
	}

	/**
	 * Gets the Y coordinate of the robot.
	 * 
	 * @return <code>double</code> The Y coordinate value.
	 */
	public double getRobotY() {
		return robotCoordinates.getY();
	}

	/**
	 * Gets the X coordinate of the robot.
	 * 
	 * @return <code>double</code> The X coordinate value.
	 */
	public double getRobotX() {
		return robotCoordinates.getX();
	}

	/**
	 * Gets the free spaces on the of free spaces on the grid {@link MainController
	 * class} and stores it in an ArrayList.
	 * 
	 * @param numCols an <code>int</code> value. Representing the number of columns in
	 *            the current grid.
	 * @param numRows an <code>int</code> value. Representing the number of rows in the
	 *            current grid.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>Point</code>. ArrayList of
	 *         Point Objects.
	 */
	public ArrayList<Point> getFreeSpacePoints(int numCols, int numRows) {
		return MainController.getFreeSpaces(numCols, numRows);
	}

	/**
	 * Gets the order status of each robot and checks to see if the robot has an
	 * order.
	 * 
	 * @return Returns a <code>boolean</code> value. True or false depending on
	 *         {@link #orderStatus}
	 */
	public boolean getOrderStatus() {
		return orderStatus;
	}

	/**
	 * Calculates the distance between starting nodes and end nodes being (Packing
	 * stations, Charging pods, Storage shelfs).
	 * 
	 * @param x a <code>double</code> value. Represents the x coordinate of the
	 *            first node.
	 * @param y a <code>double</code> value. Represents the y coordinate of the
	 *            first node.
	 * @param X a <code>double</code> value. Represents the X coordinate of the
	 *            first node.
	 * @param Y a <code>double</code> value. Represents the Y coordinate of the
	 *            first node.
	 * 
	 * @return Returns the distance between the start and end nodes.
	 */
	public double pythagoras(double x, double y, double X, double Y) {
		return Math.sqrt(Math.pow((X - x), 2) + Math.pow((Y - y), 2));
	}

	/**
	 * Gets the <code>Point</code> coordinates of the Shelves, Packing Stations and
	 * Charging Pods.
	 * 
	 * @returns order a <code>ArrayList</code>. Contains the coordinates of the
	 *          Shelves, Packing Stations and Charging Pods.
	 */
	public ArrayList<Point> getDestinations() {
		return order;
	}

	/**
	 * Checks the {@link #order} <code>ArrayList</code> and sets
	 * {@link #orderStatus} to false when the robot has finished the order.
	 * 
	 * @param index a <code>int</code> value. Represents the position of the index
	 *            value within the {@link #order} <code>ArrayList</code>.
	 */
	public void finishOrder(int index) {
		if (index == (order.size() - 1)) {
			if (getRobotCoordinates().equals(order.get(order.size() - 2))) {
				System.out.println("orderStatus is set to false");
				orderStatus = false;
				completed = true;
			}
		}
	}

	/**
	 * Checks if the robot is at the Packing Station by iterating and comparing
	 * through the <code>ArrayList</code> containing the coordinates of the robot.
	 * 
	 * @return Returns a <code>boolean</code> value of {@link #carrying} to false
	 *         when the robot drops off the storage shelf and {@link #atPacking()}
	 *         is set to true. Else returns false, when the robot isn't at the
	 *         packing station.
	 */
	public boolean atPacking() {
		for (int i = 1; i < order.size(); i += 2) {
			if (getRobotCoordinates().equals(order.get(i))) {
				carrying = false;
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the robot is at the Storage by iterating and comparing through the
	 * <code>ArrayList</code> containing the coordinates of the robot.
	 * 
	 * @return Returns a <code>boolean</code> value of {@link #carrying} to true
	 *         when the robot picks up the storage shelf and {@link #atShelf()} is
	 *         set to true. Else returns false, when the robot isn't at the storage
	 *         shelf.
	 */
	public boolean atShelf() {
		for (int i = 0; i < order.size() - 1; i += 2) {
			if (getRobotCoordinates().equals(order.get(i))) {
				carrying = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the robot is its charging pod by iterating and comparing through
	 * the <code>ArrayList</code> containing the coordinates of the robot.
	 * 
	 * @return Returns a <code>boolean</code> value of true when the robot
	 *         coordinate is equal to its {@link #start} coordinate. Else returns
	 *         false, when the robot isn't at its Charging Pod.
	 */
	public boolean atChargePod() {
		if (getRobotCoordinates().equals(start)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the value of {@link #completed}.
	 * 
	 * @return Returns <code>boolean</code> value of {@link #completed}.
	 */
	public boolean completeOrder() {
		return completed;
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

}
