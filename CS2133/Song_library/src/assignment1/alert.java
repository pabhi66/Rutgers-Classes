/**
 * Manan patel
 * Hiren Patel
 */

package assignment1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class alert extends Controller {

    static boolean yesClicked = false;

    public static boolean display(String title){
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setTitle(title);
        alertStage.setWidth(250);
        Label label = new Label();
        label.setText("Are you sure???");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> { yesClicked = true;
        alertStage.close(); } );

        noButton.setOnAction(e -> { yesClicked = false;
            alertStage.close(); } );

        VBox layout = new VBox();
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        alertStage.setScene(scene);
        alertStage.showAndWait();

        return yesClicked;
    }
}
