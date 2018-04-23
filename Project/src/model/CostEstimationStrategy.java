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
	
	public void distanceEstimator(int i, String uid) {
		robotStorage = Math.sqrt((Math.pow((Order.storagePoints().get(Order.getOrders().get(i)).getX()-getRobotX()),2))+Math.pow((Order.storagePoints().get(uid).getY()-getRobotY()), 2));
		
		for (int j=2; j< Order.getOrders().get(i).size(); j++) {  // gets the ss1 ss2 etc

			double storageDistances = Math.sqrt((Math.pow((Order.storagePoints().get(j).getX()-Order.storagePoints().get(j++).getX()),2))+Math.pow((Order.storagePoints().get(j).getY()-Order.storagePoints().get(j++).getY()),2));
			double storagePacking = Math.sqrt((Math.pow((Order.storagePoints().get(Order.storagePoints().size()-1).getX()-Order.packingPoints().get(uid).getX()),2)) + Math.pow((Order.storagePoints().get(Order.storagePoints().size()-1).getY()-Order.packingPoints().get(uid).getY()),2));
			doblediagonals += storageDistances+storagePacking;
		}
		//return doblediagonals;
	}
	
	public static double distanceToSteps() {
	//	distanceEstimator(i, uid);
		noSteps = ((1.422*robotStorage)-0.07577)+(2*(1.422*(doblediagonals)-0.07577));
		System.out.println(noSteps);
		return noSteps;
	
	}
}