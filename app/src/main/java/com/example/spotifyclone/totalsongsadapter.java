package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class totalsongsadapter extends RecyclerView.Adapter<totalsongsadapter.PlaylistsView> {

    private List<Track> Tracks;
    private Context context;
    onClickInterface onClickInterface;
    public totalsongsadapter(Context context, List<Track> list, onClickInterface onClickInterface) {
        this.Tracks = list;
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
        Track block = Tracks.get(position);

        holder.textViewTitle.setText(block.gettName());
        /// holder.textViewGenre.setText(playlist.getDescription());



// load images using picasso
        Picasso.with(context).
                load(block.gettPreviewUrl())
                .into(holder.imageViewMovie);
    }

    @Override
    public int getItemCount() {
        return Tracks.size();
    }

    public class PlaylistsView extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
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

