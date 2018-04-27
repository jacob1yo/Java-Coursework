package model;

import java.awt.Point;

/**
 * This class contains the implementation of the Storage Shelf methods.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 *         
 * @version 1.0
 */

public class StorageShelf implements Entity {

	/**
	 * The unique identifier of each charging pod
	 * 
	 * @see #getID, #generateID
	 */
	private String uid;

	/**
	 * The Point coordinates where the Storage Shelf is placed on the grid.
	 * 
	 * @see #chargingPod #getStorageCoordinates #getStorageX #getStorageY
	 */
	private Point storageCoordinates;

	/**
	 * Storage Shelf Constructor.
	 * 
	 * @param int x an <code>int</code> value, {@link storageCoordinates} used to initialise a new Point coordinate for a Storage Shelf.
	 * @param int y an <code>int</code> value, {@link storageCoordinates} used to initialise a new Point coordinate for a Storage Shelf.
	 */
	public StorageShelf(int x, int y) {
		storageCoordinates = new Point(x, y);
	}

	/**
	 * Gets the X and Y coordinates of the Storage Shelf.
	 * 
	 * @return Returns a <code>Point</code> The coordinate value.
	 */
	public Point getStorageCoordinates() {
		return storageCoordinates;
	}

	/**
	 * Accesses the X coordinate of the Storage Shelf.
	 * 
	 * @return Returns a <code>Point</code>. The X coordinate value.
	 */
	public double getStorageX() {
		return storageCoordinates.getX();
	}

	/**
	 * Accesses the Y coordinate of the Storage Shelf.
	 * 
	 * @return Returns a <code>Point</code>. The Y coordinate value.
	 */
	public double getStorageY() {
		return storageCoordinates.getY();
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
	public void setId(String newUid) {
		uid = newUid;
	}
}
