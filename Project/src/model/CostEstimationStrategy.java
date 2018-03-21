package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */


import javafx.geometry.Point2D;

public class CostEstimationStrategy extends Robot {
	
	/**
	 * Start location for the algorithm.
	 * @see #distanceCalculator
	 */
	private Point2D startPoint;
	
	/**
	 * End location for the algorithm.
	 * @see #distanceCalculator
	 */
	private Point2D endPoint;

	public CostEstimationStrategy() {}

	/**
	 * Calculates the distance from one point to another.
	 * @return <code>double</code> Distance between locations
	 */
	public double distanceCalculator() {}
	
	
	/**
	 * Loop that adds up every distance that it has calculated
	 * @return <code>double</code> Final/total distance
	 */
	public double totalDistanceEstimator() {}
	
}