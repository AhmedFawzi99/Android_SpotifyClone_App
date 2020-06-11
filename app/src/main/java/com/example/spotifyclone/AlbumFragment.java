package com.example.spotifyclone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


/**
 * @author shaimaa
 * Album Fragment in view pager.
 */
public class AlbumFragment extends Fragment {

    ArrayList<Album> albums=null;

    /**
     * empty constructor
     */
    public AlbumFragment() {
        // Required empty public constructor
    }

    /**
     * creating the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false);
    }

    /**
     * this function check if the album is liked or not if yes it will keep it but if not it will remove it from the list
     */
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

    /**
     * this function is called when the user like an album and it will add the liked album to the list
     */
    void checklikeadd(Album p)
    {
        albums.add(p);
    }

}