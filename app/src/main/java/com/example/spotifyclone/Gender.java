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
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        bFemale = (Button) findViewById(R.id.bFemale);
        bMale = (Button) findViewById(R.id.bMale);

        bFemale.setOnClickListener(this);
        bMale.setOnClickListener(this);

        // Adding back button
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void userSignUp() {

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser("moh@hij.com","789456123", "gatsgbd", "2/2/1999", gender );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.isSuccessful()){
                        String s = response.body().string();
                        int d = response.code();
                        Toast.makeText(Gender.this, "Created", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Gender.this, Name.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(Gender.this, "Not Created", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Gender.this, Name.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Gender.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bFemale:
                gender = "Female";
                Profile_DATA.Gender=gender;
                userSignUp();
                //startActivity(new Intent(this, Name.class));
                break;
            case R.id.bMale:
                gender = "Male";
                Profile_DATA.Gender=gender;
                userSignUp();
                // startActivity(new Intent(this, Name.class));



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