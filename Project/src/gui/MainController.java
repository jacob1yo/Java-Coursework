package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import model.Robot;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class MainController {
	@FXML
	private Slider height, width;
	@FXML
	private GridPane grid;
	@FXML
	private Button addRobot;

	private int numRows;
	private int numCols;
	private Boolean pressed;
	
	public MainController() {
	}

	@FXML
	public void initialize() {

		height.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				numRows = arg2.intValue();
				// grid.getChildren().clear();
				// grid.add(new Pane(), 0, numRows);

				grid.getRowConstraints().clear();

				for (int i = 0; i < numRows; i++) {
					RowConstraints rowConst = new RowConstraints();
					rowConst.setPercentHeight(100.0 / numRows);
					grid.getRowConstraints().add(rowConst);

				}
			}
		});

		width.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				numCols = arg2.intValue();
				grid.getColumnConstraints().clear();

				for (int i = 0; i < numCols; i++) {
					ColumnConstraints colConst = new ColumnConstraints();
					colConst.setPercentWidth(100.0 / numCols);
					grid.getColumnConstraints().add(colConst);

				}
			}

		});
		
		for(int i = 0; i < numCols; i++) {
			for(int j = 0; j < numRows; j++) {
				addPane(i,j);
			}
		}

	}
	
	private void addPane(int colIndex, int rowIndex) {
		StackPane pane = new StackPane();
		grid.add(pane, colIndex, rowIndex);
	}

	// Methods to implement the buttons to add / remove entities
	// @FXML public void boxPressed() {}
	@FXML
	public void gridPressed(MouseEvent e) {
		if(pressed = true) {
			Node src = (Node) e.getSource();
			Integer colIndex = GridPane.getColumnIndex(src);
			Integer rowIndex = GridPane.getRowIndex(src);
			addRobot.setOnAction((event) -> { 
				grid.add(new Circle(20), colIndex.intValue(), rowIndex.intValue());
			});
		}
	}

	@FXML
	public void initalize(MouseEvent e) {
		/*Node src = (Node) e.getSource();
		Integer colIndex = GridPane.getColumnIndex(src);
		Integer rowIndex = GridPane.getRowIndex(src);
		addRobot.setOnAction((event) -> { 
			grid.add(new Circle(20), colIndex.intValue(), rowIndex.intValue());
			//System.out.println(colIndex.intValue());
			//System.out.println(rowIndex.intValue());
		});*/
	}
	
	@FXML
	public void robotPressed() {
		pressed = true;
		
	}

	@FXML
	public void storagePressed() {
	}

	@FXML
	public void packingPressed() {
	}

	@FXML
	public void deletePressed() {
	}

}