/**
 * @author Manan Patel, Hiren Patel
 */

package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.Album2;
import Model.Backend;
import Model.User;
import Model.User2;

import java.io.File;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import static Model.Backend.writeBackend;


/**
 * this class is admin controller.  It handless all the events in admin screen
 */
public class adminController{

    //local variables
    Backend be = new Backend();  //beckend
    @FXML TableView<User> userTableView; //tableview
    @FXML TableColumn<User, String> usernameCol; //username col
    @FXML TextField newuserText, searchText; //username and search text field
    @FXML PasswordField newpassText; //password text field
    @FXML Label newlabelText; //warning label
    int usercount = 0; //number of user count
    Parent parent; //parent
    Stage stage; //stage
    //data to be put on tableview
    private final ObservableList<User> userData =
            FXCollections.observableArrayList(
                    //new User("a","b")
            );

    /**
     * initialize the window
     */
    public void initialize(){
        usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        setListusersButton();
    }

    /**
     * list all the users on table when clicked on the list user button
     *
     */
    @FXML private void setListusersButton(){
        userTableView.setItems(userData);
        ObservableList<User> alluser = userTableView.getItems();
        userData.removeAll(alluser);
        List<String> users = be.listUsers();
        usercount = 0;
        if(users != null) {
            for (String u : users) {
                usercount++;
                User u2 = new User(u);
                userData.add(u2);
            }
        }
    }

    /**
     * add user to the table, and the login.txt file, and select the newly created user
     * @param event
     */
    @FXML private void setAdduserButton(ActionEvent event) throws Exception{
        String newuText = newuserText.getText();
        String newuPass = newpassText.getText();
        boolean iswhitespace = newuText.matches(",");
        if((newuPass.equals("") || newuText.equals(""))){
            newlabelText.setText("Enter Username and Password");
        }else if(iswhitespace){
            alert("Invalid","username should not contain special characters","");
        }
        else if(!addUser(newuText,newuPass)){
            newlabelText.setText("username already exists");
        }
        else{
            newlabelText.setText("account created");
            User user1 = new User(newuText);
            userData.add(user1);
            usercount++;
            writeBackend(be);
        }
        newuserText.clear();
        newpassText.clear();
    }

    /**
     * adds user to album
     * @param userID user name
     * @param password password
     * @return
     */
    public boolean addUser(String userID, String password){

        List<Album2> albs = new ArrayList<Album2>();
        User2 user2 = new User2(userID,password);
        return be.addUser(user2);
    }

    /**
     * delete the user from file and table
     * @param event
     */
    @FXML private void setDeleteuserButton(ActionEvent event) throws  IOException{
        int index = userTableView.getSelectionModel().getSelectedIndex();
        if(index >=0){
            ObservableList<User> selectedUser, alluser;
            alluser = userTableView.getItems();
            selectedUser = userTableView.getSelectionModel().getSelectedItems();
            User u = userTableView.getSelectionModel().getSelectedItem();
            String name = u.getUsername();
            selectedUser.forEach(alluser::remove);
            usercount--;
            userTableView.requestFocus();
            userTableView.focusModelProperty().get().focus(new TablePosition(userTableView, 0, usernameCol));
            userTableView.getSelectionModel().select(0);
            newlabelText.setText("Account deleted");
            be.deleteUser(name);
            writeBackend(be);

        }else{
            alert("Delete error","Select user before deleting","");
        }
    }

    /**
     * search for the user and select it in the table
     * @param event
     */
    @FXML private void setSearchuserButton(ActionEvent event){
        newlabelText.setText("");
        String search = searchText.getText();
        if(search.equals("")){
            searchText.clear();
            alert("Search error","Enter username before searching","");
            return;
        }
        for(int i = 0; i < usercount; i++){
            searchText.clear();
            userTableView.getSelectionModel().select(i);
            User u = userTableView.getSelectionModel().getSelectedItem();
            if(u.getUsername().equals(search)){
                userTableView.requestFocus();
                userTableView.focusModelProperty().get().focus(new TablePosition(userTableView, 0, usernameCol));
                userTableView.getSelectionModel().select(i);
                searchText.clear();
                return;
            }
        }
        alert("Search error","User does not exists","");
    }

    /**
     * logs user out
     * @param event
     */
    @FXML private void setLogoutButton(ActionEvent event){

        newlabelText.setText("");
        ((Node) (event.getSource())).getScene().getWindow().hide();
        openWindow("../FXML_Files/login.fxml");
        stage.setTitle("Photo Album login");
        stage.show();
    }

    /**
     * open window given fxml file
     * @param fxmlName
     */
    private void openWindow(String fxmlName){
        try {
            parent = FXMLLoader.load(getClass().getResource(fxmlName));
            stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delets file recursively
     * @param filename file to be deleted
     */
    private void deleteRec(File filename){
        if(filename.isDirectory()){
            for(File child : filename.listFiles())
                deleteRec(child);
        }
        filename.delete();
    }

    /**
     * alert pop up to display alerts
     * @param title alert title
     * @param header alert header
     * @param body alert body
     */
    public void alert(String title, String header, String body){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(body);
        alert.showAndWait();
    }


}
