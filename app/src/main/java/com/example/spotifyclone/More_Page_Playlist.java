package com.example.spotifyclone;

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

public class More_Page_Playlist extends BottomSheetDialogFragment {
    private static More_Page_Playlist instance;
    String Playlistname;
    String playlistimage;
    private ImageView musicImage;
    private LinearLayout smallLayout;
    private RelativeLayout moreLayout;
    public ImageButton likebut;
    private TextView likete;
    private LinearLayout likelay;
    private TextView playlistname;
    private  boolean like;
    private RowItem play;

    public static More_Page_Playlist getInstance(){
        return instance;
    }
    public More_Page_Playlist(RowItem playlist){
        Playlistname=playlist.getName();
        playlistimage=playlist.getImage();
        like=playlist.isIsliked();
        this.play=playlist;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.more_down_playlist, container, false);
        likebut = v.findViewById(R.id.btn_like2);
        likete = v.findViewById(R.id.btn_like3);
      likelay = v.findViewById(R.id.like_lay);

        musicImage=v.findViewById(R.id.Playlistimage_);
        playlistname=v.findViewById(R.id.name_playlist_);

        Picasso.with(getContext()).load(playlistimage).into(musicImage);
        playlistname.setText(Playlistname);


        updatelike();
        smallLayout = v.findViewById(R.id.layout_small);
        smallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        moreLayout =v.findViewById(R.id.more_layout3);
        moreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        likete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likefunction();
            }
        });
        likebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likefunction();
            }
        });
        likelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likefunction();

            }
        });


        return v;
    }



    @Override
    public void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        instance=this;
        setStyle(BottomSheetDialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);


    }


    private void likefunction(){
        // EachArtist artisteach = (EachArtist) getFragmentManager().findFragmentByTag("");
        if(!play.isIsliked())
        {
            likete.setText("Liked");
            ///////////////  main.buttonLikeAction();
            likebut.setImageResource(R.drawable.favorite_green);
            play.setIsliked(true);
            /////////////// main.showLikeToast(" Added to Liked Songs. ");
            dismiss();

        }else {
            likebut.setImageResource(R.drawable.like);
            likete.setText("Like");
            ///////////////  main.buttonLikeAction();
            play.setIsliked(false);

            ////////////   main.showLikeToast(" Removed from Liked Songs. ");
            dismiss();
        }

    }
    public void updatelike()
    {
        if(play.isIsliked()){
            likete.setText("Liked");
            likebut.setImageResource(R.drawable.favorite_green);
        }
        else{
            likete.setText("Like");
            likebut.setImageResource(R.drawable.like);
        }
    }



}
