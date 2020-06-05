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

public class Player_Adapter_two extends RecyclerView.Adapter<Player_Adapter_two.PlaylistsViewHolder> {

    private List<Type> blocks;
    private Context context;
    onClickInterface onClickInterface;
    public Player_Adapter_two(Context context, List<Type> list, onClickInterface onClickInterface) {
        this.blocks = list;
        this.context = context;
        this.onClickInterface = onClickInterface;

    }
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























    /*public Player_Adapter_two(@NonNull Context context, @NonNull ArrayList<RowItem> rowItems) {
        super(context, 0, rowItems);
    }
    /**
     * to fill the listview (names and images ) of the playlist (rowItems) in the home
     */
    //  @NonNull
    // @Override

    /*  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          View listItemView=convertView;
          listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_itemhome,parent,false);

          RowItem rowItem =getItem(position);
          TextView name =(TextView) listItemView.findViewById(R.id.namehell);
          name.setText(rowItem.getName());
          ImageView image =(ImageView) listItemView.findViewById(R.id.imageViewhell);
          image.setImageResource(Integer.parseInt(rowItem.getImageid()));

          return listItemView;
      }*/
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

