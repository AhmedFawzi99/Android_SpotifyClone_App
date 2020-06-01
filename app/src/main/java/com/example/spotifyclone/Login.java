package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://3.137.69.49:3000/user/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

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
//    private  void login(){
//        Login login = new Login();
//        Call<User> call = jsonPlaceHolderApi.userLogin(email,password);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(Login.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
//                    token = response.body().getToken();
//
//                }
//                else{
//                    Toast.makeText(Login.this, "login not correct :(", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(Login.this, "error :(", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }

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
        String email = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

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

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(email,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    SharedPrefManager.getInstance(Login.this)
                            .saveUser(loginResponse.getUser());
                    Intent intent = new Intent(Login.this, MainActivitysha.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(Login.this, "error", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, MainActivitysha.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }


//    loginButton.setPermissions(Arrays.asList("email", "public_profile"));
//
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "facebook:onSuccess"+loginResult);
//
//                AccessToken accessToken = loginResult.getAccessToken();
//                Profile profile = Profile.getCurrentProfile();
//                handleFacebookAccessToken(accessToken);
//
//                Toast.makeText(getApplicationContext(), "Login Success with facebook", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(facebook .this, LoggedIn.class));
//
//               // startActivity(new Intent(this, MainActivity.class));
//                //call new activity here.
//            }

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

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if(id==android.R.id.home) {
//            this.finish();
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
