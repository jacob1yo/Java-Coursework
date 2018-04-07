package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

import java.awt.Point;
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

	public CostEstimationStrategy() {
		startPosition = robotCoordinates;
		endPosition = packingCoordinates;
		
	}

	/**
	 * Calculates the distance from one point to another.
	 * @return <code>double</code> Distance between locations
	 */
	public double distanceCalculator(Point to, Point from) {
		return Math.sqrt((Math.pow(from.getX()-to.getX(),2))+(Math.pow(from.getY()-to.getY(),2)));
		
		
	}
	


	/**
	 * Loop that adds up every distance that it has calculated
	 * @return <code>double</code> Final/total distance
	 */
	public Point totalDistanceEstimator() {
		return robotCoordinates;
		
	}
	
	
}