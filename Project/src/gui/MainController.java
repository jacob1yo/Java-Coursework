package gui;

import javafx.fxml.FXML;
import model.Entity;

public class MainController {
	@FXML private Entity entity;
	
	public MainController(Entity entity) {
		this.entity = entity;
	}
	
	@FXML public void initialize() {}

}