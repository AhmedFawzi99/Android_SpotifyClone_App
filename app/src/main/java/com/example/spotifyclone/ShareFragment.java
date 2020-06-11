package com.example.spotifyclone;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.muddzdev.styleabletoast.StyleableToast;
import com.squareup.picasso.Picasso;

import java.net.URI;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * ShareFragment class that opens a dialog and lets the user choose which way he wants to send with.
 * Helping Source: <a href="https://www.youtube.com/watch?v=CC79Qz6n22c&t=375s">https://www.youtube.com/watch?v=CC79Qz6n22c&t=375s</a> <br>
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class ShareFragment extends BottomSheetDialogFragment {
    private static ShareFragment instance;
    private ImageView musicImage;
    private TextView songname;
    private TextView artistname;
    private LinearLayout smallLayout;
    private RelativeLayout LargeLayout;
    private LinearLayout Instagram;
    private LinearLayout whatsapp;
    private LinearLayout facebook;
    private LinearLayout messenger;
    private LinearLayout twitter;
    private LinearLayout copylink;
    private LinearLayout Moreee;

    public static ShareFragment getInstance(){
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View s = inflater.inflate(R.layout.activity_share, container, false);
        MusicActivity main = (MusicActivity) getActivity();
        songname=s.findViewById(R.id.name_songs);
        artistname=s.findViewById(R.id.name_artists);
        musicImage=s.findViewById(R.id.music_images);
        Instagram=s.findViewById(R.id.instagram);
        whatsapp=s.findViewById(R.id.whatsapp);
        facebook=s.findViewById(R.id.facebookshare);
        messenger=s.findViewById(R.id.Messengerr);
        twitter=s.findViewById(R.id.Twitterr);
        copylink=s.findViewById(R.id.CopyLink);
        Moreee=s.findViewById(R.id.more);



        Picasso.with(getContext()).load(main.imageUrl).into(musicImage);
        songname.setText(main.name_song.getText());
        artistname.setText(main.name_artist.getText());
        smallLayout = s.findViewById(R.id.sharefragment_layout_small);
        smallLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        LargeLayout =s.findViewById(R.id.activity_share);
        LargeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getContext().getPackageManager();
                boolean isInstalled = isPackageInstalled("com.instagram.android", pm);
                if(isInstalled==true) {
                    share(2);
                }else{
                    StyleableToast.makeText(getContext(), "Instagram is not Installed", R.style.exampleToast).show();
                }
                dismiss();
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getContext().getPackageManager();
                boolean isInstalled = isPackageInstalled("com.whatsapp", pm);
                if(isInstalled==true) {
                    share(1);
                }else{
                    StyleableToast.makeText(getContext(), "Whatsapp is not Installed", R.style.exampleToast).show();
                }
                dismiss();

            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PackageManager pm = getContext().getPackageManager();
                boolean isInstalled = isPackageInstalled("com.facebook.katana", pm);
                if(isInstalled==true) {
                    share(3);
                }else{
                    StyleableToast.makeText(getContext(), "Facebook is not Installed", R.style.exampleToast).show();
                }
                dismiss();

            }
        });
        messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getContext().getPackageManager();
                boolean isInstalled = isPackageInstalled("com.facebook.orca", pm);
                if(isInstalled==true) {
                    share(4);
                }else{
                    StyleableToast.makeText(getContext(), "Messenger is not Installed", R.style.exampleToast).show();
                }
                dismiss();

            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getContext().getPackageManager();
                boolean isInstalled = isPackageInstalled("com.twitter.android", pm);
                if(isInstalled==true) {
                    share(5);
                }else{
                    StyleableToast.makeText(getContext(), "Twitter is not Installed", R.style.exampleToast).show();
                }
                dismiss();


            }
        });
        copylink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicActivity main = (MusicActivity) getActivity();
                ClipboardManager clipboard = getSystemService(getContext(), ClipboardManager.class);
                ClipData clip = ClipData.newPlainText("Listen to: "+main.name_song.getText()+"\n"+"By: "+main.name_artist.getText()+"\n"+main.musicUrl, "Listen to: "+main.name_song.getText()+"\n"+"By: "+main.name_artist.getText()+"\n"+main.musicUrl);
                clipboard.setPrimaryClip(clip);
                StyleableToast.makeText(getContext(), "Copied to Clipboard", R.style.exampleToast).show();
                dismiss();

            }

        });
        Moreee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicActivity main = (MusicActivity) getActivity();
                Intent myIntent=new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                myIntent.putExtra(Intent.EXTRA_TEXT, "Listen to: "+main.name_song.getText()+"\n"+"By: "+main.name_artist.getText()+"\n"+main.musicUrl);
                startActivity(Intent.createChooser(myIntent,"Share Using:"));
            }
        });




        return s;
    }

    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        instance=this;
        setStyle(BottomSheetDialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);


    }


    /**
     * the major function of share which determines which app the user wants to share to
     * @param a
     */
    private void share(int a){
        MusicActivity main = (MusicActivity) getActivity();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Listen to: "+main.name_song.getText()+"\n"+"By: "+main.name_artist.getText()+"\n"+main.musicUrl);
        shareIntent.setType("text/plain");
        if (a==1){
            shareIntent.setPackage("com.whatsapp");
        }else if (a==2){
            shareIntent.setPackage("com.instagram.android");
        }else if (a==3){
            shareIntent.setPackage("com.facebook.katana");
        }else if (a==4){
            shareIntent.setPackage("com.facebook.orca");
        }else if (a==5) {
            shareIntent.setPackage("com.twitter.android");
        }
        startActivity(shareIntent);
    }

    /**
     * function that Checks if the app is installed or no on the user phone
     * @param packageName
     * @param packageManager
     * @return
     */
    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}