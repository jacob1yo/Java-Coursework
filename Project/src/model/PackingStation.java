package model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * This class contains the implementation of the Packing Station methods.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 *         
 * @version 1.0
 */

public class PackingStation implements Entity{
	/**
	 * If packing of an order has been completed.
	 * 
	 * @see #isCompleted #resetCompleted
	 */
	private boolean completed;

	/**
	 * The unique identifier of each Packing station.
	 * 
	 * @see #getID, #generateID
	 */
	private String uid;

	/**
	 * The Point coordinates where the Packing Station is placed on the grid.
	 * 
	 * @see #getPackingCoordinates #getPackingX #getPackingY
	 */
	private Point packingCoordinates;

	/**
	 * This parameter is instantiated with integers for the X and Y coordinates.
	 * 
	 * @see #passOnPoint
	 */
	private Point p;

	/**
	 * Packing Station Constructor. Creates a new <code>Point</code> and assigns it to p and sets a <code>boolean</code>
	 * value set to false automatically for the Order to be completed.
	 * 
	 * @param int x and int y {@link packingCoordinates} used to initialise a new <code>Point</code> coordinate for each Packing Station.
	 */	
	public PackingStation(int x, int y) {
		packingCoordinates = new Point(x, y);
		completed = false;
		p = new Point(x, y);
	}

	/**
	 * This method is called to pass on the coordinate, to the Cost Estimation class.
	 * 
	 * @return Returns <code>p</code>. Which is the <code>Point</code> coordinate for the Cost Estimation class.
	 */
	public Point passOnPoint() {
		return p;
	}

	/**
	 * Gets the Point coordinate of the Packing station.
	 * 
	 * @return Returns <code>Point</code>. The coordinate values of the Point.
	 */
	public Point getPackingCoordinates() {
		return packingCoordinates;
	}

	/**
	 * Accesses the double value of the X coordinate of the Packing Station.
	 * 
	 * @return Returns <code>double</code>. Representing the X coordinate value.
	 */
	public double getPackingX() {
		return packingCoordinates.getX();
	}

	/**
	 * Accesses the double value of the Y coordinate of the Packing Station.
	 * 
	 * @return Returns <code>double</code>. Representing the Y coordinate value.
	 */
	public double getPackingY() {
		return packingCoordinates.getY();
	}

	/**
	 * This method checks and signals, if the packing station has completed packing the order it is assigned.
	 *
	 * @return Returns <code>boolean</code>. Assigned value of true if completed, otherwise false until it is completed {@link #completed}.
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * This method resets the value of {@link #completed} when called, back to false when an order is finished.
	 */
	public void resetCompleted() {
		completed = false;

	}

	/**
	 * This method keeps track of how the packing process. It depends on the number of ticks defined in the "SIM" files for different simulation scenarios.
	 */
	public void packing() {

	}

	/**
	 * This method sets the value of {@link #completed} to true. It is called to let simulation know order is ready for delivery, completing the order.
	 * 
	 */
	public void dispatch() {
		completed = true;
	}


	/**
	 * This method gets the next <code>String</code> order out of the ArrayList and requests another order once current order is completed. The method passes parameter to
	 * the decision making method where the robot decides whether to accept the order. If accepted, the order is removed from the ArrayList.
	 * 
	 * @param o a Order object. Used to call the getOrder method and assigns to the ArrayList. Which is passed into the decision making process.
	 * 
	 * @return Returns the Order which is selected from this method. 
	 */
	public ArrayList<String> getNextOrder(Order o) {
		if(o.getOrders().size() != 0) {
			ArrayList<String> order = o.getOrders().get(0);
			o.addToDecision(order);
			o.removeFromOrders();
			return order;
		}
		return null;
	}

	@Override
	public void generateID(int id) {
		int num = id;
		uid = "ps" + num;
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
