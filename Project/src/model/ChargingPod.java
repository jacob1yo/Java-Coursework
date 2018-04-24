package model;

import java.awt.Point;

/**
 * This class contains the implementation of the Charging Pod methods.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 * @version 1.0
 *
 */

public class ChargingPod extends Warehouse implements Entity {

	/**
	 * Determines the speed at which the battery of a robot is charged.
	 * 
	 * @see #charge
	 */
	private int chargeRate;
	
	/**
	 * The unique identifier of each charging pod.
	 * 
	 * @see #getID, #generateID
	 */
	private String uid;
	
	/**
	 * Boolean value used to check if robots unique charging pod is occupied.
	 * 
	 * @see #ChargingPod #charge
	 */
	private boolean occupied;
	
	/**
	 * The Point coordinates where the Charging Pod is placed on the grid.
	 * 
	 * @see #chargingPod #getChargingCoordinates #getChargingX #getChargingY
	 */
	private Point chargingCoordinates;
		
	  /**
	  * Charging Pod Constructor.
	  * 
	  * @param int x and int y {@link chargingCoordinates} used to initialise a new Point coordinate for a Charging Pod.
	  */
	public ChargingPod(int x, int y) {
		chargingCoordinates = new Point(x, y);
		occupied = false;	
	}
	
	/**
	 * Gets the X and Y coordinates of the charging Pod.
	 * 
	 * @return <code>Point</code> The coordinate value.
	 */
	public Point getChargingCoordinates() {
		return chargingCoordinates;
	}
	
	/**
	 * Accesses the X coordinate of the charging Pod.
	 * 
	 * @return <code>Point</code>. The X coordinate value.
	 */
	public double getChargingX(){
		return chargingCoordinates.getX();
	}
	
	/**
	 * Accesses the Y coordinate of the charging Pod.
	 * 
	 * @return <code>Point</code>. The Y coordinate value.
	 */
	public double getChargingY(){
		return chargingCoordinates.getY();
	}
	
	/**
	 * Charges the battery of a robot depending on charge rate.
	 * 
	 * @return <code>int</code>. The value after the charge is completed.
	 */
	private int charge() {
		// think about int or void?
		
		if (occupied == true) {
			// charge rate of 5 is added to robots battery level
		}
		return 0; // returns the charge level

	}
	
	/**
	 * Updates the chargeRate of the Charging Pod
	 * 
	 * @param chargeRate {@link chargeRate}. Int value assigned to link the chargeRate parameter to the chargeRate field.
	 */
	public void updateChargeRate(int chargeRate) {
		this.chargeRate = chargeRate;
	}
	
	@Override
	public void generateID(int id) {
			int num = id++;
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
	public void setId(String newUid) {
		uid = newUid;
	}
}
