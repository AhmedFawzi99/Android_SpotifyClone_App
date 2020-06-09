package com.example.spotifyclone;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        String method = getIntent().getStringExtra("method");
        ChartFragment chartFragment = new ChartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("method", method);
        chartFragment.setArguments(bundle);

        Log.d("Bar Entries", String.valueOf(method));


        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,chartFragment).commit();
    }
}
