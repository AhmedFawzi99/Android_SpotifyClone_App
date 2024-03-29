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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * addsongscreateadapter is the adapter of addsongscreate  that is used to add the songs that has no albums <br>
 * Helping Source: <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">https://developer.android.com/guide/topics/ui/layout/recyclerview</a> <br>
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class addsongscreateadapter extends RecyclerView.Adapter<addsongscreateadapter.PlaylistsView> {

    private List<Track> Tracks;
    private Context context;
    onClickInterface onClickInterface;

    /**
     * the constructor for the adpter taking context, list to show and an onClickInterface
     * @param context
     * @param list
     * @param onClickInterface
     */
    public addsongscreateadapter(Context context, List<Track> list, onClickInterface onClickInterface) {
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
     * In the onBindViewHoler if the add button is on and a user clicks on check it checks the poition of the the album clicked and then adds the chosen songs to the album and seting it to this album
     * @param holder
     * @param position
     * @author Ahmed Mahmoud Fawzi <br>
     */
    @Override
    public void onBindViewHolder(@NonNull PlaylistsView holder, final int position) {
        Track block = Tracks.get(position);

        holder.textViewTitle.setText(block.gettName());
        /// holder.textViewGenre.setText(playlist.getDescription());

        holder.check.setVisibility(View.GONE);
        if(addsongscreate.addd){
            holder.check.setVisibility(View.VISIBLE);
        }else{
            holder.check.setVisibility(View.GONE);
        }

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addsongscreate.addd == true) {
                    Log.d(String.valueOf(artPlaylistFragment.array.get(artPlaylistFragment.pos)), "onClick: ");
                    Log.d(String.valueOf(artPlaylistFragment.pos), "onClick: ");
                    ArrayList<Track> list =new ArrayList<Track>();
                    list.add(addsongscreate.array.get(position));
                    if(artPlaylistFragment.array.get(artPlaylistFragment.pos).getTracks()==null){
                        artPlaylistFragment.array.get(artPlaylistFragment.pos).setTracks(list);}
                    else{
                        artPlaylistFragment.array.get(artPlaylistFragment.pos).getTracks().add(list.get(0));
                    }
                    addsongscreate.array.get(position).settPname( artPlaylistFragment.array.get(artPlaylistFragment.pos).getPlayname());

                    Call<String> call =  RetrofitSingleton.getInstance().getApi().putsongtop(addsongscreate.array.get(position).gettId());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d(response.body(), "onResponse: ");
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
                addsongscreate.array.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, addsongscreate.array.size());


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

    /**
     * Intializez the views and text in the adapter
     */
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
