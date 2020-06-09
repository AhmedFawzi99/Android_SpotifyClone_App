package com.example.spotifyclone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistManagment extends AppCompatActivity {
    private TextView artname;
    public static TextView artplay;
    private TextView artfollo;
    public static TextView artsongs;
    private TextView artpopular;
    private TextView artTlist;
    private TextView artTlikes;
    private de.hdodenhof.circleimageview.CircleImageView imagee;
    private LinearLayout aplayl;
    private LinearLayout asongl;
    public ArrayList<PlaylistResponse> array= new ArrayList<PlaylistResponse>();
    public ArrayList<Track> songarray= new ArrayList<Track>();
    public ArrayList<String> playlistname= new ArrayList<String>();
    public ArrayList<Track> track= new ArrayList<Track>();
    public ArrayList<PlaylistResponse> sentarray= new ArrayList<PlaylistResponse>();
    public static ArrayList<Track> sentsongarray= new ArrayList<Track>();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artistmanagment);
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



        aplayl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artPlaylistFragment art = new artPlaylistFragment(sentarray);
                art.show(getSupportFragmentManager(), "Playlist");

            }
        });
        asongl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalsongs art = new totalsongs(sentsongarray);
                art.show(getSupportFragmentManager(), "Playlist");
            }
        });
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
    public void getPlaylists( ArrayList<PlaylistResponse> a)
    {

        sentarray=a;


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

    public void gettracks(ArrayList<PlaylistResponse> a){
        for(int i = 0; i < a.size(); i++){

            for (int j = 0; j < a.get(i).getTracks().size(); j++)
            {
                songarray.add(a.get(i).getTracks().get(j));
            }
        }
        sentsongarray=songarray;

    }

    @Override
    public void onBackPressed() {
        return;
    }
}
