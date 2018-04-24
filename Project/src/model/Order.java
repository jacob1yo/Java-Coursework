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
	private ArrayList<String> commands = new ArrayList<String>(); // reads a full sim file
	private static ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> assignedOrders = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> completedOrders = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> decisionsList = new ArrayList<ArrayList<String>>();
	private File file;

	/**
	 * Reads the orders from a file.
	 */

	public Order() {
	}

	public String getCommands() {
		String line = "";
		for (int i = 0; i < commands.size(); i++) {
			line += commands.get(i) + "\n";
		}
		return line;
	}

	public void setFile(File f) {
		file = f;// happens in maincontroller just sets the file that's been chosen
	}

	public File getFile() {
		return file;
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

	public void addToDecision(ArrayList<String> order) {
		decisionsList.add(order);
	}

	public void removeFromDecision(ArrayList<String> order) {
		//decisionsList.remove(index);
		decisionsList.remove(order);
	}

	public void addToAssigned(ArrayList<String> order) {
		assignedOrders.add(order);
	}

	public void removeFromAssigned(int index) {
		assignedOrders.remove(index);
	}

	public void addToCompleted(ArrayList<String> order) {
		completedOrders.add(order);
	}

	//private static ArrayList<ArrayList<String>> assignedOrders = new ArrayList<ArrayList<String>>();

	public String getAssigned() {
		String assigned = "";
		for (int i = 0; i < assignedOrders.size(); i++) {
			for (int j = 0; j < assignedOrders.get(i).size(); j++) {
				assigned = assignedOrders.get(i).get(j);
			}
		}
		return assigned;
	}

	public void fillLists() {
		try {
			Scanner scanner = new Scanner(file);
			clearLists();
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.contains("order")){
					String[] temp = line.split(" ");
					ArrayList<String> sentence = new ArrayList<String>();
					for(int i = 0; i < temp.length; i++) {
						sentence.add(temp[i]);
					}
					orders.add(sentence);	
				}
			} scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void clearLists() {
		orders.clear();
		commands.clear();
		//TODO Maybe clear rest of lists too?
	}

	public ArrayList<String> printCommands() {
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				commands.add(line);
			}
			scanner.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return commands;
	}

}