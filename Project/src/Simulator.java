import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.*;

public class Simulator extends Application {
	private int ticks;

	public Simulator() {
		ticks = 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);

	}

	public void simulateOneStep() {

	}

	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		try {
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MainScene.fxml"));
			loader.setController(new MainController());
			final Parent root = loader.load();
			final Scene scene = new Scene(root, 400, 300);
			primaryStage.setTitle("Warehouse simulator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
