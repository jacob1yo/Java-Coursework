package model;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */
import java.awt.Point;

public class StorageShelf extends Warehouse implements Entity{
	/**
	 * The x coordinate of a storage shelf on the grid
	 */
	/**
	 * The y coordinate of a storage shelf on the grid
	 */
	/*public int storageX;
	

	private int storageY;*/
	/**
	 * The unique identifier of each charging pod
	 * @see #getID, #generateID
	 */
	private String uid;
	
	/**
	 * Stores the last number used for the ID
	 */
	private static int lastNum = 0;
	
	private Point storageCoordinates;
	
	public StorageShelf(int x, int y) {
		storageCoordinates = new Point(x, y);
		generateID();
	//	this.storageX =storageX;
		//this.storageY = storageY;
	}
	
	/**
	 * Gets the X n' Y co-ordinates of the Storage.
	 * @return <code>Point</code> The co-ordinate value.
	 */
	public Point getStorageCoordinates() {
		return storageCoordinates;
	}
	
	/**
	 * Accesses the X-coordinate of the Storage
	 */
	/*public double getStorageX() {
		return storageCoordinates.getX();
	}*/

	/**
	 * Accesses the Y-coordinate of the Storage
	 */
	public double getStorageY() {
		return storageCoordinates.getY();
	}
	
	/**
	 * Rests the lastNum field to 0.
	 */
	public void resetID() {
		lastNum = 0;
	}
	
	/**
	 * Decrease lastNum field by 1.
	 */
	public void decreaseUID() {
		lastNum--;
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
	public boolean compare(Entity r) {
		// TODO Auto-generated method stub
		return true;
	}

}
