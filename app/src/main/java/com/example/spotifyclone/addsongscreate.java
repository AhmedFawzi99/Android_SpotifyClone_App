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
 * This Class creates a Fragment that has songs which are not in albums and you can add them to Albums
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class AddSongsCreate extends DialogFragment {


    static ArrayList<Track> array = new ArrayList<Track>();
    onClickInterface onClickInterface;
    private RecyclerView.RecycledViewPool recycledViewPool;
    RecyclerView playadapt;
    AddSongsCreateAdapter totalsongsadapter;
    private ImageButton backk3;
    TextView text;

    static ArrayList<Track> addsongscreate = new ArrayList<Track>();

    public ArrayList<Track> createdata = new ArrayList<Track>();
    ImageButton add;
    static boolean addd=false;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);



    }

    /**
     * onCreateView of the addsongsadapterin which it checks if there are songs which are not in albums yet and then adds them if they are solo to be able to add them in Albums
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addsongscreate, container, false);


        playadapt = v.findViewById(R.id.addsongs);
        backk3=v.findViewById(R.id.back32);
        text=v.findViewById(R.id.addsongss);
        add=v.findViewById(R.id.add12);
        text.setText("Choose Songs to Add");
        backk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        array.clear();
        for (int i = 0; i < TotalSongs.array.size(); i++){
            if(TotalSongs.array.get(i).gettPname()=="No"){
                array.add(TotalSongs.array.get(i));
            }
        }
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        playadapt.setLayoutManager(mLayoutManager);
        playadapt.addItemDecoration(new SpaceItemDecoration(2));
        playadapt.setItemAnimator(new DefaultItemAnimator());
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
        totalsongsadapter = new AddSongsCreateAdapter(getContext(), array, onClickInterface);
        playadapt.setAdapter(totalsongsadapter);
        playadapt.setRecycledViewPool(recycledViewPool);

        /**
         * this is a button that when makes the check marks visible or no
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addd==false) {
                    addd = true;
                    totalsongsadapter.notifyDataSetChanged();
                    add.setImageResource(R.drawable.baseline_check_24);

                }else{
                    addd = false;
                    totalsongsadapter.notifyDataSetChanged();
                    add.setImageResource(R.drawable.outline_playlist_add_24);

                }
            }
        });

        return v;
    }


}