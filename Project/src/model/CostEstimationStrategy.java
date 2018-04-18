package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

import java.awt.Point;
import java.util.ArrayList;
//import javafx.geometry.Point2D;

public class CostEstimationStrategy extends Robot {
	
	/**
	 * Start location for the algorithm.
	 * @see #distanceCalculator
	 */
	private Point startPosition; // x and y of the start position
	
	/**
	 * End location for the algorithm.
	 * @see #distanceCalculator
	 */
	private Point endPosition;//x and y of the end position

	private Point byPosition;//x and y of the end position
	
	private double distanceEstimate;

//	private Point[] route;
	
	public CostEstimationStrategy() {
		super();
		startPosition = robotCoordinates;
		endPosition = PackingStation.getPackingCoordinates();
		byPosition=StorageShelf.getStorageCoordinates();
		//route = new Point[2];
	}

	/**
	 * Calculates the distance from one point to another.
	 * @param firstStop 
	 * @param secondStop 
	 * @param returnStop 
	 * @return <code>double</code> Distance between locations
	 * this is repeated code, so we will write down the maths into a single function and call the function in every time with parameters
	 */
	public double distanceCalculator(double firstStop, double secondStop, double returnStop) {
		firstStop=Math.sqrt((Math.pow(startPosition.getX()-byPosition.getX(),2))+(Math.pow(startPosition.getY()-byPosition.getY(),2)));
		secondStop=Math.sqrt((Math.pow(byPosition.getX()-endPosition.getX(),2))+(Math.pow(byPosition.getY()-endPosition.getY(),2)));
		returnStop=Math.sqrt((Math.pow(endPosition.getX()-startPosition.getX(),2))+(Math.pow(endPosition.getY()-startPosition.getY(),2)));
		distanceEstimate=firstStop+secondStop+returnStop;
		return distanceEstimate;
	}
	


	/**
	 * Loop that adds up every distance that it has calculated
	 * @return <code>double</code> Final/total distance
	 */
	/*public Point totalDistanceEstimator() {
		route[0]= startPosition;
		route[1]= byPosition;
		route[2]= endPosition;
		for (int i = 0; i<2; i++) {
			route[i];
		}
		return byPosition;

	}*/
	
	
}