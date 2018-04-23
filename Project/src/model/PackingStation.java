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

public class PackingStation extends Warehouse implements Entity{
	/**
	 * If packing has been completed by robot
	 * 
	 * @see #isCompleted
	 * @see #resetCompleted
	 */
	private boolean completed;
	/**
	 * The unique identifier of each charging pod
	 * @see #getID, #generateID
	 */
	private String uid;
	
	/**
	 * Stores the last number used for the ID
	 */
	private static int lastNum = 0; //may need to be deleted

	private Point packingCoordinates;
	
	private static int index = 0;
	
	private static Point p;
	
	public PackingStation(int x, int y) {
		packingCoordinates = new Point(x, y);
		completed = false;
		p = Order.packingPoints().get(uid);
	}
	
	/**
	 * Passes on point to cost estimation
	 * @return
	 */
	public static Point passOnPoint() {
		return p;
	}

	/**
	 * Gets the X n' Y co-ordinates of the Packing.
	 * @return <code>Point</code> The co-ordinate value.
	 */
	public Point getPackingCoordinates() {
		return packingCoordinates;
	}
	
	/**
	 * Accesses the X-coordinate of the Packing Station
	 */
	public double getPackingX() {
		return packingCoordinates.getX();
	}
	
	/**
	 * Acceses the Y-coordinate of the Packing Station
	 */
	public double getPackingY() {
		return packingCoordinates.getY();
	}
	
	
	/**
	 * This method signals if the packing station has completed its order packing.
	 * 
	 * @return <code>boolean</code> true if completed otherwise false.
	 *         {@link #completed}
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * This method resets the value of {@link #completed} back to false when an
	 * order is finished.
	 */
	public void resetCompleted() {
		completed = false;

	}

	/**
	 * This method keeps track of how long the robot packs for according to the
	 * number of ticks in the simulation
	 */
	public void packing() {
		
	}

	/**
	 * This method dispatches item for delivery, completing the order.
	 */
	public void dispatch() {
		completed = true;
	}

	
	/**
	 * This method requests another order once current order is completed.
	 * @return
	 */
	public static ArrayList<String> getNextOrder() {
		ArrayList<String> order = Order.getOrders().get(0);
		return order;
	}
	
	/**
	 * Rests the lastNum field to 0.
	 */
	public void resetID() {	//maybe delete
		lastNum = 0;
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
	public boolean compare(Entity r) {
		// TODO Auto-generated method stub
		return true;

	}

	@Override
	public void setId(String newUid) {
		uid = newUid;
	}
}
