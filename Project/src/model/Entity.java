package model;

/**
 * This abstract interface contains the abstract methods for all entities in the
 * simulation.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 * 
 * @version 1.0
 */

public interface Entity {

	/**
	 * Generates the ID for the each Entity object.
	 * 
	 * @param id ID value assigned to num and then incremented by 1 for each object
	 * created.
	 * 
	 */
	public abstract void generateID(int id);

	/**
	 * Gets the ID generated.
	 * 
	 * @return <code>String</code>. The uid of the Entity object created.
	 */
	public abstract String getID();

	/**
	 * Ensures each ChargingPod created when read from .SIM file is assigned a new
	 * UID.
	 * 
	 * @param newUid a String representation for the UID of each Entity object.       
	 */
	public abstract void setId(String newUid);

}