package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Create Password Activity to allow new users to create password
 * @author Salma Hazem
 * @version 1.0
 */

public class createPassword extends AppCompatActivity implements View.OnClickListener {

    /**
     * Buttons fo the user to submit the password
     * Edit text to write the password in it
     */
    private Button bNext2;
    private EditText etNewPass;

    /**
     * Assign Instances
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        etNewPass = (EditText) findViewById(R.id.etNewPass);
        bNext2 = (Button) findViewById(R.id.bNext2);

        bNext2.setOnClickListener(this);

        etNewPass.addTextChangedListener(passTextWatcher);

    }

    /**
     * Text watcher that sees what is written in the edit text
     */

    private TextWatcher passTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         * Enables the button to be clicked only when edit texts contains text with length greater than or equal 8 characters
         * @param s
         * @param start
         * @param count
         */
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
    /**
     * Assign the text in the edit texts to Strings to make it easier for checks
     * Calls createPassword
     */
    private void userSignUp() {
        String password = etNewPass.getText().toString().trim();
        Profile_DATA.Password=password;
        Intent intent = new Intent(createPassword.this, Birthdate.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    /**
     * Calls userSignUp if bNext2 button is clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bNext2:
                userSignUp();

                break;
        }
    }
}