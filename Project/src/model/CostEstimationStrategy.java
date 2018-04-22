package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner; 



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
		 noSteps = (1.422*distanceEstimate)-0.07577;
		System.out.println(noSteps);
		 return noSteps;
	}
	
	
	//NEW implementation of cost estimation
	
	public void totalDistance() {
		int totalDistance = 0;
		Point n = robotPoint;
		for(int i = 0; i < ssPoints.size(); i++) {
			totalDistance += pointToPoint(n, ssPoints.get(i).getCoordinates());
			
			
		
		}
	}
	
	public void distanceEstimator() {
		for (int i=0; i< Order.getOrders().size(); i++) {//get the sentence i.e order 9 ss0 ss3 ss1 etc
			for (int j=2; j< Order.getOrders().get(i).size(); j++) {  // gets the ss1 ss2 etc
	
			double storageDistances = Math.sqrt((Math.pow((Order.storagePoints().get(j).getX()-Order.storagePoints().get(j++).getX()),2))+Math.pow((Order.storagePoints().get(j).getY()-Order.storagePoints().get(j++).getY()),2));
			
			 double sum =+ storageDistances;
			//System.out.println(Order.getOrders().get(i).get(j));
			
			if (j == Order.storagePoints().size()-1) {
			double storagePacking = Math.sqrt((Math.pow((Order.storagePoints().get(j).getX()-Order.packingPoints().get(j).getX()),2))+Math.pow((Order.storagePoints().get(j).getY()-Order.packingPoints().get(j).getY()), 2));
			}
			
			}
		}
		double robotStorage = Math.sqrt((Math.pow((Order.storagePoints().get(0).getX()-getRobotX()),2))+Math.pow((Order.storagePoints().get(0).getY()-getRobotY()), 2));
		
		if (j == Order.storagePoints().size()-1) {
			double storagePacking = Math.sqrt((Math.pow((Order.storagePoints().get(j).getX()-Order.packingPoints().get(j).getX()),2))+Math.pow((Order.storagePoints().get(j).getY()-Order.packingPoints().get(j).getY()), 2));
			}
	
	}
	
	
	public int pointToPoint(Point start, Point end) {
		int distance = 0;
		
		return distance;
	}
	
	
}