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
import java.util.HashMap;
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
	private static ArrayList<ArrayList<String>> assignedOrders = new ArrayList<ArrayList<String>>();
	private static ArrayList<ArrayList<String>> completedOrders = new ArrayList<ArrayList<String>>();
	private static ArrayList<ArrayList<String>> decisionsList = new ArrayList<ArrayList<String>>();
	private static ArrayList<String> configuration = new ArrayList<String>();
	private static ArrayList<String> podRob = new ArrayList<String>();
	private static ArrayList<String> shelves = new ArrayList<String>();
	private static ArrayList<String> stations = new ArrayList<String>();
	private static File file;
	private static HashMap<String, Point> storagePoints = new HashMap<String, Point>();
	private static HashMap<String, Point> packingPoints = new HashMap<String, Point>();
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
		file = f;// happens in maincontroller just sets the file that's been chosen
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
		return shelves; //gets storage shelves both uid and coordinates
	}
	
	static ArrayList<ArrayList<String>> getOrders() {
		return orders; //gets orders i.e. for i=0 order 9 ss0 ss1 ss3 for i=1 order 7 ss0 ss1 ss5 etc.
	}
	/**
	 * Checks if an order has been completed, and moves it to "completedOrders" if
	 * it is completed.
	 */
	public void isCompleted() {
	}
	
	public static void removeFromOrders() {
		orders.remove(0);
	}
	
	public static void addToDecision(ArrayList<String> order) {
		decisionsList.add(order);
	}
	
	public static void removeFromDecision(int index) {
		decisionsList.remove(index);
	}
	
	public static void addToAssigned(ArrayList<String> order) {
		assignedOrders.add(order);
	}
	
	public static void removeFromAssigned(int index) {
		assignedOrders.remove(index);
	}
	
	public static void addToCompleted(ArrayList<String> order) {
		completedOrders.add(order);
	}
	
	/**
	 * 
	 * @return
	 */
	public static HashMap<String, Point> storagePoints() {
		for(StorageShelf s: Warehouse.getStorageList()) {
			storagePoints.put(s.getID(), s.getStorageCoordinates());
		}
		return storagePoints;
	}
	
	public static HashMap<String, Point> packingPoints(){
		for(PackingStation p : Warehouse.getPackingStations()) {
			packingPoints.put(p.getID(), p.getPackingCoordinates());
		}
		return packingPoints;
	}
}