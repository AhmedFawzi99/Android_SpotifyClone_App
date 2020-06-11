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

/**
 * This Class is responible for showing the choosen help reponse
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class HelpEach extends DialogFragment {

    public static TextView barname;
    public static TextView texthelp;
    public ImageButton backhelp;
    public static int a=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.helpeach, container, false);

         backhelp=v.findViewById(R.id.backhelp);
         backhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
         barname=v.findViewById(R.id.hepbar);
         texthelp=v.findViewById(R.id.textt);

         printhelp(a);

        return v;
    }



    @Override
    public void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        setStyle(BottomSheetDialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);


    }

    public void printhelp(int a){

        if(a==1){
            barname.setText("Search Help");
            texthelp.setText( "Find what you’re looking for with Search, including:" + "\n"+ "\n"
            + "Songs" + "\n"+ "\n" + "Albums"  + "\n"+ "\n"+ "Artists"  + "\n"+ "\n"+ "Playlists" + "\n"+ "\n"+ "Podcast shows and episodes" + "\n" +"\n"+
                            "On mobile and tablet, you can also use Search " +
                            "to browse categories such as genres, moods, charts, and new releases. Find Radio and Concerts here too. "+ "\n"
                    + "\n" + "Advanced search"+ "\n"+"\n"+ "Enter your search term in quotation marks to narrow results to exact matches, e.g. Let’s Dance.\n" +
                            "\n" +
                            "You can also enter any of these before your search term to narrow the results." +"Exclude or add search terms\n" +
                    "Refine your search with AND, OR, and NOT, for example:\n" +
                    "\n" +
                    "Kyuss AND Green - Displays results with keywords 'Kyuss' and 'Green'.\n" +
                    "Zeppelin OR Floyd - Displays results with keywords 'Zeppelin' or 'Floyd'.\n" +
                    "Metallica NOT Anger - Displays all Metallica tracks except with the word 'Anger'. \n" +
                    "Tip: You can also use + or - instead of AND or NOT.");

        }else if(a==2){
            barname.setText("Playlists Help");
            texthelp.setText("A playlist is simply a collection of songs. You can make your own, share them, and enjoy the millions of other playlists created by Spotify, artists, and other listeners worldwide.\n" +
                    "\n" +
                    "Tip: Get organized with Playlist folders.\n" +
                    "\n" +
                    "Made for you \n" +
                    "The many playlists Spotify makes just for you, such as Discover Weekly and Release Radar, are based on your listening habits (what you like, share, save, skip) and the listening habits of others with similar taste.\n" +
                    "\n" +
                    "On mobile, they're featured in Home . \n" +
                    "\n" +
                    "On desktop, you can find these under YOUR LIBRARY on the left, in Made For You.\n" +
                    "\n" +
                    "Learn more about Made For You playlists.\n" +
                    "\n" +
                    "Made for everyone\n" +
                    "Curated by music experts from around the globe, find these in Browse on desktop or Search on mobile. We’ve categorized them into Genres & Moods for you.\n" +
                    "\n" +
                    "Some of these playlists are personalized, so you may see different track listings to someone else. As an example, if a playlist has ‘sing-along hits’, it’ll have songs you know the words to!");
        }
        else if(a==3){
            barname.setText("Radio Help");
            texthelp.setText("Keep the mood going. Spotify Radio creates a collection of songs based on any artist, album, playlist, or song of your choice. It even updates over time to keep fresh.\n" +
                    "\n" +
                    "Go to any artist, album, playlist, or song.\n" +
                    "Select  or .\n" +
                    "Select Go to radio.\n" +
                    "You can also Search to find an Artist Radio.\n" +
                    "\n" +
                    "Tip: On desktop, go to Radio in the menu on the left to get recommended Radio playlists.\n" +
                    "\n" +
                    "Save a Radio\n" +
                    "Like  a Radio to save it in Your Library under Playlists. If you have Premium, you can download it too!\n" +
                    "\n" +
                    "Note: You can’t save or download a Playlist Radio.");

        }
        else if(a==4){
            barname.setText("Share Help");
            texthelp.setText("Calling all tastemakers! We’ve made it easy for you to share direct from the app.\n" +
                    "\n" +
                    "Go to what you want to share and select  (desktop and iOS) /   (Android).\n" +
                    "Tip: You can also right-click it on desktop or web player. \n" +
                    "Select Share. There you can:\n" +
                    "Share to any social or messaging app listed.\n" +
                    "Choose Copy Link to paste the link wherever you like.\n" +
                    "Note: This is the only option to share on web player.\n" +
                    "Share a Spotify Code (more info below).\n" +
                    "On desktop, you can also Copy Embed Code to share on websites.\n" +
                    "\n" +
                    "What about secret playlists?\n" +
                    "The share feature will share a playlist even if you’ve made it secret. The recipient can view, play, and follow it, and they can share with others who can also view, play, follow, and share it.\n" +
                    " \n" +
                    "Note: If you’ve set the playlist to be collaborative, any recipient can also edit the playlist. ");
        }
        else if(a==5){
            barname.setText("Data rights and Privacy");
            texthelp.setText("Your privacy and the security of your personal data are, and always will be, our highest priority. \n" +
                    "\n" +
                    "That’s why we’ve created a Privacy Center to highlight some of the important sections of our Privacy Policy and to give you more info about the rights and controls you have in relation to your personal data.\n" +
                    "\n" +
                    "For more info about your data rights, and the privacy settings available to you, see our FAQ below.+ \n" +
                    "\n" +
                    "How do I understand my personal data download?\n" +
                    "Where can I find information about data processing that Spotify is required to provide under Article 15 of the GDPR?\n" +
                    " \n" +
                    "\n" +
                    "How do I control what personal data is processed about me?\n" +
                    "What happens if I opt out of Spotify processing my Facebook data?\n" +
                    "What happens if I opt out of Spotify processing my personal data for tailored ads?\n" +
                    "I want Spotify to stop processing my personal data\n" +
                    "I want to port my personal data to another music provider\n" +
                    "Can I update my personal details?");
        }
    }




}

