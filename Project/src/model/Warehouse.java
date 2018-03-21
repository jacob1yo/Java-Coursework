package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Warehouse extends Application{
	
	public Warehouse() {
		
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 400, 400);
		
		grid.addColumn(columnIndex, children);
		grid.addRow(rowIndex, children);
		
	}
	
	

}
