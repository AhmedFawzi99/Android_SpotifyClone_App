package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {


    Button bLogin,bforgot;
    private EditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bforgot = (Button) findViewById(R.id.bforgot);

        bLogin.setOnClickListener(this);
        bforgot.setOnClickListener(this);

        etUsername.addTextChangedListener(emailTextWatcher);
        etPassword.addTextChangedListener(emailTextWatcher);

        // Adding back button
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private static String token;

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent= new Intent(this, MainActivitysha.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private void userLogin(){
        final String email = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if(email.isEmpty()) {
            etUsername.setError("Email is required");
            etUsername.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etUsername.setError("Enter a valid email");
            etUsername.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }
        if(password.length() < 8) {
            etPassword.setError("Password should be at least 8 character long");
            etPassword.requestFocus();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<List<LoginResponse>> call = jsonPlaceHolderApi.userLogin();
        call.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {

                Log.d(response.message(), "onResponse: ");
                List<LoginResponse> login= response.body();


                for (LoginResponse log :login)
                {
                    String mail=log.getlEmail();
                    String pass=log.getlPassword();
                    if((email.equals(mail)) && (pass.equals(password)))
                    {
                        Profile_DATA.mail=log.getlEmail();
                        Profile_DATA.password=log.getlPassword();
                        Profile_DATA.date=log.getlBirthDate();
                        Profile_DATA.gender=log.getlGender();
                        Profile_DATA.userName=log.getlName();

                        Intent intent = new Intent(Login.this, MainActivitysha.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

    }

    private TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = etUsername.getText().toString().trim();
            String passwordInput = etPassword.getText().toString().trim();

            bLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogin:
                userLogin();
                //startActivity(new Intent(this, LoggedIn.class));


                break;
            case R.id.bforgot:


                break;
        }
    }


}