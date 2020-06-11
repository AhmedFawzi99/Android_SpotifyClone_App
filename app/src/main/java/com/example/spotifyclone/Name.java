package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Name Activity to allow new users to add their names
 * @author Salma Hazem
 * @version 1.0
 */

public class Name extends AppCompatActivity implements View.OnClickListener {

    /**
     * Buttons fo the user to submit the name
     * Edit text to write the name in it
     */
    private Button bCreate;
    private EditText etName;

    /**
     * Assign Instances
     * @param savedInstanceState
     */
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
        /**
         * Text watcher that sees what is written in the edit text
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         * Enables the button to be clicked only when edit is not empty
         * @param s
         * @param start
         * @param count
         */
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
    /**
     * Assign the text in the edit texts to Strings to make it easier for checks
     * Calls createName
     */
    private void userSignUp() {
        String name = etName.getText().toString().trim();
        Profile_DATA.UserName=name;
        Profile_DATA.Type="r";
        SignUP signup=new SignUP(Profile_DATA.mail, Profile_DATA.Password,Profile_DATA.Gender, Profile_DATA.Date, Profile_DATA.UserName, Profile_DATA.Type, "ncdisnjcb;dsjb");
        Call<SignUP> call=RetrofitSingleton.getInstance().getApi().createuser(Profile_DATA.mail, Profile_DATA.Password, Profile_DATA.Date,Profile_DATA.Gender, Profile_DATA.UserName, Profile_DATA.Type, "ncdisnjcb;dsjb");
        call.enqueue(new Callback<SignUP>() {
            /**
             * Checks if the email is added in the database, gets the page of the user and go to create password activity
             * If not, Toast an error and go to the createPassword
             * @see CreatePassword
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<SignUP> call, Response<SignUP> response) {
                if(!response.isSuccessful()){
                    Log.d("Errorsigning", "onResponse: ");
                    Log.d(String.valueOf(response.code()), "ismot: ");
                    return;
                }
                Log.d("successyaba", "onResponse: ");

            }

            @Override
            public void onFailure(Call<SignUP>call, Throwable t) {
                Log.d("Errorsigningfail", "onResponse: ");
            }
        });
    }
    /**
     * Calls userSignUp if bCreate button is clicked
     * @param v
     */
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