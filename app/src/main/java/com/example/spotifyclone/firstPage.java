package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The First Page of the Application
 * @author Salma Hazem
 * @version 1.0
 * This is the first screen the user sees
 */
public class firstPage extends AppCompatActivity implements View.OnClickListener {

    /**
     *Buttons for the user to login, login with facebook or sign up
     */
    Button bLogin1, bFacebook, bSignup;

    /**
     * Assign Instances
     * @param savedInstanceState
     */
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

    /**
     * Opens the next activity
     * @see Login
     * @see facebook
     * @see Register
     * @param v
     */
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