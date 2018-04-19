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
	
	/**
	 * Reads the orders from a file.
	 */
	private Scanner scanner;

	private File file;
	
	public Order() {
	}
	
	public void processData() {
		//int width = fileWidth;
		//int height = fileHeight;
		// this.coordinates = coordinates;
		try {
			Scanner scanner = new Scanner(file);
			File file = new File(scanner.nextLine());
			while (scanner.hasNextLine()) {
				String[] data = scanner.nextLine().split(" ");
				String line = scanner.nextLine();				
				System.out.println(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void setFile(File f) {
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