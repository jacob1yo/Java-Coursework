package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, 
 *
 */

public interface Entity {

	public abstract void generateID();
	public abstract String getID();
	public abstract Robot compare(Robot r);
	//public abstract Location getLocation();	//do we need a location class?
}
