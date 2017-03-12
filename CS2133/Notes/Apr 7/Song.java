package com.example.sesh.songlibrary;

public class Song implements Comparable<Song> {
	public int id;
	public String name;
	public String artist;
	public String album;
	public String year;
	
	public Song(int id, String name, String artist, String album, String year) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public int compareTo(Song other) {

		return name.compareToIgnoreCase(other.name);
	}
	
	public String toString() {   // this is for ListView adapter

		return name + "\n(" + artist + ")";
	}
	
	public String getString() {

		return name + ":" + artist + ":" + album + ":" + year;
	}
}
