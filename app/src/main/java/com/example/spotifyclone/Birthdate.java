package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Birthdate extends AppCompatActivity implements View.OnClickListener {
    DatePicker picker;
    Button btnGet;
    TextView tvw;
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdate);
        tvw = (TextView) findViewById(R.id.textView1);
        picker = (DatePicker) findViewById(R.id.datePicker1);
        btnGet = (Button) findViewById(R.id.bNext3);
        btnGet.setOnClickListener(this);

    }
    private void userSignUp() {
        int year = picker.getYear();
        int month = picker.getMonth();
        int day = picker.getDayOfMonth();
        Profile_DATA.Date=day+"/"+month+"/"+year;

        Intent intent = new Intent(Birthdate.this, Gender.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.bNext3):
                btnGet.setEnabled(false);
                if(year - picker.getYear() > 21) {
                    btnGet.setEnabled(true);
                    userSignUp();
                }
                else {
                    Toast.makeText(this, "Age is not allowed", Toast.LENGTH_SHORT).show();
                    btnGet.setEnabled(true);
                }

                break;

        }
    }
}



// tvw.setText("Selected Date: "+ picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear());