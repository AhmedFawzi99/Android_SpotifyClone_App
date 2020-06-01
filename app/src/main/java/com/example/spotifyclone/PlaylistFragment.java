package com.example.spotifyclone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment implements OnClickListener{




    private ArrayList<RowItem> Rowitems=new ArrayList<RowItem>();
    private  int index=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivitysha main =(MainActivitysha) getActivity();

        View s=inflater.inflate(R.layout.fragment_playlist,container,false);
        // Rowitems=main.Rowitems;
        addPlaylist();
        TextView textView1= (TextView) s.findViewById(R.id.createplaylist);
        FloatingActionButton button=(FloatingActionButton) s.findViewById(R.id.floatingActionButton202);
        textView1.setOnClickListener(this);
        button.setOnClickListener(this);
        PlaylistAdapter adapter =new PlaylistAdapter(this.getContext(),Rowitems);
        ListView listView=(ListView) s.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                RowItem r1 = Rowitems.get(position);
                Fragment selectedFragment=new EachPlaylist(r1.getName(),r1.getImageid());
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

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
    public void onClick(View v)
    {
        int id = v.getId();
        switch (id) {
            case R.id.createplaylist:
                DialogFragment builder = FloatingFragment.newInstance();
                builder.show(getFragmentManager(), "tag");

                break;
            case R.id.floatingActionButton202:
                DialogFragment builder2 = FloatingFragment.newInstance();
                builder2.show(getFragmentManager(), "tag");



                break;
        }

    }



}
