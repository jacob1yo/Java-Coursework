//USED AS UNDERSTANDING JAVAFX

package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class gridTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300, Color.WHITE);

        GridPane gridpane = new GridPane();
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            gridpane.getColumnConstraints().add(column);
        }
    
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}