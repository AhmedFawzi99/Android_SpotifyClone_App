package com.example.spotifyclone;

import android.animation.TypeEvaluator;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;
/**
 * @author  shaimaa
 * this is the class of artist page
 */
public class EachArtist extends Fragment {
    String name;
    String image;
    Artist artist;
    private RecyclerView.RecycledViewPool recycledViewPool2;
    private ArtistFragment artistFragment=  new ArtistFragment();
    private ImageView btn_more;
    private boolean Follow;
    private ArrayList<Artist> Fansalsolike= new ArrayList<Artist>();
    ArrayList<Type> playlists = new ArrayList<>();
    static ArrayList<Type> array = new ArrayList<>();
    private TextView textView1;
    private onClickInterface onclickInterface;
    EachArtist(Artist artist)
    {
        this.artist=artist;
//        array=artist.getPopular_releases();
        name=artist.getName();
        image=artist.getImage();
//        Fansalsolike=artist.getFANSALSOLIKE();
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

    /**
     * the main functions
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View s=inflater.inflate(R.layout.each_artist,container,false);
        fillingfansalsolike();
        fillingpopularreleases();
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
        /**
         * filling the popular releases
         */
        ////////////////////////////////popularReleases//////////////
        if(array!=null) {


                recyclerViewpopularreleases = (RecyclerView) s.findViewById(R.id.recycler_viewartist);
                GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                recycledViewPool2 = new RecyclerView.RecycledViewPool();
                recyclerViewpopularreleases.setLayoutManager(mLayoutManager);
                recyclerViewpopularreleases.setItemAnimator(new DefaultItemAnimator());
                myadapter = new Popular_releases_adapter(getContext(), playlists);
                recyclerViewpopularreleases.setAdapter(myadapter);
                recyclerViewpopularreleases.addItemDecoration(new SpaceItemDecoration(2));
                recyclerViewpopularreleases.setAdapter(myadapter);

        }
        /**
         *fans like you
         */
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
    /**
     * showing the message
     */
    public void showfollow(String S ) {
        StyleableToast.makeText(this.getContext(), S, R.style.exampleToast).show();
    }

    /**
     * when a user clicks on follow button
     */
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
    void fillingfansalsolike()
    {
        Artist artist =new Artist("1170522", "Amr Diab", "https://celebborn.com/celeb_image/amr-diab-26592.jpg?fbclid=IwAR1NnxgzRJFNahdrocTRjv1yP2OwMgnZ_Ox5J1wuVZOJN9TPOop3RwTJCZ0",false);
        Artist artist2 =new Artist("1170523", "Tamer Hosny", "https://p16-va-default.akamaized.net/img/musically-maliva-obj/1654423082333189~c5_720x720.jpeg",false);
        Artist artist3 =new Artist("1170524", " Hamza Namira", "https://www.namirahamza.com/wp-content/uploads/2019/03/479.jpeg",false);
        Artist artist4 =new Artist("1170524", " Nancy Ajram", "https://i1.wp.com/www.eg24.news/wp-content/uploads/2020/03/6534651-2015959685.jpg?fit=1000%2C678&ssl=1",false);
        Artist artist1= new Artist("1170524", " Moahmed Foad", "https://nogomistars.com/wallpaper/untitled8.jpg?fbclid=IwAR3Bl2ApUkKDd8VKUaC4sMDRAPZWAO_J-59G6r7y0NYj0OY9bGgvz52JiBo",false);
        Artist artist6= new Artist("1170524", " Elisa", "https://imagevars.gulfnews.com/2018/8/7/1_16a084fe2d8.2263028_1984710504_16a084fe2d8_medium.jpg",false);
        Artist artist5= new Artist("1170524", " Om Kalthom", "https://egyptianstreets.com/wp-content/uploads/2016/02/maxresdefault.jpg",false);
        Fansalsolike.add(artist);
        Fansalsolike.add(artist2);
        Fansalsolike.add(artist3);
        Fansalsolike.add(artist1);
        Fansalsolike.add(artist4);
        Fansalsolike.add(artist5);
        Fansalsolike.add(artist6);
    }
    void fillingpopularreleases()
    {
        Call<ArrayList<Type>> call = RetrofitSingleton.getInstance().getApi().getlist();
        call.enqueue(new Callback<ArrayList<Type>>() {
            @Override
            public void onResponse(Call<ArrayList<Type>> call, Response<ArrayList<Type>> response) {
                if (!response.isSuccessful()) {
                    return;

                }
                ArrayList<Type> types= response.body();
                for (Type track :types)
                {

                    playlists.add(track);


                }
                arraydata(playlists);
            }

            @Override
            public void onFailure(Call<ArrayList<Type>> call, Throwable t) {

            }
        });
    }

    public void arraydata(ArrayList<Type> a){
        array= a;
    }
}