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

public class Popular_releases_adapter extends RecyclerView.Adapter<Popular_releases_adapter.MyViewHolder2>{
        ArrayList<RowItem> playlists;
        Context context;
public Popular_releases_adapter(Context contextt, ArrayList<RowItem> playlists) {
        this.context=contextt;
        this.playlists=playlists;
        }
@NonNull
@Override

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



@Override
public int getItemCount() {
        return playlists.size();
        }
class MyViewHolder2 extends RecyclerView.ViewHolder {
    LinearLayout parent;
    public TextView playlistname;
    public ImageView imageView;
    public MyViewHolder2(View itemView) {
        super(itemView);


        playlistname = (TextView) itemView.findViewById(R.id.name);
        imageView=(ImageView) itemView.findViewById(R.id.image);

        //  parent = itemView.findViewById(R.id.parent);
        // name = itemView.findViewById(R.id.name);
        // age = itemView.findViewById(R.id.age);
    }
}
}

