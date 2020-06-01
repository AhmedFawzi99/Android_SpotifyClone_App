package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class firstPage extends AppCompatActivity implements View.OnClickListener {

    Button bLogin1, bFacebook, bSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        bLogin1 = (Button) findViewById(R.id.bLogin1);
        bFacebook = (Button) findViewById(R.id.bFacebook);
        bSignup = (Button) findViewById(R.id.bSignup);


        bLogin1.setOnClickListener(this);
        bFacebook.setOnClickListener(this);
        bSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin1:
                startActivity(new Intent(this, Login.class));

                break;
            case R.id.bFacebook:
                startActivity(new Intent(this, facebook.class));

                break;

            case R.id.bSignup:
                startActivity(new Intent(this, Register.class));

                break;
        }
    }
}
