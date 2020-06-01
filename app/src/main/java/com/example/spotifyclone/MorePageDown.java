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

public class MorePageDown extends BottomSheetDialogFragment {
    private static MorePageDown instance;
    private TextView bShare2;
    private ImageButton bShare3;
    private LinearLayout shareLayout;

    public TextView timer3;
    public ImageButton timer2;
    private LinearLayout timer;
    private ImageView musicImage;
    private LinearLayout smallLayout;
    private RelativeLayout moreLayout;
    public int likeToggle;
    public ImageButton bLike2;
    private TextView bLike3;
    private LinearLayout likeLayout;
    private TextView songname;
    private TextView artistname;



    public static MorePageDown getInstance(){
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_more_page, container, false);
        MusicActivity main = (MusicActivity) getActivity();


        bLike2 = v.findViewById(R.id.btn_like2);
        bLike3 = v.findViewById(R.id.btn_like3);
        likeLayout = v.findViewById(R.id.like_lay);
        timer = v.findViewById(R.id.timer1);
        timer2 = v.findViewById(R.id.timer2);
        timer3 = v.findViewById(R.id.timer3);
        musicImage=v.findViewById(R.id.music_imagem);
        songname=v.findViewById(R.id.name_songm);
        artistname=v.findViewById(R.id.name_artistm);

        Picasso.with(getContext()).load(main.imageUrl).into(musicImage);
        songname.setText(main.name_song.getText());
        artistname.setText(main.name_artist.getText());


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
        bShare2 = v.findViewById(R.id.btn_share2);
        bShare2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareFunction();
            }
        });
        bShare3 = v.findViewById(R.id.btn_share3);
        bShare3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareFunction();
            }
        });
        shareLayout = v.findViewById(R.id.share_lay);
        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareFunction();
            }
        });
        bLike3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                likefunction();
            }


        });
        bLike2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likefunction();
            }
        });
        likeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likefunction();

            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timerFunction();
            }


        });
        timer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerFunction();
            }
        });
        timer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerFunction();

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
    private void timerFunction(){
        TimerClass time=new TimerClass();
        time.show(this.getFragmentManager(),"TIMER");
        dismiss();

    }

    private void shareFunction(){
        ShareFragment share=new ShareFragment();
        share.show(this.getFragmentManager(),"SHARE");
        dismiss();
    }
    private void likefunction(){
        MusicActivity main = (MusicActivity) getActivity();
        if(likeToggle==0)
        {
            bLike3.setText("Liked");
            bLike2.setImageResource(R.drawable.favorite_green);
            main.buttonLikeAction();
            likeToggle=1;
//                    main.likeToggle=1;
//                    main.btn_like.setImageResource(R.drawable.favorite_green);
            main.showLikeToast(" Added to Liked Songs. ");
            dismiss();

        }else {
            bLike2.setImageResource(R.drawable.like);
            bLike3.setText("Like");
            main.buttonLikeAction();
            likeToggle = 0;
//                    main.likeToggle=0;
//                    main.btn_like.setImageResource(R.drawable.like);
            main.showLikeToast(" Removed from Liked Songs. ");
            dismiss();
        }

    }
    public void updatelike()
    {
        if(getLikeToggle()==1){
            bLike3.setText("Liked");
            bLike2.setImageResource(R.drawable.favorite_green);
        }
        else{
            bLike3.setText("Like");
            bLike2.setImageResource(R.drawable.like);
        }
    }



    int getLikeToggle(){
        return likeToggle;
    }
    void setLikeToggle(int a){

        likeToggle=a;
    }



}

