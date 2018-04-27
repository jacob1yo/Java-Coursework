package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, 
 * Vivek Bhukhan, Christos Dolopikos 
 *
 * @version 1.0
 */


public class CostEstimationStrategy extends Robot {

	/**
	 * Stores the no number of steps required for the estimated as double.
	 * 
	 * @see #distanceCalculator
	 */
	private double noSteps;

	/**
	 * Used in distanceEstimator, to pass in a Order.
	 * 
	 * @see #distanceEstiamator
	 */
	private Order order;

	/**
	 * Used in disanceEstimator, to pass in the Packing Station the Robot has to deliver the items to.
	 * 
	 * @see #distanceEstimator
	 */
	private PackingStation packingStation;

	/**
	 * A <code>HashMap</code> of the coordinates each Storage Shelf.
	 * 
	 * @see #distanceEstimator
	 */
	private HashMap<String, Point> storagePoints;

	/**
	 * Constant Field that calculates the gradient in y=mx+c.
	 * 
	 * @see #distanceEstimator
	 */
	public final double GRADIENT = 1.422;
	
	/**
	 * Constant Field that calculates the intercept in y=mx+c.
	 * 
	 * @see #distanceEstimator
	 */
	public final double INTERCEPT = 0.07577;

	/**
	 * An <code>ArrayList</code> that holds the single order that need to be assigned, from the "SIM" file.
	 * 
	 * @see #distanceEstimator #getSentence
	 */
	private ArrayList<String> sentence;

	/**
	 * CostEstimationStrategy Constructor.
	 * 
	 * Gets the values from the superClass <code>Robot</code>.
	 * Assigns the the fields: Order, PackingStation.
	 * Populates the StoragePoints <code>HashMap</code>.
	 * Initialises the value of noSteps.
	 * 
	 * @param order an <code>Order</code> object. Holds the instance of the Order class that contains the orders from the "SIM" file.
	 * @param packing an an <code>PackingStatioln</code> object. Holds the instance of the Packing Station that the Robot need to deliver the items to.
	 * @param storagePoints a <code>HashMap</code> of <code>String, Point</code>. Contains the coordinates of the Storage Shelves.
	 */
	public CostEstimationStrategy(Order order, PackingStation packing, HashMap<String, Point> storagePoints) {
		super();
		this.order = order;
		packingStation = packing;
		this.storagePoints = storagePoints;
		noSteps = 0.0;
	}

	/**
	 * Calculates whether or not a Robot can carry out an order.
	 * 
	 * @param x a <code>double</code> value. Holds the current x coordinate of the Robot.
	 * @param y a <code>double</code> value. Holds the current y coordinate of the Robot.
	 * @param id a <code>String</code>. Holds the Robots UID.
	 * @param batteryLvl an <code>int</code> value. Holds the current battery level of the Robot.
	 * @param robotsCharge a <code>HashMap</code> of <code>String, String</code>. Contains the Robots UID, and their respective Charging Pod.
	 * @param charges a <code>HashMap</code> of <code>String, Point</code>. Contains the coordinates of each Charging Pod.
	 * 
	 * @return Returns a <code>boolean</code> value. True if the Robot can carry out the order, else false.
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

	/**
	 * A <code>ArrayList</code> contains the Order that needs to be checked.
	 * 
	 * @return Returns a <code>ArrayList</code> of <code>String</code>, used in {@link #sentence}.
	 */
	public ArrayList<String> getSentence(){
		return sentence;
	}
}