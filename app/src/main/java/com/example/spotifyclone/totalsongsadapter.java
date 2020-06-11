package com.example.spotifyclone;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * The Adapter for the total songs of the Artist
 *  * @author Ahmed Mahmoud Fawzi <br>
 */
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

    /**
     * on the onBindViewHolder the delete happens when the user checks songs deleting them from the array and also from the albums as artist removed it from the songs list so no need to keep them in the album as they are deleted
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PlaylistsView holder, final int position) {
        Track block = Tracks.get(position);

        holder.textViewTitle.setText(block.gettName());
        /// holder.textViewGenre.setText(playlist.getDescription());

        holder.check.setVisibility(View.GONE);
        if(totalsongs.deleteonoff){
            holder.check.setVisibility(View.VISIBLE);
        }else{
            holder.check.setVisibility(View.GONE);
        }

        /**
         * deleing the songs and removing them from the albums also
         */
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalsongs.deleteonoff == true) {
                    Log.d(String.valueOf(artPlaylistFragment.array.size()), "Sizee: ");
                    for (int i = 0; i < artPlaylistFragment.array.size(); i++){
                        Log.d("checker", "onClick: ");
                        Log.d(String.valueOf(artPlaylistFragment.array.size()), "onClick: ");
                        if(! (artPlaylistFragment.array.get(i).getTracks()==null)){
                            for (int j = 0; j < artPlaylistFragment.array.get(i).getTracks().size(); j++){
                                if (artPlaylistFragment.array.get(i).getTracks().get(j).equals(totalsongs.array.get(position))){
                                    artPlaylistFragment.array.get(i).getTracks().remove(j);

                                    Log.d(String.valueOf(artPlaylistFragment.array.get(i).getTracks().size()), "onClick: ");
                                }
                            }
                        }

                    }
                    Artist_DATA.TotalSongs--;
                    ArtistManagment.artsongs.setText(String.valueOf(Artist_DATA.TotalSongs));
                    totalsongs.array.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, totalsongs.array.size());
                }

            }
        });
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
        private CheckBox check;


        public PlaylistsView (View itemView) {
            super(itemView);
            check=itemView.findViewById(R.id.checkbox);
            textViewTitle = itemView.findViewById(R.id.name);
            imageViewMovie = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.relatart);

        }


    }
}
