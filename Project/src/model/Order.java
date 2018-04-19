package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Order {
	
	/**
	 * Contains the orders
	 * @see #show, #isCompleted, #addtoUnassigned, #addToAssigned, #removeFromUnassigned, #removeFromAssigned
	 */
	private List<String> assignedOrders, unassignedOrders, completedOrders;
	private static String[] data; // reads the whole order
	private static String[] podRob; // arrays for entities one podrob, shelf and station
	private static String[] shelves;
	private static String[] stations;
	private static String[] orders; // holds orders
	private static String[] setUp; // format width, height, capacity charge speed sets up the grid dimensions
	/**
	 * Reads the orders from a file.
	 */
	private Scanner scanner;

	private static File file;
	
	public Order() {
	}
	
	public static void processData() {
		//int width = fileWidth;
		//int height = fileHeight;
		// this.coordinates = coordinates;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				if (scanner.equals("podRob")) {
					podRob = scanner.nextLine().split(" ");
				}
				data = scanner.nextLine().split(" ");						
				for (int i = 0; i < data.length; i++) {
					System.out.println(data[i].toString());
				}
				
				for (int i = 0; i < podRob.length; i++) {
					System.out.println(podRob.length);
				}
				
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
	}
	}
	
	/*
	public static ArrayList<Point> getCoordinates() {
		String[] coordinates = processData();
		for (int i = 0; i < coordinates.length; i+=4) {
			for (int j = 0; j < coordinates.length; j+)
			
		}
	}
*/
	
	public static void setFile(File f) {
		// happens in maincontroller just sets the file that's been chosen
		file = f;
	}

	
	/**
	 * Shows the list of orders.
	 */
	public void show() {}
	
	/**
	 * Checks if an order has been completed, and moves it to
	 * "completedOrders" if it is completed.
	 */
	public void isCompleted() {}
	
	/**
	 * Adds an order to the "unassigned" list.
	 */
	public void addToUnassigned() {}
	
	/**
	 * Adds an order to the "assigned" list.
	 */
	public void addToAssigned() {}
	
	/**
	 * Removes an order from the "unassigned" list.
	 */
	public void removeFromUnassigned() {}
	
	/**
	 * Removes an order from the "assigned" list.
	 */
	public void removeFromAssigned() {}
	
}