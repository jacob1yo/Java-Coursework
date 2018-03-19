package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainController {
	@FXML private Slider height, width;
	@FXML private GridPane grid;

	@FXML public void initialize() {
		height.valueProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				final int newValue = arg2.intValue();
				grid.getChildren().clear();
				grid.add(new Pane(), 0, newValue);
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public MainController() {
		
		
		
	}
	// changing the rows and columns of the grid
	@FXML public void alterRow() {}
	@FXML public void alterColumn() {}
	
	@FXML public void alterHeight() {}

}


