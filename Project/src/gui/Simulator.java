package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jpd.aston.lab6.ListController;

public class Simulator extends Application{

	public Simulator() {}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
	        final FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("GridScene.fxml"));
	        loader.setController(new GridController());
	        final Parent root = loader.load();
	        final Scene scene = new Scene(root, 400, 300);
	        primaryStage.setTitle("Grid");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		}
	}

}