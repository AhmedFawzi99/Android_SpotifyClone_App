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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The Adapter forr the songs that are not yet released and waiting to be added by the Artist.
 * Helping Source: <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">https://developer.android.com/guide/topics/ui/layout/recyclerview</a> <br>
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class Unrealeasedsongsadapter extends RecyclerView.Adapter<Unrealeasedsongsadapter.PlaylistsView> {

    private List<Track> Tracks;
    private Context context;
    onClickInterface onClickInterface;
    public Unrealeasedsongsadapter(Context context, List<Track> list, onClickInterface onClickInterface) {
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
     * onBindViewHolder adds the unreleased songs to the adapter
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PlaylistsView holder, final int position) {
        Track block = Tracks.get(position);

        holder.textViewTitle.setText(block.gettName());
        /// holder.textViewGenre.setText(playlist.getDescription());

        holder.check.setVisibility(View.VISIBLE);
        if(Unrealeasedsongs.add){
            holder.check.setVisibility(View.VISIBLE);
        }else{
            holder.check.setVisibility(View.GONE);
        }
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Unrealeasedsongs.add == true){

                    totalsongs songss=new totalsongs(Unrealeasedsongs.array.get(position));
                    ArtistManagment.notffication(2);
                    Call<String> call =  RetrofitSingleton.getInstance().getApi().putsong(Unrealeasedsongs.array.get(position).gettId());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d(response.body(), "onResponse: ");
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    Unrealeasedsongs.array.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, Unrealeasedsongs.array.size());

                }
            }
        });
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

