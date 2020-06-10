package com.example.spotifyclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private TextView user_name, pFollowing,pFollower;
    private de.hdodenhof.circleimageview.CircleImageView user_profile_image;
    Button logout_button;
    private TextView user_play;
    TextView recent_activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user_name = findViewById(R.id.pname);
        user_play =(TextView)findViewById(R.id.pPlaylists);
        logout_button = (Button)findViewById(R.id.logout_button);
        logout_button.setOnClickListener(this);
        user_profile_image = findViewById(R.id.user_profile_image);
        pFollower = findViewById(R.id.pFollower);
        pFollowing = findViewById(R.id.pFollowing);
        recent_activity = (TextView)findViewById(R.id.recent_activity);

        if(PermiumFragment.message == "You are Premium Now") {
            recent_activity.setText(PermiumFragment.message);
            recent_activity.setTextColor(Color.GREEN);
        }
//        } else recent_activity.setText("No Recent Activities Yet");

        Log.d("Message", PermiumFragment.message);




        Call<List<LoginResponse>> call =RetrofitSingleton.getInstance().getApi().userLogin();
        call.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {

                Log.d(response.message(), "Entered: ");
                List<LoginResponse> login= response.body();

                List<LoginResponse> a= response.body();
                for (LoginResponse user :a) {

                        Log.d(response.message(), "Entered: ");
//                         Profile_DATA.UImage = user.getImage();
//                         Profile_DATA.Followers = user.getFollowers();
//                         Profile_DATA.Following = user.getFollowing();

                    Picasso.with(Profile.this).load(Profile_DATA.UImage).into(user_profile_image);

                        user_name.setText(Profile_DATA.UserName);
                        pFollower.setText(Profile_DATA.Followers);
                        pFollowing.setText(Profile_DATA.Following);
                        // Add playlist from playlist fragment
                        user_play.setText(String.valueOf(Artist_DATA.APlaylists));


                }

            }

            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout_button:
                PermiumFragment.message = "You are logged out";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Profile.this)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Activity Notification")
                        .setContentText(PermiumFragment.message)
                        .setAutoCancel(true);
                Intent i = new Intent(getApplicationContext(), firstPage.class);
                String recent = getIntent().getStringExtra("message");
//                recent_activity.setText(recent);
                startActivity(i);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(Profile.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
                finish();
        }
    }
}
