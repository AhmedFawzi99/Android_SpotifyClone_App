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

import com.muddzdev.styleabletoast.StyleableToast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class EachArtist extends Fragment {
    String name;
    String image;
    Artist artist;
    private ArtistFragment artistFragment=  new ArtistFragment();
    private ImageView btn_more;
    private boolean Follow;
    private ArrayList<Artist> Fansalsolike;
    ArrayList<Type> playlists = new ArrayList<>();
    private TextView textView1;
    private onClickInterface onclickInterface;
    EachArtist(Artist artist)
    {
        this.artist=artist;
        playlists=artist.getPopular_releases();
        name=artist.getName();
        image=artist.getImage();
        Fansalsolike=artist.getFANSALSOLIKE();
        Follow=artist.isFollowing();

    }
    EachArtist(String Artist_name, String imageid, String id)
    {

        Follow=false;
        name=Artist_name;

        image=imageid;

        artist= new Artist(id,name,image,Follow);
    }

    private  Fansalsolike_Adapter recentlyPlayedAdapter;
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

    private Popular_releases_adapter myadapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View s=inflater.inflate(R.layout.each_artist,container,false);
        ImageView imageView2=s.findViewById(R.id.backbuttonartist);
        TextView textView = s.findViewById(R.id.Artistname);
        btn_more= s.findViewById(R.id.btn_more);
        textView1=(TextView)s.findViewById(R.id.followbut) ;
        ImageView imageView= s.findViewById(R.id.artistimage);
        if(artist.isFollowing())
        {
            textView1.setText("Following");
        }
        else
        {
            textView1.setText("Follow");
        }
        textView.setText(name);
        Picasso.with(getContext()).
                load(image)
                .into(imageView);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonfollowAction();
            }
        });
      btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Moredownartist moredownartist=new Moredownartist(artist);
                 moredownartist.show(getFragmentManager(),"DownMoreartist");
            }
        });
        ////////////////////////////////popularReleases//////////////
        if(playlists!=null) {
            recyclerViewpopularreleases = (RecyclerView) s.findViewById(R.id.recycler_viewartist);
            GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
            recyclerViewpopularreleases.setLayoutManager(mLayoutManager);
            recyclerViewpopularreleases.setItemAnimator(new DefaultItemAnimator());
            myadapter = new Popular_releases_adapter(getContext(), playlists);
            recyclerViewpopularreleases.setAdapter(myadapter);
            recyclerViewpopularreleases.addItemDecoration(new SpaceItemDecoration(2));
        }
      /*  ////////////////Feasuring/////////////////////
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
        recyclerViewFeasuring.setItemAnimator(new DefaultItemAnimator());*/
        ///////////////////////////// fans like you/////////////////////
        if(Fansalsolike!=null){
        layoutManager3 = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewrecentlyplayed = s.findViewById(R.id.home_recycler_view_horizontal2);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerViewrecentlyplayed.setLayoutManager(layoutManager3);
        //Toast.makeText(getContext(),, Toast.LENGTH_LONG).show();

        recentlyPlayedAdapter = new Fansalsolike_Adapter(this.getContext(), Fansalsolike);
        recyclerViewrecentlyplayed.setAdapter(recentlyPlayedAdapter);

        recyclerViewrecentlyplayed.setRecycledViewPool(recycledViewPool);
        //recyclerView2.setHasFixedSize(true);
        recyclerViewrecentlyplayed.setNestedScrollingEnabled(false);
        recyclerViewrecentlyplayed.setItemAnimator(new DefaultItemAnimator());}
        return s;
    }
    public void showfollow(String S ) {
        StyleableToast.makeText(this.getContext(), S, R.style.exampleToast).show();
    }

    public void buttonfollowAction(){

        if(artist.isFollowing())
        {
            artist.setFollowing(false);
            textView1.setText("Follow");
            showfollow("Follow");
            artistFragment.checkfollow();
            //////// putlike();

            // showLikeToast(" Added to Liked Songs. ");


        }else{
            textView1.setText("Following");
            showfollow("Following");
            artistFragment.checkfollowadd(artist);
//            notificationManager.notify(1, );
          //  artistFragment.artistslist.remove(artist);
            /////////////deletelike();
            artist.setFollowing(true);
            //    showLikeToast(" Removed from Liked Songs. ");

        }

    }
}
