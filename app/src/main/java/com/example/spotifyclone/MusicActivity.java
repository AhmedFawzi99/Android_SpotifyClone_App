package com.example.spotifyclone;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.muddzdev.styleabletoast.StyleableToast;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.spotifyclone.App.CHANNEL_2_ID;

/**
 * Main Music Activity Class Where Music Data Gets,Set and Played <br>
 * Helping Source: <a href="https://www.youtube.com/watch?v=Qr88MxPijN4">https://www.youtube.com/watch?v=Qr88MxPijN4</a> <br>
 * @author Ahmed Mahmoud Fawzi <br>
 * @version 2.0
 */
public class MusicActivity<pubic> extends AppCompatActivity {

    /**
     * Define all Variables needed throughout the Whole Activity
     */
    private static MusicActivity instance;
    private ImageButton btn_more;
    private View parent_view;
    private SeekBar song_progress;
    private ImageButton btn_pause_stop;
    private TextView current_duration;
    private TextView full_duration;
    public long remaining_duration;
    private ImageView music_image;
    private String sID;
    public MediaPlayer media_player;
    private Handler mHandler = new Handler();
    private MusicControl control;
    public TextView name_song;
    public TextView name_artist;
    public TextView name_playlist;
    public TextView name_hit;
    public int song_index;
    public int flag=1;
    private ImageButton btn_share;
    private ImageButton btn_playerdown;
    private ImageButton volumebut;
    private AudioManager audiomanager;
    private SeekBar volume;
    private TextView btn_share2;
    private ImageButton btn_share3;
    private ImageButton next;
    private ImageButton prev;
    public int likeToggle;
    public ImageButton btn_like;
    public int addcount=0;
    public int skipscount=0;
    public ImageButton btn_hide;
    public String imageUrl;
    public String musicUrl;
    private int likeDrawable=R.drawable.baseline_favorite_24;
    private int pauseplayDrawable=R.drawable.pause;
    private NotificationManagerCompat notificationManager;
    private MediaSessionCompat mediaSession;
    private static CountDownTimer addtimer;
    public static int maddtimer=0;
    EachPlaylist playlist=new EachPlaylist("kkkk","aaa","aa");




    /**
     * A Runnable that is used to set the progress with the current position of the song.
     */
    private Runnable mUpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            song_progress.setProgress(media_player.getCurrentPosition());
            MainActivitysha.song_progress2.setProgress(media_player.getCurrentPosition());
            mHandler.postDelayed(this, 50);
        }
    };



    /**
     * The on Start of the MusicActivity.
     */
    @Override
    protected void onStart() { // onStart() of your activity
        super.onStart();
        instance = this;
    }
    /**
     * Getting the Instance of the MusicActivity Class.
     * @return Return the Instance of the MusicActivity.
     */
    public static MusicActivity getInstance(){
        return instance;
    }


    /**
     * The Oncreate Funtion is responsible for assigning the variables needed to each content in the layotu page and creates the activity with the layout.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_music);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        btn_playerdown=findViewById(R.id.btn_playerdown);
        next=findViewById(R.id.btn_forward);
        prev=findViewById(R.id.btn_rewind);
        name_song=findViewById(R.id.song_name);
        name_hit=findViewById(R.id.hit_name);
        name_playlist=findViewById(R.id.playlist_name);
        name_artist=findViewById(R.id.artist_name);
        btn_share=findViewById(R.id.btn_share);
        btn_share2=findViewById(R.id.btn_share2);
        btn_share3=findViewById(R.id.btn_share3);
        volume=findViewById(R.id.volume);
        btn_hide=findViewById(R.id.btn_hide);
        volumebut=findViewById(R.id.volumebut);
        volumeinit();
        btn_more= findViewById(R.id.btn_more);
        parent_view=findViewById(R.id.parent_view);
        btn_like=findViewById(R.id.btn_Like);
        song_progress=findViewById(R.id.song_progress);
        btn_pause_stop=findViewById(R.id.btn_pause_stop);
        current_duration=findViewById(R.id.current_duration);
        full_duration=findViewById(R.id.full_duration);
        music_image=findViewById(R.id.music_image);
        notificationManager = NotificationManagerCompat.from(this);
        mediaSession = new MediaSessionCompat(this, "tag");
        MainActivitysha.barval=1;
        MainActivitysha.barimage.setVisibility(View.VISIBLE);
        MainActivitysha.barpausestop.setVisibility(View.VISIBLE);
        MainActivitysha.barlike.setVisibility(View.VISIBLE);

        final String sender=this.getIntent().getExtras().getString("SENDER_KEY");


        if(sender != null)
        {
            this.receiveData();

        }

        /**
         * this is called when the share button is clicked beging a new intent to share a subject and opens options for the user.
         */
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareFragment share=new ShareFragment();
                share.show(getSupportFragmentManager(),"SHARE");
            }
        }) ;


        /**
         * A click on the down Button calls the onBackPressed
         */
        btn_playerdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }


        });

        /**
         *  A click on the like button triggers the buttonlikeAction
         */
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLikeAction();
            }
        });
        /**
         *  A click on the like button opens the More Fragment
         */
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MorePageDown moreDown=new MorePageDown();
                moreDown.setLikeToggle(likeToggle);
                moreDown.show(getSupportFragmentManager(),"DownMore");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        btn_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev();
            }
        });

        volumebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volume.setVisibility(View.VISIBLE);

            }
        });
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    if(progress==volume.getMax()){
                        volumebut.setImageResource(R.drawable.outline_volume_up_24);
                    }
                    else if (progress==0){
                        volumebut.setImageResource(R.drawable.outline_volume_off_24);
                    }
                    else{
                        volumebut.setImageResource(R.drawable.volumemed);
                    }

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                volume.setVisibility(View.GONE);
            }
        });


    }


    /**
     * A Function Responsible to return to home when pressed on inside the player
     */
    public void onBackPressed() {

        Intent activityIntent = new Intent(MusicActivity.this,MainActivitysha.class);
//        activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(activityIntent);
        overridePendingTransition(R.anim.hold, R.anim.slide_down);

    }



    public void volumeinit(){
        volume.setVisibility(View.GONE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        audiomanager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volume.setMax(audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volume.setProgress(audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }


    /**
     * A Funtion responsible to set the Music Data and Plays it using Android Media Player <br>
     * It gets the needed data and assigns it then sets the path for the music to stream and play <br>
     * The Whole Music Playing is all about this Function after setting the data media player is prepared and then started <br>
     * Also inside the function seekbar listener is called to detect when the user moves the seek bar and then seeks the music to the desired position <br>
     * handlers for seekbar and time changing are called also in function to keep updating. <br>
     * @param position position of the song playlist array
     * @param sName Song Name Parameter
     * @param aName Artist Name Parameter
     * @param pName Playlist Name Parameter
     * @param hName Hit Name Parameter
     * @param iURL  Image Url to Set the photo in the assigned Image View
     * @param mURL  Music Url to stream the music
     * @param isliked the song is liked or no
     * @param id id of the song
     */
    public void setMusicPlayerComponents( int position, String sName, String aName, String pName, String hName, final String iURL, final String mURL, boolean isliked,String id){

        sID=id;
        btn_pause_stop.setImageResource(R.drawable.baseline_pause_circle_filled_24);
        MainActivitysha.barpausestop.setImageResource(R.drawable.barpause);
        song_index=position;
        name_song.setText(sName);
        MainActivitysha.barsonfname.setText(sName);
        MainActivitysha.barartistname.setText(aName);
        name_artist.setText(aName);
        name_playlist.setText(pName);
        name_hit.setText(hName);
        imageUrl=iURL;
        Picasso.with(this).load(imageUrl).into(music_image);
        Picasso.with(this).load(imageUrl).into(MainActivitysha.barimage);
        String path= mURL;
        musicUrl=mURL;
        Uri uri=Uri.parse(path);
        media_player=new MediaPlayer();
        media_player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        if(isliked==true){
            likeToggle=1;
            btn_like.setImageResource(R.drawable.favorite_green);
        }else{
            likeToggle=0;
            btn_like.setImageResource(R.drawable.like);
        }

        try {
            media_player.reset();
            media_player.setDataSource(String.valueOf(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            media_player.prepare();
            media_player.start();
//            media_player.pause();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        addPOP(	5000);

//        btn_pause_stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                media_player.start();
//
//            }
//        });

        control=new MusicControl();
        song_progress.setMax(media_player.getDuration());
        MainActivitysha.song_progress2.setMax(media_player.getDuration());


        Log.d(Profile_DATA.Type, "setMusicPlayerComponents: ");

        /**
         * Checks is the seekbar is moved and then seeks the song to the new place
         */
        song_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    media_player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        updateTimeAndSeekbar();
        /**
         * A click on the paue/play button calls the buttonPlayerAction
         */
        btn_pause_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                buttonPlayerAction();
            }
        });

        media_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {

                next();

            }
        });
        mHandler.postDelayed(mUpdateSeekbar, 0);
        mHandler.post(mUpdateTimeTask);
    }

    /**
     * A Function that listens to the user interaction with the Play/Pause Button changing the Image resource and then stops the music or starts it again. <br>
     * Also inside the function notification Player is updated to change the Play/pause icon also <br>
     * A Handler Remove Call Back is Also Called to stop/continue updating the music seekbar and the time <br>
     * Also the mainactivity widget is updated also
     */
    public void buttonPlayerAction(){
//        TimerClass timer=new TimerClass();
        if(media_player.isPlaying() ){
            pauseplayDrawable=R.drawable.play;
            sendOnChannel2(parent_view,pauseplayDrawable,likeDrawable);
            media_player.pause();
            mHandler.removeCallbacks(mUpdateSeekbar);
            MainActivitysha.barpausestop.setImageResource(R.drawable.barplay);
            btn_pause_stop.setImageResource(R.drawable.baseline_play_circle_filled_24);
//                if(timer.mTimerRunning==1)
//                {
//                    timer.pauseTimer();
//                }
        }else{
            pauseplayDrawable=R.drawable.pause;
            sendOnChannel2(parent_view,pauseplayDrawable,likeDrawable);
            media_player.start();
            mHandler.postDelayed(mUpdateSeekbar, 0);
            btn_pause_stop.setImageResource(R.drawable.baseline_pause_circle_filled_24);
            MainActivitysha.barpausestop.setImageResource(R.drawable.barpause);
            mHandler.post(mUpdateTimeTask);
        }
    }

    /**
     * a function that gets the next song and sets its data
     */
    public void next()
    {
        skipscount++;
        if(Profile_DATA.Type.equals("r")) {
            addcount = addcount + 1;
            if (addcount == 4 && skipscount<6) {
                startActivity(new Intent(MusicActivity.this, Pop.class));
                addcount = 0;
            }
        }
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        media_player.reset();
        if(!(skipscount>6 && Profile_DATA.Type.equals("r"))) {
            song_index = song_index + 1;
            playlist.check();
            if (song_index > EachPlaylist.Songs.size() - 1) {
                song_index = 0;
            }
            Tracks track = playlist.getSONG(song_index);
            setMusicPlayerComponents(song_index, track.getName(), "salmaaa", "Playing from Playlist", "salmaaaaa", track.getImageid(), track.getURL(), track.getIsliked(), track.getId());
            pauseplayDrawable = R.drawable.pause;
            sendOnChannel2(parent_view, pauseplayDrawable, likeDrawable);
        }
        if(skipscount>6 && Profile_DATA.Type.equals("r")){
            startActivity(new Intent(MusicActivity.this, Pop.class));
        }
    }

    /**
     * A function that gets the previous song and sets its data
     */
    public void prev()
    {
        if(Profile_DATA.Type.equals("r")) {
            addcount = addcount + 1;
            if (addcount == 4) {
                startActivity(new Intent(MusicActivity.this, Pop.class));
                addcount = 0;
            }
        }
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        media_player.reset();
        song_index=song_index-1;
        playlist.check();
        if(song_index<0){
            song_index=EachPlaylist.Songs.size()-1;
        }
        Tracks track= playlist.getSONG(song_index);
        setMusicPlayerComponents(song_index,track.getName(),"salmaaa","Playing from Playlist","salmaaaaa",track.getImageid(),track.getURL(),track.getIsliked(),track.getId());
        pauseplayDrawable=R.drawable.pause;
        sendOnChannel2(parent_view,pauseplayDrawable,likeDrawable);
    }


    /**
     * StopTimer is a function Called by the Timer Fragment created and is called when the timer is reached to the assigned time and then stops the music and updates all components.
     */
    public void stopTimer()
    {
        if(media_player.isPlaying() ) {
            pauseplayDrawable=R.drawable.play;
            sendOnChannel2(parent_view,pauseplayDrawable,likeDrawable);
            media_player.pause();
            mHandler.removeCallbacks(mUpdateSeekbar);
            btn_pause_stop.setImageResource(R.drawable.baseline_play_circle_filled_24);
            flag=0;
        }else {
            return;
        }
    }

    /**
     * ReceiveData is a Function responsible for receiving the Track Data sent from MainActivitysha Class and then calls setMusicPlayerComponents Function to start the player.
     */
    public void receiveData()
    {

        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        String url = i.getStringExtra("URL");
        String imageid = i.getStringExtra("IMAGE");
        String id = i.getStringExtra("ID");
        String playlistnamee = i.getStringExtra("PLAYLISTNAME");
        String artist = i.getStringExtra("Artist");
        int position =i.getIntExtra("pos",0);
        boolean isliked=i.getBooleanExtra("isliked",false);
        setMusicPlayerComponents(position,name,artist,"Playing from Playlist",playlistnamee,imageid,url,isliked,id);
    }

    /**
     * A function reponsible to convert the drawable to Bitmap. <br>
     * This Function is used in the notification calling because the notification Large icon needs a bitmap but the music image is a drawable. <br>
     * So this function converts it to transform the Image to be able to be used in Notification. <br>
     * @param drawable   The Drawable Image to be converted
     * @return  Returns a Bitmap
     * Source: <a href="https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap">https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap</a>
     */
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * This Function creates the Notification Player it takes 2 Drawabels and then Creates a notificarion with using NotificationCompat MediaStyle. <br>
     * This Function has 4 Actions like/previous/Play/next/dislike <br>
     * Inside the Fucntion the type for each action is set to be able to tell which action is pressed on and do a response in terms of the type <br>
     * @param v
     * @param drawable    This Drawable is Play/Pause Button Image to be able to update the Image Resource every time
     * @param likeDrawable This Drawable is for the Like Button and it is used to update ethier it is liked or no and set the image resource.
     * Source Took Things From : <a href="https://codinginflow.com/tutorials/android/notifications-notification-channels/part-4-bigpicturestyle-mediastyle">https://codinginflow.com/tutorials/android/notifications-notification-channels/part-4-bigpicturestyle-mediastyle</a>
     * Modified By : Ahmed Mahmoud Fawzi
     */
    public void sendOnChannel2(View v,int drawable,int likeDrawable) {


        Intent activityIntent = new Intent(this,MusicActivity.class);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent play = new Intent(this, NotificationReceiver.class);
        play.setType("ACTION_PLAY");
        PendingIntent playAction = PendingIntent.getBroadcast(this,
                0, play,0);

        Intent next = new Intent(this, NotificationReceiver.class);
        next.setType("ACTION_NEXT");
        PendingIntent nextAction = PendingIntent.getBroadcast(this,
                0, next,0 );

        Intent prev = new Intent(this, NotificationReceiver.class);
        prev.setType("ACTION_PREV");
        PendingIntent prevAction = PendingIntent.getBroadcast(this,
                0, prev,0 );

        Intent like = new Intent(this, NotificationReceiver.class);
        like.setType("ACTION_LIKE");
        PendingIntent likeAction = PendingIntent.getBroadcast(this,
                0, like,0 );


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_spotify)
                .setContentTitle(name_song.getText())
                .setContentText(name_artist.getText())
                .setContentIntent(contentIntent)
                .setLargeIcon(drawableToBitmap(music_image.getDrawable()))
                .addAction(likeDrawable, "Like", likeAction)
                .addAction(R.drawable.notifyprev, "Previous", prevAction)
                .addAction(drawable, "Play", playAction)
                .addAction(R.drawable.notifynext, "Next", nextAction)
                .addAction(R.drawable.minus_circle_outline, "Dislike", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                )
                .setSubText(name_playlist.getText())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(false)
                .setShowWhen(false)
                .build();

        notificationManager.notify(1, notification);
    }


    /**
     * A getter for the likeToggle which acts as a flag to weather the Track is liked or no
     * @return
     */
    int getLikeToggle(){
        return likeToggle;
    }
    /**
     * A setter for the likeToggle which acts as a flag to weather the Track is liked or no
     * @param a  An integr either 1/0
     */
    void setLikeToggle(int a){

        likeToggle=a;
    }

    /**
     * A toast that shows when the like button is pressed Indicating what happened now (added to liked or Removed )
     * @param S     A String for the Toast
     */
    public void showLikeToast(String S ) {
        StyleableToast.makeText(this, S, R.style.exampleToast).show();
    }



    /**
     * This Function is Called when a like button has been pressed. <br>
     * It sets the Player like Button and the notification like to either liked or unliked and it also sets the liktoggle flag to 1 which means that it is liked. <br>
     * I also show a toast using the showliketoast function. <br>
     */
    public void buttonLikeAction(){

        if(getLikeToggle()==0)
        {
            likeDrawable=R.drawable.favorite_green;
            btn_like.setImageResource(R.drawable.favorite_green);
            sendOnChannel2(parent_view,pauseplayDrawable,likeDrawable);
            MainActivitysha.barlike.setImageResource(R.drawable.favorite_green);
            putlike();
            likeToggle=1;
            showLikeToast(" Added to Liked Songs. ");


        }else{
            likeDrawable=R.drawable.baseline_favorite_24;
            btn_like.setImageResource(R.drawable.like);
            sendOnChannel2(parent_view,pauseplayDrawable,likeDrawable);
//            notificationManager.notify(1, );
            MainActivitysha.barlike.setImageResource((R.drawable.like));
            deletelike();
            likeToggle=0;
            showLikeToast(" Removed from Liked Songs. ");

        }

    }


    /**
     * A runnable that is used to update the time while the music is playing.
     */
    private Runnable mUpdateTimeTask=new Runnable() {
        @Override
        public void run() {
            updateTimeAndSeekbar();
            if(media_player.isPlaying()){
                mHandler.postDelayed(this, 100);
            }
        }
    };

    /**
     * This Function is Used to update the time and is called in the runnable. <br>
     * It uses a object to MusicControl Class to transform the Milliseconds to Time for both the Current duration and the Total Duration. <br>
     */
    private void updateTimeAndSeekbar(){

        long totalDuration= media_player.getDuration();
        long currentDuration=media_player.getCurrentPosition();

        setRemaining(totalDuration-currentDuration);

        full_duration.setText(control.milliSecondsToTIme(totalDuration));
        current_duration.setText(control.milliSecondsToTIme(currentDuration));



    }

    /**
     * function that sets remaining time of playing
     * @param a
     */
    private void setRemaining(long a){
        remaining_duration=a;
    }
    /**
     * function that gets remaining time of playing
     */
    public long getRemaining(){
        return remaining_duration;
    }

    /**
     * This Function Connects to the server and The Song Id variable(sID) to add it as liked in the server
     */
    public void putlike() {

        LikeDislike likee=new LikeDislike(sID);
        Call<LikeDislike> likeee =  RetrofitSingleton.getInstance().getApi().putlike("",likee);

        likeee.enqueue(new Callback<LikeDislike>() {
            @Override
            public void onResponse(Call<LikeDislike> call, Response<LikeDislike> response) {
                if (!response.isSuccessful()) {
                    Log.d("errrrrer", "onResponse: ");
                    return;
                }

                Log.d("update", "onResponse: ");
                LikeDislike l=response.body();
                Log.d(l.getId(), "onResponse: ");

            }

            @Override
            public void onFailure(Call<LikeDislike> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });

    }


    /**
     * This Function Connects to the server and The Song Id variable(sID) to delete it from the liked songs
     */
    public void deletelike() {



        LikeDislike likee=new LikeDislike("7fmVIBMLYiXRtTFOlxv90i");
        Call<GetMessage> likeee =  RetrofitSingleton.getInstance().getApi().deletelike("",likee);

        likeee.enqueue(new Callback<GetMessage>() {
            @Override
            public void onResponse(Call<GetMessage> call, Response<GetMessage> response) {
//                textViewResult.setText("Code: " + response.code());

                if (!response.isSuccessful()) {
                    Log.d("errrrrer", "onResponse: ");
                    return;
                }
                Log.d("delete", "onResponse: ");

            }


            @Override
            public void onFailure(Call<GetMessage> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });

    }

    /**
     * This Function is Called to show adds after certain amount of time
     */
    public void addPOP(final int a ){

        addtimer = new CountDownTimer(a, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MusicActivity.this,Pop.class));
                addPOP(a);
            }
        }.start();
        maddtimer=1;


    }



    /**
     * This Function is Called when the activity is closed reseting the mediaplayer and the notificationplayer also
     */
    @Override
    protected void onDestroy(){

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
//        notificationManager.cancelAll();
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.removeCallbacks(mUpdateSeekbar);
        media_player.release();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }



}
