/**
 * @author Abhishek Prajapati, Darshan Patel
 */
package PhotoAlbum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PhotoAlbum extends Application{

    /**
     * starts the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * sets the main log in screen
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_Files/login.fxml"));
        primaryStage.setTitle("Photo Album Login");
        primaryStage.setScene(new Scene(root, 350, 455));
        primaryStage.show();
    }
}
