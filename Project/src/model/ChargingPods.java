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

	public ChargingPods() {
		chargeRate = 5;
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
		// TODO Auto-generated method stub

	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Robot compare(Robot r) {
		// TODO Auto-generated method stub
		return null;
	}

}
