/**
 * @author Abhishek Prajapati, Darshan Patel
 */
package Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Abhi on 3/29/16.
 */

/**
 * this is a album class. which is used to add,remove photos/dates of specific user album
 */
public class Album2 implements Serializable {
    //private static final long serialVersionUID = 1L;
    private String name;
    private int numPhotos;
    //public List<String> photoList;
    private String lastModDate;
    public String firstDate = null;
    public String lastDate = null;
    public List<photo2> photo2List;
    public List<String> dates = new ArrayList<String>();
    public int count = 0;

    /**
     *
     * @return last mod date
     */
    public String getLastModDate() {
        return lastModDate;
    }

    /**
     * set last modified date
     * @param lastModDate last modified date
     */
    public void setLastModDate(String lastModDate) {
        this.lastModDate = lastModDate;
    }

    //constructors

    /**
     * default constructor
     */
    public Album2(){}

    /**
     * constructor that only takes album name
     * @param name album name
     */
    public Album2(String name){
        this.name = name;
        this.numPhotos = 0;
        //this.photoList = new ArrayList<String>();
    }

    /**
     * constructor
     * @param name album name
     * @param photos photo list
     */
    public Album2(String name, List<photo2> photos){
        this.name = name;
        if(photos == null){
            photos = new ArrayList<photo2>();
        }
        this.photo2List = photos;
    }

    /**
     *
     * @return name of album
     */
    public String getName() {
        return name;
    }

    /**
     * sets album name
     * @param name album name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return number of photos in album
     */
    public int getNumPhotos() {
        return numPhotos;
    }

    /**
     * sets number of photos
     * @param numPhotos number of photos
     */
    public void setNumPhotos(int numPhotos) {
        this.numPhotos = numPhotos;
    }

    /**
     *
     * @return coung of albums
     */
    public int getCount() {
        return count;
    }

    /**
     * sets album count
     * @param count album count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * returns photo list with all the photos that are in an album
     * @return photo list
     */
    public List<photo2> getPhoto2List() {

        return photo2List;
    }

    /**
     * sets photo in album
     * @param photo2List photo list
     */
    public void setPhoto2List(List<photo2> photo2List) {
        this.photo2List = photo2List;
    }

    public photo2 getPhoto(String photoName){
        for(photo2 photo2 : photo2List){
            if(photo2.getPhotoName().equalsIgnoreCase(photoName))
                return photo2;
        }
        return null;
    }

    /**
     * deletes photo from photolist
     * @param toDelete photo
     * @return true if deleted false otherwise
     */
    public boolean deletePhoto(photo2 toDelete) {

        if(toDelete == null) {
            return false;
        }

        for(photo2 p : this.photo2List) {

            //if (p.equals(toDelete))
            if(p == toDelete) {
                List<photo2> tempPhoto = new ArrayList<photo2>();


                for(photo2 photo2 : this.photo2List){
                    if(photo2.getPhotoName().equals(toDelete.getPhotoName()))
                        continue;
                    tempPhoto.add(photo2);
                }

                changeDate();
                this.numPhotos = getNumPhotos() -1;
                //this.photo2List.remove(toDelete);
                this.photo2List = tempPhoto;

                return true;
            }
        }
        return false;

    }
    
    /**
     * 
     * @return lastDate
     */
    public String getLastDate() {
        return lastDate;
    }
    
    /**
     * set the last date
     * @param lastDate
     */
    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    /**
     * add photo to photolist
     * @param toAdd photo
     * @return true if photo added false otherwise
     */
    public boolean addPhoto(photo2 toAdd) {

        if(toAdd == null) {
            return false;
        }

        for(photo2 p : this.photo2List) {
            if (p.getPhotoName().equals(toAdd.getPhotoName())) {
                return false;
            }
        }
        if(firstDate == null){
            this.firstDate = changeDate("");
            this.lastDate = changeDate("");
        }else{
            this.lastDate = changeDate("");
        }

        changeDate();
        this.numPhotos = getNumPhotos()+1;
        this.photo2List.add(toAdd);

        return true;
    }

    /**
     * checks for equality
     * @param o object
     * @return true if equal
     */
    public boolean equals(Object o) {
        boolean retVal = false;

        if(o == null || !(o instanceof Album2)) {
            return false;
        }

        Album2 toCompare = (Album2)o;
        if(name.equals(toCompare))
            return true;


        return false;
    }

    /**
     * changes the last modified date
     */
    public void changeDate(){
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy'-'HH:mm:ss");
        Date date = new Date();
        String frmdate = formatter.format(date);
        this.setLastModDate(frmdate);
    }

    /**
     *
     * @param date1 useless
     * @return date in string
     */
    public String changeDate(String date1){
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy'-'HH:mm:ss");
        Date date = new Date();
        String frmdate = formatter.format(date);
        return frmdate;
    }


}