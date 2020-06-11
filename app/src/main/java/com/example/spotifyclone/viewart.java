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

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

/**
 * The Class that shows the artist data in Music Player
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class ViewArt extends BottomSheetDialogFragment {
    private static MorePageDown instance;
    private LinearLayout smallLayout;
    private RelativeLayout moreLayout;
    private TextView artist;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.artshowcredits, container, false);

        smallLayout=v.findViewById(R.id.layoutsmall);
        moreLayout=v.findViewById(R.id.aart);
        artist=v.findViewById(R.id.artName);
        MusicActivity main = (MusicActivity) getActivity();
        artist.setText(main.name_artist.getText());
        return v;
    }



    @Override
    public void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        setStyle(BottomSheetDialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);


    }




}

