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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNewEmail = (EditText) findViewById(R.id.etNewEmail);
        bNext = (Button) findViewById(R.id.bNext);

        bNext.setOnClickListener(this);
        etNewEmail.addTextChangedListener(registerTextWatcher);

    }

    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (etNewEmail.getText().toString().trim().contains("@") && etNewEmail.getText().toString().trim().endsWith(".com")) {
                bNext.setEnabled(true);
            }

        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void userSignUp() {
        String email = etNewEmail.getText().toString().trim();
        Profile_DATA.mail=email;

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

}