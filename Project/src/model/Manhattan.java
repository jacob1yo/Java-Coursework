package model;

import java.util.ArrayList;

import gui.MainController;

import java.awt.Point;

/**
 * Write a description of class Manhattan here.
 *
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 * 
 */
public class Manhattan extends Robot implements Entity {
	private ArrayList<Point> freeSpaces;
	private ArrayList<Point> robotLocations;
	private double robotY = getRobotY();
	private double robotX = getRobotX();

	/*
	 * private double storageY = super.getStorageY(); private double storageX =
	 * getStorageX(); private double packingY = getPackingY(); private double
	 * packingX = getPackingX();
	 */

	/**
	 * Constructor for objects of class Manhattan
	 */
	public Manhattan() {
		ArrayList<Point> freeSpaces = freeSpacePoints(MainController.getNumRows(), MainController.getNumCols());
		ArrayList<Point> robotLocations = super.robotPoints();
	}

	public void manhattanCalc() {
		for (int i = 0; i < robotLocations.size(); i++) {
			for (int j = 0; j < freeSpaces.size(); j++) {
				double x = robotLocations.get(i).getX();
				double y = robotLocations.get(i).getY();
				if (freeSpaces.get(j).getX() == x - 1 && freeSpaces.get(j).getY() == y) {
					// (x - 1, y) is available
				}
				if (freeSpaces.get(j).getX() == x && freeSpaces.get(j).getY() == y - 1) {
					// (x, y - 1) is available
				}
				if (freeSpaces.get(j).getX() == x + 1 && freeSpaces.get(j).getY() == y) {
					// (x + 1, y) is available
				}
				if (freeSpaces.get(j).getX() == x && freeSpaces.get(j).getY() == y + 1) {
					// (x, y + 1) is available
				}
			}
		}
	}
}
