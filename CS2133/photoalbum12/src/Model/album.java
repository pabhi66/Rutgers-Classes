//package photoalbum;
//
//import java.util.Calendar;
//
///**
// * Created by Abhi on 3/28/16.
// */
//public class album {
//    private String name;
//    private int numPhotos;
//    private Calendar calendar;
//
//    public album(String name){
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getNumPhotos() {
//        return numPhotos;
//    }
//
//    public void setNumPhotos(int numPhotos) {
//        this.numPhotos = numPhotos;
//    }
//}

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

/**
 * This class is used to display albums and its information to be shown on the Tableview in User.fxml (user controller)
 */
public class album{
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty photos;
    private final SimpleStringProperty date;
    private final SimpleStringProperty dates;
    private final SimpleStringProperty oldestDate;
    private int count = 0;


    /**
     * default constructors
     */
    public album(){this(null);}

    /**
     *
     * @return returns oldest date
     */
    public String getOldestDate() {
        return oldestDate.get();
    }

    /**
     *
     * @return returns oldest date
     */
    public SimpleStringProperty oldestDateProperty() {
        return oldestDate;
    }
    
    /**
     * set old Date for Album
     * @param oldestDate
     */
    public void setOldestDate(String oldestDate) {
        if(count == 0) {
            this.oldestDate.set(oldestDate);
            count = 1;
        }
    }

    /**
     * constructor
     * @param name name of album
     */
    public album(String name){
        this.name = new SimpleStringProperty(name);
        this.photos = new SimpleIntegerProperty(0);
        this.date = new SimpleStringProperty("never");
        this.dates = new SimpleStringProperty("no pictures");
        this.oldestDate = new SimpleStringProperty("");

    }

    /**
     * constructor
     * @param name photo name
     * @param photos number of photos
     * @param date last modified date
     * @param dates range of dates
     */
    public album(String name,Integer photos,String date, String dates){
        this.name = new SimpleStringProperty(name);
        this.photos = new SimpleIntegerProperty(photos);
        if(getPhotos() == 0){
            this.date = new SimpleStringProperty("never");
        }
        else this.date = new SimpleStringProperty(date);
        if(getPhotos() == 0){
            this.dates = new SimpleStringProperty("no photos");
        }else
            this.dates = new SimpleStringProperty(dates);
        this.oldestDate = new SimpleStringProperty("");
    }

    /**
     *
     * @return name of album
     */
    public String getName() {
        return name.get();
    }

    /**
     *
     * @return name property
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * sets photo name
     * @param name photo name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     *
     * @return number of photos
     */
    public int getPhotos() {
        return photos.get();
    }

    /**
     *
     * @return number of photo property
     */
    public SimpleIntegerProperty photosProperty() {
        return photos;
    }

    /**
     * sets number of photos
     * @param photos number of photo
     */
    public void setPhotos(int photos) {
        this.photos.set(photos);
    }

    /**
     *
     * @return last modified date
     */
    public String getDate() {
        return date.get();
    }

    /**
     *
     * @return last modified date property
     */
    public SimpleStringProperty dateProperty() {
        return date;
    }

    /**
     * sets last modified date
     * @param date date in String form
     */
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     *
     * @return range of dates in string
     */
    public String getDates() {
        return dates.get();
    }

    /**
     *
     * @return range of dates property
     */
    public SimpleStringProperty datesProperty() {
        return dates;
    }

    /**
     * sets dates
     * @param dates range of dates
     */
    public void setDates(String dates) {
        this.dates.set(dates);
    }
}
