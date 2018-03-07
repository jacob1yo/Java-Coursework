package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 *
 */

public class ChargingPod implements Entity {

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
	
	/**
	 * Used to check if robots unique charging pod is occupied
	 */
	private boolean occupied;

	public ChargingPod() {
		chargeRate = 5;
		lastNum = 0;
		generateID();
		occupied = false;
	}

	/**
	 * Charges the battery of a robot depending on charge rate.
	 */
	private int charge() {
		// think about int or void?
		
		if (occupied == true) {
			// charge rate of 5 is added to robots battery level
		}
		return 0; // returns the charge level

	}

	@Override
	public void generateID() {
		int num = lastNum++;
		uid = "c" + num;

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