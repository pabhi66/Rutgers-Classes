/**
 * @author Manan Patel, Hiren Patel
 */
package Controllers;

import Model.Album2;
import Model.Backend;
import Model.User2;
import Model.photo2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class slidshowController {
    @FXML ImageView imageView;
    private String UNAME = null;
    private String ANAME = null;
    public Backend be = new Backend();
    int photoIndex = 0;
    int screen = 0;
    
    /**
     * controller for SlideShow
     * @param UNAME
     * @param ANAME
     * @param i
     */
    public slidshowController(String UNAME, String ANAME, int i){
        this.UNAME = UNAME;
        this.ANAME = ANAME;
        this.screen = i;
    }

    public void initialize() throws FileNotFoundException{
        User2 user = be.readUser(UNAME);
        Map<String, Album2> userAlbums = user.getAlbumss();
        Album2 selectedAlbum = userAlbums.get(ANAME);
        List<photo2> selectedPhotos = selectedAlbum.getPhoto2List();
        Image image1 = null;


        if(selectedPhotos.size() > 0){
            photoIndex = selectedPhotos.size() - 1;
            image1 = new Image(new FileInputStream("data/photos/" + selectedPhotos.get(photoIndex).getPhotoName()));
            imageView.setImage(image1);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        }
    }
    
    /**
     * set nextButton
     * @param event
     * @throws IOException
     */
    @FXML private void setNextButton(ActionEvent event)throws IOException{
        User2 user = be.readUser(UNAME);
        Map<String, Album2> userAlbums = user.getAlbumss();
        Album2 selectedAlbum = userAlbums.get(ANAME);
        List<photo2> selectedPhotos = selectedAlbum.getPhoto2List();
        Image image1 = null;
        photoIndex--;
        if(photoIndex < 0)
            photoIndex = selectedPhotos.size()-1;
        image1 = new Image(new FileInputStream("data/photos/" + selectedPhotos.get(photoIndex).getPhotoName()));
        imageView.setImage(image1);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);



    }
    
    /**
     * set previousButton
     * @param event
     * @throws IOException
     */
    @FXML private void setPreviousButton(ActionEvent event)throws IOException{
        User2 user = be.readUser(UNAME);
        Map<String, Album2> userAlbums = user.getAlbumss();
        Album2 selectedAlbum = userAlbums.get(ANAME);
        List<photo2> selectedPhotos = selectedAlbum.getPhoto2List();
        Image image1 = null;
        photoIndex++;
        if(photoIndex == selectedPhotos.size())
            photoIndex = 0;
        image1 = new Image(new FileInputStream("data/photos/" + selectedPhotos.get(photoIndex).getPhotoName()));
        imageView.setImage(image1);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

    }
    @FXML private void setPlayButton(ActionEvent event){

    }
    /**
     * set back Button
     * @param event
     */
    @FXML private void setBackButton(ActionEvent event){
        Parent parent;
        try {
            FXMLLoader loader;
            if(screen == 0) {
                loader = new FXMLLoader(getClass().getResource("../FXML_Files/photo.fxml"));
                photoController photoController = new photoController(UNAME,ANAME);
                loader.setController(photoController);
            }
            else {
                loader = new FXMLLoader(getClass().getResource("../FXML_Files/user.fxml"));
                userController userController = new userController(UNAME);
                loader.setController(userController);
            }

            parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
