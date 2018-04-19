package model;

import java.util.ArrayList;
import java.util.HashMap;

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
	private HashMap<Point, Point> hashMap;

	public Manhattan() {
		hashMap = new HashMap<Point, Point>();
	}

	/**
	 * Finds all possible available adjacent nodes and from them, finds the best one to choose
	 * @param destination
	 */
	/*public void manhattanCalc (Point destination) {
		ArrayList<Point> freeSpaces = freeSpacePoints(MainController.getNumRows(), MainController.getNumCols());
		ArrayList<Point> robotLocations = super.robotPoints();
		for(int i = 0; i < robotLocations.size(); i++) {	//Iterates through each coordinate of each robot created
			for(int j = 0; j < freeSpaces.size(); j++) {	//Iterates through every node that is free
				double x = robotLocations.get(i).getX();
				double y = robotLocations.get(i).getY();
				double freeX = freeSpaces.get(j).getX();
				double freeY = freeSpaces.get(j).getY();
				double compare = 100;
				Point node = new Point();
				if(freeX == x - 1 && freeY == y) {
					//(x - 1, y) is available
					double result = pythagoras(freeSpaces.get(j), destination);		//Gets the result of pythagoras between possible next node and destination
					if(result < compare) {			//If the result of pythagoras is less than the smallest result of pythagoras so far
						node = freeSpaces.get(j);	//node becomes this specific adjacent node, currently making it the next node to be travelled
					}
				}
				if(freeX == x && freeY == y - 1) {
					//(x, y - 1) is available
					double result = pythagoras(freeSpaces.get(j), destination);
					if(result < compare) {
						node = freeSpaces.get(j);
					}
				}
				if(freeX == x + 1 && freeY == y) {
					//(x + 1, y) is available
					double result = pythagoras(freeSpaces.get(j), destination);
					if(result < compare) {
						node = freeSpaces.get(j);
					}
				}
				if(freeX == x && freeY == y + 1) {
					//(x, y + 1) is available
					double result = pythagoras(freeSpaces.get(j), destination);
					if(result < compare) {
						node = freeSpaces.get(j);
					}
				}
				availableSpace(robotLocations.get(i), node);		//Puts the locations in a HashMap so that the warehouse/GUI knows which robot to move where
			}
		}
	}*/

	public void manhattanCalc(Point destination) {
		ArrayList<Point> freeSpaces = freeSpacePoints(MainController.getNumRows(), MainController.getNumCols());
		ArrayList<Point> robotLocations = super.robotPoints();
		
		for(int i = 0; i < robotLocations.size(); i++) {
			Double x = robotLocations.get(i).getX();
			Double y = robotLocations.get(i).getY();
			Point left = new Point(x.intValue() - 1, y.intValue());
			Point right = new Point(x.intValue() + 1, y.intValue());
			Point up = new Point(x.intValue(), y.intValue() - 1);
			Point down = new Point(x.intValue(), y.intValue() + 1);
			double compare = 100;
			Point node = new Point();

			if(freeSpaces.contains(left)) {
				double result = pythagoras(left, destination);
				if(result < compare) {
					node = left;
					compare = result;
				}
			}
			if(freeSpaces.contains(right)) {
				double result = pythagoras(right, destination);
				if(result < compare) {
					node = right;
					compare = result;
				}
			}
			if(freeSpaces.contains(up)) {
				double result = pythagoras(up, destination);
				if(result < compare) {
					node = up;
					compare = result;
				}
			}
			if(freeSpaces.contains(down)) {
				double result = pythagoras(down, destination);
				if(result < compare) {
					node = down;
					compare = result;
				}
				hashMap.put(robotLocations.get(i), node);
			}
		}
	}

	/**
	 * Returns the result of Pythagoras's theorem between node and destination
	 * @param node Point coordinate of where the robot currently is
	 * @param destination Point coordinate of the robot's destination
	 * @return <code>double</code>
	 */
	public double pythagoras(Point node, Point destination) {
		double x = node.getX();
		double y = node.getY();
		double X = destination.getX();
		double Y = destination.getY();

		return Math.sqrt((X - x)*(X - x) + (Y - y)*(Y - y));	
	}

	/**
	 * Returns the HashMap created in availableSpace
	 * @return <code>HashMap<Point, Point></code>
	 */
	public HashMap<Point, Point> getNewNodes() {
		return hashMap;
	}
}