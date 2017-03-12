/**
 * @author Manan Patel, Hiren Patel
 */
package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

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
