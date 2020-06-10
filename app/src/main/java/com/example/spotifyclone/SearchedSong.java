package com.example.spotifyclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
