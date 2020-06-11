package com.example.spotifyclone;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.muddzdev.styleabletoast.StyleableToast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.spotifyclone.App.CHANNEL_1_ID;

/**
 * @author shaimaa
 * each playlist class
 */
public class EachPlaylist extends Fragment
{
    /**
     * each playlist class
     */
    private static EachPlaylist instance;
    private  String PlaylistName;
    private  String image2;
    public static TextView textView;
    public static TextView textView2;
    private ImageView btn_more;
    public  static  ImageView imageView;
    /**
     * playlist fragment objrct
     */
    PlaylistFragment playlistFragment= new PlaylistFragment();
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private ImageButton next;
    public  static ArrayList<Tracks> Songs= new ArrayList<Tracks>();


    private Tracks s1;
    private  RowItem playlistt;

    /**
     * constructor
     * @param playlistname
     * @param image
     * @param id
     */
    public EachPlaylist(String playlistname, String image , String id ) {
        PlaylistName=playlistname;
        image2=image;
    }

    /**
     * overriding constructor
     * @param s
     */
    public  EachPlaylist(RowItem s){Songs=s.getSongs(); PlaylistName=s.getName();
        image2=s.getImage();
        playlistt=s;
    }


    //public static EachPlaylist getInstance(){
    //    return instance;
    //}

    /**
     * on creating the page this function is called
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        {
            MainActivitysha main =(MainActivitysha) getActivity();
            View s=inflater.inflate(R.layout.list_viewp,container,false);
            Songs=main.Songs;
//            Songs2=Songs;
            final SongsAdapter adapter =new SongsAdapter(this.getContext(),Songs);
            ListView listView=(ListView) s.findViewById(R.id.listview500);
            btn_more= s.findViewById(R.id.btn_moreplaylist);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setClickable(true);
            adapter.notifyDataSetChanged();
            textView=(TextView)s.findViewById(R.id.textView3);
            textView2=(TextView)s.findViewById(R.id.textView9);
            textView2.setText(PlaylistName);
            imageView= (ImageView) s.findViewById(R.id.btn_Likeplaylist);
            ImageView imagee=(ImageView) s.findViewById(R.id.image2);
            ImageView imageVieww=(ImageView) s.findViewById(R.id.backplayer);
            Picasso.with(getContext()).
                    load(image2)
                    .into(imagee);
            imageVieww.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getFragmentManager().getBackStackEntryCount() != 0) {
                        getFragmentManager().popBackStack();
                    }
                }
            });
            btn_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    More_Page_Playlist more_page_playlist=new More_Page_Playlist(playlistt);
                    more_page_playlist.show(getFragmentManager(),"DownMoreplaylist");
                }
            });
            if(playlistt.isIsliked())
            {
                imageView.setImageResource(R.drawable.favorite_green);
            }
            else
            {
                imageView.setImageResource(R.drawable.like);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonlikeplaylist();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(MainActivitysha.checker==0) {
                        Intent i = new Intent(getActivity(), MusicActivity.class);

                        s1 = Songs.get(0);
                        Log.d(String.valueOf(Songs.size()), "SIZEEEE: ");
                        adapter.notifyDataSetChanged();
                        //MusicActivity activity=(MusicActivity) getActivity();;
                        i.putExtra("SENDER_KEY", "EachPlaylist");
                        i.putExtra("NAME", s1.getName());
                        i.putExtra("URL", s1.getURL());
                        i.putExtra("IMAGE", s1.getImageid());
                        i.putExtra("ID", s1.getId());
                        i.putExtra("PLAYLISTNAME", s1.getPname());
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
                        if( main.media_player.isPlaying())
                        {
                            textView.setText("PAUSE");
                        }
                        else
                        {
                            textView.setText("PLAY");
                        }

                    }

                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                    //MusicActivity activity=(MusicActivity) getActivity();;
                    Intent i = new Intent(getActivity(), MusicActivity.class);

                    s1 = Songs.get(position);
                    Log.d(String.valueOf(Songs.size()), "SIZEEEE: ");
                    adapter.notifyDataSetChanged();
                    //MusicActivity activity=(MusicActivity) getActivity();;
                    i.putExtra("SENDER_KEY", "EachPlaylist");
                    i.putExtra("NAME", s1.getName());
                    i.putExtra("URL",s1.getURL() );
                    i.putExtra("IMAGE",s1.getImageid());
                    i.putExtra("ID",s1.getId() );
                    i.putExtra("PLAYLISTNAME",s1.getName());
                    i.putExtra("Artist","salma" );
                    i.putExtra("pos", position);
                    i.putExtra("isliked",s1.getIsliked());

                    //RESET WIDGETS
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    startActivityForResult(i,0);
//                    getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);
                    //activity.setMusicPlayerComponents(s1.getName(),PlaylistName,PlaylistName,s1.getImageid(),s1.getURL());
                }
            });


            return s;
        }
    }
    void addsongs()
    {
        ArrayList<Artist> artists =new ArrayList<Artist>();
        Songs.add(new Tracks("shaimaa","playlist","imageid","URL","iyyb",artists));
        Songs.add(new Tracks("shaimaa","playlist","imageid","URL","iyyb",artists));
        Songs.add(new Tracks("shaimaa","playlist","imageid","URL","iyyb",artists));
        Songs.add(new Tracks("shaimaa","playlist","imageid","URL","iyyb",artists));
    }

    public Tracks getSONG(int a){



        MusicActivity activity=(MusicActivity) getActivity();;
        Log.d(String.valueOf(Songs.size()), "getSONG: ");
        Tracks s2 = Songs.get(a);
        return s2;


    }

    public void check(){

        Log.d("testttttttttttt", "check: ");
    }
    public void showlike(String S ) {
        StyleableToast.makeText(this.getContext(), S, R.style.exampleToast).show();
    }
    public void buttonlikeplaylist()
    {
        if(playlistt.isIsliked())
        {
            playlistt.setIsliked(false);
            imageView.setImageResource(R.drawable.like);
            showlike("disliked");
            playlistFragment.checklike();
            //////// putlike();

            // showLikeToast(" Added to Liked Songs. ");


        }else{
            More_Page_Playlist.like_song="You Liked a song";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setContentTitle("Activity Notification")
                    .setContentText("You liked a song")
                    .setAutoCancel(true);
            Intent i = new Intent(getContext(), More_Page_Playlist.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("message", "You liked a song ");

            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());
            imageView.setImageResource(R.drawable.favorite_green);
            showlike("Liked");
            playlistFragment.checklikeadd(playlistt);
//            notificationManager.notify(1, );
            //  artistFragment.artistslist.remove(artist);
            /////////////deletelike();
            playlistt.setIsliked(true);
            //    showLikeToast(" Removed from Liked Songs. ");

        }


    }
}