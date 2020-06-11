package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author shaimma
 * Helping Source: <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">https://developer.android.com/guide/topics/ui/layout/recyclerview</a> <br>
 * Adapter to fill the popular_releases adaapter
 */
public class Popular_releases_adapter extends RecyclerView.Adapter<Popular_releases_adapter.MyViewHolder3>{
    ArrayList<Type> playlists;
    Context context;
    public Popular_releases_adapter(Context contextt, ArrayList<Type> playlists) {
        this.context=contextt;
        this.playlists=playlists;
    }
    /**
     * main functions of each adapter
     */
    @NonNull
    @Override


    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder3(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, int position) {
        if (playlists.get(position).type == "playlist") {
            holder.playlistname.setText(playlists.get(position).getRowitem().getName());
            Picasso.with(context).
                    load(playlists.get(position).getRowitem().getImage())
                    .into(holder.imageView);
        } else if (playlists.get(position).type == "album")
        {holder.playlistname.setText(playlists.get(position).getAlbum().getAlbum_name());
            Picasso.with(context).
                    load(playlists.get(position).getAlbum().getImage_id())
                    .into(holder.imageView);}
        else if(playlists.get(position).type == "single")
        {holder.playlistname.setText(playlists.get(position).getTrack().getName());
            Picasso.with(context).
                    load(playlists.get(position).getTrack().getImageid())
                    .into(holder.imageView);}

    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }
    class MyViewHolder3 extends RecyclerView.ViewHolder {
        LinearLayout parent;
        public TextView playlistname;
        public ImageView imageView;
        public MyViewHolder3(View itemView) {
            super(itemView);


            playlistname = (TextView) itemView.findViewById(R.id.name);
            imageView=(ImageView) itemView.findViewById(R.id.image);

            //  parent = itemView.findViewById(R.id.parent);
            // name = itemView.findViewById(R.id.name);
            // age = itemView.findViewById(R.id.age);
        }
    }
}