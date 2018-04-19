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
	 * Start location for the algorithm.
	 * @see #distanceCalculator
	 */
	private Point startPosition; // x and y of the start position
	
	/**
	 * End location for the algorithm.
	 * @see #distanceCalculator
	 */
	private Point endPosition;//x and y of the end position

	private Point byPosition;//x and y of the end position
	
	private double distanceEstimate;


	
	public CostEstimationStrategy() {
		super();
		startPosition = robotCoordinates;
		endPosition = PackingStation.getPackingCoordinates();
		byPosition=StorageShelf.getStorageCoordinates();
		//route = new Point[2];
	}

	/**
	 * Calculates the distance from one point to another.
	 * @param firstStop 
	 * @param secondStop 
	 * @param returnStop 
	 * @return <code>double</code> Distance between locations
	 * this is repeated code, so we will write down the maths into a single function and call the function in every time with parameters
	 */
	/*public double distanceCalculator(double firstStop, double secondStop, double returnStop) {
		firstStop=Math.sqrt((Math.pow(startPosition.getX()-byPosition.getX(),2))+(Math.pow(startPosition.getY()-byPosition.getY(),2)));
		secondStop=Math.sqrt((Math.pow(byPosition.getX()-endPosition.getX(),2))+(Math.pow(byPosition.getY()-endPosition.getY(),2)));
		returnStop=Math.sqrt((Math.pow(endPosition.getX()-startPosition.getX(),2))+(Math.pow(endPosition.getY()-startPosition.getY(),2)));
		distanceEstimate=firstStop+secondStop+returnStop;
		return distanceEstimate;
	}*/
	public double calcengine (double r2s, double s2p, double p2c) {
		robotPoints().get(1).getX();
		int i =0;
		
		r2s=Math.sqrt((Math.pow(robotPoints().get(i).getX()-storageShelfPoints().get(i).getX(),2))+(Math.pow(robotPoints().get(i).getY()-storageShelfPoints().get(i).getY(), 2)));
		s2p=Math.sqrt((Math.pow(storageShelfPoints().get(i).getX()-packingStationPoints().get(i).getX(),2))+(Math.pow(storageShelfPoints().get(i).getY()-packingStationPoints().get(i).getY(), 2)));
		p2c=Math.sqrt((Math.pow(packingStationPoints().get(i).getX()-chargingPodPoints().get(i).getX(),2))+(Math.pow(packingStationPoints().get(i).getY()-chargingPodPoints().get(i).getY(), 2)));
		distanceEstimate = r2s;
		return distanceEstimate;
	

	}
	
 // mporoume na paroume tis sintetagmenes apo to ta antistoixa ArrayList, px gia to packing station pou briskete. Kathe index antistoixei se ena uid.
//	px. [0] kanei access ta coordinates tou robot 0, [1] kanei access ta coordintates tou robot 1 kok.
//	Ara exoume 3-4 arraylist pou mporoume na kanoume access gia na paroume tis 4 diaforetikes sintetagmenes.
// mono thema einai oti ama baloume koino i mporei h paragkeleia na mi simpititei me to sim file. me alla logia sinithws tha prepepe na nai robot 1 packing 1 alla mporei na tuxei robot 1 packing 3 ktl ktl
// ara theloume ena scanner gia na pernoume ta indexes kathe fora gia tin paagkeleia.
	
	/*
	 * Greek above
	 */

	
	
}