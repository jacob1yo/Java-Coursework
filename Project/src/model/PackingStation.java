package model;

/**
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 */

public class PackingStation {
	// Declare fields
	/**
	 * If packing has been completed by robot
	 * 
	 * @see #isCompleted
	 * @see #resetCompleted
	 */
	private boolean completed;

	public PackingStation() {
		completed = false;
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
}
