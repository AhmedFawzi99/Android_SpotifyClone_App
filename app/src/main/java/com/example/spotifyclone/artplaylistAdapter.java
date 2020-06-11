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
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * This is an adapter for the artist Albums that shows all the albums of the artist loggedin <br>
 * Helping Source: <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">https://developer.android.com/guide/topics/ui/layout/recyclerview</a> <br>
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class artplaylistAdapter extends RecyclerView.Adapter<artplaylistAdapter.PlaylistsView> {

    private List<PlaylistResponse> Playlists;
    private Context context;
    onClickInterface onClickInterface;
    CheckBox check;
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

    /**
     * the onBindViewHolder checks if an Album is chosen to delete and deletes it and all the songs it has because if an album is deleted the songs are deleted unless the user add them to realease again
     * @param holder
     * @param position
     * @author Ahmed Mahmoud Fawzi <br>
     */
    @Override
    public void onBindViewHolder(@NonNull PlaylistsView holder, final int position) {
        PlaylistResponse block = Playlists.get(position);

        holder.textViewTitle.setText(block.getPlayname());
        /// holder.textViewGenre.setText(playlist.getDescription());

// load images using picasso
        Picasso.with(context).
                load(block.getArtimg())
                .into(holder.imageViewMovie);
        holder.checkbox.setVisibility(View.GONE);

        if(artPlaylistFragment.deleteonoff){
            holder.checkbox.setVisibility(View.VISIBLE);
        }else{
            holder.checkbox.setVisibility(View.GONE);
        }
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (artPlaylistFragment.deleteonoff == true) {
                    if(artPlaylistFragment.array.get(position).getTracks()==null)
                    {
                    }
                    else{
                        for (int j = 0; j < artPlaylistFragment.array.get(position).getTracks().size(); j++)
                        {
                            Artist_DATA.TotalSongs--;
                            totalsongs.array.remove(artPlaylistFragment.array.get(position).getTracks().get(j));
                        }
                    }
                    ArtistManagment.artsongs.setText(String.valueOf(Artist_DATA.TotalSongs));
                    artPlaylistFragment.array.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, artPlaylistFragment.array.size());
                    ArtistManagment art = new ArtistManagment();
                    Artist_DATA.APlaylists--;
                    ArtistManagment.artplay.setText(String.valueOf(Artist_DATA.APlaylists));

                }
            }
        });
        /**
         * when the user clicks on the Album it checks if it is empty or not to add songs or show the songs contained
         * @author Ahmed Mahmoud Fawzi <br>
         */
        holder.imageViewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(! (artPlaylistFragment.array.get(position).getTracks()==null)){
                    artPlaylistFragment.valuecheck=0;
                    onClickInterface.setClick(position);
                }else{
                    artPlaylistFragment.valuecheck=1;
                    onClickInterface.setClick(position);
                }

            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(! (artPlaylistFragment.array.get(position).getTracks()==null)){
                    artPlaylistFragment.valuecheck=0;
                    onClickInterface.setClick(position);
                }else{
                    artPlaylistFragment.valuecheck=1;
                    onClickInterface.setClick(position);
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return Playlists.size();
    }

    /**
     * Initialize the Image and text and checbox of the adapter contents
     * @author Ahmed Mahmoud Fawzi <br>
     */
    public class PlaylistsView extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        //private TextView textViewGenre;
        private ImageView imageViewMovie;
        private RelativeLayout layout;
        public CheckBox checkbox;

        public PlaylistsView (View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.name);
            imageViewMovie = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.relatart);
            checkbox=itemView.findViewById(R.id.checkbox);
        }


    }
}

