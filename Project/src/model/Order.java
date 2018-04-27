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
	 * This File object which is the "SIM" file being loaded into the GUI.
	 * 
	 * @see #setFile #getFile #fillLists #printCommands 
	 */
	private File file;

	/**
	 * ArrayList containing a single <code>String</code> order line read from {@link #orders}.
	 * 
	 * @see #getNextSentence #newSentence
	 */
	private ArrayList<String> sentence;

	/**
	 * Order constructor.
	 */
	public Order() {
	}

	/**
	 * This method iterates through the {@link #commands} each index of the ArrayList and 
	 * gets each order line by line. 
	 * 
	 * @return <code>String</code> Returns <code>String</code> line which represents each order line by line
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
	 * @return <code>File</code> Returns <code>file</code> which is set by the {@link #setFile} method.
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Gets the orders from the Nested ArrayList containing the <code>String</code> representation.
	 * 
	 * @return Returns <code>ArrayList</code> of all <code>orders</code>. All Orders from the "SIM" file by index.
	 */
	public ArrayList<ArrayList<String>> getOrders() {
		return orders; 
	}

	/**
	 * Sets {@link #sentence} to the first index of {@link #orders}.
	 */
	public void newSentence() {
		if(!orders.isEmpty()) {
			sentence = orders.get(0);
		}
	}

	/**
	 * Deletes the first index of {@link #orders}.
	 */
	public void completedSentence() {
		if(!orders.isEmpty()) {
			orders.remove(0);
		}
	}

	/**
	 * Gets the {@link #sentence} from the ArrayList containing the <code>String</code> representation of an order.
	 * 
	 * @return Returns <code>ArrayList<String></code> {@link #sentence} which is set by the {@link #newSentence()} method, 
	 * or <code>null</code> if there are no more orders.
	 */
	public ArrayList<String> getNextSentence(){
		if(!orders.isEmpty()) {
			return sentence;
		}
		return null;
	}

	/**
	 * Removes the first index of {@link #order} from {@link #orders}.
	 */
	public void removeFromOrders() {
		if(!orders.isEmpty()) {
			orders.remove(0);
		}
	}

	/**
	 *
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
					ArrayList<String> word = new ArrayList<String>();
					for (int i = 0; i < temp.length; i++) {
						word.add(temp[i]);
					}
					orders.add(word);
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
	}

	/**
	 * This methods scans the lines/sentences in the "SIM" file and adds them to the {@link #commands} ArrayList.
	 * 
	 * @return Returns an <code>ArrayList</code> of sentences in the "SIM" file.
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