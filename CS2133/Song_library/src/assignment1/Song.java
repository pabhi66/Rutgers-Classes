
package assignment1;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class for Song
 */
/**
 * Manan patel
 * Hiren Patel
 */
public class Song {

    private final StringProperty songName;
    private final StringProperty artist;
    private final StringProperty album;
    private final IntegerProperty year;

    public Song() {
        this(null, null);
    }

    public Song(String songName, String artist){
        this.songName = new SimpleStringProperty(songName);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty("");
        this.year = new SimpleIntegerProperty(0);
    }
    public Song(String songName, String artist, String album){
        this.songName = new SimpleStringProperty(songName);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.year = new SimpleIntegerProperty(0);
    }
    public Song(String songName, String artist, int year){
        this.songName = new SimpleStringProperty(songName);
        this.artist = new SimpleStringProperty(artist);
        this.year = new SimpleIntegerProperty(year);
        this.album = new SimpleStringProperty("");
    }
    public Song(String songName, String artist, String album,int year){
        this.songName = new SimpleStringProperty(songName);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.year = new SimpleIntegerProperty(year);
    }

    public String getSongName() {
        return songName.get();
    }

    public StringProperty songNameProperty() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName.set(songName);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getAlbum() {
        return album.get();
    }

    public StringProperty albumProperty() {
        return album;
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public String getArtist() {
        return artist.get();
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

}
