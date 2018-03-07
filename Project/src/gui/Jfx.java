package gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Jfx extends Application {
	private Button button;
	public static void main(String[] args) {
	launch(args);	
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Title of the window");
		
		// to add our rows and columns
		button = new Button("Click me");
		
		// add our rows and colums to the layout
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 800, 600);
		primaryStage.setScene(scene);
		
		
		primaryStage.show();
		
		
	}

}
