package gui;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class SimulatorController {

	/**
	 * Value of the previously set height of the grid
	 */
	private int finalGridHeight;
	
	/**
	 * Value of the previously set width of the grid
	 */
	private int finalGridWidth;
	
	/**
	 * The grid from Simulator.fxml
	 */
	@FXML private GridPane grid;

	public SimulatorController() {
		finalGridHeight = MainController.getNumRows();
		finalGridWidth = MainController.getNumCols();
		System.out.println("Height: " + finalGridHeight + " Width: " + finalGridWidth);
	}

	@FXML
	public void initialize() {
		grid.getChildren().clear();
		
		grid.getRowConstraints().clear();
		for (int i = 0; i < finalGridWidth; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / finalGridWidth);
			grid.getRowConstraints().add(rowConst);
		}
		
		grid.getColumnConstraints().clear();
		for (int i = 0; i < finalGridHeight; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / finalGridHeight);
			grid.getColumnConstraints().add(colConst);
		}
		
		addPane();
	}
	
	public void addColumns() {
		
	}

	/**
	 * Adds a stack pane to every node in the grid
	 */
	public void addPane() {
		for(int i = 0; i < finalGridWidth; i++) {
			for(int j = 0; j < finalGridHeight; j++) {
				StackPane pane = new StackPane();
				grid.add(pane, i, j);
				pane.getStyleClass().add("grid");
			}
		}
	}

}
