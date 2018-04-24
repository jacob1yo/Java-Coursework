package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Order;
import model.Warehouse;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.Point;

public class MainController {
	/**
	 * The value that the slider is set to
	 */
	@FXML private Slider height, width, battery, charge;
	
	/**
	 * The grid from MainScene.fxml
	 */
	@FXML private GridPane grid;
	
	/**
	 * When pressed loads a file from which instructions are read
	 */
	@FXML private Button load, robotButton, storageButton, packingButton, deleteButton, clearButton;
	
	/**
	 * Lists the .sim file
	 */
	@FXML private ListView <String> listOrders;
	
	/**
	 * The number of rows in the grid
	 */
	private static int numRows = 2;
	
	/**
	 * The number of columns in the grid
	 */
    private static int numCols = 2;
    
    /**
     * Battery life of the robot
     */
	private int batteryLevel = 20;
	
	/**
	 * Charge rate of the charging pod
	 */
	private int chargeRate = 1;
	
	/**
	 * Stores a string that is compared to various words 
	 * to determine if the user has pressed a specific button
	 */
	private String pressed;
	
	/**
	 * Contains all the entities that are on the grid and does all the computation
	 */
	private static Warehouse warehouse;
	
	public MainController() {}
	
	public static Warehouse getWarehouse() {
		return warehouse;
	}

	@FXML
	public void initialize() {
		warehouse = new Warehouse();
		height.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				numRows = arg2.intValue();
				clearPressed();
				grid.getRowConstraints().clear();

				for (int i = 0; i < numRows; i++) {
					RowConstraints rowConst = new RowConstraints();
					rowConst.setPercentHeight(100.0 / numRows);
					grid.getRowConstraints().add(rowConst);
				}
				addPane();
			}
		});

		width.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				numCols = arg2.intValue();
				clearPressed();
				grid.getColumnConstraints().clear();

				for (int i = 0; i < numCols; i++) {
					ColumnConstraints colConst = new ColumnConstraints();
					colConst.setPercentWidth(100.0 / numCols);
					grid.getColumnConstraints().add(colConst);
				}
				addPane();
			}
		});
		addPane();

		battery.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				batteryLevel = arg2.intValue();
			}
		});

		charge.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				chargeRate = arg2.intValue();	
			}
		});
	}
	
	/**
	 * Adds a stack pane to every node in the grid
	 */
	public void addPane() {
		for(int i = 0; i < numCols; i++) {
			for(int j = 0; j < numRows; j++) {
				StackPane pane = new StackPane();
				grid.add(pane, i, j);
				pane.getStyleClass().add("grid");
			}
		}
	}
	
	/**
	 * Inputs entities into the grid depending on what the user has chosen, 
	 * when clicking on a node in the grid
	 * @param e
	 */
	@FXML
	public void gridPressed(MouseEvent e) {
		Node src = (Node) e.getTarget();
		Integer colIndex = GridPane.getColumnIndex(src);
		Integer rowIndex = GridPane.getRowIndex(src);
		if(colIndex == null) {
			colIndex = 0;
		}
		if(rowIndex == null) {
			rowIndex = 0;
		}
		int col = colIndex;
		int row = rowIndex;
		if(warehouse.check(col, row)) {
			if(pressed.equals("robot")) {
				warehouse.addRobot(null, null, col, row, batteryLevel, chargeRate);
				warehouse.genId();
				Circle circle = new Circle(20);
				circle.setFill(Color.GREEN);
				Rectangle rect = new Rectangle(50,50);
				rect.setFill(Color.BLUE);
				grid.add(rect, col, row);
				grid.add(circle, col, row);
				GridPane.setHalignment((Node) circle, HPos.CENTER);
				GridPane.setHalignment((Node) rect, HPos.CENTER);
			}
			else if(pressed.equals("storage")) {
				warehouse.addStorage(null, col, row);
				warehouse.genId();
				Polygon triangle = new Polygon();
				triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
				triangle.setFill(Color.RED);
				grid.add(triangle, col, row);
				GridPane.setHalignment((Node) triangle, HPos.CENTER);
			}
			else if(pressed.equals("packing")) {
				warehouse.addPacking(null, col, row);
				warehouse.genId();
				Polygon triangle = new Polygon();
				triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
				triangle.setFill(Color.YELLOW);
				grid.add(triangle, col, row);
				GridPane.setHalignment((Node) triangle, HPos.CENTER);
			}
			
			else {}
		}
		if(pressed.equals("delete")) {
			warehouse.delete(col, row);
			grid.getChildren().remove(src);
		}
	}
	
	/**
	 * Gets the number of columns set for the grid
	 * @return <code>int</code> Number of columns
	 */
	public static int getNumCols() {
		return numCols;
	}
	
	/**
	 * Gets the number of rows set for the grid
	 * @return <code>int</code> Number of rows
	 */
	public static int getNumRows() {
		return numRows;
	}

	/**
	 * Changes value of "pressed" to "robot"
	 */
	@FXML
	public void robotPressed() {
		pressed = "robot";

	}

	/**
	 * Changes value of "pressed" to "storage"
	 */
	@FXML
	public void storagePressed() {
		pressed = "storage";
	}

	/**
	 * Changes value of "pressed" to "packing"
	 */
	@FXML
	public void packingPressed() {
		pressed = "packing";
	}

	/**
	 * Changes value of "pressed" to "delete"
	 */
	@FXML
	public void deletePressed() {
		pressed = "delete"; //Might have to put all implementations in the respective methods
	}

	/**
	 * Clears the grid and all the entities within it
	 */
	@FXML
	public void clearPressed() {
		grid.getChildren().clear(); 
		addPane();
		warehouse.removeAll();
	}

	/**
	 * Loads instructions from a .SIM file
	 */
	@FXML
	public void loadPressed() {
		listOrders.getItems().clear();
		
		grid.getChildren().clear();
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters().addAll(new ExtensionFilter(".SIM Files", "*.sim"));
		File selectedFile = filechooser.showOpenDialog(null);

		Order.setFile(selectedFile);
		Order.printCommands();
		String line = Order.getCommands();
		
		listOrders.getItems().addAll(line);
		listOrders.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		height.setDisable(true);
		width.setDisable(true);
		robotButton.setDisable(true);
		storageButton.setDisable(true);
		packingButton.setDisable(true);
		deleteButton.setDisable(true);
		clearButton.setDisable(true);
		System.out.println("File loaded: " + selectedFile.getName() + "\n " + selectedFile.getAbsolutePath());
		Order.fillLists();

		setUpGrid();
		warehouse.removeAll();
		warehouse.readRobotData();
		warehouse.readStorageData();
		warehouse.readPackingData();
		addRobots();
		addStorage();
		addPacking();
	}
	
	public void setUpGrid() {
		numCols = Integer.valueOf(Order.getConfiguration().get(3)).intValue();
		numRows = Integer.valueOf(Order.getConfiguration().get(5)).intValue();
		
		grid.getRowConstraints().clear();
		for (int i = 0; i < numRows; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / numRows); //fix
			grid.getRowConstraints().add(rowConst);
		}
		
		grid.getColumnConstraints().clear();
		for (int i = 0; i < numCols; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / numCols); //fix
			grid.getColumnConstraints().add(colConst);
		}
		
		addPane();
	}
	
	public void addRobots() {
		Integer x = 0;
		Integer y = 0;
		for(int i = 2; i < Order.getPodRob().size(); i+=5) {
			Circle circle = new Circle(20);
			circle.setFill(Color.GREEN);
			GridPane.setHalignment((Node) circle, HPos.CENTER);
			Rectangle rect = new Rectangle(50,50);
			rect.setFill(Color.BLUE);
			GridPane.setHalignment((Node) rect, HPos.CENTER);
				x = Integer.valueOf(Order.getPodRob().get(i+1));
				y = Integer.valueOf(Order.getPodRob().get(i+2));
			grid.add(rect, x.intValue(), y.intValue());
			grid.add(circle, x.intValue(), y.intValue());
		}
	}
	
	public void addStorage() {
		Integer x = 0;
		Integer y = 0;
		for(int i = 1; i < Order.getStorageShelves().size(); i+=4) {
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
			triangle.setFill(Color.RED);
			GridPane.setHalignment((Node) triangle, HPos.CENTER);
			x = Integer.valueOf(Order.getStorageShelves().get(i+1));
			y = Integer.valueOf(Order.getStorageShelves().get(i+2));
			grid.add(triangle, x, y);
		}
	}
	
	public void addPacking() {
		Integer x = 0;
		Integer y = 0;
		for(int i = 1; i < Order.getPackingStations().size(); i+=4) {
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
			triangle.setFill(Color.YELLOW);
			GridPane.setHalignment((Node) triangle, HPos.CENTER);
			x = Integer.valueOf(Order.getPackingStations().get(i+1));
			y = Integer.valueOf(Order.getPackingStations().get(i+2));
			grid.add(triangle, x, y);
		}
	}
	
	public static ArrayList<Point> getFreeSpaces(int numCols, int numRows){
		return warehouse.freeSpacePoints(numCols, numRows);
	}
	
	public static ArrayList<Point> getRobotSpaces(){
		return warehouse.robotPoints();
	}
	
	/**
	 * Opens a new window where the simulation can be run through
	 */
	@FXML
	public void startPressed() {
		warehouse.addToRobotsChargePod();
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Simulator.FXML"));
		final SimulatorController simulatorController = new SimulatorController();
		loader.setController(simulatorController);
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
	
}