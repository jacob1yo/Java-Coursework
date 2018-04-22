package model;

/**
 * This class contains the implementation of the Storage Shelf methods.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 *         
 * @version 1.0
 */
import java.awt.Point;

public class StorageShelf extends Warehouse implements Entity {

	/**
	 * The unique identifier of each charging pod
	 * 
	 * @see #getID, #generateID
	 */
	private String uid;

	/**
	 * Stores the last number used for the ID.
	 * 
	 * @see #resetID
	 */
	private static int lastNum = 0;

	/**
	 * The Point coordinates where the Storage Shelf is placed on the grid.
	 * 
	 * @see #chargingPod #getStorageCoordinates #getStorageX #getStorageY
	 */
	private Point storageCoordinates;

	/**
	 * Storage Shelf Constructor.
	 * 
	 * @param int x and int y {@link storageCoordinates} used to initialise a new Point coordinate for a Storage Shelf.
	 */
	public StorageShelf(int x, int y) {
		storageCoordinates = new Point(x, y);
	}

	/**
	 * Gets the X and Y coordinates of the Storage Shelf.
	 * 
	 * @return <code>Point</code> The coordinate value.
	 */
	public Point getStorageCoordinates() {
		return storageCoordinates;
	}

	/**
	 * Accesses the X coordinate of the Storage Shelf.
	 * 
	 * @return <code>Point</code>. The X coordinate value.
	 */
	public double getStorageX() {
		return storageCoordinates.getX();
	}

	/**
	 * Accesses the Y coordinate of the Storage Shelf.
	 * 
	 * @return <code>Point</code>. The Y coordinate value.
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

	@Override
	public void generateID(int id) {
		int num = id;
		uid = "ss" + num;
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
