/**
 * @author Abhishek Prajapati, Darshan Patel
 */
package Controllers;

import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static Model.Backend.writeBackend;

public class userController{
    @FXML private Button logoutButton;
    @FXML Label usernameLabel;
    @FXML Label totalAlbumLabel;
    @FXML Label totalphotosLabel;
    @FXML Label dateLabel;
    @FXML Label datesLabel;
    @FXML Slider zoomSlider;
    @FXML Button backButton;
    @FXML Button nextButton;
    @FXML Button homeButton;
    @FXML Button searchButton;
    @FXML Button slideshowButton;
    @FXML Button userButton;
    @FXML Button newalbumButton;
    @FXML Button editalbumButton;
    @FXML Button deletealbumButton;
    @FXML Button createButton;
    @FXML Button cancelButton;
    @FXML Label newTitleLabel;
    @FXML TextField newAlbumText;
    int totalalbum = 0;

    String UNAME = null;
    private int albums = 0;

    @FXML TableView<album> albumTableView;
    @FXML TableColumn<album,String> albumCol;
    @FXML TableColumn<album,String> photosCol;
    @FXML TableColumn<album, String> dateCol;
    @FXML TableColumn<album,String> datesCol;

    public Controller controller = new Controller();
    public Backend be = new Backend();
    public Controllers.adminController adminController = new adminController();
    public User2 currentuser;

    protected final ObservableList<album> userAlbums = FXCollections.observableArrayList(
            //new album("hello")
    );



    public String albumName;

    public userController(){}
    public userController(String UNAME){
        this.UNAME = UNAME;
    }

    /**
     * initialize
     */
    public void initialize(){
        albumCol.setCellValueFactory(new PropertyValueFactory<album, String>("name"));
        photosCol.setCellValueFactory(new PropertyValueFactory<album, String>("photos"));
        dateCol.setCellValueFactory(new PropertyValueFactory<album, String>("date"));
        datesCol.setCellValueFactory(new PropertyValueFactory<album, String>("dates"));
        albumTableView.setItems(userAlbums);



                albumTableView.setRowFactory(tv -> {
            TableRow<album> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    album rowdata = row.getItem();
                    albumName = rowdata.getName();
                    Stage stage;
                    Parent parent;
                    stage = (Stage) nextButton.getScene().getWindow();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML_Files/photo.fxml"));
                        photoController photoController = new photoController(UNAME,albumName);
                        loader.setController(photoController);
                        parent = loader.load();
                        Scene scene = new Scene(parent);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });


            User2 user = null;
            usernameLabel.setText(UNAME +"'s Album");
            user = be.readUser(UNAME);
            currentuser = user;
            controller.currentUser = user;
            Map<String, Album2> albums = user.getAlbumss();

            Map<String, Album2> albumHash = currentuser.getAlbumss();


            for (String alb: albums.keySet()) {
                Album2 targetAlbum = albumHash.get(alb);
                List<photo2> targetPhotos = targetAlbum.getPhoto2List();
                album al = new album(alb,targetPhotos.size(),targetAlbum.getLastModDate(),targetAlbum.firstDate + " - " + targetAlbum.getLastDate());
                userAlbums.add(al);
            }
            totalAlbumLabel.setText(user.getTotalAlbums()+"");
            dateLabel.setText(user.getLastModDate());
            totalphotosLabel.setText(user.getTotalPhotos()+"");
            totalalbum = user.getTotalAlbums();

    }

    /**
     * search for item
     * @param event search button
     */
    @FXML private void setSearchButton(ActionEvent event){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Search");
        dialog.setHeaderText("Search for photos by date or tag");
        dialog.setGraphic(new ImageView(this.getClass().getResource("../icons/Planner-96.png").toString()));

        //ButtonType searchButtonType = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);


        ButtonType searchByTagButton = new ButtonType("Search by tag",ButtonBar.ButtonData.OK_DONE);
        ButtonType searchBydate = new ButtonType("Search by A date",ButtonBar.ButtonData.OK_DONE);
        ButtonType searchRangOfDates = new ButtonType("Search by range of dates",ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(searchByTagButton,searchBydate,searchRangOfDates, ButtonType.CANCEL);

        Node tagButton = dialog.getDialogPane().lookupButton(searchByTagButton);
        tagButton.setDisable(true);

        //searchByTagButton.setText("Search by tag");
        //searchBydate.setText("Search by a date");
        //searchRangOfDates.setText("Search by range of dates");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        //LocalDate date = null,date1 = null,date2 = null;
        final String[] formattedDate = new String[1];
        final String[] formattedDate1 = new String[1];
        final String[] formattedDate2 = new String[1];
        final LocalDate[] date100 = {null};
        final LocalDate[] date200 = {null};

        TextField tagFiled = new TextField();
        tagFiled.setPromptText("tag");
        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(event1 -> {
            LocalDate date = datePicker.getValue();
            if(date != null)
                formattedDate[0] = date.toString();
            //System.out.println("Selected date: " + date);
        });

        DatePicker datePicker1 = new DatePicker();
        datePicker1.setOnAction(event1 -> {
            LocalDate date1 = datePicker1.getValue();
            if(date1 != null) {
                formattedDate1[0] = date1.toString();
                date100[0] = date1;
            }
            //System.out.println("Selected date: " + date1);
        });

        DatePicker datePicker2 = new DatePicker();
        datePicker2.setOnAction(event1 -> {
            LocalDate date2 = datePicker2.getValue();
            if(date2 != null) {
                formattedDate2[0] = date2.toString();
                date200[0] = date2;
            }
            //System.out.println("Selected date: " + date2);
        });

        grid.add(new Label("Search by Tag (add \" , \" to separate tags): "), 0, 0);
        grid.add(tagFiled, 1, 0);
        //grid.add(searchByTagButton,3,0);
        grid.add(new Label("OR"),0,1);
        grid.add(new Label("Search by Date: "),0,2);
        grid.add(datePicker,1,2);
        //grid.add(searchBydate,3,2);
        grid.add(new Label("OR"),0,3);
        grid.add(new Label("Search by Range of Dates: "),0,4);
        grid.add(datePicker1,1,4);
        grid.add(new Label("-"),2,4);
        grid.add(datePicker2,3,4);
       // grid.add(searchRangOfDates,4,4);
        dialog.getDialogPane().setContent(grid);

        tagFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            tagButton.setDisable(newValue.trim().isEmpty());
        });


        final int[] button = new int[1];
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == searchByTagButton) {
               // System.out.println(tagFiled.getText());
                if(!tagFiled.getText().equals("")){
                    List<String> tags = new ArrayList<String>();
                    String[] tokens ;
                    tokens = tagFiled.getText().split(",");

                    for(String s : tokens){
                        tags.add(s);
                    }

                    List<photo2> tagphotos = controller.getPhotosByTag(tags);
                    if(tagphotos.size() <= 0){
                        adminController.alert("tag not found","No photos found","No photos of the given tag were found");
                    }
                    else searchDisplay(event, tagphotos);

                }else{
                    adminController.alert("error","enter tag","enter tag");
                }
                button[0] = 1;
            }
            else if (dialogButton == searchBydate) {
                //System.out.println(formattedDate[0]);
                if(formattedDate[0] != null){
                    List<photo2> tagphotos = null;
                    try {
                        tagphotos = controller.getPhotosByDate(formattedDate[0]);
                        if(tagphotos.size() <= 0){
                            adminController.alert("tag not found","No photos found","No photos of the given tag were found");
                        }
                        else searchDisplay(event, tagphotos);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{
                    adminController.alert("error","Enter valid date","enter date in \"search by date\" then click 'search by a date'");
                    setSearchButton(event);
                }
                button[0] = 2;
            }
            else if (dialogButton == searchRangOfDates) {
                //System.out.println(formattedDate1[0] + "," + formattedDate2[0]);
                if(formattedDate1[0] != null && formattedDate2[0] != null){
                    try {
                        List<photo2> tagphotos = controller.getPhotosByDate(formattedDate1[0],formattedDate2[0]);
                        if(tagphotos.size() <= 0){
                            adminController.alert("tag not found","No photos found","No photos of the given tag were found");
                        }
                        else searchDisplay(event, tagphotos);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{
                    adminController.alert("error","Enter valid date in range of dates","enter date in \"search by range of date\" then click 'search by range of date'");
                }
                button[0] = 3;
            }
            return null;
        });

        dialog.showAndWait();

        //dialog.showAndWait();


//        Optional<Pair<String,String>> result = dialog.showAndWait();
//
//        if(result.isPresent()){
//            System.out.println(result.get());
//        }

    }

    private void searchDisplay(ActionEvent event, List<photo2> photo2){
        Parent parent;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML_Files/searchDisplay.fxml"));
            searchDisplay searchDisplay = new searchDisplay(UNAME,photo2);
            loader.setController(searchDisplay);
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

    /**
     * open album
     * @param event open album button
     * @throws IOException
     */
    @FXML private void openAlbum(ActionEvent event) throws IOException{
        int index = albumTableView.getSelectionModel().getSelectedIndex();
        if(index >=0){
            album rowdata = albumTableView.getSelectionModel().getSelectedItem();
            String name = rowdata.getName();
            Parent parent;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML_Files/photo.fxml"));
            photoController photoController = new photoController(UNAME,name);
            loader.setController(photoController);
            Stage stage = new Stage();
            parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle(UNAME + "'s Photo Album");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.show();
        }else{
            adminController.alert("Opeaning Error","Select an album before opeaning","");
        }
    }

    /**
     * logout
     * @param event logout button
     */
    @FXML protected void setLogoutButtonAction(ActionEvent event){
        ((Node) (event.getSource())).getScene().getWindow().hide();
        showloginScreen();
    }

    /**
     * shows login screen
     */
    private void showloginScreen(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../FXML_Files/login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Photo Album Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void setSlideshowButton(ActionEvent event){
        int index = albumTableView.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            album rowdata = albumTableView.getSelectionModel().getSelectedItem();
            User2 user = be.readUser(UNAME);
            Map<String, Album2> userAlbums = user.getAlbumss();
            Album2 selectedAlbum = userAlbums.get(rowdata.getName());
            List<photo2> selectedPhotos = selectedAlbum.getPhoto2List();

            if(selectedPhotos.size() <= 0){
                adminController.alert("Slide show error","There are no photos in the album","");
                return;
            }
            Parent parent;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML_Files/slideshow.fxml"));
            slidshowController slidshowController = new slidshowController(UNAME,rowdata.getName(),1);
            loader.setController(slidshowController);
            try {
                parent = loader.load();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Slide Show");
                ((Node) (event.getSource())).getScene().getWindow().hide();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            adminController.alert("Slide show error","Select an album before Starting Slide show","");
        }
    }

    /**
     * home button
     * @param event home button
     */
    @FXML private void setHomeButton(ActionEvent event){
        setLogoutButtonAction(event);
    }

    /**
     * new album
     * @param event new album button
     * @throws IOException
     */
    @FXML private void setNewalbumButton(ActionEvent event)throws IOException{
        TextInputDialog dialog = new TextInputDialog("New Album..");
        dialog.setContentText("New Album Name: ");
        dialog.setTitle("New Album");
        dialog.setHeaderText("Creating New Album");
        Optional<String> name = dialog.showAndWait();

        if(name.isPresent()){
            if(!controller.createAlbum(name.get())){
                adminController.alert("Creating Album","Album Already Exists","Enter different album name");
            }else{
                String date = getDate();
                dateLabel.setText(date);
                totalAlbumLabel.setText(++totalalbum+"");
                writeBackend(be);
                album al = new album(name.get());
                userAlbums.add(al);
            }
        }
    }

    /**
     * delete album
     * @param event delete album button
     * @throws IOException
     */
    @FXML private void setdeletealbumButton(ActionEvent event) throws IOException{
        int index = albumTableView.getSelectionModel().getSelectedIndex();
        if(index >=0){
            ObservableList<album> selectedUser,alluser;
            alluser = albumTableView.getItems();
            selectedUser = albumTableView.getSelectionModel().getSelectedItems();
            album a = albumTableView.getSelectionModel().getSelectedItem();
            String name = a.getName();


            Map<String, Album2> albumHash = currentuser.getAlbumss();
            Album2 targetAlbum = albumHash.get(a.getName());
            List<photo2> targetPhotos = targetAlbum.getPhoto2List();
            if(!controller.deleteAlbum(name)){
                adminController.alert("Deleting Album","Album Doesn't Exists","Select an album");
            }else{
                String date = getDate();
                dateLabel.setText(date);
                totalAlbumLabel.setText(--totalalbum+"");
                currentuser.setTotalPhotos(currentuser.getTotalPhotos()-targetPhotos.size());
                totalphotosLabel.setText(currentuser.getTotalPhotos()+"");
                writeBackend(be);
                selectedUser.forEach(alluser::remove);
            }

        }else{
            adminController.alert("Delete error","Select an album before deleting","");
        }

    }

    /**
     * edit album
     * @param event edit album button
     * @throws Exception
     */
    @FXML private void sereditalbumButton(ActionEvent event)throws Exception{
        int index = albumTableView.getSelectionModel().getSelectedIndex();
        if(index >=0){
            album a = albumTableView.getSelectionModel().getSelectedItem();
            String orignalName = a.getName();
            TextInputDialog dialog = new TextInputDialog("Rename Album..");
            dialog.setContentText("New Album Name: ");
            dialog.setTitle("Rename Album");
            dialog.setHeaderText("Renaming Album");
            Optional<String> name = dialog.showAndWait();
            if(name.isPresent()){
                if(!controller.renameAlbum(orignalName,name.get())){
                    adminController.alert("Edit error","Album of the same name already exists","");
                }else{
                    String date = getDate();
                    dateLabel.setText(date);
                    a.setName(name.get());
                    writeBackend(be);
                }
            }

        }else{
            adminController.alert("Edit error","Select an album before editing","");
        }
    }

    /**
     * back
     * @param event back button
     */
    @FXML private void setBackButton(ActionEvent event){
        setLogoutButtonAction(event);
    }

    /**
     *
     * @return date
     */
    private String getDate(){
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy'-'HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
