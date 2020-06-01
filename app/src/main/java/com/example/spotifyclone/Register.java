package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bNext;
    EditText etNewEmail;
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNewEmail = (EditText) findViewById(R.id.etNewEmail);
        bNext = (Button) findViewById(R.id.bNext);

        bNext.setOnClickListener(this);
        etNewEmail.addTextChangedListener(registerTextWatcher);

        // Adding back button
//        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String passwordInput = etNewPass.getText().toString().trim();
//
//            bNext2.setEnabled(!passwordInput.isEmpty());

            if (etNewEmail.getText().toString().trim().contains("@") && etNewEmail.getText().toString().trim().endsWith(".com")) {
                bNext.setEnabled(true);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent= new Intent(this, Register.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    private void userSignUp() {
        Profile_DATA profile=new Profile_DATA();
        String email = etNewEmail.getText().toString().trim();
        profile.setMail(email);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bNext:
                userSignUp();
                Intent intent = new Intent(Register.this, createPassword.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                // startActivity(new Intent(this, createPassword.class));


                break;
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if(id==android.R.id.home) {
//            this.finish();
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}