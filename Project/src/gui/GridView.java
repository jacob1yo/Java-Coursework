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
	//private static final int BOARD_DIM = 8;
	private static final int SQUARES = 64;
	//private Cell[][] boardG = new Cell[8][8];
	private GridPane board = new GridPane();
	
	Rectangle rect;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage){
		buildBoard(board, 8); //Alter 8 to users input
		putSquaresOnBoard(board, 8); //Ditto
		board.setPadding(new Insets(15,15,15,15));
		Scene scene = new Scene(board, 700, 700);
		primaryStage.setTitle("Grid");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void buildBoard(GridPane board, int boardDim){
		for(int i = 0; i < boardDim; i++){
			RowConstraints rc = new RowConstraints();
			rc.setMinHeight(SQUARES);
			rc.setMaxHeight(SQUARES);
			rc.setPrefHeight(SQUARES);
			rc.setValignment(VPos.CENTER);
			board.getRowConstraints().add(rc);

			ColumnConstraints cc = new ColumnConstraints();
			cc.setMinWidth(SQUARES);
			cc.setMaxWidth(SQUARES);
			cc.setPrefWidth(SQUARES);
			cc.setHalignment(HPos.CENTER);
			board.getColumnConstraints().add(cc);
		}
	}
	private void putSquaresOnBoard(GridPane board, int boardDim) {
		for (int i = 0; i < boardDim; i ++){
			for (int j = 0; j < boardDim; j++){
				rect = new Rectangle(SQUARES,SQUARES,Color.TRANSPARENT);
				rect.setStroke(Color.BLACK);
				rect.setStrokeWidth(5);
				board.add(rect,i,j);
			}
		}
	}
}