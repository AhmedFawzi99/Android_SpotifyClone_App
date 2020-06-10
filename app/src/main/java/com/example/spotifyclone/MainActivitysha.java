package com.example.spotifyclone;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.spotifyclone.App.CHANNEL_2_ID;

public class MainActivitysha extends AppCompatActivity {
    public ArrayList<Tracks> Songs= new ArrayList<Tracks>();
    ArrayList<RowItem> Rowitems=new ArrayList<RowItem>();
    private TextView textViewResult;
    private NotificationManagerCompat notificationManager;
    public static int checker=0;
    public String sName,aName,pName,hName,iURL,mURL;
    Fragment selectedFragment=null;

    private RelativeLayout barcontrol;
    public static ImageView barimage;
    public static TextView barsonfname;
    public static TextView barartistname;
    public static ImageButton barlike;
    public static ImageButton barpausestop;
    public static SeekBar song_progress2;
    public static int barval;

    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment3 = new LibraryFragment();
    final Fragment fragment4 = new PermiumFragment();
    final Fragment fragment2 = new SearchFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;






    public ArrayList<Tracks> gettrackss()
    {

        Call <ArrayList<Tracks>> call = RetrofitSingleton.getInstance().getApi().gettracks();
        call.enqueue(new Callback<ArrayList<Tracks>>() {

            @Override
            public void onResponse(Call<ArrayList<Tracks>> call, Response<ArrayList<Tracks>> response) {

                if (!response.isSuccessful()) {
                    return;

                }
                ArrayList<Tracks> tracks= response.body();


                for (Tracks track :tracks)
                {
                    String id=track.getId();
                    String name=track.getName();
                    String type=track.getType();
                    String imageid=track.getImageid();
                    String URL=track.getURL();
                    ArrayList<Artist> artists2=track.getArtists();
                    Songs.add(new Tracks(name,type,imageid,URL,id,artists2));


                }

            }

            @Override
            public void onFailure(Call<ArrayList<Tracks>> call, Throwable t) {


            }
        });
        return Songs;
    }
    public void functionF(View view)
    {

        /// LayoutInflater factory = LayoutInflater.from(this);
        //final View textEntryView;
        // textEntryView = factory.inflate(R.layout.floating, null);
        ///   Dialog builder = new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        // builder.setTitle("Give your playlist a name");
        //builder.setMessage("Would you like to continue learning how to use Android alerts?");
        //add plain text


        // add the buttons
      /*  builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                EditText text2=(EditText)findViewById(R.id.editText);

            }
        });*/

///        builder.setContentView(R.layout.floating);

        // create and show the alert dialog

        ///  builder.show();
        DialogFragment builder= FloatingFragment.newInstance();
        builder.show(getSupportFragmentManager(),"tag");

    }
    private  JsonPlaceHolderApi jsonPlaceHolderApi;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        Gson gson=new GsonBuilder().serializeNulls().create();
        BottomNavigationView bottomnav =findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(Profile_DATA.UserName, "Name: ");
        Log.d(Profile_DATA.mail, "Mail: ");
        Log.d(Profile_DATA.Password, "Password: ");
        Log.d(Profile_DATA.Gender, "Gender: ");
        Log.d(Profile_DATA.Date, "Date: ");
        Log.d(Profile_DATA.Type, "Type: ");
        Log.d(Profile_DATA.ID, "ID: ");



        gettrackss();
        fm.beginTransaction().add(R.id.fragment_container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();
        barartistname=findViewById(R.id.barartistname);
        barsonfname=findViewById(R.id.barsongname);
        barimage=findViewById(R.id.barimage);
        barlike=findViewById(R.id.barlike);
        barcontrol=findViewById(R.id.barcontrol);
        barpausestop=findViewById(R.id.barpausestop);
        song_progress2=findViewById(R.id.song_progress2);
        song_progress2.getThumb().mutate().setAlpha(0);
        song_progress2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return true;
            }
        });
        barcontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(barval==0){
                    return;
                }
                else {
                    Intent activityIntent = new Intent(MainActivitysha.this, MusicActivity.class);
                    activityIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(activityIntent);
                    overridePendingTransition(R.anim.slide_up, R.anim.hold);
                }
            }
        });

        barlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicActivity main= MusicActivity.getInstance();
                main.buttonLikeAction();
            }
        });
        barpausestop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicActivity main= MusicActivity.getInstance();
                main.buttonPlayerAction();
            }
        });
        if(barval==0){
            barimage.setImageResource(R.drawable.spotify);
            barsonfname.setText("Welcome Back to Maestro");
            barartistname.setText("!!");
            barlike.setVisibility(View.GONE);
            barpausestop.setVisibility(View.GONE);
        }


    }
    public  ArrayList<RowItem> getplaylists()
    {

        Call <ArrayList<RowItem>> call =  RetrofitSingleton.getInstance().getApi().getplaylists();
        call.enqueue(new Callback<ArrayList<RowItem>>() {
            @Override
            public void onResponse(Call<ArrayList<RowItem>> call, Response<ArrayList<RowItem>> response) {
                if (!response.isSuccessful()) {
                    return;

                }

                ArrayList<RowItem> rowItems = response.body();
                int i ;
                for (RowItem rowItem :rowItems)
                {
                    String id=rowItem.getId();
                    String name=rowItem.getName();
                    String type=rowItem.getType();
                    String imageid=rowItem.getImage();
                    String description=rowItem.getDescription();
                    Rowitems.add(new RowItem(name,id));

                }

                //textViewResult.append(content);



            }



            @Override
            public void onFailure(Call<ArrayList<RowItem>> call, Throwable t) {

            }
        });
        return Rowitems;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.home_item:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.search_item:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.Library_item:


                    selectedFragment = new LibraryFragment();

                    break;
                case R.id.Premium_item:
                    selectedFragment = new PermiumFragment();
                    break;


            }
            return loadfragment(selectedFragment);
        }   };

    private boolean loadfragment(Fragment fragment) {
        //switching fragment
        if(fragment!=null)
        { getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;}
        return false;
    }

    @Override
    protected void onDestroy(){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        return;
    }

}
