package model;
/**
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman, Vivek Bhukhan, Christos Dolopikos 
 *
 */

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
	private static ArrayList<String[]> orders = new ArrayList<String[]>();
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

	/*
	 * public static ArrayList<Point> getCoordinates() { String[] coordinates =
	 * processData(); for (int i = 0; i < coordinates.length; i+=4) { for (int j =
	 * 0; j < coordinates.length; j+)
	 * 
	 * } 
	 * }
	 */
	
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
			ArrayList<String> sentences = new ArrayList<String>();
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
					sentences.add(line);
					for(int i = 0; i < sentences.size(); i++) {
						String[] word = sentences.get(i).split(" ");
						orders.add(word);
					}
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
		System.out.println("Array: " + orders.get(4).length);
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
	
	static ArrayList<String> getConfiguration() {
		return configuration;
	}
	
	static ArrayList<String> getPackingStations() {
		return stations;
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

}