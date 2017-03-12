
/**
 * @author Manan  Patel, Hiren Patel
 */
package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Model.Album2;
import Model.Backend;
import Model.User2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Model.Backend.writeBackend;

/**
 * login controller, controls login events
 */
public class loginController {
    private static final long serialVersionUID = 1L;
    public String UNAME;
    public Backend be = new Backend();
    private User2 currentUser;

    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Button newuserButton;
    @FXML
    private Button helpButton;

    private Map<String,User2> users;

    int counter = 0;


    /**
     * login button action
     * @param event login button
     * @throws IOException
     */
    @FXML private void loginButtonAction(ActionEvent event) throws IOException{
        UNAME = usernameText.getText();

        Parent parent = null;
        if(UNAME.equals("admin") && passwordText.getText().equals("admin")){
            ((Node) (event.getSource())).getScene().getWindow().hide();
            parent = FXMLLoader.load(getClass().getResource("../FXML_Files/admin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle(UNAME + "'s Photo Album");
            stage.show();

        }else if(usernameText.getText().equals("") || passwordText.getText().equals("")){
            errorLabel.setText("Enter username and password");
        }else{
            if(!(loginCheck(usernameText.getText(),passwordText.getText()))){
                errorLabel.setText("username or password is incorrect");
                refresh();
            }
            else{
                ((Node) (event.getSource())).getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML_Files/user.fxml"));
                userController userController = new userController(UNAME);
                loader.setController(userController);
                parent = loader.load();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle(UNAME + "'s Photo Album");
                stage.show();

            }
        }


    }

    /**
     * new account action
     * @param event new account button
     * @throws Exception
     */
    @FXML private void newAccoutAction(ActionEvent event) throws Exception{
        if(usernameText.getText().equals("admin")){
            refresh();
            errorLabel.setText("Cannot create admin account");
        }else if(usernameText.getText().equals("") || passwordText.getText().equals("")){
            errorLabel.setText("Enter username and password");
        }
        else if(!addUser(usernameText.getText(),passwordText.getText())){
            refresh();
            errorLabel.setText("Username already exists");
        }
        else{
            //User2 user = new User2(usernameText.getText(),passwordText.getText());
            writeBackend(be);
            refresh();
            errorLabel.setText("account created");
        }
    }


    /**
     * help button
     * @param event help button
     */
    @FXML private void setHelpButtonAction(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Help!");
        alert.setHeaderText("For Admin: Userid = admin, Pass = admin");
        alert.setContentText("For other user, first create account then login with your new user id and password");
        alert.showAndWait();
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
     * login check
     * @param id username
     * @param pass password
     * @return true if valid
     */
    public boolean loginCheck(String id, String pass){
        List<String> userIds = be.listUsers();
        if(userIds != null && userIds.contains(id))
        {
            currentUser = be.readUser(id);
            if(currentUser.getPassword().equals(pass))
                return true;
        }
        return false;
    }

    /**
     * refresh text fields
     */
    public void refresh(){
        usernameText.clear();
        passwordText.clear();
    }

    /**
     * write username to file
     * @param id username
     * @param filename filename
     */
    public void writeToFile(String id, String filename){
        FileWriter fw = null;
        try {
            fw = new FileWriter(filename);
            fw.write(id);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

  }

    public void setExitButton(ActionEvent event){
        System.exit(0);
    }

}
