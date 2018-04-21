package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {

	/**
	 * Contains the orders
	 * 
	 * @see #show, #isCompleted, #addtoUnassigned, #addToAssigned,
	 *      #removeFromUnassigned, #removeFromAssigned
	 */
	private static ArrayList<String> commands = new ArrayList<String>(); // reads a full sim file
	private static ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();
	private static ArrayList<String> configuration = new ArrayList<String>();
	private static ArrayList<String> podRob = new ArrayList<String>();
	private static ArrayList<String> shelves = new ArrayList<String>();
	private static ArrayList<String> stations = new ArrayList<String>();
	private static File file;

	/**
	 * Reads the orders from a file.
	 */

	public Order() {
	}
	
	/**
	 * Reads the .sim file and stores each line in an ArrayList
	 * 
	 * @return
	 */
	public static ArrayList<String> printCommands() {
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				commands.add(line);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return commands;
	}

	/**
	 * Prints the .sim file in the GUI
	 * 
	 * @return
	 */
	public static String getCommands() {
		String line = "";
		for (int i = 0; i < commands.size(); i++) {
			line += commands.get(i) + "\n";
		}
		return line;
	}

	/**
	 * Filters the results from the .sim file into their respective ArrayLists
	 */
	public static void fillLists() {
		try {
			Scanner scanner = new Scanner(file);
			clearLists();
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
			
				if(line.contains("podRobot")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length;i++) {
						podRob.add(temp[i]);
					}
				
				}
				else if(line.contains("shelf")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length;i++) {
						shelves.add(temp[i]);
					}
					
				}
				else if(line.contains("station")) {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length;i++) {
						stations.add(temp[i]);
					}
				}
				else if(line.contains("order")){
					String[] temp = line.split(" ");
					ArrayList<String> sentence = new ArrayList<String>();
					for(int i = 0; i < temp.length; i++) {
						sentence.add(temp[i]);
					}
					orders.add(sentence);
				}
				else {
					String[] temp = line.split(" ");
					for (int i = 0; i < temp.length;i++) {
						configuration.add(temp[i]);
					}
				}
			} scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Array: " + orders.get(4).size());
	}

	/**
	 * Clears all the ArrayLists
	 */
	public static void clearLists() {
		podRob.clear();
		shelves.clear();
		stations.clear();
		commands.clear();
		orders.clear();
		configuration.clear();
	}
	

	public static void setFile(File f) {
		// happens in maincontroller just sets the file that's been chosen
		file = f;
	}

	public static ArrayList<String> getPodRob(){
		ArrayList<String> podRobot = podRob;
		return podRobot;
	}
	
	public static ArrayList<String> getConfiguration() {
		return configuration;
	}
	
	public static ArrayList<String> getPackingStations() {
		return stations;
	}
	
	public static ArrayList<String> getStorageShelves() {
		return shelves;
	}
	
	static ArrayList<ArrayList<String>> getOrders() {
		return orders;
	}
	/**
	 * Checks if an order has been completed, and moves it to "completedOrders" if
	 * it is completed.
	 */
	public void isCompleted() {
	}

	/**
	 * Adds an order to the "unassigned" list.
	 */
	public void addToUnassigned() {
	}

	/**
	 * Adds an order to the "assigned" list.
	 */
	public void addToAssigned() {
	}

	/**
	 * Removes an order from the "unassigned" list.
	 */
	public void removeFromUnassigned() {
	}

	/**
	 * Removes an order from the "assigned" list.
	 */
	public void removeFromAssigned() {
	}
	
	/**
	 * Converts orders to an ArrayList of an ArrayList of storage shelf points
	 */
	public static void ordersToPoints() {
		ArrayList<ArrayList<Point>> storagePoints = new ArrayList<ArrayList<Point>>();
		ArrayList<Point> storages = new ArrayList<Point>();
		for(int i = 0; i < orders.size(); i++) {
			for(int j = 2; j < orders.get(i).size(); j++) {
				ArrayList<StorageShelf> storageList = Warehouse.getStorageShelfs();
				for(int n = 0; n < storageList.size(); n++) {
					if(storageList.get(n).getID() == orders.get(i).get(j)) {
						storages.add(storageList.get(n).getStorageCoordinates());
					}
				}
			}
			storagePoints.add(storages);
		}
	}
	
	public static void del() {
		for (int i = 0; i < configuration.size(); i++) {
			System.out.println(configuration.get(i));
		}
	}

}