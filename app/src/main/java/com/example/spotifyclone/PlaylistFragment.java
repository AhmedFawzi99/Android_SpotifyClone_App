package com.example.spotifyclone;


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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment implements OnClickListener{



    /**
     * array of playlists
     */
    private static final String TAG = "MyActivity";
    private ArrayList<RowItem> Rowitems=new ArrayList<RowItem>();
    private  int index=0;
    private int REQUEST_CODE = 0;
    static PlaylistAdapter adapter ;
    public PlaylistFragment() {
        // Required empty public constructor
    }
    private ArrayList<RowItem> Rowitems2=new ArrayList<RowItem>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivitysha main =(MainActivitysha) getActivity();

        View s=inflater.inflate(R.layout.fragment_playlist,container,false);
//        // Rowitems=main.Rowitems;
//        addPlaylist();
//        getplaylist();
        TextView textView1= (TextView) s.findViewById(R.id.createplaylist);
        ImageView button=(ImageView) s.findViewById(R.id.floatingActionButton202);
        textView1.setOnClickListener(this);
        button.setOnClickListener(this);
        adapter =new PlaylistAdapter(this.getContext(),Rowitems2);
        ListView listView=(ListView) s.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        /**
         * to detect which playlist selected and open the page that shows its songs
         * @see EachPlaylist
         */
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                RowItem r1 = Rowitems.get(position);
                Fragment selectedFragment=new EachPlaylist(r1);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).addToBackStack(null).commit();

            }
        });
        //RowItem r=Rowitems.get(index);
      /*  Button button2=(Button) s.findViewById(R.id.la);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {       }
        });*/
        // Inflate the layout for this fragment
        return s;
    }
    /**
     * filling the ArrayList with values
     */


    void getplaylist()
    {
        Call<List<RowItem>> play = RetrofitSingleton.getInstance().getApi().getplaylist2(Profile_DATA.ID);
        play.enqueue(new Callback<List<RowItem>>() {
            @Override
            public void onResponse(Call<List<RowItem>> call, Response<List<RowItem>> response) {
//Rowitems=null;
//Rowitems=new ArrayList<>();
                List<RowItem> p = response.body();
                for (RowItem play : p) {
                    Log.d(response.message(), "Entered: ");
                    Rowitems.add(new RowItem(play.getImage(), play.getName(),play.getId(),play.getSongs() ,play.getUserassociated()));
//                Toast.makeText(getContext(), "entered2", Toast.LENGTH_LONG).show();
                }
                getPlaylists1(Rowitems);

            }

            @Override
            public void onFailure(Call<List<RowItem>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }

        });
    }

    public void getPlaylists1(ArrayList<RowItem> rowitems)
    {
        Rowitems2=rowitems;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Make sure fragment codes match up
        Random rand = new Random(); //instance of random class
        int upperbound = 734948535;
        int int_random = rand.nextInt(upperbound);
        ArrayList<Tracks> songs=new ArrayList<>();
        String editTextString = data.getStringExtra(
                "STRING_RESULT");
        Toast.makeText(this.getContext(), editTextString, Toast.LENGTH_LONG).show();
        Rowitems2.add(new RowItem("https://images.macrumors.com/t/MKlRm9rIBpfcGnjTpf6ZxgpFTUg=/1600x1200/smart/article-new/2018/05/apple-music-note.jpg",editTextString,String.valueOf(int_random),songs,Profile_DATA.ID));

        getFragmentManager().beginTransaction().detach(this).attach(this).commit();


    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getplaylist();

    }


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
                DialogFragment builder2 = FloatingFragment.newInstance();
                builder2.setTargetFragment(this, REQUEST_CODE);
                builder2.show(getFragmentManager(), "tag");
                break;
        }

    }
void checklike()
{if (Rowitems2!=null)
{
    for(int i=0;i<Rowitems2.size();i++)
    {
        if(!Rowitems2.get(i).isIsliked())
        {
            Rowitems2.remove(i);
            //  Toast.makeText(this.getContext(),"removed" , Toast.LENGTH_LONG).show();

        }
//                myadapter.notifyItemRemoved(i);
        //      myadapter.notifyItemRangeChanged(i, artistslist.size());
    }

}}

   void checklikeadd(RowItem p)
   {
       Rowitems2.add(p);
   }

}
