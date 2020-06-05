package com.example.spotifyclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class EachArtist extends Fragment {
    String name;
    String image;
    private onClickInterface onclickInterface;
    EachArtist(String Artist_name, String imageid, String id)
    {
        name=Artist_name;
        image=imageid;
    }
    private ArrayList<RowItem> recentlyplayed;
    private  RecentlyPlayedAdapter recentlyPlayedAdapter;
    private LinearLayoutManager layoutManager3;
    private RecyclerView recyclerViewrecentlyplayed;
    /////////////////////////////
    ArrayList<Type> playlistsFeasuring = new ArrayList<>();
    private RecyclerView recyclerViewFeasuring;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private Player_Adapter_two adapterfeasuring;
    private LinearLayoutManager layoutManager2;
/////////////////////////////////////////
    private RecyclerView recyclerViewpopularreleases;
    ArrayList<RowItem> playlists = new ArrayList<>();
    private Popular_releases_adapter myadapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View s=inflater.inflate(R.layout.each_artist,container,false);

        TextView textView = s.findViewById(R.id.Artistname);
        ImageView imageView= s.findViewById(R.id.music_image);
                textView.setText(name);
        Picasso.with(getContext()).
                load(image)
                .into(imageView);
        ////////////////////////////////popularReleases//////////////
        recyclerViewpopularreleases = (RecyclerView) s.findViewById(R.id.recycler_viewartist);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerViewpopularreleases.setLayoutManager(mLayoutManager);
        recyclerViewpopularreleases.setItemAnimator(new DefaultItemAnimator());
        myadapter = new Popular_releases_adapter(getContext(), playlists);
        recyclerViewpopularreleases.setAdapter(myadapter);
        recyclerViewpopularreleases.addItemDecoration(new SpaceItemDecoration(2));
         ////////////////Feasuring/////////////////////
        layoutManager2 = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFeasuring = s.findViewById(R.id.home_recycler_view_horizontal);
      //  recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerViewFeasuring.setLayoutManager(layoutManager2);
        //Toast.makeText(getContext(),, Toast.LENGTH_LONG).show();
        adapterfeasuring = new Player_Adapter_two(this.getContext(), playlistsFeasuring,onclickInterface);
        recyclerViewFeasuring.setAdapter(adapterfeasuring);

       // recyclerViewFeasuring.setRecycledViewPool(recycledViewPool);
        //recyclerView2.setHasFixedSize(true);
        recyclerViewFeasuring.setNestedScrollingEnabled(false);
        recyclerViewFeasuring.setItemAnimator(new DefaultItemAnimator());
        ///////////////////////////// fans like you/////////////////////
        layoutManager3 = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewrecentlyplayed = s.findViewById(R.id.home_recycler_view_horizontal2);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerViewrecentlyplayed.setLayoutManager(layoutManager3);
        //Toast.makeText(getContext(),, Toast.LENGTH_LONG).show();
        recentlyplayed=new ArrayList<>();
        recentlyPlayedAdapter = new RecentlyPlayedAdapter(this.getContext(), recentlyplayed);
        recyclerViewrecentlyplayed.setAdapter(recentlyPlayedAdapter);

        recyclerViewrecentlyplayed.setRecycledViewPool(recycledViewPool);
        //recyclerView2.setHasFixedSize(true);
        recyclerViewrecentlyplayed.setNestedScrollingEnabled(false);
        recyclerViewrecentlyplayed.setItemAnimator(new DefaultItemAnimator());
        return s;
    }}
