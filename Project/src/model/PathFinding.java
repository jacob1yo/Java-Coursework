package model;

import java.util.ArrayList;
import java.util.HashMap;

import gui.MainController;

import java.awt.Point;

/**
 * This class contains the implementation of the methods for the robots to find a path between destinations.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos
 * 
 */
public class PathFinding extends Robot implements Entity {
	private HashMap<Point, Point> currentToNext;

	/**
	 * PathFinding constructor. Creates a <code>hashMap</code> to store <code>Point</code> coordinates
	 *
	 */
	public PathFinding() {
		currentToNext = new HashMap<Point, Point>();
	}

	/**
	 * Finds all possible available adjacent nodes and from them, finds the best one to choose
	 * 
	 * @param destination. The destination point for the robot.
	 */
	public void pathCalc(Point destination, ArrayList<Point> robotLocations) {
		ArrayList<Point> takenNodes = new ArrayList<Point>();
		ArrayList<Point> freeSpaces = super.getFreeSpacePoints(MainController.getNumCols(), MainController.getNumRows());
		//ArrayList<Point> robotLocations = super.getRobotSpaces();
		
		takenNodes.add(new Point(-1, -1));

		for(int i = 0; i < robotLocations.size(); i++) {
			Double x = robotLocations.get(i).getX();
			Double y = robotLocations.get(i).getY();
			Point left = new Point(x.intValue() - 1, y.intValue());
			Point right = new Point(x.intValue() + 1, y.intValue());
			Point up = new Point(x.intValue(), y.intValue() - 1);
			Point down = new Point(x.intValue(), y.intValue() + 1);
			double compare = 100;
			Point node = robotLocations.get(i);

			if(freeSpaces.contains(left) && !takenNodes.contains(left)) {
				double result = super.pythagoras(left.getX(), left.getY(), destination.getX(), destination.getY());
				if(result < compare) {
					node = left;
					compare = result;
				}
			}
			if(freeSpaces.contains(right) && !takenNodes.contains(right)) {
				double result = super.pythagoras(right.getX(), right.getY(), destination.getX(), destination.getY());
				if(result < compare) {
					node = right;
					compare = result;
				}
			}
			if(freeSpaces.contains(up) && !takenNodes.contains(up)) {
				double result = super.pythagoras(up.getX(), up.getY(), destination.getX(), destination.getY());
				if(result < compare) {
					node = up;
					compare = result;
				}
			}
			if(freeSpaces.contains(down) && !takenNodes.contains(down)) {
				double result = super.pythagoras(down.getX(), down.getY(), destination.getX(), destination.getY());
				if(result < compare) {
					node = down;
					compare = result;
				}
			}
			takenNodes.add(node.getLocation());
			currentToNext.put(robotLocations.get(i), node);
		}
	}

	/**
	 * Returns the HashMap created in manhattanCalc
	 * 
	 * @return <code>HashMap<Point, Point></code>
	 */
	public HashMap<Point, Point> getNewNodes() {
		return currentToNext;
	}

	// add all the path into an Arraylist and sum the all the indexes (indexes=steps=ticks), in order to compare them to the estimated + mos
}