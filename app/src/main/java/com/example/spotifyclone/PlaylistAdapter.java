package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaylistAdapter extends ArrayAdapter<PlaylistResponse> {
    public PlaylistAdapter(@NonNull Context context, @NonNull ArrayList<PlaylistResponse> playlistResponses) {
        super(context, 0, playlistResponses);
    }
    /**
     * to fill the listview (names and images ) of the playlist (rowItems) in the playlist page
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;

        listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        PlaylistResponse playlistResponse =getItem(position);
        TextView name =(TextView) listItemView.findViewById(R.id.name);
        name.setText(playlistResponse.getPlayname());
        ImageView image =(ImageView) listItemView.findViewById(R.id.image);
<<<<<<< HEAD
        Picasso.with(getContext()).
                load(playlistResponse.getArtimg())
                .into(image);
=======
        image.setImageResource(Integer.parseInt(rowItem.getImage()));
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

        return listItemView;
    }
}
