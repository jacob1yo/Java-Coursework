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

	public MainController() {
	}

	@FXML
	public void initialize() {

		height.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				final int numRows = arg2.intValue();
				// grid.getChildren().clear();
				// grid.add(new Pane(), 0, numRows);

				grid.getRowConstraints().clear();

				for (int i = 0; i < numRows; i++) {
					RowConstraints rowConst = new RowConstraints();
					rowConst.setPercentHeight(100.0 / numRows);
					grid.getRowConstraints().add(rowConst);

					StackPane stack = new StackPane();
					grid.getChildren().add(stack); // NOT verified same for method below ..............................................

				}
			}
		});

		width.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				final int numCols = arg2.intValue();
				grid.getColumnConstraints().clear();

				for (int i = 0; i < numCols; i++) {
					ColumnConstraints colConst = new ColumnConstraints();
					colConst.setPercentWidth(100.0 / numCols);
					grid.getColumnConstraints().add(colConst);

					StackPane stack = new StackPane();
					grid.getChildren().add(stack);

				}
			}

		});

	}

	// Methods to implement the buttons to add / remove entities
	// @FXML public void boxPressed() {}
	@FXML
	public void gridPressed(MouseEvent e) {

	}

	@FXML
	public void robotPressed(MouseEvent e) {
		addRobot.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				grid.getChildren().add(new Circle(23));
				
			}
		});
		
		
	/*	Miraj's attempt
		addRobot.setOnAction(event -> {
			grid.getChildren().add(new Polygon(10));
	});
	/*	
	/*	Button robot = new Button();
		robot.setOnAction(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				grid.getChildren().add(new Polygon(10));
			}

		});
		
	*/
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