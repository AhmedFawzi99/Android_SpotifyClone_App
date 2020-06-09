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

public class RecentlyPlayedAdapter extends RecyclerView.Adapter<RecentlyPlayedAdapter.PlaylistsViewHolder2> {


<<<<<<< HEAD
    private List<PlaylistResponse> playlists=new ArrayList<>();
    private Context context;

    public RecentlyPlayedAdapter(Context context, List<PlaylistResponse> list) {
=======
    private List<RowItem> playlists=new ArrayList<>();
    private Context context;

    public RecentlyPlayedAdapter(Context context, List<RowItem> list) {
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

        this.playlists = list;
        this.context = context;


    }
    @NonNull
    @Override
    public PlaylistsViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PlaylistsViewHolder2(LayoutInflater.from(context).inflate(R.layout.recentlyplayedsingleitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistsViewHolder2 holder, int position) {
<<<<<<< HEAD
        PlaylistResponse playlist = playlists.get(position);
=======
        RowItem playlist = playlists.get(position);
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

        holder.textViewTitle.setText(playlist.getDescription());

        /// holder.textViewGenre.setText(playlist.getDescription());

// load images using picasso
        Picasso.with(context).
<<<<<<< HEAD
                load(playlist.getArtimg())
=======
                load(playlist.getImage())
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
                .into(holder.imageViewMovie);

    }

    @Override
    public int getItemCount() {
        return playlists.size();
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
    public class PlaylistsViewHolder2 extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        //private TextView textViewGenre;
        private CircleImageView imageViewMovie;


        public PlaylistsViewHolder2(View itemView) {

            super(itemView);
            textViewTitle = itemView.findViewById(R.id.description2);
            // textViewGenre = itemView.findViewById(R.id.tv_genre);
            imageViewMovie = itemView.findViewById(R.id.circleimage2);


        }


    }
}

