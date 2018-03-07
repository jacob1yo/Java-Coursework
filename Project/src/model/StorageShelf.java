package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

public class StorageShelf implements Entity{
	/**
	 * The unique identifier of each charging pod
	 * @see #getID, #generateID
	 */
	private String uid;
	/**
	 * Stores the last number used for the ID
	 */
	private int lastNum;
	
	public StorageShelf() {
		lastNum = 0;
		generateID();
	}

	@Override
	public void generateID() {
		int num = lastNum++;
		uid = "s + num";
		
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
