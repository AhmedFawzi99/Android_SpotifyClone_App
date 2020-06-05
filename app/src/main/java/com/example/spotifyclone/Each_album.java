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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Each_album extends Fragment {
    String name;
    String image;
   private RecyclerView recyclerViewpopularartists;
   private  ArrayList<Artist> artists = new ArrayList<>();
    private Artist_Adapter myadapter;
    private onClickInterface onclickInterface;
    Each_album(String Artist_name, String imageid, String id)
    {
        name=Artist_name;
        image=imageid;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View s=inflater.inflate(R.layout.each_album,container,false);

        TextView textView = s.findViewById(R.id.textView16);
        ImageView imageView= s.findViewById(R.id.music_image);
        textView.setText(name);
        Picasso.with(getContext()).
                load(image)
                .into(imageView);
        recyclerViewpopularartists = (RecyclerView) s.findViewById(R.id.recycler_viewartist);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerViewpopularartists.setLayoutManager(mLayoutManager);
        recyclerViewpopularartists.setItemAnimator(new DefaultItemAnimator());
        Artist artist =new Artist("1170522", "Amr Diab", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff");
        Artist artist2 =new Artist("1170523", "Tamer Hosny", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff");
        Artist artist3 =new Artist("1170524", " Hamza Namira", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff");

        artists.add(artist);
        artists.add(artist2);
        artists.add(artist3);

        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                // list.remove(abc);
                Toast.makeText(getContext(),"Position is"+abc, Toast.LENGTH_LONG).show();
                Artist r1 = artists.get(abc);
                Fragment selectedFragment=new EachArtist(r1.getName(),r1.getImage(),r1.getId());
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            }
        };
        myadapter = new Artist_Adapter(getContext(), artists,onclickInterface);
        recyclerViewpopularartists.setAdapter(myadapter);
        recyclerViewpopularartists.addItemDecoration(new SpaceItemDecoration(2));
        recyclerViewpopularartists.setClickable(true);

        return s;
}
}
