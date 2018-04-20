package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

public interface Entity {
	/**
	 * generates each entities unique ID
	 */
	public abstract void generateID();
	/**
	 * get the unique id of each entity
	 * @return uid
	 */
	public abstract String getID();
	
	public abstract void changeId(String newUid);
	
	/**
	 * Compares if the robot is in the same position as another entity.
	 * @param r a Robot object
	 * @return <code>boolean</code> true if there is an entity in the same location or false otherwise.
	 */
	public abstract boolean compare(Entity r);
	
}