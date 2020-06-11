package com.example.spotifyclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author shaimaa
 * class artistadapter that fill the recyclerview
 */
public class Artist_Adapter extends RecyclerView.Adapter<Artist_Adapter.MyViewHolder2>{
    /**
     * array of artists that will be shown
     */
    ArrayList<Artist> artists;
    Context context;
    onClickInterface onClickInterface;

    /**
     * the constructor
     * @param contextt
     * @param artistist
     * @param onClickInterface
     */
    public Artist_Adapter(Context contextt, ArrayList<Artist> artistist, onClickInterface onClickInterface) {
        this.context=contextt;
        this.artists=artistist;
        this.onClickInterface = onClickInterface;
    }
    @NonNull
    @Override

    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_list, parent, false);
        return new MyViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, final int position) {
        holder.artistname.setText(artists.get(position).getName());
        Picasso.with(context).
                load(artists.get(position).getImage())
                .into(holder.imageViewartist);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.setClick(position);



            }
        });
    }



    @Override
    public int getItemCount() {
        return artists.size();
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder {
        LinearLayout parent;
        public TextView artistname;
        public CircleImageView imageViewartist;
        public CardView cardView;
        public MyViewHolder2(View itemView) {
            super(itemView);

            cardView= (CardView)  itemView.findViewById(R.id.card_view_artist);
            artistname = (TextView) itemView.findViewById(R.id.artistname);
            imageViewartist=(CircleImageView) itemView.findViewById(R.id.circleimageartist);

        }
    }
}