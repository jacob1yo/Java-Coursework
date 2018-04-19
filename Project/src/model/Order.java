package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
	
	/**
	 * Contains the orders
	 * @see #show, #isCompleted, #addtoUnassigned, #addToAssigned, #removeFromUnassigned, #removeFromAssigned
	 */
	private static ArrayList<String> commands = new ArrayList<String>(); // reads a full sim file
	private static ArrayList<String> orders = new ArrayList<String>(); 
	private static ArrayList<Integer> configuration = new ArrayList<Integer>();
	private static ArrayList<String> podRob = new ArrayList<String>();
	private static ArrayList<String> shelves = new ArrayList<String>();
	private static ArrayList<String> stations = new ArrayList<String>();
	private static File file;
	
	/**
	 * Reads the orders from a file.
	 */

		
	public Order() {
	}
	
	/*public static void processData() {
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
	}*/
	
	/*
	public static ArrayList<Point> getCoordinates() {
		String[] coordinates = processData();
		for (int i = 0; i < coordinates.length; i+=4) {
			for (int j = 0; j < coordinates.length; j+)
			
		}
	}
*/
	// This method will be used tomorrow to separate out the orders
	public static ArrayList<String> printCommands(){
		try{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				commands.add(line);	
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return commands;
	}
	
	
	public static String getCommands(){
		String line = "";
		for(int i=0; i < commands.size(); i++ ) {
			line += commands.get(i) + "\n";
		}
		return line;
	}
	
	public void fillPodRobList() {
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.contains("podRobot")) {
					podRob.add(line);
				}
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(podRob.size()); //delete manual test after; need to clear list after
	}
	
	public void fillShelvesList() {
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.contains("shelf")){
					shelves.add(line);
				}
			}
			scanner.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(shelves.size()); //delete manual test after; need to clear list after
	}
	
	public void fillStationsList() {
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.contains("station")) {
					stations.add(line);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(stations.size()); //delete manual test after; need to clear list after
	}
	
	public static void fillOrdersList() {
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.contains("order")) {
					orders.add(line);		//does not work properly i think
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < orders.size(); i++) {
			System.out.println(orders.get(i).toString() + "\n");
		}
		//System.out.println(orders.size()); //delete manual test after; need to clear list after
	}
	
	public void clearLists() { //needs to be implemented
		podRob.clear();
		shelves.clear();
		stations.clear();
		commands.clear();
		orders.clear();
	}
	
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