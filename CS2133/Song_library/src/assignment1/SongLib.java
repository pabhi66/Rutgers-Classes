
/**
 * Manan patel
 * Hiren Patel
 */
package assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SongLib extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("songLib.fxml"));
        primaryStage.setTitle("Song Library");
        primaryStage.setScene(new Scene(root, 938, 525));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
