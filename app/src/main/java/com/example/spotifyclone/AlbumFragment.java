package com.example.spotifyclone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {

ArrayList<Album> albums=null;
    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false);
    }


    void checklike()
    {if (albums!=null)
    {
        for(int i=0;i<albums.size();i++)
        {
            if(!albums.get(i).isIsliked())
            {
                albums.remove(i);
                //  Toast.makeText(this.getContext(),"removed" , Toast.LENGTH_LONG).show();

            }
//                myadapter.notifyItemRemoved(i);
            //      myadapter.notifyItemRangeChanged(i, artistslist.size());
        }

    }}

    void checklikeadd(Album p)
    {
        albums.add(p);
    }

}
