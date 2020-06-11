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
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment for Artist Albums It has all the Albums that are of that the Artist loggedin
 * @author Ahmed Mahmoud Fawzi <br>
 * @version 1.0
 */
public class artPlaylistFragment extends DialogFragment {


    public static ArrayList<PlaylistResponse> array=new ArrayList<PlaylistResponse>();
    onClickInterface onClickInterface2;
    private RecyclerView.RecycledViewPool recycledViewPool;
    RecyclerView  playadapt;
    com.example.spotifyclone.artplaylistAdapter artplaylistAdapter;
    private ImageButton back;

    TextView text;
    static int valuecheck=0;
    ImageButton add;
    static ImageButton delete;
    static int pos=0;
    static boolean deleteonoff=false;
    public artPlaylistFragment(ArrayList<PlaylistResponse> sentarray) {
        array=sentarray;
        Log.d(String.valueOf(array.size()), "artPlaylistFragment: ");
    }

    /**
     * A constructor Taking a string it is called when we create a new playlist passing that name to the constructor
     * @param start a regular string
     */
    public artPlaylistFragment(String start) {
        Random rand = new Random(); //instance of random class
        int upperbound = 734948535;
        int int_random = rand.nextInt(upperbound);
        PlaylistResponse p=new PlaylistResponse(String.valueOf(int_random),Profile_DATA.ID, cretaeartplay.GetEditText,"https://images.macrumors.com/t/MKlRm9rIBpfcGnjTpf6ZxgpFTUg=/1600x1200/smart/article-new/2018/05/apple-music-note.jpg",null);
        array.add(p);
        Artist_DATA.APlaylists++;
        ArtistManagment.artplay.setText(String.valueOf(Artist_DATA.APlaylists));
        ArtistManagment.notffication(1);
        Call<String> call =  RetrofitSingleton.getInstance().getApi().putplaylist(String.valueOf(int_random));
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


    /**
     * Oncreate Function
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.FullscreenDialogTheme);
    }


    /**
     * The oncreateview where variables are initialized
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.artplaylistfrag, container, false);
        playadapt = v.findViewById(R.id.artplaylistlistview);
        text=v.findViewById(R.id.artplaylists_name);
        add=v.findViewById(R.id.add);
        delete=v.findViewById(R.id.delete);

        text.setText("Albums");
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
        /**
         * The oncClick listens to when a playlist is clicked and then checks if the album is empty or no if empty it calls addsongscreate to add new songs if there are songs that are without a list
         * and if it has songs it shows them.
         */
        onClickInterface2 =new onClickInterface() {
            @Override
            public void setClick(int abc) {
                if(valuecheck==0) {
                    pos=abc;
                    PlaylistResponse BLOCK = array.get(abc);
                    ArrayList<Track> trs = new ArrayList<Track>();
                    trs = BLOCK.getTracks();
                    EachplaylistSongs art = new EachplaylistSongs(trs, BLOCK.getPlayname());
                    art.show(getFragmentManager(), "Playlist");
                }else{
                    pos=abc;
                    addsongscreate arto = new addsongscreate();
                    arto.show(getFragmentManager(), "Playlist");
                }
            }
        };
        artplaylistAdapter = new artplaylistAdapter(getContext(), array, onClickInterface2);
        playadapt.setAdapter(artplaylistAdapter);
        playadapt.setRecycledViewPool(recycledViewPool);
        return v;
    }


}
