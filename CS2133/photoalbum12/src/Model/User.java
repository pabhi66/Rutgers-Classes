/**
 * @author Abhishek Prajapati, Darshan Patel
 */
package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Created by Abhi on 3/26/16.
 */
public class User{
    private final SimpleStringProperty username;

    /**
     * gets username
     * @return
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * returns username
     * @return username
     */
    public SimpleStringProperty usernameProperty() {
        return username;
    }

    /**
     * sets username
     * @param username username
     */
    public void setUsername(String username) {
        this.username.set(username);
    }



    /**
     * default constructors
     */
    public User(){this(null);}


    public User(String username){
        this.username = new SimpleStringProperty(username);

    }


}
