package model;

/**
 * This abstract interface contains the abstract methods for all entities in the simulation.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 *         
 * @version 1.0
 */

public interface Entity {
	
	/** 
	 * Generates the ID for the each ChargingPod object.
	 * 
	 * @param id. ID value assigned to num and then incremented by 1 for each object created
	 */
	public abstract void generateID(int id);
	
	/**
	 * 
	 */
	public abstract void decreaseID(int selectedId);
	
	/**
	 * Gets the ID generated.
	 * 
	 * @return <code>String</code>. The uid of the Charging Pod object created.
	 */
	public abstract String getID();
	
	
	/**
	 * Ensures each ChargingPod created when read from .SIM file is assigned a new uid.
	 */
	public abstract void setId(String newUid);
	
	/**
	 * Compares if the robot is in the same position as another entity.
	 * 
	 * @param r a Robot object.
	 * 
	 * @return <code>boolean</code> true if there is an entity in the same location or false otherwise.
	 */
	public abstract boolean compare(Entity r);
	
}