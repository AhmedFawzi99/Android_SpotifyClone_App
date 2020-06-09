package com.example.spotifyclone;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.muddzdev.styleabletoast.StyleableToast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class artPlaylistFragment extends DialogFragment {


    static ArrayList<PlaylistResponse> array=new ArrayList<PlaylistResponse>();
    onClickInterface onClickInterface2;
    private RecyclerView.RecycledViewPool recycledViewPool;
    RecyclerView  playadapt;
    artplaylistAdapter artplaylistAdapter;
    private ImageButton back;
    TextView text;

    ImageButton add;
    static ImageButton delete;
    static boolean deleteonoff=false;
    public artPlaylistFragment(ArrayList<PlaylistResponse> sentarray) {
        array=sentarray;
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
    }

    public artPlaylistFragment(String start) {

        Random rand = new Random(); //instance of random class
        int upperbound = 734948535;
        int int_random = rand.nextInt(upperbound);
        PlaylistResponse p=new PlaylistResponse(String.valueOf(int_random),Profile_DATA.ID,cretaeartplay.GetEditText,"https://images.macrumors.com/t/MKlRm9rIBpfcGnjTpf6ZxgpFTUg=/1600x1200/smart/article-new/2018/05/apple-music-note.jpg",null);
        array.add(p);
        ArtistManagment art = new ArtistManagment();
        Artist_DATA.APlaylists++;
        ArtistManagment.artplay.setText(String.valueOf(Artist_DATA.APlaylists));
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.FullscreenDialogTheme);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.artplaylistfrag, container, false);
        playadapt = v.findViewById(R.id.artplaylistlistview);
        text=v.findViewById(R.id.artplaylists_name);
        add=v.findViewById(R.id.add);
        delete=v.findViewById(R.id.delete);
        text.setText("Playlists");
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        playadapt.setLayoutManager(mLayoutManager);
        playadapt.setClickable(true);
        playadapt.addItemDecoration(new SpaceItemDecoration(2));
        playadapt.setItemAnimator(new DefaultItemAnimator());
        back=v.findViewById(R.id.backk);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");

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
                if(deleteonoff==false) {
                    deleteonoff = true;
                    artplaylistAdapter.notifyDataSetChanged();
                    delete.setImageResource(R.drawable.baseline_check_24);

                }else{
                    deleteonoff = false;
                    artplaylistAdapter.notifyDataSetChanged();
                    delete.setImageResource(R.drawable.outline_delete_24);

                }
            }
        });
        onClickInterface2 =new onClickInterface() {
            @Override
            public void setClick(int abc) {
                StyleableToast.makeText(getContext(), "sdddddddsafdsg", R.style.exampleToast).show();
                Log.d("gfdswert", "setClick: ");
                PlaylistResponse BLOCK = array.get(abc);
                ArrayList<Track> trs= new ArrayList<Track>();
                trs=BLOCK.getTracks();
                EachplaylistSongs art = new EachplaylistSongs(trs,BLOCK.getPlayname());
                art.show(getFragmentManager(), "Playlist");

            }
        };
        artplaylistAdapter = new artplaylistAdapter(getContext(), array, onClickInterface2);
        playadapt.setAdapter(artplaylistAdapter);
        playadapt.setRecycledViewPool(recycledViewPool);
        return v;

    }


}
