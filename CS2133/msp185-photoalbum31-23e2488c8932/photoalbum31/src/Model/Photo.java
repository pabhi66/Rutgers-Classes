/**
 * @author Manan Patel, Hiren Patel
 */
package Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

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
