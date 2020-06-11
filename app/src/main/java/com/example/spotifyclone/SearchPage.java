package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Search pge to redirect the user to the search page to search songs
 * @author Salma Hazem
 * @version 1.0
 */

public class SearchPage extends AppCompatActivity implements View.OnClickListener {

    private Button bSearch;
    private ScrollView mainScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        bSearch = (Button) findViewById(R.id.bSearch);

        bSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSearch:
                startActivity(new Intent(this, Search.class));
                break;
        }
    }

}