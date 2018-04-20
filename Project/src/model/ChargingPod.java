package model;

import java.awt.Point;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 *
 */

public class ChargingPod extends Warehouse implements Entity {
	/**
	 * The x coordinate of a charging pod on the grid.
	 */
	//private int chargingX;
	
	/**
	 * The y coordinate of a robot on the grid
	 */
//	private int chargingY;
	
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
	private static int lastNum = 0;
	
	/**
	 * Used to check if robots unique charging pod is occupied
	 */
	private boolean occupied;
	
	private Point chargingCoordinates;

	public ChargingPod(int x, int y) {
		chargingCoordinates = new Point(x, y);
		generateID();
		occupied = false;
	//	this.chargingX = chargingX;
	//	this.chargingY = chargingY;
	}
	
	/**
	 * Gets the X n' Y co-ordinates of the chargin.
	 * @return <code>Point</code> The co-ordinate value.
	 */
	public Point getChargingCoordinates() {
		return chargingCoordinates;
	}
	
	/**
	 * Acceses the X-coordinate of the charging Pod
	 */
	public double getChargingX(){
		return chargingCoordinates.getX();
	}
	
	/**
	 * Acceses the Y-coordinate of the charging Pod
	 */
	public double getChargingY(){
		return chargingCoordinates.getY();
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
	
	/**
	 * Rests the lastNum field to 0.
	 */
	public void resetID() {
		lastNum = 0;
	}
	
	/**
	 * 
	 */
	public void updateChargeRate(int chargeRate) {
		this.chargeRate = chargeRate;
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
	public boolean compare(Entity r) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void changeId(String newUid) {
		uid = newUid;
		
	}

}
