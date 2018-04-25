package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

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
	private double noSteps;

	private Order order;

	private PackingStation packingStation;

	private HashMap<String, Point> storagePoints;

	public final double GRADIENT = 1.422;

	public final double INTERCEPT = 0.07577;

	public CostEstimationStrategy(Order order, PackingStation packing, HashMap<String, Point> storagePoints) {
		super();
		this.order = order;
		packingStation = packing;
		this.storagePoints = storagePoints;
		noSteps = 0.0;
	}

	/**
	 * Calculates the distance from one point to another.
	 * @param 
	 * @return <code>double</code> Distance between locations
	 * this is repeated code, so we will write down the maths into a single function and call the function in every time with parameters
	 */
	public boolean distanceEstimator(double x, double y, String id, int batteryLvl, HashMap<String, String> robotsCharge, HashMap<String, Point> charges) {
		double robotX = x;
		double robotY = y;
		String uid = id;
		batteryLevel = batteryLvl;
		HashMap<String, String> robotsChargePod = robotsCharge;
		HashMap<String, Point> chargePoints = charges;

		Point destination = packingStation.passOnPoint();

		ArrayList<String> sentence = packingStation.getNextOrder(order);
		double storageDistances = 0.0;
		double storagePacking = 0.0;
		double packingPod = 0.0;
		double doublediagonals = 0.0;
		double robotStorage = 0.0;
		
		System.out.println("CostEst storagePoints: " + storagePoints.size());
		double X = storagePoints.get(sentence.get(2)).getX();
		double Y = storagePoints.get(sentence.get(2)).getY();
		
		if(robotX != X || robotY != Y) {
			robotStorage = super.pythagoras(robotX, X, robotY, Y);
		}

		for (int j = 2; j< sentence.size(); j++) {  // gets the ss1 ss2 etc
			if ((j++) < sentence.size()) {
				double x1 = storagePoints.get(sentence.get(j)).getX();
				double x2 = storagePoints.get(sentence.get(j++)).getX();
				double y1 = storagePoints.get(sentence.get(j)).getY();
				double y2 = storagePoints.get(sentence.get(j++)).getY();
				storageDistances += super.pythagoras(x1, y1, x2, y2);
			}
			else {
				double x1 = storagePoints.get(sentence.get(sentence.size() - 1)).getX();
				double x2 = destination.getX();
				double y1 = storagePoints.get(sentence.get(sentence.size() - 1)).getY();
				double y2 = destination.getY();
				storagePacking = super.pythagoras(x1, y1, x2, y2);
			}
			doublediagonals += storageDistances+storagePacking;
		}
		double x1 = chargePoints.get(robotsChargePod.get(uid)).getX();
		double x2 = destination.getX();
		double y1 = chargePoints.get(robotsChargePod.get(uid)).getY();
		double y2 = destination.getY();
		packingPod = super.pythagoras(x1, y1, x2, y2);

		noSteps = ((GRADIENT*(robotStorage + packingPod))-INTERCEPT)+(2*(GRADIENT*(doublediagonals)-INTERCEPT));
		System.out.println(noSteps);

		if(noSteps < (SAFETY_MARGIN*batteryLevel)+(batteryLevel)) {
			return true;
		}
		else {
			return false;		
		}
	}

	public void getDistanceEstimator(ArrayList<String> sentence) {
		order.removeFromDecision(sentence);
		order.addToAssigned(sentence);
	}

	public ArrayList<Point> getDestinations(){
		ArrayList<Point> destinations = new ArrayList<Point>();
		ArrayList<String> newOrder = packingStation.getNextOrder(order);
		for(int i = 2; i < newOrder.size(); i++) {
			destinations.add(storagePoints.get(newOrder.get(i)));
		}
		destinations.add(packingStation.passOnPoint());
		System.out.println(packingStation.passOnPoint());
		//destinations.add(new Point(0, 2));
		System.out.println("Cost estimation dest: " + destinations.toString());
		return destinations;
	}

	public int numTicks() {
		String temp = packingStation.getNextOrder(order).get(1);
		Integer ticks = Integer.parseInt(temp);
		System.out.println("Ticks: " + ticks);
		return ticks;
	}

}