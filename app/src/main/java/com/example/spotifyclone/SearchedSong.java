package com.example.spotifyclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Shows the clicked on song in the search
 * @author Salma Hazem
 * @version 1.0
 */

public class SearchedSong extends AppCompatActivity {

    TextView searched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_song);

        searched = (TextView)findViewById(R.id.searched);
        String song = getIntent().getStringExtra("SearchedSong");
        searched.setText(song);
    }
}