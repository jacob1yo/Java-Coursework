package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class MainController {
	@FXML
	private Slider height, width;
	@FXML
	private GridPane grid;
	@FXML
	private StackPane tZero;

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
					System.out.println(numRows);
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
					System.out.println(numCols);

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
	public void robotPressed(ActionEvent e) {

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