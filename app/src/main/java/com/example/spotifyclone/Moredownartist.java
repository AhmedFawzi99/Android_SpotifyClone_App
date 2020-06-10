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

public class Moredownartist extends BottomSheetDialogFragment {
    private static Moredownartist instance;

String artistName;
String artistimage;
    private ImageView musicImage;
    private LinearLayout smallLayout;
    private RelativeLayout moreLayout;
    public ImageButton followimg;
    private TextView Followte;
    private LinearLayout followlay;
    private TextView artistname;
     private  boolean follow;
private Artist artist;

 public static Moredownartist getInstance(){
        return instance;
    }
   public Moredownartist(Artist artist){
    artistimage=artist.getImage();
    artistName=artist.getName();
    follow=artist.isFollowing();
    this.artist=artist;

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.more_down_artist, container, false);
        followimg = v.findViewById(R.id.btn_like2);
        Followte = v.findViewById(R.id.btn_like3);
        followlay = v.findViewById(R.id.like_lay);

        musicImage=v.findViewById(R.id.music_imagem);
        artistname=v.findViewById(R.id.name_songm);

        Picasso.with(getContext()).load(artistimage).into(musicImage);
        artistname.setText(artistName);


        updatelike();
        smallLayout = v.findViewById(R.id.layout_small);
        smallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        moreLayout =v.findViewById(R.id.more_layout);
        moreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        Followte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followfunction();
            }
        });
        followimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followfunction();
            }
        });
        followlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followfunction();

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


    private void followfunction(){
       // EachArtist artisteach = (EachArtist) getFragmentManager().findFragmentByTag("");
        if(!artist.isFollowing())
        {
            Followte.setText("Following");
            ///////////////  main.buttonLikeAction();
            followimg.setImageResource(R.drawable.favorite_green);
            artist.setFollowing(true);
           /////////////// main.showLikeToast(" Added to Liked Songs. ");
            dismiss();

        }else {
            followimg.setImageResource(R.drawable.like);
            Followte.setText("Follow");
          ///////////////  main.buttonLikeAction();
            artist.setFollowing(false);

         ////////////   main.showLikeToast(" Removed from Liked Songs. ");
            dismiss();
        }

    }
    public void updatelike()
    {
        if(artist.isFollowing()){
            Followte.setText("Following");
            followimg.setImageResource(R.drawable.favorite_green);
        }
        else{
            Followte.setText("Follow");
            followimg.setImageResource(R.drawable.like);
        }
    }



}

