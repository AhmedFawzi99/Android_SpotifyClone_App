package com.example.spotifyclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muddzdev.styleabletoast.StyleableToast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Each_album extends Fragment {
    String name;
    Album album ;
    String image;
    private ImageView btn_more;
    AlbumFragment albumFragment = new AlbumFragment();
   private ImageView like;
   private RecyclerView recyclerViewpopularartists;
   private  ArrayList<Artist> artists = new ArrayList<>();
    private Artist_Adapter myadapter;
    private onClickInterface onclickInterface;
   /* Each_album(String Artist_name, String imageid, String id)
    {
        name=Artist_name;
        image=imageid;
    }*/
Each_album(Album a)
{
album=a;
    name=a.getAlbum_name();
    image=a.getImage_id();
}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View s=inflater.inflate(R.layout.each_album,container,false);
        ImageView imageView2=s.findViewById(R.id.btn_backalbum);
        like =s.findViewById(R.id.btn_Like1);
        btn_more=s.findViewById(R.id.btn_more1);
        TextView textView = s.findViewById(R.id.textView16);
        ImageView imageView= s.findViewById(R.id.album_image);
        textView.setText(name);
        Picasso.with(getContext()).
                load(image)
                .into(imageView);

        if(album.isIsliked())
        {
            like.setImageResource(R.drawable.favorite_green);
        }
        else
        {
            like.setImageResource(R.drawable.like);
        }
        recyclerViewpopularartists = (RecyclerView) s.findViewById(R.id.recycler_viewartist);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerViewpopularartists.setLayoutManager(mLayoutManager);
        recyclerViewpopularartists.setItemAnimator(new DefaultItemAnimator());
        Artist artist =new Artist("1170522", "Amr Diab", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff",false);
        Artist artist2 =new Artist("1170523", "Tamer Hosny", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff",false);
        Artist artist3 =new Artist("1170524", " Hamza Namira", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff",false);

        artists.add(artist);
        artists.add(artist2);
        artists.add(artist3);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonlikealbum();
            }
        });
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                More_Page_Album more_page_playlist=new More_Page_Album(album);
                more_page_playlist.show(getFragmentManager(),"DownMoreplaylist");
            }
        });
        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                // list.remove(abc);
                Toast.makeText(getContext(),"Position is"+abc, Toast.LENGTH_LONG).show();
                Artist r1 = artists.get(abc);
                Fragment selectedFragment=new EachArtist(r1);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).addToBackStack(null).commit();

            }

        };
       /* getView().setFocusableInTouchMode(true);
       getView().requestFocus();
        getView().setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    return true;
                }
                return false;
            }
        } );*/
        myadapter = new Artist_Adapter(getContext(), artists,onclickInterface);
        recyclerViewpopularartists.setAdapter(myadapter);
        recyclerViewpopularartists.addItemDecoration(new SpaceItemDecoration(2));
        recyclerViewpopularartists.setClickable(true);

        return s;
}
    public void showlike(String S ) {
        StyleableToast.makeText(this.getContext(), S, R.style.exampleToast).show();
    }
    public void buttonlikealbum()
    {
        if(album.isIsliked())
        {
            album.setIsliked(false);
            like.setImageResource(R.drawable.like);
            showlike("disliked");
          //>>>>>>  albumFragment.checklike();
            //////// putlike();

            // showLikeToast(" Added to Liked Songs. ");


        }else{
            like.setImageResource(R.drawable.favorite_green);
            showlike("Liked");
          //>>>>>>>>>>>>  albumFragment.checklikeadd(album); after making Array of albums
//            notificationManager.notify(1, );
            //  artistFragment.artistslist.remove(artist);

            album.setIsliked(true);
            //    showLikeToast(" Removed from Liked Songs. ");

        }


    }
}
