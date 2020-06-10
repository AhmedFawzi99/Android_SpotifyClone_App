package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fansalsolike_Adapter extends RecyclerView.Adapter<Fansalsolike_Adapter.PlaylistsViewHolder26> {


    private List<Artist> artists=new ArrayList<>();
    private Context context;

    public Fansalsolike_Adapter(Context context, List<Artist> list) {

        this.artists = list;
        this.context = context;


    }
    @NonNull
    @Override
    public PlaylistsViewHolder26 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PlaylistsViewHolder26(LayoutInflater.from(context).inflate(R.layout.recentlyplayedsingleitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistsViewHolder26 holder, int position) {
        Artist playlist = artists.get(position);

        holder.textViewTitle.setText(playlist.getName());

        /// holder.textViewGenre.setText(playlist.getDescription());

// load images using picasso
        Picasso.with(context).
                load(playlist.getImage())
                .into(holder.imageViewMovie);

    }

    @Override
    public int getItemCount() {
        return artists.size();
    }


    public class PlaylistsViewHolder26 extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        //private TextView textViewGenre;
        private CircleImageView imageViewMovie;


        public PlaylistsViewHolder26(View itemView) {

            super(itemView);
            textViewTitle = itemView.findViewById(R.id.description2);
            // textViewGenre = itemView.findViewById(R.id.tv_genre);
            imageViewMovie = itemView.findViewById(R.id.circleimage2);


        }


    }
}

