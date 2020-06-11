package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

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
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//
//        mainScrollView = (ScrollView)findViewById(R.id.scrollView);
//
//        mainScrollView.smoothScrollTo(1,1);
//
//    }
}


