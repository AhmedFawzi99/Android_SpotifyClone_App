package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistManagment extends AppCompatActivity {
    private TextView artname;
    private TextView artplay;
    private TextView artfollo;
    private TextView artsongs;
    private TextView artpopular;
    private TextView artTlist;
    private TextView artTlikes;
    private de.hdodenhof.circleimageview.CircleImageView imagee;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artistmanagment);
        artname=findViewById(R.id.aartname);
        artplay=findViewById(R.id.aplaylists);
        artfollo=findViewById(R.id.afollower);
        artsongs=findViewById(R.id.atotalsongs);
        artpopular=findViewById(R.id.apopularsongs);
        artTlist=findViewById(R.id.atotallisteners);
        artTlikes=findViewById(R.id.atotallikes);
        imagee=findViewById(R.id.artist_profile_image);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<List<ArtistResponse>> call = jsonPlaceHolderApi.artistdata(Profile_DATA.ID);
        call.enqueue(new Callback<List<ArtistResponse>>() {
            @Override
            public void onResponse(Call<List<ArtistResponse>> call, Response<List<ArtistResponse>> response) {

                Log.d(response.message(), "Entered: ");
                List<ArtistResponse> login= response.body();

                List<ArtistResponse> a= response.body();
                for (ArtistResponse art :a)
                {
                    Log.d(response.message(), "Entered: ");
                    Artist_DATA.AID=art.getID();
                    Artist_DATA.Aname=art.getName();
                    Artist_DATA.AFollowers=art.getFollowers();
                    Artist_DATA.APlaylists=art.getPlaylists();
                    Artist_DATA.PS=art.getPS();
                    Artist_DATA.TLikes=art.getTLikes();
                    Artist_DATA.Tlisteners=art.getTlisteners();
                    Artist_DATA.TotalSongs=art.getTotalSongs();
                    Artist_DATA.artimage=art.getImage();
                    Picasso.with(ArtistManagment.this).load(Artist_DATA.artimage).into(imagee);




                    artname.setText(Artist_DATA.Aname);
                    artplay.setText(String.valueOf(Artist_DATA.APlaylists));
                    artfollo.setText(String.valueOf(Artist_DATA.AFollowers));
                    artsongs.setText(String.valueOf(Artist_DATA.TotalSongs));
                    artpopular.setText(Artist_DATA.PS);
                    artTlist.setText(String.valueOf(Artist_DATA.Tlisteners));
                    artTlikes.setText(String.valueOf(Artist_DATA.TLikes));


                }

            }

            @Override
            public void onFailure(Call<List<ArtistResponse>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }

        });




    }
    @Override
    public void onBackPressed() {
        return;
    }
}
