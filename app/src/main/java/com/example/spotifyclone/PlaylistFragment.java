package com.example.spotifyclone;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;



/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment implements OnClickListener{



<<<<<<< HEAD
    onClickInterface onClickInterface2;
    private RecyclerView.RecycledViewPool recycledViewPool;
    RecyclerView  recyclerView;
    artplaylistAdapter artplaylistAdapter;
    /**
     * array of playlists
     */
    private ArrayList<PlaylistResponse> Rowitems2=new ArrayList<PlaylistResponse>();
    private static final String TAG = "MyActivity";
    private ArrayList<PlaylistResponse> Rowitems=new ArrayList<PlaylistResponse>();
    //private ArrayList<RowItem> Rowitems=new ArrayList<RowItem>();
    private int REQUEST_CODE = 0;
=======
    /**
     * array of playlists
     */
    private static final String TAG = "MyActivity";
    private ArrayList<RowItem> Rowitems=new ArrayList<RowItem>();
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
    private  int index=0;
    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View s=inflater.inflate(R.layout.fragment_playlist,container,false);
        recyclerView= s.findViewById(R.id.listview);


        TextView textView1= (TextView) s.findViewById(R.id.createplaylist);
        ImageView button=(ImageView) s.findViewById(R.id.floatingActionButton202);
        textView1.setOnClickListener(this);
        button.setOnClickListener(this);
<<<<<<< HEAD
       onClickInterface2 =new onClickInterface() {
            @Override
            public void setClick(int abc) {
                StyleableToast.makeText(getContext(), "sdddddddsafdsg", R.style.exampleToast).show();
                PlaylistResponse r1 = Rowitems2.get(abc);
                Fragment selectedFragment=new EachPlaylist(r1.getPlayname(),r1.getArtimg(),r1.getPlayid());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplaylist,selectedFragment).addToBackStack(null).commit();
=======
        PlaylistAdapter adapter =new PlaylistAdapter(this.getContext(),Rowitems);
        ListView listView=(ListView) s.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        /**
         * to detect which playlist selected and open the page that shows its songs
         * @see EachPlaylist
         */
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                RowItem r1 = Rowitems.get(position);
                Fragment selectedFragment=new EachPlaylist(r1.getName(),r1.getImage(),r1.getId());
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

            }
        };
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recycledViewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setClickable(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        artplaylistAdapter = new artplaylistAdapter(this.getContext(), Rowitems2, onClickInterface2);
        recyclerView.setAdapter(artplaylistAdapter);
        recyclerView.setRecycledViewPool(recycledViewPool);



        /**
         * to detect which playlist selected and open the page that shows its songs
         * @see EachPlaylist
         */

        return s;
    }
<<<<<<< HEAD

void getplaylist()
{
    Call<List<PlaylistResponse>> play = RetrofitSingleton.getInstance().getApi().getplaylist(Profile_DATA.ID);
    play.enqueue(new Callback<List<PlaylistResponse>>() {
        @Override
        public void onResponse(Call<List<PlaylistResponse>> call, Response<List<PlaylistResponse>> response) {
//Rowitems=null;
//Rowitems=new ArrayList<>();
            List<PlaylistResponse> p = response.body();
            for (PlaylistResponse play : p) {
                Log.d(response.message(), "Entered: ");
                Rowitems.add(new PlaylistResponse(play.getPlayid(), play.getUserassociated(), play.getPlayname(),play.getArtimg(), play.getTracks()));
//                Toast.makeText(getContext(), "entered2", Toast.LENGTH_LONG).show();
            }
            getPlaylists1(Rowitems);

        }

        @Override
        public void onFailure(Call<List<PlaylistResponse>> call, Throwable t) {
            Log.d("Error", t.getMessage());
        }

    });
}

=======
    /**
     * filling the ArrayList with values
     */
    public void addPlaylist()
    {
        ArrayList<Tracks> songs= new ArrayList<Tracks>();
        // // songs.add(new Tracks("shaimaa"));
        ////songs.add(new Tracks("shaimaa"));
        ////songs.add(new Tracks("shaimaa"));
        ////songs.add(new Tracks("shaimaa"));
        ////songs.add(new Tracks("shaimaa"));
        ////songs.add(new Tracks("shaimaa"));
        Rowitems.add(new RowItem ("Amr Diab",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Mohamed Monir",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Mohamed Monir",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Mohamed Monir",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Mohamed Monir",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Mohamed Monir",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem ("Amr Diab", String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem ("Amr Diab", String.valueOf(R.drawable.flower) ));
        RowItem r1 = Rowitems.get(1);
        //// r1.setSongs(songs);
        RowItem r0 = Rowitems.get(0);
        ////r0.setSongs(songs);
        RowItem r2 = Rowitems.get(2);
        ////r2.setSongs(songs);
    }
    /**
     * opening the dialogFragment in case the user clicked on the floating button or "Create playlist"
     * @see DialogFragment
     * @param v
     */
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
    public void onClick(View v)
    {
        int id = v.getId();
        switch (id) {
            case R.id.createplaylist:

                DialogFragment builder = FloatingFragment.newInstance();
                builder.setTargetFragment(this, REQUEST_CODE);
                builder.show(getFragmentManager(), "tag");
                break;
            case R.id.floatingActionButton202:
                Log.i(TAG, "MyClass");
<<<<<<< HEAD

=======
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
                DialogFragment builder2 = FloatingFragment.newInstance();
                builder2.setTargetFragment(this, REQUEST_CODE);
                builder2.show(getFragmentManager(), "tag");
                break;
        }

    }
public void getPlaylists1(ArrayList<PlaylistResponse> rowitems)
{
      Rowitems2=rowitems;

}
int i=0;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Make sure fragment codes match up
        Random rand = new Random(); //instance of random class
        int upperbound = 734948535;
        int int_random = rand.nextInt(upperbound);

        String editTextString = data.getStringExtra(
                "STRING_RESULT");
        Toast.makeText(this.getContext(), editTextString, Toast.LENGTH_LONG).show();
        Rowitems2.add(new PlaylistResponse(editTextString,String.valueOf(int_random),"https://images.macrumors.com/t/MKlRm9rIBpfcGnjTpf6ZxgpFTUg=/1600x1200/smart/article-new/2018/05/apple-music-note.jpg",Profile_DATA.ID));
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getplaylist();

    }

}
