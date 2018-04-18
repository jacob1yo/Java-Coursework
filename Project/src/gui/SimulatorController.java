package gui;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class SimulatorController {

	private int finalGridHeight;
	private int finalGridWidth;
	@FXML private GridPane grid;
	
	public SimulatorController() {
		this.finalGridHeight = MainController.numRows;
		this.finalGridWidth = MainController.numCols;
	}
	
	@FXML
	public void initalize() {
		grid.getRowConstraints().clear();

		for (int i = 0; i < finalGridHeight; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / finalGridHeight);
			grid.getRowConstraints().add(rowConst);
		}
		grid.getColumnConstraints().clear();

		for (int i = 0; i < finalGridWidth; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / finalGridWidth);
			grid.getColumnConstraints().add(colConst);
		}
		addPane();
	}
	
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
