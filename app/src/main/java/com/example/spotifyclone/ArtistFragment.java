package com.example.spotifyclone;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass. <br>
 * @author Shaimaa Osama
 */
public class ArtistFragment extends Fragment {
    private RecyclerView recyclerView;
    public static   ArrayList<Artist> artistslist = new ArrayList<>();
    private Artist_Adapter myadapter;
    private int REQUEST_CODE = 0;
    private onClickInterface onclickInterface;
    public ArtistFragment() {
        // Required empty public constructor
    }

    /**
     * this function always check if the user clicks on one of the artists or clicks on choose artist.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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
                builder.show(getFragmentManager(), "z");
                Toast.makeText(getContext(), " shaimaa ", Toast.LENGTH_LONG).show();
            }
        });

        recyclerView = (RecyclerView) s.findViewById(R.id.recycler_viewartist);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                // list.remove(abc);
                Toast.makeText(getContext(),"Position is"+abc, Toast.LENGTH_LONG).show();
                Artist r1 = artistslist.get(abc);
                Fragment selectedFragment=new EachArtist(r1);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).addToBackStack(null).commit();
            }
        };
        myadapter = new Artist_Adapter(getContext(), artistslist,onclickInterface);
        checkfollow();
        recyclerView.setAdapter(myadapter);

        recyclerView.addItemDecoration(new SpaceItemDecoration(2));
        recyclerView.setClickable(true);
        return  s;




    }

    /**
     * check if the user unfollowed an artist from the list
     */
    void checkfollow()
    {
        if (artistslist!=null)
        {
            for(int i=0;i<artistslist.size();i++)
            {
                if(!artistslist.get(i).isFollowing())
                {
                    artistslist.remove(i);
                    //  Toast.makeText(this.getContext(),"removed" , Toast.LENGTH_LONG).show();

                }
//                myadapter.notifyItemRemoved(i);
                //      myadapter.notifyItemRangeChanged(i, artistslist.size());
            }

        }
    }
    void checkfollowadd(Artist a)
    {
        artistslist.add(a);
//        myadapter.notifyDataSetChanged();
    }
    public void hey(DialogFragment builder2)
    {
        builder2.setTargetFragment(this, REQUEST_CODE);
    }
    public void onActivityResult( int requestCode, int resultCode, Intent data) {
        String editTextString = "shread";
        // Toast.makeText(this.getContext(), editTextString, Toast.LENGTH_LONG).show();
        // Make sure fragment codes match up
        int listsize = data.getIntExtra("size", 0);
        for (int i = 0; i < listsize; i++) {
            String name = data.getStringExtra(
                    "Name" + i);
            String imageid = data.getStringExtra(
                    "image" + i);
            String id = data.getStringExtra(
                    "id" + i);
            boolean follow=data.getBooleanExtra("follow+i",true);
            artistslist.add(new Artist(id,name,imageid,follow));

        }
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

}