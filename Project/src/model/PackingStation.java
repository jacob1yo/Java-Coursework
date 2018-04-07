package model;

import java.awt.Point;

/**
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 */

public class PackingStation extends Warehouse implements Entity{
	// Declare fields
	/**
	 * The x coordinate of a packing station on the grid
	 */
//	private int packingX;
	
	/**
	 * The y coordinate of a packing station on the grid
	 */
	//private int packingY;
	
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
	private int lastNum;

	
	
	public PackingStation() {
		completed = false;
		lastNum = 0;
		generateID();
	/*	this.packingX = packingX;
		this.packingY = packingY;*/
	}

	/**
	 * Gets the X n' Y co-ordinates of the Packing.
	 * @return <code>Point</code> The co-ordinate value.
	 */
	public Point getSPackingCoordinates() {
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
	public Order nextOrder() {
		return 0; //this needs changing for correct implementation

	}
	@Override
	public void generateID() {
		int num = lastNum++;
		uid = "p" + num;
		
	}

	@Override
	public String getID() {
		return uid;
	}

	@Override
	public boolean compare(Robot r) {
		// TODO Auto-generated method stub
		return null;

	}
}
