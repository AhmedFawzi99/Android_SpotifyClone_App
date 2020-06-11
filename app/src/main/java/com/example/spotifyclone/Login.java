package com.example.spotifyclone;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
import androidx.core.app.NotificationCompat;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.spotifyclone.App.CHANNEL_1_ID;

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




        Call<List<LoginResponse>> call = RetrofitSingleton.getInstance().getApi().userLogin();
        call.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {

                Log.d(response.message(), "myresponse: ");
                List<LoginResponse> login= response.body();


                for (LoginResponse log :login)
                {
                    String mail=log.getlEmail();
                    String pass=log.getlPassword();
                    String uname=log.getlName();
                    if((email.equals(mail) || email.equals(uname) ) && (pass.equals(password)))
                    {
                        if(log.getlUsertype().equals("r")){
                            Profile_DATA.mail=log.getlEmail();
                            Profile_DATA.ID=log.getlID();
                            Profile_DATA.Password=log.getlPassword();
                            Profile_DATA.Date=log.getlBirthDate();
                            Profile_DATA.Gender=log.getlGender();
                            Profile_DATA.UserName=log.getlName();
                            Profile_DATA.Type=log.getlUsertype();
                            Profile_DATA.Followers=log.getlFollowers();
                            Profile_DATA.Following=log.getlFollowing();
                            Profile_DATA.UImage=log.getlImage();

                            String message = "You are logged in";
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this,CHANNEL_1_ID)
                                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                    .setContentTitle("Activity Notification")
                                    .setContentText(message)
                                    .setAutoCancel(true);
                            Intent i = new Intent(getApplicationContext(), MainActivitysha.class);
                            startActivity(i);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("message", message);

                            PendingIntent pendingIntent = PendingIntent.getActivity(Login.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);

                            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(0, builder.build());
                            finish();

                        }
                        else if(log.getlUsertype().equals("a")){
                            Profile_DATA.ID=log.getlID();
                            String message = "You are logged in";
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this,CHANNEL_1_ID)
                                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                    .setContentTitle("Activity Notification")
                                    .setContentText(message)
                                    .setAutoCancel(true);
                            Intent i = new Intent(getApplicationContext(), ArtistManagment.class);
                            startActivity(i);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("message", message);

                            PendingIntent pendingIntent = PendingIntent.getActivity(Login.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);

                            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(0, builder.build());
                            finish();

                        }else if(log.getlUsertype().equals("pr")){
                            Profile_DATA.mail=log.getlEmail();
                            Profile_DATA.ID=log.getlID();
                            Profile_DATA.Password=log.getlPassword();
                            Profile_DATA.Date=log.getlBirthDate();
                            Profile_DATA.Gender=log.getlGender();
                            Profile_DATA.UserName=log.getlName();
                            Profile_DATA.Type=log.getlUsertype();
                            Profile_DATA.Followers=log.getlFollowers();
                            Profile_DATA.Following=log.getlFollowing();
                            Profile_DATA.UImage=log.getlImage();
                            String message = "You are logged in";
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this,CHANNEL_1_ID)
                                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                    .setContentTitle("Activity Notification")
                                    .setContentText(message)
                                    .setAutoCancel(true);
                            Intent i = new Intent(getApplicationContext(), MainActivitysha.class);
                            startActivity(i);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtra("message", message);

                            PendingIntent pendingIntent = PendingIntent.getActivity(Login.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);

                            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(0, builder.build());
                            finish();
                        }

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

    private void sendMail() {
        // This should be the email saved in the server
        String mail = "aaaa@gmail.com";
        String message = "No Problem, your password will be reset";
        String subject = "Forgot your password?";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);

        javaMailAPI.execute();

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogin:
                userLogin();
                break;

            case R.id.bforgot:
                sendMail();
                break;


        }
    }


}
