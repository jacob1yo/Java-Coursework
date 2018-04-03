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

public class MainController {
	@FXML private Slider height, width;
	@FXML private GridPane grid;
	@FXML private StackPane tZero;
	
	
	public MainController() {}

	@FXML public void initialize() {
		
		height.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				final int numRows = arg2.intValue();
				grid.getChildren().clear();
				grid.add(new Pane(), 0, numRows);
				System.out.println(numRows);
			}
		});
		
		width.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				final int numCols = arg2.intValue();
				grid.getChildren().clear();
				grid.add(new Pane(), numCols, 0);
				System.out.println(numCols);
				
			}
			
		});
	}
	
	
	
	//Methods to implement the buttons to add / remove entities
	//@FXML public void boxPressed() {}
	@FXML public void gridPressed(MouseEvent e) {
		
	}
	
	@FXML public void robotPressed(ActionEvent e) {		
		
	}
	
	@FXML public void storagePressed() {}
	@FXML public void packingPressed() {}
	@FXML public void deletePressed() {}
	
}