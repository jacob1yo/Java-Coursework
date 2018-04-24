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
	
	private static double robotX;
	
	private static double robotY;
	
	private static String uid;
	
	private static double safetyMargin;
	
	private static int batteryLevel;

	public CostEstimationStrategy() {
		super();
		noSteps=0.0;
	}
	
	public void addToRobotCoordinates(Order order, double x, double y, String uid, int batteryLvl) {
		robotX = x;
		robotY = y;
		this.uid = uid;
		safetyMargin = 0.2;
		batteryLevel = batteryLvl;
	}

	/**
	 * Calculates the distance from one point to another.
	 * @param 
	 * @return <code>double</code> Distance between locations
	 * this is repeated code, so we will write down the maths into a single function and call the function in every time with parameters
	 */
	public static void distanceEstimator() {
		Point destination=PackingStation.passOnPoint();

		ArrayList<String> sentence = PackingStation.getNextOrder();
		double storageDistances = 0.0;
		double storagePacking = 0.0;
		double packingPod;
		double doublediagonals=0.0;
		double robotStorage=0.0;
		System.out.println("Destionation: " + destination);
		System.out.println("PackingX: " + Warehouse.getRobotsChargePod().size());
		System.out.println("DestX: " + destination.getX());
		System.out.println("String: " + Order.chargePoints().get(Warehouse.getRobotsChargePod().size()-1));
		robotStorage = Math.sqrt((Math.pow((Order.storagePoints().get(sentence.get(2)).getX()-robotX),2))+Math.pow((Order.storagePoints().get(sentence.get(2)).getY()-robotY), 2));

		for (int j = 2; j< sentence.size(); j++) {  // gets the ss1 ss2 etc
			System.out.println("StorageP: " + Order.storagePoints().size());
			System.out.println("Sentence: " + sentence.size());
			System.out.println("X: " + Order.storagePoints().get(sentence.get(j)).getX());
			System.out.println("SecX: " + Order.storagePoints().get(sentence.get(j++)).getX());
			if ((j++) < sentence.size()) {
				storageDistances = Math.sqrt((Math.pow((Order.storagePoints().get(sentence.get(j)).getX()-Order.storagePoints().get(sentence.get(j++)).getX()),2))+Math.pow((Order.storagePoints().get(sentence.get(j)).getY()-Order.storagePoints().get(sentence.get(j++)).getY()),2));
			}
			else {
				storagePacking = Math.sqrt((Math.pow((Order.storagePoints().get(sentence.get(sentence.size() - 1)).getX()-destination.getX()),2)) + Math.pow((Order.storagePoints().get(sentence.get(sentence.size() - 1)).getY()-destination.getY()),2));
			}
			doublediagonals += storageDistances+storagePacking;
		}
		packingPod = Math.sqrt((Math.pow((Order.chargePoints().get(Warehouse.getRobotsChargePod().get(uid)).getX() -destination.getX()),2)) + Math.pow((Order.chargePoints().get(Warehouse.getRobotsChargePod().get(uid)).getY()-destination.getY()),2));

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
		distanceEstimator();
		return value;
	}

	public void getDistanceEstimator(ArrayList<String> sentence) {
		Order.removeFromDecision(sentence);
		Order.addToAssigned(sentence);
	}

	public static ArrayList<Point> getDestinations(){
		ArrayList<Point> destinations = new ArrayList<Point>();
		ArrayList<String> order = PackingStation.getNextOrder();
		for(int i = 2; i < order.size(); i++) {
			destinations.add(Order.storagePoints().get(order.get(i)));
		}
		destinations.add(PackingStation.passOnPoint());
		System.out.println(PackingStation.passOnPoint());
		//destinations.add(new Point(0, 2));
		System.out.println("Cost estimation dest: " + destinations.toString());
		return destinations;
	}
	
	public static int numTicks() {
		String temp = PackingStation.getNextOrder().get(1);
		Integer ticks = Integer.parseInt(temp);
		System.out.println("Ticks: " + ticks);
		return ticks;
	}
	
	private double pythagoras(double x, double y, double X, double Y) {
		return Math.sqrt((X - x)*(X - x) + (Y - y)*(Y - y));	
	}

}