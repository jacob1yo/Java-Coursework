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
	public abstract Robot compare(Robot r);
	
}
