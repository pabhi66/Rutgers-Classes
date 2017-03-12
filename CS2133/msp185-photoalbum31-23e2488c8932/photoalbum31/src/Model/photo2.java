
/**
 * @author Manan Patel, Hiren Patel
 */

package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * photo class
 */
public class photo2 extends Album2 implements Serializable{
    private static final long serialVersionUID = 1L;
    private String photoName;
    private String caption;
    private Calendar dateAndTime;
    public List<Tag> tags;
    private String photoImportDate;
    private String photoModDate;

    /**
     * constructor
     * @param photoName photo name
     * @param photoImportDate import date
     * @param photoModDate modified date
     */
    public photo2(String photoName,String photoImportDate,String photoModDate){
        this.photoImportDate = photoImportDate;
        this.photoModDate = photoModDate;
        this.photoName = photoName;
    }

    /**
     * constructor
     * @param photoName photo name
     * @param photoModDate modified date
     */
    public photo2(String photoName,String photoModDate){
        this.photoName = photoName;
        this.photoModDate = photoModDate;
    }

    /**
     * constructor
     * @param photoName photoname
     * @param caption caption
     * @param dateAndTime date and time
     */
    public photo2(String photoName, String caption, Calendar dateAndTime){
        this.photoName = photoName;
        this.caption = caption;
        this.dateAndTime = dateAndTime;
        this.tags = new ArrayList<Tag>();
    }

    /**
     * add tag to photo
     * @param newTag tag
     * @return true if added
     */
    public boolean addTag(Tag newTag) {
        for(Tag t : this.tags) {
            if(t.equals(newTag)) {
                return false;
            }
        }

        changeDate();
        tags.add(newTag);

        return true;
    }

    /**
     * delete tag
     * @param deleteTag tag
     * @return true if deleted
     */
    public boolean removeTag(Tag deleteTag) {
        for(int i = 0; i < this.tags.size(); i++) {
            if(this.tags.get(i).equals(deleteTag)) {
                changeDate();
                this.tags.remove(i);
                return true;
            }
        }
        return false;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        changeDate();
        this.caption = caption;
    }

    public Calendar getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Calendar dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public boolean equals(Object o) {

        if(o == null || !(o instanceof Photo)) {
            return false;
        }

        photo2 toCompare = (photo2)o;

        if(toCompare.getCaption().equals(this.getCaption())) {
            if(toCompare.getPhotoName().equals(this.getPhotoName())) {
                if(toCompare.getDateAndTime().equals(this.getDateAndTime())) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<Tag> getTags()
    {
        return this.tags;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }



    public String getPhotoImportDate() {
        return photoImportDate;
    }

    public void setPhotoImportDate(String photoImportDate) {
        this.photoImportDate = photoImportDate;
    }

    public String getPhotoModDate() {
        return photoModDate;
    }

    public void setPhotoModDate(String photoModDate) {
        this.photoModDate = photoModDate;
    }
}
