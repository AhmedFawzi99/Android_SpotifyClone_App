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

public class createPassword extends AppCompatActivity implements View.OnClickListener {

    private Button bNext2;
    private EditText etNewPass;
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        etNewPass = (EditText) findViewById(R.id.etNewPass);
        bNext2 = (Button) findViewById(R.id.bNext2);

        bNext2.setOnClickListener(this);

        etNewPass.addTextChangedListener(passTextWatcher);

        // Adding back button
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }






    private TextWatcher passTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String passwordInput = etNewPass.getText().toString().trim();
//
//            bNext2.setEnabled(!passwordInput.isEmpty());

            if(etNewPass.getText().toString().length()>=8) {
                bNext2.setEnabled(true);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void userSignUp() {
        String password = etNewPass.getText().toString().trim();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser("s@yah.com", password, "sara", "2/2/1999", "male");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.isSuccessful()){
                        String s = response.body().string();
                        int d = response.code();
                        Toast.makeText(createPassword.this, "Created", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(createPassword.this, Birthdate.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(createPassword.this, "Not Created", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(createPassword.this, Birthdate.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(createPassword.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bNext2:
                userSignUp();



                break;
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if(id==android.R.id.home) {
//            this.finish();
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}