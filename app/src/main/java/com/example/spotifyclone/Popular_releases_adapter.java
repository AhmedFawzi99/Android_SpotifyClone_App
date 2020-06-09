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

<<<<<<< HEAD
public class Popular_releases_adapter extends RecyclerView.Adapter<Popular_releases_adapter.MyViewHolder3>{
        ArrayList<Type> playlists;
        Context context;
public Popular_releases_adapter(Context contextt, ArrayList<Type> playlists) {
=======
public class Popular_releases_adapter extends RecyclerView.Adapter<Popular_releases_adapter.MyViewHolder2>{
        ArrayList<RowItem> playlists;
        Context context;
public Popular_releases_adapter(Context contextt, ArrayList<RowItem> playlists) {
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
        this.context=contextt;
        this.playlists=playlists;
        }
@NonNull
@Override

<<<<<<< HEAD
public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder3(itemView);
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder3 holder, int position) {
    if (playlists.get(position).type == "playlist") {
        holder.playlistname.setText(playlists.get(position).getRowitem().getPlayname());
        Picasso.with(context).
                load(playlists.get(position).getRowitem().getArtimg())
                .into(holder.imageView);
    } else if (playlists.get(position).type == "album")
    {holder.playlistname.setText(playlists.get(position).getAlbum().getAlbum_name());
        Picasso.with(context).
                load(playlists.get(position).getAlbum().getImage_id())
                .into(holder.imageView);}
    else if(playlists.get(position).type == "single")
    {holder.playlistname.setText(playlists.get(position).getTrack().gettName());
        Picasso.with(context).
                load(playlists.get(position).getTrack().gettPreviewUrl())
                .into(holder.imageView);}

}
=======
public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder2(itemView);
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.playlistname.setText(playlists.get(position).getName());
        Picasso.with(context).
        load(playlists.get(position).getImage())
        .into(holder.imageView);
        }


>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

@Override
public int getItemCount() {
        return playlists.size();
        }
<<<<<<< HEAD
class MyViewHolder3 extends RecyclerView.ViewHolder {
    LinearLayout parent;
    public TextView playlistname;
    public ImageView imageView;
    public MyViewHolder3(View itemView) {
=======
class MyViewHolder2 extends RecyclerView.ViewHolder {
    LinearLayout parent;
    public TextView playlistname;
    public ImageView imageView;
    public MyViewHolder2(View itemView) {
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
        super(itemView);


        playlistname = (TextView) itemView.findViewById(R.id.name);
        imageView=(ImageView) itemView.findViewById(R.id.image);

        //  parent = itemView.findViewById(R.id.parent);
        // name = itemView.findViewById(R.id.name);
        // age = itemView.findViewById(R.id.age);
    }
}
}

