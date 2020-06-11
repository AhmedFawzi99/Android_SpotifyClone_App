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


/**
 * Register Activity to allow new users to create email
 * @author Salma Hazem
 * @version 1.0
 */
public class Register extends AppCompatActivity implements View.OnClickListener {

    /**
     * Buttons fo the user to submit the email
     * Edit text to write the email in it
     */
    Button bNext;
    EditText etNewEmail;

    /**
     * Assign Instances
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNewEmail = (EditText) findViewById(R.id.etNewEmail);
        bNext = (Button) findViewById(R.id.bNext);

        bNext.setOnClickListener(this);
        etNewEmail.addTextChangedListener(registerTextWatcher);

    }

    /**
     * Text watcher that sees what is written in the edit text
     */
    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        /**
         * Enables the button to be clicked only when edit texts contains ("@") and ends with (".com")
         * @param s
         * @param start
         * @param count
         */

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

    /**
     * Assign the text in the edit texts to Strings to make it easier for checks
     * Calls createEmail
     */
    private void userSignUp() {
        String email = etNewEmail.getText().toString().trim();
        Profile_DATA.mail=email;

    }

    /**
     * Calls userSignUp if bNext button is clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bNext:
                userSignUp();
                Intent intent = new Intent(Register.this, CreatePassword.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                // startActivity(new Intent(this, createPassword.class));


                break;
        }
    }

}