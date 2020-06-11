package com.example.spotifyclone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A class that is resposible to show total songs of the artist
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class totalsongs extends DialogFragment {


    static ArrayList<Track> array = new ArrayList<Track>();
    onClickInterface onClickInterface;
    private RecyclerView.RecycledViewPool recycledViewPool;
    RecyclerView playadapt;
    com.example.spotifyclone.totalsongsadapter totalsongsadapter;
    private ImageButton backk3;
    TextView text;

    static ArrayList<Track> sendingarray = new ArrayList<Track>();

    public ArrayList<Track> createdata = new ArrayList<Track>();
    ImageButton add;
    static ImageButton delete;
    static boolean deleteonoff=false;
    public totalsongs(ArrayList<Track> sentarray) {
        array = sentarray;
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
    }
    public totalsongs(Track t) {
        array.add(t);
        Artist_DATA.TotalSongs++;
        ArtistManagment.artsongs.setText(String.valueOf(Artist_DATA.TotalSongs));

    }
    public totalsongs() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
    }

    /**
     * the onCreateView where the adapter is set and the buttons for the add and delete songs which calls their functions
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.songstotal, container, false);

        playadapt = v.findViewById(R.id.songstotal2);
        backk3=v.findViewById(R.id.backk2);
        text=v.findViewById(R.id.songssnames);
        add=v.findViewById(R.id.add2);
        delete=v.findViewById(R.id.delete2);
        text.setText("Songs");
        backk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        playadapt.setLayoutManager(mLayoutManager);
        playadapt.addItemDecoration(new SpaceItemDecoration(2));
        playadapt.setItemAnimator(new DefaultItemAnimator());
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
        totalsongsadapter = new totalsongsadapter(getContext(), array, onClickInterface);
        playadapt.setAdapter(totalsongsadapter);
        playadapt.setRecycledViewPool(recycledViewPool);
        if(ArtistManagment.val==0) {
            Call<List<Track>> call = RetrofitSingleton.getInstance().getApi().unrealeased(Profile_DATA.ID);
            call.enqueue(new Callback<List<Track>>() {
                @Override
                public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {

                    Log.d(response.message(), "Entered: ");
                    List<Track> login = response.body();
                    List<Track> a = response.body();
                    for (Track art : a) {
                        Log.d(response.message(), "Entered: ");
                        createdata.add(new Track(art.getAid(), art.getAname(), art.getUrl(), art.gettId(), art.gettName(), "No", art.getIsliked(), art.gettPreviewUrl()));
                    }
                    getTracks(createdata);
                }

                @Override
                public void onFailure(Call<List<Track>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }

            });


            ArtistManagment.val=1;
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Unrealeasedsongs art = new Unrealeasedsongs(sendingarray,totalsongsadapter);
                art.show(getFragmentManager(), "Playlist");

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteonoff==false) {
                    deleteonoff = true;
                    totalsongsadapter.notifyDataSetChanged();
                    delete.setImageResource(R.drawable.baseline_check_24);

                }else{
                    deleteonoff = false;
                    totalsongsadapter.notifyDataSetChanged();
                    delete.setImageResource(R.drawable.outline_delete_24);

                }
            }
        });
        return v;
    }

    /**
     * getting the tracks that are not released yet from the database and send them
     * @param grade
     */
    public void getTracks( ArrayList<Track> grade )
    {
        sendingarray = grade;
        Log.d(String.valueOf(sendingarray.size()), "getTracks: ");

    }

}