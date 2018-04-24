package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

import java.awt.Point;
import java.util.ArrayList;

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

	private static boolean value;

	public CostEstimationStrategy() {
		super();
		noSteps=0.0;
	}

	/**
	 * Calculates the distance from one point to another.
	 * @param 
	 * @return <code>double</code> Distance between locations
	 * this is repeated code, so we will write down the maths into a single function and call the function in every time with parameters
	 */
	public void distanceEstimator() {
		Point destination=PackingStation.passOnPoint();

		ArrayList<String> sentence = PackingStation.getNextOrder();
		double storageDistances = 0.0;
		double storagePacking = 0.0;
		double packingPod;
		double doublediagonals=0.0;
		double robotStorage=0.0;

		robotStorage = Math.sqrt((Math.pow((Order.storagePoints().get(sentence.get(2)).getX()-getRobotX()),2))+Math.pow((Order.storagePoints().get(sentence.get(2)).getY()-getRobotY()), 2));

		for (int j=2; j< sentence.size(); j++) {  // gets the ss1 ss2 etc	
			if (j++<sentence.size()) {
				storageDistances = Math.sqrt((Math.pow((Order.storagePoints().get(j).getX()-Order.storagePoints().get(j++).getX()),2))+Math.pow((Order.storagePoints().get(j).getY()-Order.storagePoints().get(j++).getY()),2));
			}
			else {
				storagePacking = Math.sqrt((Math.pow((Order.storagePoints().get(Order.storagePoints().size()-1).getX()-destination.getX()),2)) + Math.pow((Order.storagePoints().get(Order.storagePoints().size()-1).getY()-destination.getY()),2));
			}
			doublediagonals += storageDistances+storagePacking;	
		}
		packingPod = Math.sqrt((Math.pow((Order.chargePoints().get(Warehouse.getRobotsChargePod().get(super.getID())).getX() -destination.getX()),2)) + Math.pow((Order.chargePoints().get(Warehouse.getRobotsChargePod().get(super.getID())).getY()-destination.getY()),2));

		noSteps = ((1.422*(robotStorage + packingPod))-0.07577)+(2*(1.422*(doublediagonals)-0.07577));
		System.out.println(noSteps);

		if(noSteps < (safetyMargin*batteryLevel)+(batteryLevel)) {
			value = true;
		}
		else {
			value = false;		
		}
	}

	public static boolean getDecision() {
		return value;
	}

	public void getDistanceEstimator(ArrayList<String> sentence) {
		Order.removeFromDecision(sentence);
		Order.addToAssigned(sentence);
	}

	public static ArrayList<Point> getDestinations(){
		ArrayList<Point> destinations = new ArrayList<Point>();
		ArrayList<String> order = PackingStation.getNextOrder();
		for(int i = 0; i < order.size(); i++) {
			//destinations.add(Order.storagePoints().get(order.get(i)));
			System.out.println(order.get(i));
		}
		destinations.add(PackingStation.passOnPoint());
		return destinations;
	}

}