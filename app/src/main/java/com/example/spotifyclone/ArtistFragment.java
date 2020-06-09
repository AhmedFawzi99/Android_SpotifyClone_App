package com.example.spotifyclone;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 * @author Shaimaa Osama
 */
public class ArtistFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<Artist> artistslist = new ArrayList<>();
    private Artist_Adapter myadapter;
    private int REQUEST_CODE = 0;
    private onClickInterface onclickInterface;
    public ArtistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View s=inflater.inflate(R.layout.fragment_artist,container,false);

        TextView   textViewResult=(TextView) s.findViewById(R.id.text_view_resulttext);
        FloatingActionButton floatingActionButton=(FloatingActionButton) s.findViewById(R.id.text_view_result2);
        textViewResult.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                DialogFragment builder= Choose_Artist.newInstance();
                hey(builder);

                builder.show(getFragmentManager(), "tag");
                Toast.makeText(getContext(), " shaimaa ", Toast.LENGTH_LONG).show();
            }


        });
        floatingActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment builder= Choose_Artist.newInstance();
                hey(builder);
                builder.show(getFragmentManager(), "tag");
                Toast.makeText(getContext(), " shaimaa ", Toast.LENGTH_LONG).show();
            }
        });

        recyclerView = (RecyclerView) s.findViewById(R.id.recycler_viewartist);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

/*
        Artist artist =new Artist("1170522", "Amr Diab", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff");
        Artist artist2 =new Artist("1170523", "Tamer Hosny", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff");
        Artist artist3 =new Artist("1170524", " Hamza Namira", "https://i.scdn.co/image/ab67616d00001e0219ab0403aa0de6ee32b101ff");
*/
/*
        artistslist.add(artist);
        artistslist.add(artist2);
        artistslist.add(artist2);
        artistslist.add(artist2);
        artistslist.add(artist2);
        artistslist.add(artist3);
        artistslist.add(artist3);
        artistslist.add(artist3);
        artistslist.add(artist3);
        artistslist.add(artist);
        artistslist.add(artist);
        artistslist.add(artist2);
        artistslist.add(artist3);
        artistslist.add(artist2);

*/onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                // list.remove(abc);
                Toast.makeText(getContext(),"Position is"+abc, Toast.LENGTH_LONG).show();
                Artist r1 = artistslist.get(abc);
                Fragment selectedFragment=new EachArtist(r1.getName(),r1.getImage(),r1.getId());
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            }
        };
        myadapter = new Artist_Adapter(getContext(), artistslist,onclickInterface);
        recyclerView.setAdapter(myadapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(2));
        recyclerView.setClickable(true);
     /*   recyclerView.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Artist r1 = artistslist.get(position);
                Fragment selectedFragment=new EachArtist(r1.getName(),r1.getImage(),r1.getId());
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            }
        });*/
        return  s;
        /*    @Override
            public void onClick(View v) {
                DialogFragment builder2 = Choose_Artist.newInstance();
          //    builder2.setTargetFragment(getParentFragment(), REQUEST_CODE);
                builder2.show(getFragmentManager(), "tag");
            }});
        floatingActionButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                DialogFragment builder2 = Choose_Artist.newInstance();
              //  builder2.setTargetFragment(getParentFragment(), REQUEST_CODE);
                builder2.show(getFragmentManager(), "tag");
            }});
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist, container, false);
    }/*

         */




    }
    public void hey(DialogFragment builder2)
    {
        builder2.setTargetFragment(this, REQUEST_CODE);
    }
    public void onActivityResult( int requestCode, int resultCode, Intent data) {
        String editTextString = "shread";
        Toast.makeText(this.getContext(), editTextString, Toast.LENGTH_LONG).show();
        // Make sure fragment codes match up
        int listsize = data.getIntExtra("size", 0);
        for (int i = 0; i < listsize; i++) {
            String name = data.getStringExtra(
                    "Name" + i);
            String imageid = data.getStringExtra(
                    "image" + i);
            String id = data.getStringExtra(
                    "id" + i);
            artistslist.add(new Artist(id,name,imageid));

        }
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

}