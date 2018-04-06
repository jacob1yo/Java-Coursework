package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

public class StorageShelf implements Entity{
	/**
	 * The x coordinate of a storage shelf on the grid
	 */
	public int storageX;
	
	/**
	 * The y coordinate of a storage shelf on the grid
	 */
	private int storageY;
	/**
	 * The unique identifier of each charging pod
	 * @see #getID, #generateID
	 */
	private String uid;
	
	/**
	 * Stores the last number used for the ID
	 */
	private int lastNum;
	
	public StorageShelf(int storageY, int storageX) {
		lastNum = 0;
		generateID();
		this.storageX =storageX;
		this.storageY = storageY;
	}
	
	/**
	 * Accesses the X-coordinate of the Storage
	 */
	public int getStorageX() {
		return storageX;
	}

	/**
	 * Accesses the X-coordinate of the Storage
	 */
	public int getStorageY() {
		return storageY;
	}
	
	@Override
	public void generateID() {
		int num = lastNum++;
		uid = "s" + num;
		
	}

	@Override
	public String getID() {
		return uid;
	}

	@Override
	public boolean compare(Robot r) {
		// TODO Auto-generated method stub
		return null;
	}

}
