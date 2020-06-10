package com.example.spotifyclone;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.security.PublicKey;

public class Help extends DialogFragment {

    public TextView Search;
    public TextView Playlists;
    public TextView ShareandPoadcasts;
    public TextView Rights ;
    public TextView Radio ;
    RelativeLayout help;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.helppage, container, false);

        Search=v.findViewById(R.id.searchs);
        Playlists=v.findViewById(R.id.play);
        ShareandPoadcasts=v.findViewById(R.id.amusicandpodcasts);
        Radio=v.findViewById(R.id.radio);
        Rights=v.findViewById(R.id.datarights);
        help=v.findViewById(R.id.helplayout);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return v;
    }



    @Override
    public void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        setStyle(BottomSheetDialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);


    }




}

