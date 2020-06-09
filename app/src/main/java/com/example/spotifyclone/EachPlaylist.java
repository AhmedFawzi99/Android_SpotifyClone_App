package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EachPlaylist extends Fragment
{

<<<<<<< HEAD
    private static EachPlaylist instance;
=======
    /**
     * The name of the playlist
     */
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
    private  String PlaylistName;
    /**
     * The image  of the playlist
     */
    private  String image2;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
<<<<<<< HEAD
    private ImageButton next;
    public  static ArrayList<Track> Songs= new ArrayList<Track>();
    public static LinearLayout linearLayout;
    public static TextView textView;

//    private  ArrayList<Tracks> Songs2= new ArrayList<Tracks>();


    private Track s1;

    public EachPlaylist(String playlistname, String image , String id ) {
=======
    /**
     * each playlist contains an ArrayList of songs
     */
    private  ArrayList<Tracks> Songs= new ArrayList<Tracks>();
    /**
     * Constructor
     * @param playlistname
     * @param image
     */
    public EachPlaylist(String playlistname, String image , String id) {
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
        PlaylistName=playlistname;
        image2=image;
    }




    public static EachPlaylist getInstance(){
        return instance;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        {
<<<<<<< HEAD


            MainActivitysha main =(MainActivitysha) getActivity();
            View s=inflater.inflate(R.layout.list_viewp,container,false);

=======
            addsongs();
            MainActivitysha main =(MainActivitysha) getActivity();
            View s=inflater.inflate(R.layout.list_viewp,container,false);
            /**
             * getting the songs received from the server and putting it in the variable songs
             */
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
            Songs=main.Songs;
//            Songs2=Songs;
            final SongsAdapter adapter =new SongsAdapter(this.getContext(),Songs);
            /**
             * ListView will be shown in the screen , it consist of a name and an image for each song
             */
            ListView listView=(ListView) s.findViewById(R.id.listview500);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setClickable(true);
            adapter.notifyDataSetChanged();
            ImageView imagee=(ImageView) s.findViewById(R.id.image2);
<<<<<<< HEAD
            ImageView imagee2=(ImageView) s.findViewById(R.id.btn_playerdown);
            textView=(TextView)s.findViewById(R.id.textView3) ;

            Picasso.with(getContext()).
                    load(image2)
                    .into(imagee);
            imagee2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getFragmentManager().getBackStackEntryCount() != 0) {
                        getFragmentManager().popBackStack();
                    }
                }
            });
textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(MainActivitysha.checker==0) {
            Intent i = new Intent(getActivity(), MusicActivity.class);
                   /* i.putExtra("SENDER_KEY", "EachPlaylist");
                    i.putExtra("NAME", "salma ");
                    i.putExtra("URL",s1.getURL() );
                    i.putExtra("IMAGE",s1.getImageid());
                    i.putExtra("ID",s1.getId() );
                    i.putExtra("PLAYLISTNAME",PlaylistName);
                    i.putExtra("Artist","salma" );*/
            s1 = Songs.get(0);
            Log.d(String.valueOf(Songs.size()), "SIZEEEE: ");
            adapter.notifyDataSetChanged();
            //MusicActivity activity=(MusicActivity) getActivity();;
            i.putExtra("SENDER_KEY", "EachPlaylist");
            i.putExtra("NAME", s1.gettName());
            i.putExtra("URL", s1.getUrl());
            i.putExtra("IMAGE", s1.gettPreviewUrl());
            i.putExtra("ID", s1.gettId());
            i.putExtra("PLAYLISTNAME", s1.gettPname());
            i.putExtra("Artist", s1.getAname());
            i.putExtra("pos", 0);
            i.putExtra("isliked", s1.getIsliked());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);


            //RESET WIDGETS

textView.setText("PAUSE");
            startActivityForResult(i, 0);
            MainActivitysha.checker=1;
        }
        else {
            MusicActivity main = MusicActivity.getInstance();
            main.buttonPlayerAction();
        }

    }
});
=======
            Picasso.with(getContext()).
                    load(image2)
                    .into(imagee);
            /**
             * it detects the click on which song and send the data to the Music Activity and then open the activity
             * @see MusicActivity
             */
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                    //MusicActivity activity=(MusicActivity) getActivity();;
                    Intent i = new Intent(getActivity(), MusicActivity.class);
                   /* i.putExtra("SENDER_KEY", "EachPlaylist");
                    i.putExtra("NAME", "salma ");
                    i.putExtra("URL",s1.getURL() );
                    i.putExtra("IMAGE",s1.getImageid());
                    i.putExtra("ID",s1.getId() );
                    i.putExtra("PLAYLISTNAME",PlaylistName);
                    i.putExtra("Artist","salma" );*/
                    s1 = Songs.get(position);
                    Log.d(String.valueOf(Songs.size()), "SIZEEEE: ");
                    adapter.notifyDataSetChanged();
                    //MusicActivity activity=(MusicActivity) getActivity();;
                    i.putExtra("SENDER_KEY", "EachPlaylist");
                    i.putExtra("NAME", s1.gettName());
                    i.putExtra("URL",s1.getUrl() );
                    i.putExtra("IMAGE",s1.gettPreviewUrl());
                    i.putExtra("ID",s1.gettId() );
                    i.putExtra("PLAYLISTNAME",s1.gettPname());
                    i.putExtra("Artist",s1.getAname() );
                    i.putExtra("pos", position);
                    i.putExtra("isliked",s1.getIsliked());

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);


                    //RESET WIDGETS

                    startActivityForResult(i,0);

                    //activity.setMusicPlayerComponents(s1.getName(),PlaylistName,PlaylistName,s1.getImageid(),s1.getURL());





                }
            });


            return s;
        }
    }
  /*  void addsongs()
    {
        ArrayList<Artist> artists =new ArrayList<Artist>();
        Songs.add(new Track("shaimaa","playlist","imageid","URL","iyyb",artists));
        Songs.add(new Track("shaimaa","playlist","imageid","URL","iyyb",artists));
        Songs.add(new Track("shaimaa","playlist","imageid","URL","iyyb",artists));
        Songs.add(new Track("shaimaa","playlist","imageid","URL","iyyb",artists));
    }
*/
    public Track getSONG(int a){



        MusicActivity activity=(MusicActivity) getActivity();;
        Log.d(String.valueOf(Songs.size()), "getSONG: ");
        Track s2 = Songs.get(a);
        return s2;
//        String name = s2.getName();
//        Log.d(name, "getSONG: ");
//        String url = s2.getURL();
//        String imageid = s2.getImageid();
//        String id = s2.getId();
//        String playlistnamee = s2.getName();
//        String artist = "salmaaaaaaaaaaa";
//        int position =a;
//        boolean isliked=s2.getIsliked();

//        activity.setMusicPlayerComponents(position,name,artist,"Playing from Playlist",playlistnamee,imageid,url,isliked);

    }

    public void check(){

        Log.d("testttttttttttt", "check: ");
    }
}
