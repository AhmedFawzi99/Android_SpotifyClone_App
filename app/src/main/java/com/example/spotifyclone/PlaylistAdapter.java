package com.example.spotifyclone;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<RowItem> {
    public PlaylistAdapter(@NonNull Context context, @NonNull ArrayList<RowItem> rowItems) {
        super(context, 0, rowItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;

        listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        RowItem rowItem =getItem(position);
        TextView name =(TextView) listItemView.findViewById(R.id.name);
        name.setText(rowItem.getName());
        ImageView image =(ImageView) listItemView.findViewById(R.id.image);
        image.setImageResource(Integer.parseInt(rowItem.getImage()));

        return listItemView;
    }
}
