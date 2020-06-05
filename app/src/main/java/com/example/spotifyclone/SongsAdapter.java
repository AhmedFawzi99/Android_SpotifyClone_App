package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SongsAdapter extends ArrayAdapter<Tracks> {



    public SongsAdapter(@NonNull Context context, @NonNull ArrayList<Tracks> songs) {
        super(context,0, songs);
    }
    @NonNull
    @Override
    /**
     * to fill the ListView (names and images ) of the songs (Tracks) in the Each playlist page
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;

        listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_view2,parent,false);

        Tracks tracks =getItem(position);

        TextView name =(TextView) listItemView.findViewById(R.id.namesong);
        name.setText(tracks.getName());
        return listItemView;

    }
}
