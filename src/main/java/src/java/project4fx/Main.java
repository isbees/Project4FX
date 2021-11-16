package src.java.project4fx;
/**
 * @author Zachary Goldman, Isaac Brukhman
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Application class launches the main controller called HelloController.
 */
public class Main extends javafx.application.Application {
    /**
     * @param stage of the thing
     * @throws IOException if bad load
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main takes in args and launches our program
     * @param args in main
     */
    public static void main(String[] args) {
        launch();
    }
}
