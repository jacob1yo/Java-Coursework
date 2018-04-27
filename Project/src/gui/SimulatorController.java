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
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Order;
import model.Warehouse;


public class SimulatorController {

	/**
	 * Value of the previously set height of the grid.
	 * 
	 * @see #addPane #initialize 
	 */
	private int finalGridHeight;

	/**
	 * Value of the previously set width of the grid.
	 * 
	 * @see #addPane #initialize 
	 */
	private int finalGridWidth;

	/**
	 * Holds the step that the simulation is on.
	 * 
	 * @see #oneTickPressed #tenTickPressed
	 */
	private int ticks;

	/**
	 * Creates an instance of the {@link Warehouse} class, containing all the entities need to run the simulation.
	 * 
	 * @see #addPackage #addRobots #addStorage #initialize #move #oneTickPressed #tenTickPressed #returnPressed
	 */
	private Warehouse warehouse;
	
	/**
	 * Creates an instance of the {@link Order} class.
	 * 
	 * @see #printInfo
	 */
	private Order order;

	/**
	 * The grid from Simulator.fxml.
	 * 
	 * @see #addPackage #addPane #addRobots #addStorage #closePressed #initialize #moveRobot #returnPressed
	 */
	@FXML private GridPane grid;

	/**
	 * Displays a list of robot entities. 
	 * 
	 * @see #initialize
	 */
	@FXML private ListView<String> listRobots;

	/**
	 * Displays a list of packing station entities.
	 * 
	 * @see #initialize
	 */
	@FXML private ListView<String> listPacking;

	/**
	 * Displays a list of orders.
	 */
	@FXML private ListView<String> listOrders;
	
	/**
	 * Displays the number of orders completed in the GUI.
	 * 
	 * @see #oneTickPressed #tenTickPressed
	 */
	@FXML private Label completed;

	/**
	 * Displays the current tick the simulation is on in the GUI
	 * 
	 * @see #oneTickPressed #tenTickPressed
	 */
	@FXML private Label tickLabel;

	/**
	 * <code>ArrayList</code> containing the circle object (representing robots) present in the GUI.
	 * 
	 * @see #addRobots #moveRobot #oneTickPressed #tenTickPressed 
	 */
	private ArrayList<Circle> circleList;

	/**
	 * Simulator Controller constructor.
	 * 
	 * Sets the finalGridHeight and the finalGridWidth to values read from the "SIM" file.
	 * Initialises the Warehouse to have the same enities and parameters as the one from the "SIM" file.
	 * Initialises the the Circle List <code>ArrayList</code>
	 */
	public SimulatorController() {
		finalGridHeight = MainController.getNumRows();
		finalGridWidth = MainController.getNumCols();
		warehouse = MainController.getWarehouse();
		circleList = new ArrayList<Circle>();
		order = warehouse.getOrder();
	}

	/**
	 * Set-up the Simulation GUI, by setting up the Grid and Entities present in it.
	 */
	@FXML
	public void initialize() {
		grid.getChildren().clear();

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
		addRobots();
		addStorage();
		addPackage();
		printInfo();
	}

	/**
	 * Adds a stack pane to every node in the grid.
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
	
	/**
	 * Adds a <code>Circle</code> representing a Robot and a <code>Rectangle</code> representing a Charging Pod according to the "SIM" file.
	 * {@link #initialize}
	 */
	public void addRobots() {
		for(int i = 0; i < warehouse.robotPoints().size(); i++) {
			Circle circle = new Circle(20);
			circle.setFill(Color.GREEN);
			circleList.add(circle);
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

	/**
	 * Adds a <code>Polygon</code> representing a Storage Shelf according to the "SIM" file.
	 * {@link #initialize}
	 */
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

	/**
	 * Adds a <code>Polygon</code> representing a Packing Station according to the "SIM" file.
	 * {@link #initialize}
	 */
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

	/**
	 * When clicked, it returns to the 'set-up' GUI, allowing the user to input other parameters or change the grid.
	 */
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
			warehouse.removeAll();
			grid.getScene().getWindow().hide();
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Iterates through a <code>HashMap</code> on current locations and next locations, updating the GUI and the model.
	 * @param i <code>int</code>, index to move the right Robot
	 */
	public void move(int i) {
		HashMap<Point, Point> hashmap = warehouse.move(i);
		ArrayList<Point> robots = warehouse.robotPoints();
		for(int j = 0; j < hashmap.size(); j++) {
			Point current = robots.get(j);
			Point next = hashmap.get(current);
			if(hashmap != null) {
				moveRobot(j, current, next);
				warehouse.moveRobot(j);
			}
		}
	}

	/**
	 * Moves the Robot displayed on the GUI to the next location.
	 * @param i, index to move the right circle in the GUI.
	 * @param current, which is the current location of the Robot
	 * @param next, which is the next location of the Robot
	 */
	public void moveRobot(int i,Point current, Point next) {
		Circle delCirc = circleList.get(i);	
		grid.getChildren().remove(delCirc);	
		circleList.remove(i);				

		Circle circle = new Circle(20);
		circle.setFill(Color.GREEN);
		Double x = next.getX();
		Double y = next.getY();
		circleList.add(i, circle); 
		grid.add(circle, x.intValue(), y.intValue());
		GridPane.setHalignment((Node) circle, HPos.CENTER);
	}
	
	/**
	 * Executes cost estimation and the move method, for each Robot and increase the step the simulation is on.
	 */
	@FXML
	public void oneTickPressed() {
		for(int i = 0; i < circleList.size(); i++) {
			warehouse.costEst(i);
			move(i);
		}
		completed.setText(warehouse.getCompleted());
		ticks++;
		tickLabel.setText("Tick: " + ticks);
		printInfo();
	}

	/**
	 * Calls on the oneTickPressed method 10 times and increase the step the GUI is on, by 10.
	 * {@link #oneTickPressed}
	 */
	@FXML 
	public void tenTickPressed() {
		for(int i = 0; i < 10; i++) {
			oneTickPressed();
		}
	}
	
	@FXML
	public void endSimulationPressed() {
		while(!warehouse.getFinished()) {
			oneTickPressed();
		}
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EndScene.fxml")); 
		final EndController controller = new EndController();
		loader.setController(controller);
		try {
			final Parent parent = (Parent) loader.load();

			final Stage removeStage = new Stage();
			removeStage.initModality(Modality.APPLICATION_MODAL);
			removeStage.setScene(new Scene(parent, 500, 150));
			removeStage.showAndWait();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Sends the user an alert, letting them know that they are about to leave the simulation; once they click 'OK'.
	 */
	@FXML
	public void closePressed() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Simulation");
		alert.setContentText("Are you sure you want to end the simulation?");
		if(alert.showAndWait().get() == ButtonType.OK) {
			grid.getScene().getWindow().hide();
		}
	}
	
	/**
	 * Updates the information displayed in the sidebars of the GUI.
	 */
	public void printInfo() {
		listRobots.getItems().clear();
		listRobots.getItems().addAll(warehouse.getRobotInfo());
		listRobots.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		listPacking.getItems().clear();
		listPacking.getItems().addAll(warehouse.getPackingID());
		listPacking.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		listOrders.getItems().clear();
		listOrders.getItems().addAll(order.getOrderInfo());
		listOrders.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
}