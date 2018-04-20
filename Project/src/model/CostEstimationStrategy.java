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
	 * Stores the distance estimated.
	 * @see #distanceCalculator
	 */
	private static double distanceEstimate;

	/**
	 * Stores the no number of steps required for the estimated as double.
	 * @see #distanceCalculator
	 */
	private static double noSteps;
	
	public CostEstimationStrategy() {
		super();
		noSteps=0.0;
		distanceEstimate=0.0;
	}


	/**
	 * Calculates the distance from one point to another.
	 * @param r2s 
	 * @param s2p 
	 * @param p2c 
	 * @return <code>double</code> Distance between locations
	 * this is repeated code, so we will write down the maths into a single function and call the function in every time with parameters
	 */
	public double distanceCalculator (double r2s, double s2p, double p2c) {
		robotPoints().get(1).getX();
		int i =0;
		
		r2s=Math.sqrt((Math.pow(robotPoints().get(i).getX()-storageShelfPoints().get(i).getX(),2))+(Math.pow(robotPoints().get(i).getY()-storageShelfPoints().get(i).getY(), 2)));
		s2p=Math.sqrt((Math.pow(storageShelfPoints().get(i).getX()-packingStationPoints().get(i).getX(),2))+(Math.pow(storageShelfPoints().get(i).getY()-packingStationPoints().get(i).getY(), 2)));
		p2c=Math.sqrt((Math.pow(packingStationPoints().get(i).getX()-chargingPodPoints().get(i).getX(),2))+(Math.pow(packingStationPoints().get(i).getY()-chargingPodPoints().get(i).getY(), 2)));
		distanceEstimate = r2s+s2p+p2c;
	
		return distanceEstimate;
	
	}
	
	public static double distanceToSteps() {
		 noSteps = (0.7062*distanceEstimate)+0.05221;
		System.out.println(noSteps);
		 return noSteps;
		/*
		 * a method to create a corelation and convert distances to ticks in order to estimate wheter or not could accept the order.
		 */
	}
	
	
}