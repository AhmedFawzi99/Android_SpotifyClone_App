package com.example.spotifyclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.spotifyclone.App.CHANNEL_1_ID;

/**
 * Profile Page showing all its data
 * @author Ahmed Mahmoud Fawzi and Salma Hazem
 * License for Circular View Image: <a href="https://github.com/hdodenhof/CircleImageView/blob/master/LICENSE.txt">https://github.com/hdodenhof/CircleImageView/blob/master/LICENSE.txt</a> <br>
 */
public class Profile extends AppCompatActivity implements View.OnClickListener {
    /**
     * Declaring Variables
     */
    private TextView user_name, pFollowing, pFollower;
    private de.hdodenhof.circleimageview.CircleImageView user_profile_image;
    Button logout_button;
    private TextView user_play;
    TextView recent_activity, recent_activity1, recent_activity2;
    ImageButton help;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user_name = findViewById(R.id.pname);
        user_play = (TextView) findViewById(R.id.pPlaylists);
        logout_button = (Button) findViewById(R.id.logout_button);
        logout_button.setOnClickListener(this);
        user_profile_image = findViewById(R.id.user_profile_image);
        pFollower = findViewById(R.id.pFollower);
        pFollowing = findViewById(R.id.pFollowing);
        help = findViewById(R.id.help_btn);
        recent_activity = (TextView) findViewById(R.id.recent_activity);
        recent_activity1 = (TextView) findViewById(R.id.recent_activity1);
        recent_activity2 = (TextView) findViewById(R.id.recent_activity2);

        if (PermiumFragment.message == "You are Premium Now") {
            recent_activity1.setText(PermiumFragment.message);
            recent_activity.setVisibility(View.GONE);
        }
        if (More_Page_Playlist.like_song == "You Liked a song") {
            recent_activity2.setText(More_Page_Playlist.like_song);
            recent_activity.setVisibility(View.GONE);
        }

        Log.d("Message", PermiumFragment.message);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Help moreDown = new Help();
                moreDown.show(getSupportFragmentManager(), "More");
            }
        });


        /**
         * Gets the data of the user
         */
        Call<List<LoginResponse>> call = RetrofitSingleton.getInstance().getApi().userLogin();
        call.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {

                Log.d(response.message(), "Entered: ");
                List<LoginResponse> login = response.body();

                List<LoginResponse> a = response.body();
                for (LoginResponse user : a) {

                    Log.d(response.message(), "Entered: ");

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
                Log.d("Error", t.getMessage());
            }

        });

    }

    /**
     * For the user to log out and sowing it as a notification
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_button:
                PermiumFragment.message = "You are logged out";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Profile.this, CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Activity Notification")
                        .setContentText(PermiumFragment.message)
                        .setAutoCancel(true);
                Intent i = new Intent(getApplicationContext(), firstPage.class);
                String recent = getIntent().getStringExtra("message");
                startActivity(i);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(Profile.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
                More_Page_Playlist.like_song = "";
                recent_activity2.setText(More_Page_Playlist.like_song);
                recent_activity.setVisibility(View.VISIBLE);

                finish();
        }
    }
}
