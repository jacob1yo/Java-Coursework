package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the implementation for the Order methods.
 * 
 * @author Miraj Shah, Devin Shingadia, Jacob Williams, Mohammed Hamza Zaman,
 *         Vivek Bhukhan, Christos Dolopikos.
 * 
 * @version 1.0
 */

public class Order {
	
	/**
	 * ArrayList containing <code>String</code> all the lines/sentences from the "SIM" file.
	 * 
	 * @see #getCommands #clearLists #printCommands
	 */
	private ArrayList<String> commands = new ArrayList<String>();
	
	/**
	 * Nested ArrayList containing all the <code>String</code> Orders read from the "SIM" file.
	 * 
	 * @see #getOrders #removeOrders #fillLists #clearLists
	 */
	private ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();
	
	/**
	 * Nested ArrayList containing all the <code>String</code> Orders have been assigned to assignedOrders from the "SIM" file.
	 * 
	 * @see #addToAssigned #removeFromAssigned #getAssigned
	 */
	private ArrayList<ArrayList<String>> assignedOrders = new ArrayList<ArrayList<String>>();
	
	/**
	 * Nested ArrayList containing all the <code>String</code> Orders which are completed and 
	 * assigned to completedOrders from the "SIM" file.
	 * 
	 * @see #addToCompleted
	 */
	private ArrayList<ArrayList<String>> completedOrders = new ArrayList<ArrayList<String>>();
	
	/**
	 * Nested ArrayList containing all the <code>String</code> of orders which are waited to be accepted by the Robots.
	 * 
	 * @see #addToDecision #removeFromDecision 
	 */
	private ArrayList<ArrayList<String>> decisionsList = new ArrayList<ArrayList<String>>();
	
	/**
	 * This File object which is the "SIM" file being loaded into the GUI.
	 * 
	 * @see #setFile #getFile #fillLists #printCommands 
	 */
	private File file;

	/**
	 * Order constructor.
	 */
	public Order() {
	}

	/**
	 * This method iterates through the {@link #commands} each index of the ArrayList and 
	 * gets each sentence line by line. 
	 * 
	 * @return Returns <code>String</code> line which represents each sentence line by line
	 * which is then used and shown in the GUI.
	 */
	public String getCommands() {
		String line = "";
		for (int i = 0; i < commands.size(); i++) {
			line += commands.get(i) + "\n";
		}
		return line;
	}
	
	/**
	 * Sets the "SIM" file, which is the <code>File</code> chosen by the user in the GUI as the file
	 * 
	 * @param f a {@link #file}}. Assigned to file.
	 */
	public void setFile(File f) {
		file = f;
	}

	/**
	 * Gets the file which has been chosen and loaded by the user in the GUI.
	 * 
	 * @return Returns <code>file</code> which is set by the {@link #setFile} method.
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Gets the orders from the Nested ArrayList containing the <code>String</code> representation.
	 * 
	 * @return Returns all <code>orders</code>. All Orders from the "SIM" file by index.
	 */
	public ArrayList<ArrayList<String>> getOrders() {
		return orders; 
	}

	/**
	 * Checks if an order has been completed, and moves it to "completedOrders" if
	 * it is completed.
	 */
	public void isCompleted() {
	}

	/**
	 * Removes order from the {@link #orders} from index 0 of the ArrayList containing orders
	 * when an order is moved to a different ArrayList.
	 */
	public void removeFromOrders() {
		orders.remove(0);
	}

	/**
	 * Adds {@link #orders} to {@link #decisionsList} ArrayList, where orders are waiting to be assigned to a robot.
	 * 
	 * @param order a <code>String</code>. Added to the {@link #decisionsList} ArrayList.
	 */
	public void addToDecision(ArrayList<String> order) {
		decisionsList.add(order);
	}

	/**
	 * Removes {@link #orders} from the {@link #decisionsList} ArrayList, where orders are waiting to be assigned to a robot.
	 * 
	 * @param order a <code>String</code>. Removed from the {@link #decisionsList} ArrayList.
	 */
	public void removeFromDecision(ArrayList<String> order) {
		decisionsList.remove(order);
	}

	/**
	 * Adds {@link #orders} to the {@link #assignedOrders} ArrayList, which stores the orders assigned to each robot.
	 * 
	 * @param order a <code>String</code>. Added to the {@link #assignedOrders} ArrayList.
	 */
	public void addToAssigned(ArrayList<String> order) {
		assignedOrders.add(order);
	}

	/**
	 * Removes {@link #orders} from the {@link #assignedOrders} ArrayList, which stores the orders assigned to each robot.
	 * 
	 * @param index a <code>int</code> value. Removed from {@link #assignedOrders} ArrayList.
	 */
	public void removeFromAssigned(int index) {
		assignedOrders.remove(index);
	}

	/**
	 * Adds {@link #orders} to {@link #completedOrders} ArrayList, where orders completed are stored.
	 * 
	 * @param order a <code>String</code>. Added to the {@link #completedOrders} ArrayList.
	 */
	public void addToCompleted(ArrayList<String> order) {
		completedOrders.add(order);
	}

	/**
	 * Method iterates through both nested {@link #assignedOrders} ArrayLists. The second nested ArrayList
	 * stores the order assigned as a <code>String</code>.
	 * 
	 * @return Returns assigned as a <code>String</code>. The Uids of the order.
	 */
	public String getAssigned() {
		String assigned = "";
		for (int i = 0; i < assignedOrders.size(); i++) {
			for (int j = 0; j < assignedOrders.get(i).size(); j++) {
				assigned = assignedOrders.get(i).get(j);
			}
		}
		return assigned;
	}

	/**
	 * This method scans the "SIM" file then populates and fills the {@link #orders} ArrayList 
	 * with an ArrayList of sentences scanned and populates index by index
	 * 
	 * @exception FileNotFoundException if the user chooses a File that does not exist.
	 */
	public void fillLists() {
		try {
			Scanner scanner = new Scanner(file);
			clearLists();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.contains("order")) {
					String[] temp = line.split(" ");
					ArrayList<String> sentence = new ArrayList<String>();
					for (int i = 0; i < temp.length; i++) {
						sentence.add(temp[i]);
					}
					orders.add(sentence);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clears the {@link #orders #commands} ArrayLists of all orders stored.
	 */
	public void clearLists() {
		orders.clear();
		commands.clear();
		// TODO Maybe clear rest of lists too?
	}

	/**
	 * This methods scans the lines/sentences in the "SIM" file and adds them to the {@link #commands} ArrayList.
	 * 
	 * @return Returns a <code>ArrayList</code> of sentences in the "SIM" file.
	 * 
	 * @exception FileNotFoundException if the user chooses a File that does not exist.
	 */
	public ArrayList<String> printCommands() {
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

}