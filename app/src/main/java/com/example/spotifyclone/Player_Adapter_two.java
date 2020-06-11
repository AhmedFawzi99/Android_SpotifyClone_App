package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

/** @author shaimaa
 * Helping Source: <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">https://developer.android.com/guide/topics/ui/layout/recyclerview</a> <br>
 * adapter in which fills the list
 */
public class Player_Adapter_two extends RecyclerView.Adapter<Player_Adapter_two.PlaylistsViewHolder> {

    private List<Type> blocks;
    private Context context;
    onClickInterface onClickInterface;
    public Player_Adapter_two(Context context, List<Type> list, onClickInterface onClickInterface) {
        this.blocks = list;
        this.context = context;
        this.onClickInterface = onClickInterface;

    }

    /**
     * each adapter contains these main functions
     */
    @NonNull
    @Override
    public PlaylistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaylistsViewHolder(LayoutInflater.from(context).inflate(R.layout.singleitem_horizontalhome, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistsViewHolder holder, final int position) {
        Type block = blocks.get(position);
        if (block.getType().equals("playlist")) {
            holder.textViewTitle.setText(block.rowitem.getDescription());
            /// holder.textViewGenre.setText(playlist.getDescription());

// load images using picasso
            Picasso.with(context).
                    load(block.rowitem.getImage())
                    .into(holder.imageViewMovie);
            holder.card_view_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterface.setClick(position);
                }
            });

        }
        else if (block.getType().equals("album")) {

            holder.textViewTitle.setText(block.album.getDescription());
            /// holder.textViewGenre.setText(playlist.getDescription());

// load images using picasso
            Picasso.with(context).
                    load(block.album.getImage_id())
                    .into(holder.imageViewMovie);
            holder.card_view_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterface.setClick(position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return blocks.size();
    }

    public class PlaylistsViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        //private TextView textViewGenre;
        private ImageView imageViewMovie;
        private CardView card_view_home;

        public PlaylistsViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.description);
            // textViewGenre = itemView.findViewById(R.id.tv_genre);
            imageViewMovie = itemView.findViewById(R.id.image_view_playlist);
            card_view_home = itemView.findViewById(R.id.card_view_home);

        }


    }
}