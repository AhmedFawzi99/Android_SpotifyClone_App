package com.example.spotifyclone;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.spotifyclone.App.CHANNEL_1_ID;

/**
 * Artist Manager for Atrist's account
 * Helping Source: <a href="https://www.youtube.com/watch?v=sXo2SkX7rGk&t=658s">https://www.youtube.com/watch?v=sXo2SkX7rGk&t=658s</a> <br>
 * License for Circular View Image: <a href="https://github.com/hdodenhof/CircleImageView/blob/master/LICENSE.txt">https://github.com/hdodenhof/CircleImageView/blob/master/LICENSE.txt</a> <br>
 * BieChart License: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a> <br>
 * @authors: Salma Hazem and Ahmed Mahmoud Fawzi
 * @version 1.0
 */
public class ArtistManagment extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    /**
     * Declaring Variables
     */
    private static final int MY_PERMISSION_REQUEST = 1 ;
    private static final int RQS_OPEN_AUDIO_MP3_NEW = 1;
    private TextView artname;
    public static TextView artplay;
    private TextView artfollo;
    public static TextView artsongs;
    private TextView artpopular;
    private TextView artTlist;
    public static int not=0;
    private static Context context;
    private TextView artTlikes;
    private de.hdodenhof.circleimageview.CircleImageView imagee;
    private LinearLayout aplayl;
    private LinearLayout asongl;
    ImageButton imageButton;
    public ArrayList<PlaylistResponse> array= new ArrayList<PlaylistResponse>();
    public ArrayList<Track> songarray= new ArrayList<Track>();
    public ArrayList<String> playlistname= new ArrayList<String>();
    public ArrayList<Track> track= new ArrayList<Track>();
    public static ArrayList<PlaylistResponse> sentarray= new ArrayList<PlaylistResponse>();
    public static ArrayList<Track> sentsongarray= new ArrayList<Track>();
    public static int val = 0;
    public static ArrayList<String> stringnamesoffiles= new ArrayList<String>();
    artPlaylistFragment artt;
    totalsongs art ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artistmanagment);
        ArtistManagment.context = getApplicationContext();


        artname = findViewById(R.id.aartname);
        artplay = findViewById(R.id.aplaylists);
        artfollo = findViewById(R.id.afollower);
        artsongs = findViewById(R.id.atotalsongs);
        artpopular = findViewById(R.id.apopularsongs);
        artTlist = findViewById(R.id.atotallisteners);
        artTlikes = findViewById(R.id.atotallikes);
        imagee = findViewById(R.id.artist_profile_image);
        aplayl = findViewById(R.id.aPlalist);
        asongl = findViewById(R.id.atotalsongss);
        artplay.setText(String.valueOf(Artist_DATA.APlaylists));
        artsongs.setText(String.valueOf(Artist_DATA.TotalSongs));

        imageButton = (ImageButton) findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        /**
         * Spinner for the user to choose the data wanted in which time(day, month, year)
         */
        Spinner spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.spinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getSelectedItemPosition() == 0){
                    getDayListeners("bar");
//                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                } else if(parent.getSelectedItemPosition() == 1){
//                    value = 2;
                    getMonthListeners("bar");
//                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                } else if(parent.getSelectedItemPosition() == 2){
//                    value =3;
                    getYearListeners("bar");
//                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }

                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * Spinner for the user to choose the data wanted in which time(day, month, year)
         */
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.spinner, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getSelectedItemPosition() == 0){
                    getDayLikes("bar");
//                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                } else if(parent.getSelectedItemPosition() == 1){
                    getMonthLikes("bar");
//                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                } else if(parent.getSelectedItemPosition() == 2){
                    getYearLikes("bar");
//                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }

                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        /**
         * show total Album Fragment
         */
        aplayl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                artt.show(getSupportFragmentManager(), "Playlist");

            }
        });
        /**
         * show total Song Fragment
         */
        asongl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                art.show(getSupportFragmentManager(), "Playlist");
            }
        });

        /**
         * Get the artist data from the server
         */
        Call<List<ArtistResponse>> call = RetrofitSingleton.getInstance().getApi().artistdata(Profile_DATA.ID);
        call.enqueue(new Callback<List<ArtistResponse>>() {
            @Override
            public void onResponse(Call<List<ArtistResponse>> call, Response<List<ArtistResponse>> response) {

                Log.d(response.message(), "Entered: ");
                List<ArtistResponse> login = response.body();

                List<ArtistResponse> a = response.body();
                for (ArtistResponse art : a) {
                    Log.d(response.message(), "Entered: ");
                    Artist_DATA.AID = art.getID();
                    Artist_DATA.Aname = art.getName();
                    Artist_DATA.AFollowers = art.getFollowers();

                    Artist_DATA.PS = art.getPS();
                    Artist_DATA.TLikes = art.getTLikes();
                    Artist_DATA.Tlisteners = art.getTlisteners();

                    Artist_DATA.artimage = art.getImage();
                    Picasso.with(ArtistManagment.this).load(Artist_DATA.artimage).into(imagee);


                    artname.setText(Artist_DATA.Aname);

                    artfollo.setText(String.valueOf(Artist_DATA.AFollowers));

                    artpopular.setText(Artist_DATA.PS);
                    artTlist.setText(String.valueOf(Artist_DATA.Tlisteners));
                    artTlikes.setText(String.valueOf(Artist_DATA.TLikes));

                }

            }

            @Override
            public void onFailure(Call<List<ArtistResponse>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }

        });

        /**
         * get the Artist Albums and Songs
         */
        Call<List<PlaylistResponse>> play = RetrofitSingleton.getInstance().getApi().getplaylist(Profile_DATA.ID);
        play.enqueue(new Callback<List<PlaylistResponse>>() {
            @Override
            public void onResponse(Call<List<PlaylistResponse>> call, Response<List<PlaylistResponse>> response) {

                Log.d(response.message(), "Entered: ");

                List<PlaylistResponse> p = response.body();
                for (PlaylistResponse play : p) {
                    Log.d(response.message(), "Entered: ");
                    array.add(new PlaylistResponse(play.getPlayid(), play.getUserassociated(), play.getPlayname(),play.getArtimg(), play.getTracks()));

                }
                getPlaylists(array);
                gettracks(array);

            }

            @Override
            public void onFailure(Call<List<PlaylistResponse>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }

        });

    }

    /**
     * To show the pop up menu that allows the user to logout
     * @param v
     */
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.log_out);
        popup.show();
    }
    /**
     * Draws the bar chart showing the number of listeners in a day
     * Gets the data from the server
     * @param method
     */
    private void getDayListeners(final String method){
        final BarChart mBarChart;
        mBarChart = findViewById(R.id.barChart1);


        Call<List<Listeners>> callChart =  RetrofitSingleton.getInstance().getApi().getListenersInfo("d");
        callChart.enqueue(new Callback<List<Listeners>>() {
            @Override
            public void onResponse(Call<List<Listeners>> call, Response<List<Listeners>> response) {
                if(response.body()!=null){
                    if(method.equals("bar")){
                        List<BarEntry> barEntries = new ArrayList<>();

                        for(Listeners growth : response.body()){
                            barEntries.add(new BarEntry(growth.getTime(), growth.getListeners()));
                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries, "Listeners");
                        barDataSet.setValueTextColor(Color.WHITE);
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(1000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description =  new Description();
                        description.setText("Day Listeners");
                        description.setTextColor(Color.WHITE);
                        description.setTextSize(15);
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Listeners>> call, Throwable t) {

            }
        });
    }
    /**
     * Draws the bar chart showing the number of listeners in a month
     * Gets the data from the server
     * @param method
     */
    private void getMonthListeners(final String method){
        final BarChart mBarChart;
        mBarChart = findViewById(R.id.barChart1);

        Call<List<Listeners>> callChart =  RetrofitSingleton.getInstance().getApi().getListenersInfo("m");
        callChart.enqueue(new Callback<List<Listeners>>() {
            @Override
            public void onResponse(Call<List<Listeners>> call, Response<List<Listeners>> response) {
                if(response.body()!=null){
                    if(method.equals("bar")){
                        List<BarEntry> barEntries = new ArrayList<>();

                        for(Listeners lis : response.body()){
                            barEntries.add(new BarEntry(lis.getTime(), lis.getListeners()));
                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries, "Listeners");
                        barDataSet.setValueTextColor(Color.WHITE);
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(1000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description =  new Description();
                        description.setText("Month Listeners");
                        description.setTextColor(Color.WHITE);
                        description.setTextSize(15);
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Listeners>> call, Throwable t) {

            }
        });
    }

    /**
     * Draws the bar chart showing the number of listeners in a year
     * Gets the data from the server
     * @param method
     */
    private void getYearListeners(final String method){
        final BarChart mBarChart;
        mBarChart = findViewById(R.id.barChart1);

        Call<List<Listeners>> callChart =  RetrofitSingleton.getInstance().getApi().getListenersInfo("y");
        callChart.enqueue(new Callback<List<Listeners>>() {
            @Override
            public void onResponse(Call<List<Listeners>> call, Response<List<Listeners>> response) {
                if(response.body()!=null){
                    if(method.equals("bar")){
                        List<BarEntry> barEntries = new ArrayList<>();

                        for(Listeners growth : response.body()){
                            barEntries.add(new BarEntry(growth.getTime(), growth.getListeners()));
                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries, "Listeners");
                        barDataSet.setValueTextColor(Color.WHITE);
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(1000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description =  new Description();
                        description.setText("Year Listeners");
                        description.setTextColor(Color.WHITE);
                        description.setTextSize(15);
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Listeners>> call, Throwable t) {

            }
        });
    }

    /**
     * Draws the bar chart showing the number of likes in a day
     * Gets the data from the server
     * @param method
     */
    private void getDayLikes(final String method){
        final BarChart mBarChart;
        mBarChart = findViewById(R.id.barChart2);

        Call<List<Likes>> callChart =  RetrofitSingleton.getInstance().getApi().getLikesInfo("d");
        callChart.enqueue(new Callback<List<Likes>>() {
            @Override
            public void onResponse(Call<List<Likes>> call, Response<List<Likes>> response) {
                if(response.body()!=null){
                    if(method.equals("bar")){
                        List<BarEntry> barEntries = new ArrayList<>();

                        for(Likes growth : response.body()){
                            barEntries.add(new BarEntry(growth.getTime(), growth.getLikes()));
                            Log.d("Number of Likes", String.valueOf(growth.getLikes()));
                            Log.d("Years", String.valueOf(growth.getTime()));
                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries, "Likes");
                        barDataSet.setValueTextColor(Color.WHITE);
                        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(1000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description =  new Description();
                        description.setText("Day Likes");
                        description.setTextColor(Color.WHITE);
                        description.setTextSize(15);
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Likes>> call, Throwable t) {

            }
        });
    }
    /**
     * Draws the bar chart showing the number of likes in a month
     * Gets the data from the server
     * @param method
     */
    private void getMonthLikes(final String method){
        final BarChart mBarChart;
        mBarChart = findViewById(R.id.barChart2);

        Call<List<Likes>> callChart =  RetrofitSingleton.getInstance().getApi().getLikesInfo("m");
        callChart.enqueue(new Callback<List<Likes>>() {
            @Override
            public void onResponse(Call<List<Likes>> call, Response<List<Likes>> response) {
                if(response.body()!=null){
                    if(method.equals("bar")){
                        List<BarEntry> barEntries = new ArrayList<>();

                        for(Likes growth : response.body()){
                            barEntries.add(new BarEntry(growth.getTime(), growth.getLikes()));
                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries, "Likes");
                        barDataSet.setValueTextColor(Color.WHITE);
                        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(1000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description =  new Description();
                        description.setText("Month Likes");
                        description.setTextColor(Color.WHITE);
                        description.setTextSize(15);
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Likes>> call, Throwable t) {

            }
        });
    }
    /**
     * Draws the bar chart showing the number of likes in a year
     * Gets the data from the server
     * @param method
     */
    private void getYearLikes(final String method){
        final BarChart mBarChart;
        mBarChart = findViewById(R.id.barChart2);

        Call<List<Likes>> callChart =  RetrofitSingleton.getInstance().getApi().getLikesInfo("y");
        callChart.enqueue(new Callback<List<Likes>>() {
            @Override
            public void onResponse(Call<List<Likes>> call, Response<List<Likes>> response) {
                if(response.body()!=null){
                    if(method.equals("bar")){
                        List<BarEntry> barEntries = new ArrayList<>();

                        for(Likes growth : response.body()){
                            barEntries.add(new BarEntry(growth.getTime(), growth.getLikes()));
                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries, "Likes");
                        barDataSet.setValueTextColor(Color.WHITE);
                        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(1000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description =  new Description();
                        description.setText("Year Likes");
                        description.setTextColor(Color.WHITE);
                        description.setTextSize(15);
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Likes>> call, Throwable t) {

            }
        });
    }


    /**
     * Logs out and show notification that the user is logged out
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.log_out:
                NotificationCompat.Builder builder = new NotificationCompat.Builder(ArtistManagment.this,CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Activity Notification")
                        .setContentText("ssss")
                        .setAutoCancel(true);
                Intent i = new Intent(getApplicationContext(), firstPage.class);
                String recent = getIntent().getStringExtra("message");
//                recent_activity.setText(recent);
                startActivity(i);
                this.finish();
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(ArtistManagment.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
                this.finish();
        }
        return false;
    }


    /**
     * Gets Albums from the server and add it
     * @param a
     */
    public void getPlaylists( ArrayList<PlaylistResponse> a)
    {

        sentarray=a;
        artt = new artPlaylistFragment(sentarray);

        for (int i = 0; i < a.size(); i++) {
            playlistname.add(a.get(i).getPlayname());
            for (int j = 0; j < a.get(i).getTracks().size(); j++)
            {
                track.add(a.get(i).getTracks().get(j));
            }
        }

        Artist_DATA.APlaylists=playlistname.size();
        artplay.setText(String.valueOf(Artist_DATA.APlaylists));
        Artist_DATA.TotalSongs = track.size();
        artsongs.setText(String.valueOf(Artist_DATA.TotalSongs));
    }

    /**
     * Gets songs from the server and add it
     * @param a
     */
    public void gettracks(ArrayList<PlaylistResponse> a){
        for(int i = 0; i < a.size(); i++){

            for (int j = 0; j < a.get(i).getTracks().size(); j++)
            {
                songarray.add(a.get(i).getTracks().get(j));
            }
        }
        sentsongarray=songarray;
        art = new totalsongs(sentsongarray);
    }

    /**
     * Showing Notification when the Artist adds songs or albums
     * @param a
     */
    public static void notffication(int a){
        String Message;
        if (a==1){
            Message = Artist_DATA.Aname +" added new Album check it out !";
        }else{
            Message = Artist_DATA.Aname +" added new Song check it out !";
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getAppContext(),CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Activity Notification")
                .setContentText(Message)
                .setAutoCancel(true);
        Intent i = new Intent(getAppContext(), ArtistManagment.class);
//                startActivity(i);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("message", Message);

        PendingIntent pendingIntent = PendingIntent.getActivity(getAppContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    /**
     * get the context of the app to acces in static functions
     * @return
     */
    public static Context getAppContext() {
        return ArtistManagment.context;
    }

    /**
     * Function to prevent the user to get back to the login page
     */
    @Override
    public void onBackPressed() {
        return;
    }
}
