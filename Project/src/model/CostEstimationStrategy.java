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
	 Point destination;

	/**
	 * Stores the no number of steps required for the estimated as double.
	 * @see #distanceCalculator
	 */
	private static double noSteps;
	
//	private static double storagePacking;
	
	private static double robotStorage;
	
	private static double doblediagonals;
	
	private static double sum;
	
	public CostEstimationStrategy() {
		super();
		noSteps=0.0;
		distanceEstimate=0.0;
	}


	/**
	 * Calculates the distance from one point to another.
	 * @param 
	 * @return <code>double</code> Distance between locations
	 * this is repeated code, so we will write down the maths into a single function and call the function in every time with parameters
	 */
	public double distanceEstimator() {
		//TODO gets in order and point from packing. clean up. decide order. "i" is sentence = first line of order. j is each word
		Point destination=PackingStation.passOnPoint();
		
		ArrayList<String> sentence = PackingStation.getNextOrder();
		
		robotStorage = Math.sqrt((Math.pow((Order.storagePoints().get(sentence.get(2)).getX()-getRobotX()),2))+Math.pow((Order.storagePoints().get(sentence.get(2)).getY()-getRobotY()), 2));
		
				for (int j=2; j< sentence.size(); j++) {  // gets the ss1 ss2 etc

			double storageDistances = Math.sqrt((Math.pow((Order.storagePoints().get(j).getX()-Order.storagePoints().get(j++).getX()),2))+Math.pow((Order.storagePoints().get(j).getY()-Order.storagePoints().get(j++).getY()),2));
			
			double storagePacking = Math.sqrt((Math.pow((Order.storagePoints().get(Order.storagePoints().size()-1).getX()-destination.getX()),2)) + Math.pow((Order.storagePoints().get(Order.storagePoints().size()-1).getY()-destination.getY()),2));
			doblediagonals += storageDistances+storagePacking;
		}
		noSteps = ((1.422*robotStorage)-0.07577)+(2*(1.422*(doblediagonals)-0.07577));
		System.out.println(noSteps);
		return noSteps;
	}
	public  boolean decideOrder() {
		if(distanceEstimator() < (safetyMargin*batteryLevel)+(batteryLevel)) {
		}
		
		return true;		
	}
}