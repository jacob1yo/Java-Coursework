package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Cell;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class GridView extends Application{
	
	/**
	 * Declares a flexible grid called "grid".
	 * @see #start, #buildGrid, #putSquaresOnGrid
	 */
	private GridPane grid = new GridPane();
	
	/**
	 * Creates a Rectangle object.
	 * @see #putSquaresOnGrid
	 */
	Rectangle rect;

	/**
	 * Initialises "Application" from JavaFX.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Calls methods to create the grid, creates the scene, 
	 * sets the title of the window, sets the padding for the grid.
	 */
	public void start(Stage primaryStage){
		buildGrid(grid, 8); //Alter 8 to users input
		putSquaresOnGrid(grid, 8); //Ditto
		grid.setPadding(new Insets(15,15,15,15));
		Scene scene = new Scene(grid, 700, 700);
		primaryStage.setTitle("Grid");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Determines the number of squares in the grid, dimensions, 
	 * and the overall layout of the grid (not showing anything)
	 * @param grid
	 * @param gridDim
	 */
	private void buildGrid(GridPane grid, int gridDim){
		int squares = gridDim * gridDim;
		for(int i = 0; i < gridDim; i++){
			RowConstraints rc = new RowConstraints();
			rc.setMinHeight(squares);
			rc.setMaxHeight(squares);
			rc.setPrefHeight(squares);
			rc.setValignment(VPos.CENTER);
			grid.getRowConstraints().add(rc);

			ColumnConstraints cc = new ColumnConstraints();
			cc.setMinWidth(squares);
			cc.setMaxWidth(squares);
			cc.setPrefWidth(squares);
			cc.setHalignment(HPos.CENTER);
			grid.getColumnConstraints().add(cc);
		}
	}
	
	/**
	 * Creates rectangles to display the grid
	 * @param grid
	 * @param gridDim
	 */
	private void putSquaresOnGrid(GridPane grid, int gridDim) {
		int squares = gridDim * gridDim;
		for (int i = 0; i < gridDim; i ++){
			for (int j = 0; j < gridDim; j++){
				rect = new Rectangle(squares,squares,Color.TRANSPARENT);
				rect.setStroke(Color.BLACK);
				rect.setStrokeWidth(5);
				grid.add(rect,i,j);
			}
		}
	}
}