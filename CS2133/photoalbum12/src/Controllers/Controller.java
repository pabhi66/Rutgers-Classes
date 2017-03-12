/**
 * @author Abhishek Prajapati, Darshan Patel
 */
package Controllers;

import Model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Abhi on 4/1/16.
 */

/**
 * this class has all the user controllers
 */
public class Controller{
    public Backend be = new Backend();
    public User2 currentUser;
    private final DateFormat df = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");

    public Controller(){}

//    /**
//     *
//     * @return list of users
//     */
//    public List<String> listusers() {
//        List<String> users = be.listUsers();
//        return users;
//    }
//
//    /**
//     *gets the userID of the logged in account
//     * @return user id
//     */
//    public String getLoggedInUserId() {
//        return null;
//    }
//
//    /**
//     * checks for valid login info
//     * @param id username
//     * @param pass password
//     * @return true if valid login info
//     */
//    public boolean loginCheck(String id, String pass){
//        List<String> userIds = be.listUsers();
//        if(userIds != null && userIds.contains(id)) {
//            currentUser = be.readUser(id);
//            if(currentUser.getPassword().equals(pass))
//                return true;
//        }
//        return false;
//    }
//
//    /**
//     * add user
//     * @param id username
//     * @param name password
//     * @return true if added
//     */
//    public boolean adduser(String id, String name) {
//
//        List<Album2> tmpAlbums = new ArrayList<Album2>();
//
//        User2 user = new User2(id, name, tmpAlbums);
//
//        return be.addUser(user);
//    }
//
//    /**
//     * delete user
//     * @param id username
//     * @return true if user deleted
//     */
//    public boolean deleteuser(String id) {
//        boolean result = false;
//
//        result = be.deleteUser(id);
//
//        return result;
//    }
//
//
//    /**
//     * checks the date
//     * @param date date
//     * @return true if date matches
//     */
//    public boolean checkDate(String date) {
//        return false;
//    }

    /**
     * creates a new album
     * @param albumname album name
     * @return trye if album created
     */
    public boolean createAlbum(String albumname) {
        Album2 newAlbum = new Album2(albumname, null);
        return currentUser.addAlbum(newAlbum);
    }

    /**
     * deletes album
     * @param albumname album name
     * @return true if album is deleted
     */
    public boolean deleteAlbum(String albumname) {
        return currentUser.deleteAlbum(albumname);
    }

    /**
     * rename album
     * @param oldName oldname
     * @param newName new name
     * @return true if changed
     */
    public boolean renameAlbum(String oldName, String newName){
        return currentUser.renameAlbum(oldName, newName);
    }

//    /**
//     *list of albums on the account
//     * @return all user albums
//     */
//    public List<String> listAlbums() {
//
//        Map<String, Album2> albumHash = currentUser.getAlbumss();
//
//        List<String> albumList = new ArrayList<String>();
//
//        if(albumHash.keySet().isEmpty())
//        {
//            albumList = null;
//        }
//        else
//        {
//            for (String key : albumHash.keySet())
//            {
//                albumList.add(albumHash.get(key).toString());
//            }
//        }
//
//        return albumList;
//    }

    /**
     *list of photos in the album on the account
     * @param albumName album name
     * @return all photos in album
     */
    public List<String> listPhotos(String albumName) {

        Map<String, Album2> albumHash = currentUser.getAlbumss();

        if(!albumHash.containsKey(albumName)) {
            return null;
        }

        Album2 targetAlbum = albumHash.get(albumName);

        List<photo2> targetPhotos = targetAlbum.getPhoto2List();

        List<String> photoList = new ArrayList<String>();

        if(targetPhotos.isEmpty())
        {
            photoList = null;
        }
        else
        {
            for(photo2 p : targetPhotos) {
                photoList.add(p.getPhotoName() + " - " + df.format(p.getDateAndTime().getTime()));
            }
        }

        return photoList;

    }

    /**
     * add photo to the album 
     * @param fileName filename
     * @param captionName caption
     * @param albumName album name
     * @return 100 if added
     */
    public int addPhoto(String fileName, String captionName, String albumName) {

        Map<String, Album2> albumHash = currentUser.getAlbumss();
        boolean alreadyExistsInPhotos = false;

        photo2 newPhoto = null;
        if(albumHash == null){
            return -1;
        }

        Album2 targetAlbum = albumHash.get(albumName);
        List<photo2> targetPhotos = targetAlbum.getPhoto2List();

        for(photo2 p : targetPhotos) {
            if(fileName.equals(p.getPhotoName())) {
                return 0;
            }
        }


        Map<String, photo2> photos = currentUser.getPhotos();
        for(String fn : photos.keySet())
        {
            if(fn.equals(fileName))
            {
                alreadyExistsInPhotos = true;
                break;
            }
        }

        if(alreadyExistsInPhotos)
        {
            photo2 p = photos.get(fileName);
            targetAlbum.addPhoto(p);
            return 1;
        }else{
            Date date = new Date();
            Calendar creationDate = new GregorianCalendar();
            creationDate.setTime(date);
            creationDate.set(Calendar.MILLISECOND,0);

            photo2 addPhoto = new photo2(fileName, captionName, creationDate);
            currentUser.addPhoto(addPhoto);
            currentUser.setTotalPhotos(currentUser.getTotalPhotos()+1);
            targetAlbum.addPhoto(addPhoto);

        }

        return 100;
    }



    /**
     * move photo from album to album
     * @param fileName filename
     * @param oldAlbum old album name
     * @param newAlbum new album name
     * @return
     */
    public boolean movePhoto(String fileName, String oldAlbum, String newAlbum) {
        Map<String, Album2> albumHash = currentUser.getAlbumss();

        if(!albumHash.containsKey(oldAlbum)){
            return false;
        }
        if(!albumHash.containsKey(newAlbum)){
            return false;
        }
        Album2 previousAlbum = albumHash.get(oldAlbum);
        List<photo2> previousPhotos = previousAlbum.getPhoto2List();

        for(photo2 p: previousPhotos){
            if(fileName.equals(p.getPhotoName())) {
                Album2 nextAlbum = albumHash.get(newAlbum);
                List<photo2> nextPhotos = nextAlbum.getPhoto2List();

                for(photo2 x: nextPhotos) {
                    if(fileName.equals(x.getPhotoName())) {
                        return false;
                    }
                }

                nextAlbum.addPhoto(p);
                previousAlbum.deletePhoto(p);
                return true;
            }
        }
        return false;
    }

    /**
     * remove photo from the album 
     * @param fileName filename
     * @param albumName album name
     * @return
     */
    public boolean removePhoto(String fileName, String albumName) {

        Map<String, Album2> albumHash = currentUser.getAlbumss();

        if(!albumHash.containsKey(albumName)) {
            return false;
        }

        Album2 targetAlbum = albumHash.get(albumName);

        List<photo2> targetPhotos = targetAlbum.getPhoto2List();
        for(photo2 p : targetPhotos) {
            if(fileName.equals(p.getPhotoName())) {
                boolean retVal = targetAlbum.deletePhoto(p);

                //The photo is no longer in any albums, so remove it from users photos list
                if(retVal)
                {
                    Map<String, photo2> photos = currentUser.getPhotos();
                    currentUser.setTotalPhotos(currentUser.getTotalPhotos()-1);
                    photos.remove(fileName);
                }

                return retVal;
            }
        }

        return false;
    }

    /**
     * recaption photo
     * @param fileName filename
     * @param newCaption caption
     * @return if recaptioned
     */
    public boolean recaptionPhoto(String fileName, String newCaption) {
        boolean retVal = false;
        photo2 p = currentUser.getPhotos().get(fileName);
        if(p != null)
        {
            p.setCaption(newCaption);
            retVal = true;
        }
        return retVal;
    }

    /**
     * add tag
     * @param fileName filename
     * @param tag tag
     * @return true if added
     */
    public boolean addTag(String fileName, String tag) {

        boolean retVal = false;

        Map<String, photo2> photos = currentUser.getPhotos();

        for(String key : photos.keySet()) {
            if(key.equals(fileName)) {
                Tag newTag = tagFromString(tag);
                if(newTag != null) {
                    photo2 photo = photos.get(fileName);
                    List<Tag> currentTags = photo.getTags();

                    if(newTag.getTagType().toLowerCase().equals("location")) {
                        boolean alreadyHasLocationTag = false;
                        for(Tag t : currentTags) {
                            if(t.getTagType().toLowerCase().equals("location")) {
                                alreadyHasLocationTag = true;
                                break;
                            }
                        }
                        if(!alreadyHasLocationTag) {
                            retVal = photo.addTag(newTag);
                        }
                    }
                    else {
                        retVal = photo.addTag(newTag);
                    }
                }

                break;
            }
        }

        return retVal;
    }

    private Tag tagFromString(String tagStr) {
        Tag retTag = null;

        String[] s = tagStr.split(":");
        if(s.length > 2) {
            StringBuilder reconstruct = new StringBuilder();
            for(int i = 1; i < s.length; i++) {
                reconstruct.append(s[i]);
            }
            s[1] = reconstruct.toString();
        }

        if(s.length > 1) {
            retTag = new Tag(s[0], s[1]);
        }

        if(s.length == 1){
            retTag = new Tag(s[0],s[0]);
        }

        return retTag;
    }

    /**
     * delete tag
     * @param fileName filename
     * @param tag tag
     * @return true if deleted
     */
    public boolean deleteTag(String fileName, String tag) {

        boolean retVal = false;

        Map<String, photo2> photos = currentUser.getPhotos();

        for(String key : photos.keySet())
        {
            if(key.equals(fileName))
            {
                Tag deleteTag = tagFromString(tag);
                if(deleteTag != null)
                {
                    photo2 photo = photos.get(fileName);
                    retVal = photo.removeTag(deleteTag);
                }
                break;
            }
        }

        return retVal;
    }

//    /**
//     * list photo info
//     * @param fileName filename
//     * @return
//     */
//    public String listPhotoInfo(String fileName) {
//        return null;
//    }

    /**
     * get photos by date
     * @param startdate start date
     * @param enddate end date
     * @return photos
     * @throws ParseException
     */
    public List<photo2> getPhotosByDate(String startdate, String enddate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startCal = new GregorianCalendar();
        Calendar endCal = new GregorianCalendar();


        Date starting = df.parse(startdate);
        Date ending = df.parse(enddate);

        startCal.setTime(starting);
        startCal.set(Calendar.MILLISECOND,0);
        endCal.setTime(ending);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND,999);

        Map<String, photo2> photos = currentUser.getPhotos();


        List<photo2> photolist = new ArrayList<photo2>();

        if(ending.before(starting)) {
            return photolist;
        }

        for(String key : photos.keySet()) {
            photo2 p = photos.get(key);
            if((p.getDateAndTime().compareTo(startCal) == 0 || p.getDateAndTime().compareTo(startCal) > 0)
                    && ( p.getDateAndTime().compareTo(endCal) == 0 || p.getDateAndTime().compareTo(endCal) < 0)){
                photolist.add(p);
            }

        }
        return photolist;
    }
    /**
     * get photos by date
     * @param startdate start date
     * @return photos
     * @throws ParseException
     */
    public List<photo2> getPhotosByDate(String startdate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startCal = new GregorianCalendar();
        Calendar endCal = new GregorianCalendar();


        Date starting = df.parse(startdate);
        Date ending = df.parse(startdate);

        startCal.setTime(starting);
        startCal.set(Calendar.MILLISECOND,0);
        endCal.setTime(ending);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND,999);

        Map<String, photo2> photos = currentUser.getPhotos();


        List<photo2> photolist = new ArrayList<photo2>();



        for(String key : photos.keySet()) {
            photo2 p = photos.get(key);
            if(p.getDateAndTime().compareTo(startCal) > 0 && p.getDateAndTime().compareTo(endCal) < 0){
                photolist.add(p);
            }

        }

        return photolist;
    }


    /**
     * gets photos by tag
     * @param tags tags
     * @return photos
     */
    public List<photo2> getPhotosByTag(List<String> tags) {
        List<photo2> taggedPhotos = new ArrayList<photo2>();

        Map<String,photo2> photos = currentUser.getPhotos();
        List<Tag> listOfTags = new ArrayList<Tag>();

        for (String tag : tags) {
            Tag t = tagFromString(tag);
            if (t != null)
                listOfTags.add(t);
        }

        for(String key : photos.keySet()){
            int matches = 0;
            photo2 p = photos.get(key);
            for(Tag t : listOfTags){
                for(Tag t2 : p.getTags()){
                    if(t.equals(t2)){
                        matches++;
                        break;
                    }
                }
            }
            if(matches == listOfTags.size()){
                taggedPhotos.add(p);
            }
        }
        return taggedPhotos;

    }

}
