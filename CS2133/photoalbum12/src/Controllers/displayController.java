/**
 * @author Abhishek Prajapati, Darshan Patel
 */
package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Abhi on 4/4/16.
 * this class has the display controllers
 * 
 */
public class displayController implements Initializable {
    @FXML
    Button backDisplayButton;
    @FXML
    Label displayCaptionLabel,displayTagLabel,dateLabel;
    @FXML
    ImageView displayImageView;
    File file = null;
    String caption = null;
    String tag = null;
    String date = null;

//    public void initialize() {
//        System.out.println(file);
//    }

    /**
     * displays the Controller 
     * @param file
     * @param caption
     * @param tag
     */
    public displayController(File file,String caption, String tag,String date){
        this.file = file;
        this.caption = caption;
        this.tag = tag;
        this.date = date;
    }
    /**
     * button for display
     * @param event
     */
    @FXML private void setBackDisplayButton(ActionEvent event){
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    
    /**
     * initializes the location
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image1 = null;
        displayCaptionLabel.setText(caption);
        displayTagLabel.setText(tag);
        dateLabel.setText(date);
        try {
            image1 = new Image(new FileInputStream(file));
            displayImageView.setImage(image1);
            displayImageView.setSmooth(true);
            displayImageView.setCache(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * set the file
     * @param file
     */
    public void setFile(File file){
        this.file = file;
    }
    
    public File getFile(){
        return this.file;
    }
}
