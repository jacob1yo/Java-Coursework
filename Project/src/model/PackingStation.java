package model;

/**
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 */

public class PackingStation implements Entity{
	// Declare fields
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
	}

	/**
	 * This method signals if the packing station has completed its order packing.
	 * 
	 * @return <code>boolean</code> true if completed otherwise false.
	 *         {@link #completed}
	 */
	private boolean isCompleted() {
		return completed;
	}

	/**
	 * This method resets the value of {@link #completed} back to false when an
	 * order is finished.
	 */
	private void resetCompleted() {
		completed = false;

	}

	/**
	 * This method keeps track of how long the robot packs for according to the
	 * number of ticks in the simulation
	 */
	private void packing() {
		
	}

	/**
	 * This method dispatches item for delivery, completing the order.
	 */
	private void dispatch() {
		completed = true;
	}

	
	/**
	 * This method requests another order once current order is completed.
	 * @return
	 */
	private Order nextOrder() {
		return 0; //this needs changing for correct implementation


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
	public Robot compare(Robot r) {
		// TODO Auto-generated method stub
		return null;

	}
}
