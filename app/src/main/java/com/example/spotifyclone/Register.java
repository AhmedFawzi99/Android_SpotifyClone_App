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
        String email = etNewEmail.getText().toString().trim();

        Call<ResponseBody> call= jsonPlaceHolderApi.
                createUser(email,"1234567895","salma","1/1/1999", "female");

//            Call<ResponseBody> call = RetrofitClient
//                    .getInstance()
//                    .getApi()
//                    .createEmail(email);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.isSuccessful()){
                        String s = response.body().string();
                        int d = response.code();
                        Toast.makeText(Register.this, "Created", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register.this, createPassword.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(Register.this, "Not Created", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register.this, createPassword.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bNext:
                userSignUp();
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