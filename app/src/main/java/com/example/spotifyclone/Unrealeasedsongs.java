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

/**
 * This Class has the songs that are not yet realesed by the user which he chooses to add them to the songs list and release them now or anytime he wants
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class Unrealeasedsongs extends DialogFragment {


    static ArrayList<Track> array = new ArrayList<Track>();
    onClickInterface onClickInterface;
    private RecyclerView.RecycledViewPool recycledViewPool;
    RecyclerView playadapt;
    Unrealeasedsongsadapter totalsongsadapter;
    totalsongsadapter adapt;
    private ImageButton backk3;
    private ImageButton addd;
    TextView text;

    static boolean add=false;

    public Unrealeasedsongs(ArrayList<Track> sentarray, totalsongsadapter ad) {
        array=sentarray;
        adapt=ad;
        Log.d(String.valueOf(array.size()), "Unrealeasedsongs: ");

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);




    }

    /**
     * the oncreate
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.songsunrealeased, container, false);

        playadapt = v.findViewById(R.id.songstotal332);
        backk3=v.findViewById(R.id.backk23);
        text=v.findViewById(R.id.songssnamesdd);
        addd=v.findViewById(R.id.add23);
        text.setText(Artist_DATA.Aname+" Unrealeased Songs");
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        playadapt.setLayoutManager(mLayoutManager);
        playadapt.addItemDecoration(new SpaceItemDecoration(2));
        playadapt.setItemAnimator(new DefaultItemAnimator());
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
        totalsongsadapter = new Unrealeasedsongsadapter(getContext(), array, onClickInterface);
        playadapt.setAdapter(totalsongsadapter);
        playadapt.setRecycledViewPool(recycledViewPool);


        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add==false) {
                    add = true;
                    totalsongsadapter.notifyDataSetChanged();
                    addd.setImageResource(R.drawable.baseline_check_24);

                }else{
                    add = false;
                    totalsongsadapter.notifyDataSetChanged();
                    addd.setImageResource(R.drawable.outline_playlist_add_24);
                    adapt.notifyDataSetChanged();

                }
            }
        });

        backk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapt.notifyDataSetChanged();
                dismiss();
            }
        });


        return v;
    }

}
