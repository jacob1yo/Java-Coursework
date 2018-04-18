package model;

import java.util.ArrayList;

import gui.MainController;

import java.awt.Point;


/**
 * Write a description of class Manhattan here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Manhattan extends Robot implements Entity {
	// instance variables - replace the example below with your own
	private ArrayList <Point> freeSpaces;
	private double robotY= getRobotY();
	private double robotX= getRobotX();
	private double storageY = super.getStorageY();
	private double storageX = getStorageX();
	private double packingY = getPackingY();
	private double packingX = getPackingX();
	/**
	 * Constructor for objects of class Manhattan
	 */
	public Manhattan()
	{
		ArrayList<Point> freeSpaces = freeSpacePoints(MainController.getNumRows(), MainController.getNumCols());

	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @param  y  a sample parameter for a method
	 * @return    the sum of x and y
	 */
	public void manhattanCalc ()
	{
		// put your code here
		/*freeSpacePoints(MainController.getNumRows(), MainController.getNumCols());

    	storageShelfPoints();

    	robotPoints();

    	chargingPodPoints();

    	packingStationPoints();*/

		x=Math.abs(x1-x2);

		getRobotY();



	}
}
