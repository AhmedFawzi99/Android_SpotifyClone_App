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
import android.os.Build;
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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.palette.graphics.Palette;

import com.muddzdev.styleabletoast.StyleableToast;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.spotifyclone.App.CHANNEL_1_ID;
import static com.example.spotifyclone.App.CHANNEL_2_ID;

public class MusicActivity<pubic> extends AppCompatActivity {

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
    private MediaPlayer media_player;
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
    private TextView btn_share2;
    private ImageButton btn_share3;
    private ImageButton next;
    private ImageButton prev;
    public int likeToggle;
    public ImageButton btn_like;
    public String imageUrl;
    public String musicUrl;
    private int likeDrawable=R.drawable.baseline_favorite_24;
    private int pauseplayDrawable=R.drawable.pause;
    private NotificationManagerCompat notificationManager;
    private MediaSessionCompat mediaSession;
    private static CountDownTimer addtimer;
    public static int maddtimer=0;
    EachPlaylist playlist=new EachPlaylist("kkkk","aaa");





    private Runnable mUpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            song_progress.setProgress(media_player.getCurrentPosition());
            MainActivitysha.song_progress2.setProgress(media_player.getCurrentPosition());
            mHandler.postDelayed(this, 50);
        }
    };




    @Override
    protected void onStart() { // onStart() of your activity
        super.onStart();
        instance = this;
    }
    public static MusicActivity getInstance(){
        return instance;
    }

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

        final String sender=this.getIntent().getExtras().getString("SENDER_KEY");


        if(sender != null)
        {
            this.receiveData();

        }
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareFragment share=new ShareFragment();
                share.show(getSupportFragmentManager(),"SHARE");
            }
        }) ;



        btn_playerdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }


        });
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLikeAction();
            }
        });
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

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev();
            }
        });




    }



    public void onBackPressed() {

        Intent activityIntent = new Intent(MusicActivity.this,MainActivitysha.class);
//        activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(activityIntent);
        overridePendingTransition(R.anim.hold, R.anim.slide_down);

    }





    public void setMusicPlayerComponents( int position, String sName, String aName, String pName, String hName, final String iURL, final String mURL, boolean isliked){

        btn_pause_stop.setImageResource(R.drawable.baseline_pause_circle_filled_24);
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

//        song_progress.getThumb().mutate().setAlpha(0);
//        song_progress.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                return true;
//            }
//        });
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
    public void next()
    {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        media_player.reset();
        song_index=song_index+1;
        playlist.check();
        Tracks track= playlist.getSONG(song_index);
        setMusicPlayerComponents(song_index,track.getName(),"salmaaa","Playing from Playlist","salmaaaaa",track.getImageid(),track.getURL(),track.getIsliked());
        pauseplayDrawable=R.drawable.pause;
        sendOnChannel2(parent_view,pauseplayDrawable,likeDrawable);
    }

    public void prev()
    {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        media_player.reset();
        song_index=song_index-1;
        playlist.check();
        Tracks track= playlist.getSONG(song_index);
        setMusicPlayerComponents(song_index,track.getName(),"salmaaa","Playing from Playlist","salmaaaaa",track.getImageid(),track.getURL(),track.getIsliked());
        pauseplayDrawable=R.drawable.pause;
        sendOnChannel2(parent_view,pauseplayDrawable,likeDrawable);
    }

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
        setMusicPlayerComponents(position,name,artist,"Playing from Playlist",playlistnamee,imageid,url,isliked);
        sID=id;
    }

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
                        .setMediaSession(mediaSession.getSessionToken()))
                .setSubText(name_playlist.getText())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .setShowWhen(false)
                .build();

        notificationManager.notify(1, notification);
    }
    int getLikeToggle(){
        return likeToggle;
    }
    void setLikeToggle(int a){

        likeToggle=a;
    }

    public void showLikeToast(String S ) {
        StyleableToast.makeText(this, S, R.style.exampleToast).show();
    }



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


    private Runnable mUpdateTimeTask=new Runnable() {
        @Override
        public void run() {
            updateTimeAndSeekbar();
            if(media_player.isPlaying()){
                mHandler.postDelayed(this, 100);
            }
        }
    };

    private void updateTimeAndSeekbar(){

        long totalDuration= media_player.getDuration();
        long currentDuration=media_player.getCurrentPosition();

        setRemaining(totalDuration-currentDuration);

        full_duration.setText(control.milliSecondsToTIme(totalDuration));
        current_duration.setText(control.milliSecondsToTIme(currentDuration));



    }

    private void setRemaining(long a){
        remaining_duration=a;
    }
    public long getRemaining(){
        return remaining_duration;
    }


    public void putlike() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);



        likeDislike likee=new likeDislike(sID);
        Call<getmessage> likeee = jsonPlaceHolderApi.putlike("",likee);

        likeee.enqueue(new Callback<getmessage>() {
            @Override
            public void onResponse(Call<getmessage> call, Response<getmessage> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Log.d("update", "onResponse: ");
//                getmessage message=response.body();
//                String content = "";
//                content += "Code:" + response.code()+"\n";
//                content += "message: " + message.getMessage();
//                textViewResult.setText(content);

            }


            @Override
            public void onFailure(Call<getmessage> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });

    }

    public void deletelike() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        likeDislike likee=new likeDislike(sID);
        Call<getmessage> likeee = jsonPlaceHolderApi.deletelike("",likee);

        likeee.enqueue(new Callback<getmessage>() {
            @Override
            public void onResponse(Call<getmessage> call, Response<getmessage> response) {
//                textViewResult.setText("Code: " + response.code());

                Log.d("delete", "onResponse: ");
//                getmessage message=response.body();
//                String content = "";
//                content += "Code:" + response.code()+"\n";
//                content += "message: " + message.getMessage();
//                textViewResult.setText(content);
            }


            @Override
            public void onFailure(Call<getmessage> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });

    }

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





    @RequiresApi(api = Build.VERSION_CODES.O)
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
