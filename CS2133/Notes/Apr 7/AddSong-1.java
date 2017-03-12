package com.example.sesh.songlibrary;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddSong extends AppCompatActivity {

	public static final String SONG_NAME = "songName";
	public static final String SONG_ARTIST = "songArtist";
	public static final String SONG_ALBUM = "songAlbum";
	public static final String SONG_YEAR = "songYear";
	
	EditText songName, songArtist, songAlbum, songYear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_song);
		
		songName = (EditText)findViewById(R.id.song_name);
		songArtist = (EditText)findViewById(R.id.song_artist);
		songAlbum = (EditText)findViewById(R.id.song_album);
		songYear = (EditText)findViewById(R.id.song_year);
	}

	// called when the user taps the Save button
	public void save(View view) {
		// gather all data
		String name = songName.getText().toString();
		String artist = songArtist.getText().toString();
		String album = songAlbum.getText().toString();
		String year = songYear.getText().toString();
		
		// name and artist are mandatory
		if (name == null || name.length() == 0 ||
				artist == null || artist.length() == 0) {


			Toast
			.makeText(this, "Name and Artist are required", Toast.LENGTH_SHORT)
			.show();
			

			return;   // does not quit activity, just returns from method
		}
		
		// make Bundle
		Bundle bundle = new Bundle();
		bundle.putString(SONG_NAME,name);
		bundle.putString(SONG_ARTIST,artist);
		bundle.putString(SONG_ALBUM,album);
		bundle.putString(SONG_YEAR,year);

		// send back to caller
		Intent intent = new Intent();
		intent.putExtras(bundle);
		
		setResult(RESULT_OK,intent);
		finish(); // pops the activity from the call stack, returns to parent
	}
	
	// called when the user taps the Cancel button
	public void cancel(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}

}
