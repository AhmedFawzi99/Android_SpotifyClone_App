package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Gender extends AppCompatActivity implements View.OnClickListener {

    private Button bFemale, bMale;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        bFemale = (Button) findViewById(R.id.bFemale);
        bMale = (Button) findViewById(R.id.bMale);

        bFemale.setOnClickListener(this);
        bMale.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bFemale:
                gender = "Female";
                Profile_DATA.Gender=gender;
                Intent intent = new Intent(Gender.this, Name.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.bMale:
                gender = "Male";
                Profile_DATA.Gender=gender;
                Intent i = new Intent(Gender.this, Name.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
        }


    }

}