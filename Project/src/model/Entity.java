package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

public interface Entity {
	
	/** 
	 * Generates the ID for the each ChargingPod object
	 * @param id. ID value assigned to num and then incremented by 1 for each object created
	 */
	public abstract void generateID(int id);
	
	/**
	 * Gets the ID generated 
	 * @return <code>String</code>. The uid of the Charging Pod object created.
	 */
	public abstract String getID();
	
	
	/**
	 * Ensures each ChargingPod created when read from .SIM file is assigned a new uid.
	 */
	public abstract void setId(String newUid);
	
	/**
	 * Compares if the robot is in the same position as another entity.
	 * @param r a Robot object
	 * @return <code>boolean</code> true if there is an entity in the same location or false otherwise.
	 */
	public abstract boolean compare(Entity r);
	
}