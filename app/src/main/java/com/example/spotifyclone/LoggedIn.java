package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoggedIn extends AppCompatActivity implements View.OnClickListener{

    Button bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);


        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.bLogout):
                startActivity(new Intent(this, firstPage.class));



                break;
        }
    }
}
