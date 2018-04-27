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

	private ArrayList<String> sentence;

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

		order.newSentence();
		sentence = order.getNextSentence();

		if(sentence != null) {

			double packingStorage = 0.0;
			double storagePacking = 0.0;
			double packingPod = 0.0;
			double robotStorage = 0.0;

			double X = storagePoints.get(sentence.get(2)).getX();
			double Y = storagePoints.get(sentence.get(2)).getY();

			robotStorage = super.pythagoras(robotX, X, robotY, Y);	
			for (int j = 2; j< sentence.size(); j++) {  
				if((j + 1) < sentence.size()) {
					double x1 = storagePoints.get(sentence.get(j)).getX();
					double x2 = destination.getX();
					double y1 = storagePoints.get(sentence.get(j)).getY();
					double y2 = destination.getY();
					storagePacking += super.pythagoras(x1, y1, x2, y2);	

					double X1 = storagePoints.get(sentence.get(j++)).getX();
					double Y1 = storagePoints.get(sentence.get(j++)).getY();
					packingStorage += super.pythagoras(X1, Y1, x2, y2);	
				}
				else {
					double x1 = storagePoints.get(sentence.get(sentence.size() - 1)).getX();
					double x2 = destination.getX();
					double y1 = storagePoints.get(sentence.get(sentence.size() - 1)).getY();
					double y2 = destination.getY();
					storagePacking += super.pythagoras(x1, y1, x2, y2);	
				}
			}

			double x1 = chargePoints.get(robotsChargePod.get(uid)).getX();
			double x2 = destination.getX();
			double y1 = chargePoints.get(robotsChargePod.get(uid)).getY();
			double y2 = destination.getY();
			packingPod = super.pythagoras(x1, y1, x2, y2);	

			noSteps = ((GRADIENT * (robotStorage + packingStorage + packingPod)) - INTERCEPT) + (2 * (GRADIENT * (storagePacking) - INTERCEPT));

			if(noSteps < (SAFETY_MARGIN*batteryLevel) + (batteryLevel)) {
				return true;
			}
			else {
				return false;		
			}
		}
		return false;
	}

	public ArrayList<String> getSentence(){
		return sentence;
	}
}