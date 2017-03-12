package com.example.sesh.songlibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import android.content.Context;

/**
 * This class implements the Singleton pattern: only one instance can be
 * created. 
 * 
 * @author Sesh Venugopal
 *
 */
public class MySongList implements SongList {

	// single instance
	private static MySongList songList=null;
	
	// holds songs in a sorted array list
	private ArrayList<Song> songs;
	
	// keep track of max id dealt out so far
	private int maxId;
	
	// make constructor private for single instance control
	private MySongList() {
		songs = new ArrayList<Song>();
		maxId = -1;
	}
	
	// deal out the singleton
	public static MySongList getInstance() {
		if (songList == null) {
			songList = new MySongList();
		}
		return songList;
	}
	
	public Song add(String name, String artist, String album, String year) {
		// name and artist are mandatory
		if (name == null || artist == null) {
			throw new IllegalArgumentException("Name and artist are mandatory");
		}
		
		// set id to next available
		maxId++;
		
		// create Song object
		Song song = new Song(maxId, name, artist, album, year);
		
		// if this is the first add, it's easy
		if (songs.size() == 0) {
			songs.add(song);
			return song;
		}

		// search in array list and add at correct spot
		int lo=0, hi=songs.size()-1, mid=-1, c=0;
		while (lo <= hi) {
			mid = (lo+hi)/2;
			c = song.compareTo(songs.get(mid));
			if (c == 0) {  // duplicate name
				break;
			}
			if (c < 0) {
				hi = mid-1;
			} else {
				lo = mid+1;
			}
		}
		int pos = c <= 0 ? mid : mid+1;
		// insert at pos
		songs.add(pos,song);
		// write through
		try {
			store();
			return song;
		} catch (IOException e) {
			return null;
		}

	}

	public int getPos(Song song) {
		if (songs.size() == 0) {
			return -1;
		}

		// search in array list for name match, then id match
		int lo=0, hi=songs.size()-1;
		
		while (lo <= hi) {
			int mid = (lo+hi)/2;
			Song lsong = songs.get(mid);
			int c = song.compareTo(lsong);
			if (c == 0) {  // need to compare id
				if (song.id == lsong.id) {
					return mid;
				}
				// check left
				int i=mid-1;
				while (i >= 0) {
					lsong = songs.get(i);
					if (song.compareTo(lsong) == 0 && song.id == lsong.id) {
						return i;
					}
					i--;
				}
				// check right
				i = mid+1;
				while (i < songs.size()) {
					lsong = songs.get(i);
					if (song.compareTo(lsong) == 0 && song.id == lsong.id) {
						return i;
					}
					i++;
				}
				return -1;
			}
			if (c < 0) {
				hi = mid-1;
			} else {
				lo = mid+1;
			}
		}
		return -1;
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void load() throws IOException {
		// TO BE FILLED IN
	}

	public ArrayList<Song> remove(Song song) throws NoSuchElementException {
		int pos = getPos(song);
		if (pos == -1) {
			throw new NoSuchElementException();
		}
		songs.remove(pos);
		return songs;
	}

	public void setContext(Context ctx) {
		// TO BE FILLED IN

	}

	public void store() throws IOException {
		// TO BE FILLED IN

	}

	public ArrayList<Song> update(Song song) throws NoSuchElementException {
		// since name could be updated, best to sequentially search on id
		for (int i=0; i < songs.size(); i++) {
			if (songs.get(i).id == song.id) {
				songs.set(i, song);
				return songs;
			}
		}
		throw new NoSuchElementException();
	}

}
