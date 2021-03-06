package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public Main() {
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * Set-up the initial GUI, setting the size of the scene and which controller it should be assigned to it.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MainScene.fxml"));
			loader.setController(new MainController());
			final Parent root = loader.load();
			final Scene scene = new Scene(root, 1500, 900);
			scene.getStylesheets().add(getClass().getResource("grid-borders.css").toExternalForm());
			primaryStage.setTitle("Warehouse simulator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}