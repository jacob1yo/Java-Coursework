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
	
	private int row;
	private int column;
	
	public MainController() {}

	@FXML public void initialize() {
		//System.out.println(height.getValue());
		height.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				final int newValue = arg2.intValue();
				grid.getChildren().clear();
				grid.add(new Pane(), 0, newValue);
				//grid.addRow(newValue, new Pane());
			}
		});
		
		width.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				final int newValue = arg2.intValue();
				grid.getChildren().clear();
				grid.add(new Pane(), newValue, 0);
			}
			
		});
	}
	
	
	//Methods to implement the buttons to add / remove entities
	//@FXML public void boxPressed() {}
	@FXML public void gridPressed(MouseEvent e) {
		Node node = (Node)e.getSource();
		column = GridPane.getColumnIndex(node);
		row = GridPane.getRowIndex(node);
		System.out.println(row);
		System.out.println(column);
		System.out.println("CLICKED");
	}
	
	@FXML public void robotPressed(ActionEvent e) {		
		
	}
	
	@FXML public void storagePressed() {}
	@FXML public void packingPressed() {}
	@FXML public void deletePressed() {}
	
}