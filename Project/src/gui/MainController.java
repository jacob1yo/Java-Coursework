package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainController {
	@FXML private Slider height, width;
	@FXML private GridPane grid;
	
	private int row;
	private int column;
	
	public MainController() {}

	@FXML public void initialize() {
		height.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				final int newValue = arg2.intValue();
				grid.getChildren().clear();
				grid.add(new Pane(), 0, newValue);				
			}
		});
	}
	
	// changing the rows and columns of the grid
	@FXML public void alterRow() {}
	@FXML public void alterColumn() {}
	@FXML public void alterHeight() {}
	
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
	
	@FXML public void robotPressed(MouseEvent e) {		
	}
	
	@FXML public void storagePressed() {}
	@FXML public void packingPressed() {}
	@FXML public void deletePressed() {}
	
}