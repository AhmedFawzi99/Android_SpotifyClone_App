package com.example.spotifyclone;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EachplaylistSongs extends DialogFragment {


    ArrayList<Track> array = new ArrayList<Track>();
    onClickInterface onClickInterface;
    private RecyclerView.RecycledViewPool recycledViewPool;
    RecyclerView playadapt;
    static playlistsongsadapter playlistsongsadapter;
    private ImageButton backk2;
    private String data;
    TextView text;
    ImageButton add;

    public EachplaylistSongs(ArrayList<Track> sentarray,String a) {
        array = sentarray;
        data=a;
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.playsongs, container, false);
        playadapt = v.findViewById(R.id.plsysongs2);
        backk2=v.findViewById(R.id.backk3);
        text=v.findViewById(R.id.attachedtoplaylist);
        text.setText(data);
        backk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        add=v.findViewById(R.id.add4);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        playadapt.setLayoutManager(mLayoutManager);
        playadapt.addItemDecoration(new SpaceItemDecoration(2));
        playadapt.setItemAnimator(new DefaultItemAnimator());
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
        playlistsongsadapter = new playlistsongsadapter(getContext(), array, onClickInterface);
        playadapt.setAdapter(playlistsongsadapter);
        playadapt.setRecycledViewPool(recycledViewPool);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsongscreate arto = new addsongscreate();
                arto.show(getFragmentManager(), "Playlist");

            }
        });


//        onClickInterface = new onClickInterface() {
//            @Override
//            public void setClick(int abc) {
//
//
//                Track BLOCK = array.get(abc);
//                BLOCK.getTracks();
//
//
//            }
//
//        };
        return v;
    }
}
