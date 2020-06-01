package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class EachPlaylist extends Fragment
{
    private  String PlaylistName;
    private  String image2;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private  ArrayList<Tracks> Songs= new ArrayList<Tracks>();
    public EachPlaylist(String playlistname, String image ) {
        PlaylistName=playlistname;
        image2=image;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        {
            MainActivitysha main =(MainActivitysha) getActivity();
            View s=inflater.inflate(R.layout.list_viewp,container,false);
            Songs=main.Songs;
            final SongsAdapter adapter =new SongsAdapter(this.getContext(),Songs);
            ListView listView=(ListView) s.findViewById(R.id.listview500);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setClickable(true);
            adapter.notifyDataSetChanged();
            ImageView imagee=(ImageView) s.findViewById(R.id.image2);
            imagee.setImageResource(Integer.parseInt(image2));
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
                    Tracks s1 = Songs.get(position);
                    adapter.notifyDataSetChanged();
                    //MusicActivity activity=(MusicActivity) getActivity();;
                    i.putExtra("SENDER_KEY", "EachPlaylist");
                    i.putExtra("NAME", s1.getName());
                    i.putExtra("URL",s1.getURL() );
                    i.putExtra("IMAGE",s1.getImageid());
                    i.putExtra("ID",s1.getId() );
                    i.putExtra("PLAYLISTNAME",s1.getName());
                    i.putExtra("Artist","salma" );

                    //RESET WIDGETS
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    startActivityForResult(i,0);
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

}
