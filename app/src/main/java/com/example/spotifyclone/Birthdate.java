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
/**
 * Birthdate Activity to allow new users to enter their birthdate
 * @author Salma Hazem
 * @version 1.0
 */


public class Birthdate extends AppCompatActivity implements View.OnClickListener {
    /**
     * Buttons fo the user to submit the birth date
     * Date picker to pick the date
     * Assigning selected year, month, day
     */
    DatePicker picker;
    Button btnGet;
    TextView tvw;
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);

    /**
     * Assign Instances
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdate);
        tvw = (TextView) findViewById(R.id.textView1);
        picker = (DatePicker) findViewById(R.id.datePicker1);
        btnGet = (Button) findViewById(R.id.bNext3);
        btnGet.setOnClickListener(this);

    }
    /**
     * Assign the birth date in the date picker to integers to make it easier for checks
     * Calls createbirthDate
     */
    private void userSignUp() {
        int year = picker.getYear();
        int month = picker.getMonth();
        int day = picker.getDayOfMonth();
        Profile_DATA.Date=day+"/"+month+"/"+year;

        Intent intent = new Intent(Birthdate.this, Gender.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    /**
     * Goes to the next activity only if the entered age is more than 21 years old
     * Else, toast an error that age is not allowed
     * Calls userSignUp if bNext button is clicked
     * @param v
     */
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