package com.example.sesh.songlibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import android.content.Context;

public interface SongList {
	void setContext(Context ctx);
	void load() throws IOException;
	void store() throws IOException;
	ArrayList<Song> getSongs();
	Song add(String name, String artist, String album, String year);
	ArrayList<Song> update(Song song) throws NoSuchElementException;
	ArrayList<Song> remove(Song song) throws NoSuchElementException;
	int getPos(Song song);
}
