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

public class Name extends AppCompatActivity implements View.OnClickListener {

    private Button bCreate;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        etName = (EditText) findViewById(R.id.etName);
        bCreate = (Button) findViewById(R.id.bCreate);

        bCreate.setOnClickListener(this);

        etName.addTextChangedListener(passTextWatcher);

    }


    private TextWatcher passTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            if (etName.getText().toString().length() > 0) {
                bCreate.setEnabled(true);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (etName.getText().toString().length() > 0) {
                bCreate.setEnabled(true);

            }
        }
    };
    private void userSignUp() {
        String name = etName.getText().toString().trim();
        Profile_DATA.UserName=name;
        Profile_DATA.Type="r";
        SignUP signup=new SignUP(Profile_DATA.mail, Profile_DATA.Password,Profile_DATA.Gender, Profile_DATA.Date, Profile_DATA.UserName, Profile_DATA.Type, Profile_DATA.ID);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCreate:
                userSignUp();
                Intent intent = new Intent(Name.this, MainActivitysha.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                break;
        }
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