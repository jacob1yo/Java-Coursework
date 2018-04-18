package gui;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.Warehouse;


public class SimulatorController {

	/**
	 * Value of the previously set height of the grid
	 */
	private int finalGridHeight;
	
	/**
	 * Value of the previously set width of the grid
	 */
	private int finalGridWidth;
	
	private Warehouse warehouse;
	
	/**
	 * The grid from Simulator.fxml
	 */
	@FXML private GridPane grid;

	public SimulatorController() {
		finalGridHeight = MainController.getNumRows();
		finalGridWidth = MainController.getNumCols();
		warehouse = MainController.warehouse;
		System.out.println("Height: " + finalGridHeight + " Width: " + finalGridWidth);
	}

	@FXML
	public void initialize() {
		grid.getChildren().clear();
		
		grid.getRowConstraints().clear();
		for (int i = 0; i < finalGridWidth; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / finalGridWidth); //fix
			grid.getRowConstraints().add(rowConst);
		}
		
		grid.getColumnConstraints().clear();
		for (int i = 0; i < finalGridHeight; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / finalGridHeight); //fix
			grid.getColumnConstraints().add(colConst);
		}
		
		addPane();
		addRobots();
		addStorage();
		addPackage();			

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
	
	public void addRobots() {
		for(int i = 0; i < warehouse.robots().size(); i++) {
			Circle circle = new Circle(20);
			circle.setFill(Color.GREEN);
			Rectangle rect = new Rectangle(50,50);
			rect.setFill(Color.BLUE);
			
			Double x = warehouse.robots().get(i).getX();
			Double y = warehouse.robots().get(i).getY();
			
			grid.add(rect, x.intValue(), y.intValue());
			grid.add(circle, x.intValue(), y.intValue());
		}
	}
	
	public void addStorage() {
		for(int i = 0; i < warehouse.storageShelfs().size(); i++) {
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
			triangle.setFill(Color.RED);
			
			Double x = warehouse.storageShelfs().get(i).getX();
			Double y = warehouse.storageShelfs().get(i).getY();
			
			grid.add(triangle, x.intValue(), y.intValue());
		}
	}
	
	public void addPackage() {
		for(int i = 0; i < warehouse.packingStations().size(); i++) {
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
			triangle.setFill(Color.YELLOW);
			
			Double x = warehouse.packingStations().get(i).getX();
			Double y = warehouse.packingStations().get(i).getY();
			
			grid.add(triangle, x.intValue(), y.intValue());
		}
		
	}

}
