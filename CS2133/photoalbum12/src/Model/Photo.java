//package photoalbum;
//
//
//import java.util.*;
//import java.io.File;
//
///**
// * Created by Abhi on 3/27/16.
// */
//public class Photo{
//
//    private String name;
//    private String caption;
//    private ArrayList<String> albumname;
//    private Calendar calendar;
//    private File photoFile;
//    public HashMap<String,String> tag;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCaption() {
//        return caption;
//    }
//
//    @Override
//    public String toString() {
//        return "Photo{" +
//                "name='" + name + '\'' +
//                ", caption='" + caption + '\'' +
//                ", albumname=" + albumname +
//                ", calendar=" + calendar +
//                ", photoFile=" + photoFile +
//                ", tag=" + tag +
//                '}';
//    }
//
//    public void setCaption(String caption) {
//        this.caption = caption;
//    }
//
//    public ArrayList<String> getAlbumname() {
//        return albumname;
//    }
//
//    public void setAlbumname(ArrayList<String> albumname) {
//        this.albumname = albumname;
//    }
//
//    public Calendar getCalendar() {
//        return calendar;
//    }
//
//    public void setCalendar(Calendar calendar) {
//        this.calendar = calendar;
//    }
//
//    public File getPhotoFile() {
//        return photoFile;
//    }
//
//    public void setPhotoFile(File photoFile) {
//        this.photoFile = photoFile;
//    }
//
//    public HashMap<String, String> getTag() {
//        return tag;
//    }
//
//    public void setTag(HashMap<String, String> tag) {
//        this.tag = tag;
//    }
//
//    public Photo(String name, String caption, String albumname, Date date, File photoFile){
//        this.name = name;
//        this.caption = caption;
//        this.albumname = new ArrayList<String>();
//        this.albumname.add(albumname);
//        this.photoFile = photoFile;
//        calendar = new GregorianCalendar();
//        calendar.setTime(date);
//        calendar.set(Calendar.MILLISECOND,0);
//        tag = new HashMap<String, String>();
//
//    }
//}

/**
 * @author Abhishek Prajapati, Darshan Patel
 */
package Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * not used yet
 */
public class Photo{
    private SimpleStringProperty caption = new SimpleStringProperty();
    private ObjectProperty photoArt = new SimpleObjectProperty<>();
    private SimpleStringProperty tag = new SimpleStringProperty();

    /**
     * constructor for photo
     * @param caption
     * @param photo2
     * @param tag
     */
    public Photo(String caption, photo2 photo2, String tag){
        setTag(tag);
        setCaption(caption);
        setPhotoArt(photo2);

    }
    
    /**
     * 
     * @return caption
     */
    public String getCaption() {
        return caption.get();
    }
    
    public SimpleStringProperty captionProperty() {
        return caption;
    }

    /**
     * set caption
     * @param caption
     */
    public void setCaption(String caption) {
        this.caption.set(caption);
    }
    
    /**
     * 
     * @return photo art
     */
    public Object getPhotoArt() {
        return photoArt.get();
    }
    
    /**
     * 
     * @return photoArt
     */
    public ObjectProperty photoArtProperty() {
        return photoArt;
    }
    
    /**
     * set photoArt
     * @param
     */
    public void setPhotoArt(photo2 photoArt) {
        this.photoArt.set(photoArt);
    }
    
    public String getTag() {
        return tag.get();
    }

    public SimpleStringProperty tagProperty() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag.set(tag);
    }
}
