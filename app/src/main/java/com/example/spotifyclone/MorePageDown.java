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

/**
 * This Class is A BottomSheetDialogFragment which is created when the More Button is pressed in the MusicActivityClass.<br>
 * It gives the user the options such as: <br>
 * 1 like/dislike  <br>
 * 2 Hide <br>
 * 3 View Artist <br>
 * 4 Set Timer to Auto stop Music <br>
 * 6 Share <br>
 * 7 Report <br>
 * 8 SHow credits <br>
 * Helping Source: <a href="https://www.youtube.com/watch?v=IfpRL2K1hJk&t=709s">https://www.youtube.com/watch?v=IfpRL2K1hJk&t=709s</a> <br>
 * @author Ahmed Mahmoud Fawzi <br>
 * @version 2.0
 */
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
    private LinearLayout Report;
    public ImageButton Report2;
    private TextView Report3;
    public ImageButton hide;
    public LinearLayout hidelay;
    public TextView hide2;
    public LinearLayout artview;
    public ImageButton account_music;
    public TextView art2;


    public static MorePageDown getInstance(){
        return instance;
    }


    /**
     * OnCreate View Sets the layout to open and the image of the music track and then Updates the like button to its current state
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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
        Report=v.findViewById(R.id.Report);
        Report2=v.findViewById(R.id.report2);
        Report3=v.findViewById(R.id.report3);
        Picasso.with(getContext()).load(main.imageUrl).into(musicImage);
        songname.setText(main.name_song.getText());
        artistname.setText(main.name_artist.getText());
        hide=v.findViewById(R.id.hide1);
        hide2=v.findViewById(R.id.hide2);
        hidelay=v.findViewById(R.id.hidelay);
        artview=v.findViewById(R.id.artview);
        account_music=v.findViewById(R.id.art);

        /**
         * artist text and button defining what to do when clicked
         */
        art2=v.findViewById(R.id.art2);
        art2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewart();
            }
        });
        account_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewart();
            }
        });
        artview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewart();
            }
        });

        updatelike();
        /**
         * Dismiss when clicked else where
         */
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

        /**
         * Share text and button defining what to do when clicked
         */
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

        /**
         * Like text and button defining what to do when clicked
         */
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

        /**
         * Timer text and button defining what to do when clicked
         */
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

        /**
         * Report text and button defining what to do when clicked
         */
        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              sendreport();

            }
        });
        Report2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendreport();
            }
        });
        Report3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendreport();
            }
        });

        /**
         * Hide text and button defining what to do when clicked
         */
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicActivity main = (MusicActivity) getActivity();
                main.next();
            }
        });
        hidelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicActivity main = (MusicActivity) getActivity();
                main.next();
            }
        });
        hide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicActivity main = (MusicActivity) getActivity();
                main.next();
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

    /**
     * A function that opens the Timer BottomSheetDialogFragment for the user to set the time to stop the song
     */
    private void timerFunction(){
        TimerClass time=new TimerClass();
        time.show(this.getFragmentManager(),"TIMER");
        dismiss();

    }

    /**
     * A function that opens the Share BottomSheetDialogFragment for the user to share
     */
    private void shareFunction(){
        ShareFragment share=new ShareFragment();
        share.show(this.getFragmentManager(),"SHARE");
        dismiss();
    }
    /**
     * This Function is Responsible to update the likeButton by updating the button to either liked or no and then calls the Function in the MusicActivity to update the button in player and notification and send to the serve to add or reove the like
     */
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
    /**
     * This Function is called int the OnCreateView to update the like/dislike to match the state of the Player and notification in the MusicActivity
     */
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

    /**
     * A function to send a Report Explicit Content email
     */
    public void sendreport(){
        String mail = Profile_DATA.mail;
        String message = songname+" has been reported as explicit content" +"\n"+"We would look into it";
        String subject = "Report Explicit Content";
        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(),mail,subject,message);
        javaMailAPI.execute();
        dismiss();

    }

    /**
     * a function to open the View Artist Fragment
     */
    public void viewart() {
        ViewArt time = new ViewArt();
        time.show(this.getFragmentManager(), "vieart");
        dismiss();
    }
    /**
     * getter for the liketoogle
     * @return
     */
    int getLikeToggle(){
        return likeToggle;
    }

    /**
     * setter for the liketoogle
     * @param a Integer 1/0
     */
    void setLikeToggle(int a){

        likeToggle=a;
    }



}

