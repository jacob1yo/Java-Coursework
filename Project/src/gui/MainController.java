package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import model.Warehouse;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class MainController {
	@FXML
	private Slider height, width, battery, charge;
	@FXML
	private GridPane grid;
	@FXML
	private Button addRobot;
	@FXML
	private Button load;

	static int numRows = 2;
    static int numCols = 2;
	private int batteryLevel = 20; 
	private int chargeRate = 1;
	private String pressed;
	private Warehouse warehouse;

	
	public MainController() {
	}

	@FXML
	public void initialize() {
		warehouse = new Warehouse();
		height.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				numRows = arg2.intValue();
				resetGrid();
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
				resetGrid();
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

	public void addPane() {
		for(int i = 0; i < numCols; i++) {
			for(int j = 0; j < numRows; j++) {
				StackPane pane = new StackPane();
				//pane.setAlignment(Pos.CENTER);
				grid.add(pane, i, j);
				pane.getStyleClass().add("grid");
			}
		}
	}

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
				warehouse.addRobot(col, row, batteryLevel, chargeRate);
				Circle circle = new Circle(20);
				circle.setFill(Color.GREEN);
				Rectangle rect = new Rectangle(50,50);
				rect.setFill(Color.BLUE);
				grid.add(rect, col, row);
				grid.add(circle, col, row);
				//Create new robot entity in warehouse class
			}
			else if(pressed.equals("storage")) {
				warehouse.addStorage(col, row);
				Polygon triangle = new Polygon();
				triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
				triangle.setFill(Color.RED);
				grid.add(triangle, col, row);
				//Create new storage shelf entity in warehouse class
			}
			else if(pressed.equals("packing")) {
				warehouse.addPacking(col, row);
				Polygon triangle = new Polygon();
				triangle.getPoints().addAll(new Double[] {50.0, 0.0, 100.0, 50.0, 0.0, 50.0});
				triangle.setFill(Color.YELLOW);
				grid.add(triangle, col, row);
				//Create new packing station entity in warehouse class
			}
			
			else {}
		}
		if(pressed.equals("delete")) {
			warehouse.delete(col, row);
			grid.getChildren().remove(src);
			/*if(src.toString().contains("Circle")) {
				warehouse.removeRobot(); //need to figure out for 1 robot remaining
			}
			else if(src.toString().contains("Rectangle")) {
				warehouse.removeCharge();
			}
			else if(src.toString().contains("Polygon") && src.toString().contains("fill=0xff0000ff")) { //for storage 
				warehouse.removeStorage();
			}
			else if(src.toString().contains("Polygon") && src.toString().contains("fill=0xffff00ff")) { //for packing
				warehouse.removePacking();
			}
			grid.getChildren().remove(src);
			//Remove entity from warehouse class*/
		}
	}

	public void resetGrid() {
		grid.getChildren().clear(); 
		addPane();
	}

	@FXML
	public void robotPressed() {
		pressed = "robot";

	}

	@FXML
	public void storagePressed() {
		pressed = "storage";
	}

	@FXML
	public void packingPressed() {
		pressed = "packing";
	}

	@FXML
	public void deletePressed() {
		pressed = "delete"; //Might have to put all implementations in the respective methods
	}

	@FXML
	public void clearPressed() {
		resetGrid();
		warehouse.removeAll();
	}

	@FXML
	public void loadPressed() {
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters().addAll(new ExtensionFilter(".SIM Files", "*.sim"));
		File selectedFile = filechooser.showOpenDialog(null);

		System.out.println("File loaded: " + selectedFile.getName() + "\n " + selectedFile.getAbsolutePath());
	}
	
	@FXML
	public void startPressed() {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Simulator.FXML"));
		final SimulatorController simulatorController = new SimulatorController();
		loader.setController(simulatorController);
		try {
			final Parent parent = (Parent) loader.load();
			final Stage stage = new Stage();
			stage.setScene(new Scene(parent, 500, 300));
			stage.setTitle("Warehouse Simulator");
			grid.getScene().getWindow().hide();
			stage.show();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}