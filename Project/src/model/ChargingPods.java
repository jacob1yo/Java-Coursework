package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 *
 */

public class ChargingPods implements Entity {

	/**
	 * Determines the speed at which the battery of a robot is charged.
	 */
	private int chargeRate;
	/**
	 * The unique identifier of each charging pod
	 * @see #getID, #generateID
	 */
	private String uid;
	/**
	 * Stores the last number used for the ID
	 */
	private int lastNum;

	public ChargingPods() {
		chargeRate = 5;
		lastNum = 0;
	}

	/**
	 * Charges the battery of a robot depending on charge rate.
	 */
	private int charge() {
		// think about int or void?
		return 0;

	}

	@Override
	public void generateID() {
		int num = lastNum++;
		uid = "c + num";

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
