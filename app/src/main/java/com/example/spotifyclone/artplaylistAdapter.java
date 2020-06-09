package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class artplaylistAdapter extends RecyclerView.Adapter<artplaylistAdapter.PlaylistsView> {

    private List<PlaylistResponse> Playlists;
    private Context context;
    onClickInterface onClickInterface;
    public artplaylistAdapter(Context context, List<PlaylistResponse> list, onClickInterface onClickInterface) {
        this.Playlists = list;
        this.context = context;
        this.onClickInterface = onClickInterface;

    }
    @NonNull
    @Override
    public PlaylistsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaylistsView(LayoutInflater.from(context).inflate(R.layout.artitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistsView holder, final int position) {
        PlaylistResponse block = Playlists.get(position);

        holder.textViewTitle.setText(block.getPlayname());
        /// holder.textViewGenre.setText(playlist.getDescription());

// load images using picasso
        Picasso.with(context).
                load(block.getArtimg())
                .into(holder.imageViewMovie);


        holder.imageViewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickInterface.setClick(position);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickInterface.setClick(position);
            }
        });




    }

    @Override
    public int getItemCount() {
        return Playlists.size();
    }

    public class PlaylistsView extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        //private TextView textViewGenre;
        private ImageView imageViewMovie;
        private RelativeLayout layout;

        public PlaylistsView (View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.name);
            imageViewMovie = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.relatart);

        }


    }
}