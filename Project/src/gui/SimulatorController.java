package gui;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Order;
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
	 * The grid from Simulator.fxml.
	 */
	@FXML private GridPane grid;
	

	/**
	 * The listview for the robot entities.
	 */
	@FXML private ListView<String> listRobots;
	

	/**
	 * The listview for the packing station entities. 
	 */
	@FXML private ListView<String> listPacking;
	

	/**
	 * Lists the unassigned orders.
	 */
	@FXML private ListView<String> listUnassigned;
	

	/**
	 * Lists the assigned orders.
	 */
	@FXML private ListView<String> listAssigned;
	

	/**
	 * Lists the dispatched orders.
	 */
	@FXML private ListView<String> listDispatched;
	
	private ArrayList<Circle> circleList;

	public SimulatorController() {
		finalGridHeight = MainController.getNumRows();
		finalGridWidth = MainController.getNumCols();
		warehouse = MainController.getWarehouse();
		System.out.println("Height: " + finalGridHeight + " Width: " + finalGridWidth);
		circleList = new ArrayList<Circle>();
	}

	@FXML
	public void initialize() {
		grid.getChildren().clear();
		
		grid.getRowConstraints().clear();
		for (int i = 0; i < finalGridHeight; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / finalGridHeight); //fix
			grid.getRowConstraints().add(rowConst);
		}
		
		grid.getColumnConstraints().clear();
		for (int i = 0; i < finalGridWidth; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / finalGridWidth); //fix
			grid.getColumnConstraints().add(colConst);
		}
		
		addPane();
		addRobots();
		addStorage();
		addPackage();

		listRobots.getItems().addAll(warehouse.getRobotID());

		for(int i = 0; i < warehouse.getRobotList().size(); i++) {
			listRobots.getItems().addAll(warehouse.getRobotID(i));
		}


		listRobots.getItems().addAll(warehouse.getRobotID());

		listRobots.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		listPacking.getItems().addAll(warehouse.getPackingID());
		listPacking.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
		for(int i = 0; i < warehouse.robotPoints().size(); i++) {
			Circle circle = new Circle(20);
			circle.setFill(Color.GREEN);
			circleList.add(circle);
			System.out.println("addRobots: " + circleList.size());
			Rectangle rect = new Rectangle(50,50);
			rect.setFill(Color.BLUE);
			
			Double x = warehouse.robotPoints().get(i).getX();
			Double y = warehouse.robotPoints().get(i).getY();
			grid.add(rect, x.intValue(), y.intValue());
			grid.add(circle, x.intValue(), y.intValue());
			GridPane.setHalignment((Node) circle, HPos.CENTER);
			GridPane.setHalignment((Node) rect, HPos.CENTER);
		}
	}
	
	public void addStorage() {
		for(int i = 0; i < warehouse.storageShelfPoints().size(); i++) {
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
			triangle.setFill(Color.RED);
			
			Double x = warehouse.storageShelfPoints().get(i).getX();
			Double y = warehouse.storageShelfPoints().get(i).getY();
			
			grid.add(triangle, x.intValue(), y.intValue());
			GridPane.setHalignment((Node) triangle, HPos.CENTER);
		}
	}
	
	public void addPackage() {
		for(int i = 0; i < warehouse.packingStationPoints().size(); i++) {
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
			triangle.setFill(Color.YELLOW);
			
			Double x = warehouse.packingStationPoints().get(i).getX();
			Double y = warehouse.packingStationPoints().get(i).getY();
			
			grid.add(triangle, x.intValue(), y.intValue());
			GridPane.setHalignment((Node) triangle, HPos.CENTER);
		}
		
	}
	
	@FXML
	public void returnPressed() {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainScene.fxml"));
		final MainController mainController = new MainController();
		loader.setController(mainController);
		try {
			final Parent parent = (Parent) loader.load();
			final Stage stage = new Stage();
			final Scene scene = new Scene(parent, 1500, 900);
			scene.getStylesheets().add(getClass().getResource("grid-borders.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Warehouse Simulator");
			grid.getScene().getWindow().hide();
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void move() {
		HashMap<Point, Point> hashmap = warehouse.move();
		ArrayList<Point> robots = warehouse.robotPoints();
		for(int i = 0; i < hashmap.size(); i++) {
			Point current = robots.get(i);
			System.out.println("move current:" + current);
			Point next = hashmap.get(current);
			moveRobot(i, current, next);
			warehouse.moveRobot(i);
		}
	}
	
	public void moveRobot(int i,Point current, Point next) {
		//Removes the current circle representing the robot from the grid
		System.out.println("moveRobot: " + circleList.size());
		Circle delCirc = circleList.get(i);	//may need to remove if this doesn't work
		grid.getChildren().remove(delCirc);	//may need to remove if this doesn't work
		circleList.remove(i);				//may need to remove if this doesn't work
		
		//Adds circle/robot to the next location
		Circle circle = new Circle(20);
		circle.setFill(Color.GREEN);
		Double x = next.getX();
		Double y = next.getY();
		circleList.add(i, circle); //may need to remove if this doesn't work
		grid.add(circle, x.intValue(), y.intValue());
		GridPane.setHalignment((Node) circle, HPos.CENTER);
	}
	
	@FXML
	public void oneTickPressed() {
		move();
	}
	
	@FXML
	public void endPressed() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Simulation");
		alert.setContentText("Are you sure you want to end the simulation?");
		if(alert.showAndWait().get() == ButtonType.OK) {
			grid.getScene().getWindow().hide();
		}
	}

}
