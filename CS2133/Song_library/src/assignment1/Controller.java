/**
 * Manan patel
 * Hiren Patel
 */

package assignment1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Controller {
    @FXML TableView<Song> songTableView;
    @FXML TableColumn<Song, String> songPlaylist;
    @FXML TextField songInput;
    @FXML TextField artistInput;
    @FXML TextField albumInput;
    @FXML TextField yearInput;
    @FXML TextField songNameEdit;
    @FXML TextField artistEdit;
    @FXML TextField albumEdit;
    @FXML TextField yearEdit;
    @FXML Button addButton;
    @FXML Button deleteButton;
    @FXML Button editButton;
    @FXML Button cancelButton;
    @FXML Label songLabel;
    @FXML Label artistLabel;
    @FXML Label albumLabel;
    @FXML Label yearLabel;
    @FXML Label popLabel;
    @FXML Label loadPlaylistLabel;
    @FXML Button saveButton;

    private String[] songTable = new String[10000];
    private String[] songTable2 = new String[10000];
    int i = 0, j = 0, numSongs = 0, count = 0;
    Formatter file;
    Scanner f;

    final ObservableList<Song> songData = FXCollections.observableArrayList(
            //new Song("HotlineBling", "Drake", "hot", 2015),new Song("aaaa", "Drake", "hot", 2015)
    );

    //initialize the table in GUI
    public void initialize (){
        //add song name to the table in ascending order
        songPlaylist.setSortType(TableColumn.SortType.ASCENDING);
        songTableView.setItems(songData);
        songPlaylist.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        songTableView.getSortOrder().add(songPlaylist);
        setLoadButton();

        //default song details column
        showSongDetails(null);

        //show song detail as you click on the song
        songTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSongDetails(newValue));

        //add delete and edit button event handler
        addButton.setOnAction(e -> onAddClicked());
        deleteButton.setOnAction(e-> {
            boolean yesClicked = alert.display("Alert!!!");
            if(yesClicked)
                onDeleteClicked();
        }) ;
        editButton.setOnAction(e->onEditClicked());


    }

    /*
        deletes the selected song
     */
    public void onDeleteClicked() {
            int index = songTableView.getSelectionModel().getSelectedIndex();
            if(index >=0) {
                ObservableList<Song> songSelected, allSongs;
                allSongs = songTableView.getItems();
                songSelected = songTableView.getSelectionModel().getSelectedItems();
                int ind = songTableView.getSelectionModel().getFocusedIndex();
                Song s = songTableView.getSelectionModel().getSelectedItem();
                for(int i = 0; i < numSongs; i++){
                    if(songTable[i].equals(s.getSongName()) && songTable2[i].equals(s.getArtist())){
                        songTable[i] = ""; songTable2[i] = "";
                        break;
                    }
                }
                songSelected.forEach(allSongs::remove);
                songTableView.requestFocus();
                songTableView.focusModelProperty().get().focus(new TablePosition(songTableView, 0, songPlaylist));
                songTableView.focusModelProperty().get().focusBelowCell();
                if(songTableView.getItems().size()>0){
                    if(ind >= numSongs){
                        songTableView.getSelectionModel().select(ind-1);
                    }else songTableView.getSelectionModel().select(ind);
                }

                popLabel.setText("Song Deleted!");
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No song selected");
                alert.setHeaderText("Select a song before deleting");
                alert.showAndWait();
            }
    }

    /*
        adds the song
     */
    public void onAddClicked(){
            Song newEntry = readArguments();
            if(songInput.getText().isEmpty() || artistInput.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NOOOOOOOOOOO!");
                alert.setHeaderText("Enter Song & Artist name");
                alert.showAndWait();
                return;
            }
            if(!songInput.getText().isEmpty() && !artistInput.getText().isEmpty()) {
                if(isDuplicate(songInput.getText(), artistInput.getText())) return;
                if(yearInput.getText().isEmpty())
                    popLabel.setText("Song Added!");
                else{
                    if(!isInteger(yearInput.getText())){
                        warning();
                        return;
                    }
                    else popLabel.setText("Song Added!");
                }
                numSongs++;
                int sn = setSN(songInput.getText()) ;
                if(sn < 0)songTable[i++] = songInput.getText();
                else songTable[i++] = songInput.getText().substring(0,sn) + "_" + songInput.getText().substring(sn+1);
                sn = setSN(artistInput.getText());
                if(sn < 0)songTable2[j++] = artistInput.getText();
                else songTable2[j++] = artistInput.getText().substring(0, sn) + "_" + artistInput.getText().substring(sn+1);

                songTable2[j++] = songInput.getText();
                songData.add(newEntry);
                songTableView.getSortOrder().add(songPlaylist);
                songPlaylist.setSortType(TableColumn.SortType.ASCENDING);
                songTableView.requestFocus();
                songTableView.focusModelProperty().get().focus(new TablePosition(songTableView, 0, songPlaylist));
                songTableView.focusModelProperty().get().focusBelowCell();
                newIndex(songInput.getText(), albumInput.getText());
                int xxx = newIndex(songInput.getText(),artistInput.getText());
                if(songTableView.getItems().size()>0)
                    songTableView.getSelectionModel().select(xxx);
            }
            songInput.clear();
            artistInput.clear();
            albumInput.clear();
            yearInput.clear();

    }

    public void onCancelClicked(){
        songInput.clear();
        artistInput.clear();
        albumInput.clear();
        yearInput.clear();
        songNameEdit.clear();
        artistEdit.clear();
        albumEdit.clear();
        yearEdit.clear();
        popLabel.setText("Canceled!");
    }

    /*
       read the song arguments for add method
    */
    public Song readArguments(){
        if(albumInput.getText().isEmpty() && yearInput.getText().isEmpty())
            return new Song(songInput.getText(), artistInput.getText());
        else if(yearInput.getText().isEmpty())
            return new Song(songInput.getText(), artistInput.getText(), albumInput.getText());
        else {
            if(isInteger(yearInput.getText()))
                return new Song(songInput.getText(), artistInput.getText(), albumInput.getText(), Integer.parseInt(yearInput.getText()));
            return new Song(songInput.getText(), artistInput.getText(), albumInput.getText());
        }
    }


    /*
        shows the song name, artist, album, and year in song info
     */
    public void showSongDetails(Song song){
        if(song != null){
            songLabel.setText(song.getSongName());
            artistLabel.setText(song.getArtist());
            albumLabel.setText(song.getAlbum());
            yearLabel.setText(String.valueOf(song.getYear()));
        }else{
            songLabel.setText("");
            artistLabel.setText("");
            albumLabel.setText("");
            yearLabel.setText("");
        }
    }


    /*
        when edit button click change the song information
     */
    public void onEditClicked(){
        int index = songTableView.getSelectionModel().getSelectedIndex();
        Song ss = songTableView.getSelectionModel().getSelectedItem();
        String sn = ss.getSongName(), ar = ss.getArtist();
        if(songNameEdit.getText().isEmpty() && artistEdit.getText().isEmpty() && albumEdit.getText().isEmpty() && yearEdit.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOOOOOOOOOOO!");
            alert.setHeaderText("Invalid change!!");
            alert.showAndWait();
            return;
        }
        else if(isDuplicate(songNameEdit.getText(), artistEdit.getText())) return;
        else if(songNameEdit.getText().isEmpty() && !albumEdit.getText().isEmpty() && !artistEdit.getText().isEmpty() && !yearEdit.getText().isEmpty()){
            //songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songLabel.getText(), artistEdit.getText(), albumEdit.getText(), Integer.parseInt(yearEdit.getText())));
            if(isInteger(yearEdit.getText()) == false) {
                warning();return;}
            else
                songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songLabel.getText(), artistEdit.getText(), albumEdit.getText(), Integer.parseInt(yearEdit.getText())));
        }

        else if(albumEdit.getText().isEmpty() && !songNameEdit.getText().isEmpty() && !artistEdit.getText().isEmpty() && !yearEdit.getText().isEmpty()){
            //songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistEdit.getText(), albumLabel.getText(), Integer.parseInt(yearEdit.getText())));
            if(isInteger(yearEdit.getText()) == false) {
                warning();return;}
            else
                songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistEdit.getText(), albumLabel.getText(), Integer.parseInt(yearEdit.getText())));
        }

        else if(artistEdit.getText().isEmpty() && !songNameEdit.getText().isEmpty() && !albumEdit.getText().isEmpty() && !yearEdit.getText().isEmpty()){
            //songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistLabel.getText(), albumEdit.getText(), Integer.parseInt(yearEdit.getText())));
            if(isInteger(yearEdit.getText()) == false) {
                warning(); return;}
            else
                songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistLabel.getText(), albumEdit.getText(), Integer.parseInt(yearEdit.getText())));

        }

        else if(yearEdit.getText().isEmpty() && !songNameEdit.getText().isEmpty() && !albumEdit.getText().isEmpty() && !artistEdit.getText().isEmpty()){
            songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistEdit.getText(), albumEdit.getText(), Integer.parseInt(yearLabel.getText())));
        }


        else if(artistEdit.getText().isEmpty() && albumEdit.getText().isEmpty() && yearEdit.getText().isEmpty()){
            songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistLabel.getText(), albumLabel.getText(), Integer.parseInt(yearLabel.getText())));
        }
        else if(songNameEdit.getText().isEmpty() && albumEdit.getText().isEmpty() && yearEdit.getText().isEmpty()){
            songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songLabel.getText(), artistEdit.getText(), albumLabel.getText(), Integer.parseInt(yearLabel.getText())));
        }
        else if(songNameEdit.getText().isEmpty() && artistEdit.getText().isEmpty() && yearEdit.getText().isEmpty()){
            songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songLabel.getText(), artistLabel.getText(), albumEdit.getText(), Integer.parseInt(yearLabel.getText())));
        }
        else if(songNameEdit.getText().isEmpty() && artistEdit.getText().isEmpty() && albumEdit.getText().isEmpty()){
            if(isInteger(yearEdit.getText()) == false) {
                warning();return;}
            else
                songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songLabel.getText(), artistLabel.getText(), albumLabel.getText(), Integer.parseInt(yearEdit.getText())));
        }
        else if(albumEdit.getText().isEmpty() && yearEdit.getText().isEmpty()){
            songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistEdit.getText(), albumLabel.getText(), Integer.parseInt(yearLabel.getText())));
        }
        else if(artistEdit.getText().isEmpty() && yearEdit.getText().isEmpty()){
            songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistLabel.getText(), albumEdit.getText(), Integer.parseInt(yearLabel.getText())));
        }
        else if(artistEdit.getText().isEmpty() && albumEdit.getText().isEmpty()){
            if(isInteger(yearEdit.getText()) == false) {
                warning();return;}
            else
                songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistLabel.getText(), albumLabel.getText(), Integer.parseInt(yearEdit.getText())));
        }
        else if(songNameEdit.getText().isEmpty() && albumEdit.getText().isEmpty()){
            if(isInteger(yearEdit.getText()) == false) {
                warning();return;}
            else
                songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songLabel.getText(), artistEdit.getText(), albumLabel.getText(), Integer.parseInt(yearEdit.getText())));
        }
        else if(songNameEdit.getText().isEmpty() && yearEdit.getText().isEmpty()){
            songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songLabel.getText(), artistEdit.getText(), albumEdit.getText(), Integer.parseInt(yearLabel.getText())));
        }
        else if(songNameEdit.getText().isEmpty() && artistEdit.getText().isEmpty()){
            if(isInteger(yearEdit.getText()) == false) {
                warning();return;}
            else
                songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songLabel.getText(), artistLabel.getText(), albumEdit.getText(), Integer.parseInt(yearEdit.getText())));
        }
        else {
            if(isInteger(yearEdit.getText()) == false) {
                warning();return;}
            else
                songData.set(songTableView.getSelectionModel().getSelectedIndex(), new Song(songNameEdit.getText(), artistEdit.getText(), albumEdit.getText(), Integer.parseInt(yearEdit.getText())));
        }
        for(int i = 0; i<=numSongs; i++){
            if(songNameEdit.getText().isEmpty() && artistEdit.getText().isEmpty()) break;
            else if(songNameEdit.getText().isEmpty() && !artistEdit.getText().isEmpty()){
                if(songTable2[i].equals(ar)){
                    int snn = setSN(artistEdit.getText()) ;
                    if(snn < 0)songTable2[i] = artistEdit.getText();
                    else songTable2[i] = artistEdit.getText().substring(0,snn) + "_" + artistEdit.getText().substring(snn+1);
                    break;
                }
            }else if(!songNameEdit.getText().isEmpty() && artistEdit.getText().isEmpty()){
                if(songTable[i].equals(sn)){
                    int snn = setSN(songNameEdit.getText()) ;
                    if(snn < 0)songTable[i] = songNameEdit.getText();
                    else songTable[i] = songNameEdit.getText().substring(0,snn) + "_" + songNameEdit.getText().substring(snn+1);
                    break;
                }
            }else {
                //if (songTable[i].equals(sn) && songTable2[i].equals(ar)) {
                    int snn = setSN(songNameEdit.getText()) ;
                    if(snn < 0)songTable[i] = songNameEdit.getText();
                    else songTable[i] = songNameEdit.getText().substring(0,snn) + "_" + songNameEdit.getText().substring(snn+1);
                    snn = setSN(artistEdit.getText()) ;
                    if(snn < 0)songTable2[i] = artistEdit.getText();
                    else songTable2[i] = artistEdit.getText().substring(0,snn) + "_" + artistEdit.getText().substring(snn+1);
                    break;
                //}
            }
        }
        songTableView.getSelectionModel().select(index);
        songTableView.requestFocus();
        popLabel.setText("Song Edited!");
        songNameEdit.clear();
        artistEdit.clear();
        albumEdit.clear();
        yearEdit.clear();
    }
    private static boolean isInteger(String s){
        boolean isValid = false;
        try{
            Integer.parseInt(s);
            isValid = true;
        }catch (NumberFormatException ex){isValid = false;}
            return isValid;
    }

    public void warning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("NOOOOOOOOOOO!");
        alert.setHeaderText("Year INVALID!!!");
        alert.showAndWait();
        return;
    }

    private boolean isDuplicate(String s, String ss){
        for(int k = 0; k<numSongs; k++){
            if(songTable[k].equals(s) && songTable2[k].equals(ss)){
                if(count == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("NOOOOOOOOOOO!");
                    alert.setHeaderText("Duplicate Entry!!!");
                    alert.showAndWait();
                    return true;
                }
                if(count >1) count = 0;
            }
        }
        return false;
    }

    public int newIndex(String song, String artist){
        for(int i = 0; i <= 10000; i++){
            songTableView.getSelectionModel().select(i);
            Song ss = songTableView.getSelectionModel().getSelectedItem();
            if(ss.getSongName().equals(song) && ss.getArtist().equals(artist)){
                return i;
            }
        }
        return  0;
    }

    public void setSaveButton(){
        Formatter file = null;
        int v = songTableView.getSelectionModel().getSelectedIndex();
        if(v<0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOOOOOOOOOOO!");
            alert.setHeaderText("Please add songs in playlist first!!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("MESSAGE!");
        alert.setHeaderText("This will save the playlist in 'playlist.txt' in the project folder and load the \n" +
                "playlist again automatically when you start the program again :)) ");
        alert.showAndWait();
        try {
            if(loadPlaylistLabel.getText().isEmpty())
                file = new Formatter(new File("playlist.txt"));
            else file = new Formatter(loadPlaylistLabel.getText());
            for(int i = 0; i<= numSongs; i++){
                songTableView.getSelectionModel().select(i);
                Song ss = songTableView.getSelectionModel().getSelectedItem();
                if(isDuplicate(ss.getSongName(),ss.getAlbum())) {
                    count++;
                    continue;
                }
                if(ss.getAlbum().isEmpty())
                    file.format(songTable[i] + " " + songTable2[i] + " " + "?" + " " + ss.getYear() + "\n");
                else file.format(songTable[i] + " " + songTable2[i] + " " + ss.getAlbum() + " " + ss.getYear() + "\n");
            }
        } catch (FileNotFoundException e) {}
        popLabel.setText("File Saved :)");
        file.close();
    }

    public void setLoadButton(){
        Scanner file = null;
        try{
            if(loadPlaylistLabel.getText().isEmpty())
                file = new Scanner(new File("playlist.txt"));
            else file = new Scanner(new File(loadPlaylistLabel.getText()));
            String j = null,k = null;
            while(file.hasNext()){
                String s = file.next(), ar = file.next(), al = file.next(), y = file.next();
                if(s.equals(j) && k.equals(ar))
                    break;
                j = s;
                k = ar;
                if(s.equals("null")) continue;
                if(al.isEmpty() || al.equals(" "))
                    al = " ";
                if(!isInteger(y))
                    y = String.valueOf(0);
                Song newSong;
                newSong = new Song(s, ar,al,Integer.parseInt(y));
                songData.add(newSong);
            }
        }catch (Exception e){}
        songTableView.getSelectionModel().select(0);
        songTableView.requestFocus();
        file.close();
    }

    public int setSN(String sn){
        return sn.indexOf(' ');
    }
}
