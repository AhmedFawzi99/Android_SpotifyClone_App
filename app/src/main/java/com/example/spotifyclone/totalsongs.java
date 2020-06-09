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
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class totalsongs extends DialogFragment {


    static ArrayList<Track> array = new ArrayList<Track>();
    onClickInterface onClickInterface;
    private RecyclerView.RecycledViewPool recycledViewPool;
    RecyclerView playadapt;
    playlistsongsadapter totalsongsadapter;
    private ImageButton backk3;
    TextView text;

    ImageButton add;
    static ImageButton delete;
    static boolean deleteonoff=false;
    public totalsongs(ArrayList<Track> sentarray) {
        array = sentarray;
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
    }

    public totalsongs(String start) {
        Random rand = new Random(); //instance of random class
        int upperbound = 734948535;
        int int_random = rand.nextInt(upperbound);
        PlaylistResponse p=new PlaylistResponse(String.valueOf(int_random),Profile_DATA.ID,cretaeartplay.GetEditText,"https://images.macrumors.com/t/MKlRm9rIBpfcGnjTpf6ZxgpFTUg=/1600x1200/smart/article-new/2018/05/apple-music-note.jpg",null);
        array.add(p);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.songstotal, container, false);
        playadapt = v.findViewById(R.id.songstotal2);
        backk3=v.findViewById(R.id.backk2);
        text=v.findViewById(R.id.songssnames);
        text.setText("Songs");
//        backk3.setOnClickListener(lnew View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        playadapt.setLayoutManager(mLayoutManager);
        playadapt.addItemDecoration(new SpaceItemDecoration(2));
        playadapt.setItemAnimator(new DefaultItemAnimator());
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
        totalsongsadapter = new playlistsongsadapter(getContext(), array, onClickInterface);
        playadapt.setAdapter(totalsongsadapter);
        playadapt.setRecycledViewPool(recycledViewPool);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cretaeartplay a=new cretaeartplay();
                a.show(getFragmentManager(),"hii");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(deleteonoff==false) {
//                    deleteonoff = true;
//                    artplaylistAdapter.notifyDataSetChanged();
//                    delete.setImageResource(R.drawable.baseline_check_24);
//
//                }else{
//                    deleteonoff = false;
//                    artplaylistAdapter.notifyDataSetChanged();
//                    delete.setImageResource(R.drawable.outline_delete_24);
//
//                }
            }
        });
        return v;
    }
}
