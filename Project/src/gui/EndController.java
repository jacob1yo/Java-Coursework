package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndController {

	/**
	 * Gets the message label in the GUI 
	 */
	@FXML private Label message;
	
	/**
	 * When clicked, it hides the message shown on the GUI
	 */
	public void okPressed() {
		message.getScene().getWindow().hide();
	}
}
