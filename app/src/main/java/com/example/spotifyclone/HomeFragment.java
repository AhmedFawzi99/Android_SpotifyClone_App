package com.example.spotifyclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<RowItem> Rowitems=new ArrayList<RowItem>();
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivitysha main =(MainActivitysha) getActivity();
        // Rowitems=main.Rowitems;
        addPlaylist();
        View v= inflater.inflate(R.layout.list_itemhome2,container,false);
        Player_Adapter_two adapter =new Player_Adapter_two(this.getContext(),Rowitems);
        ListView listView=(ListView) v.findViewById(R.id.playlistshome);
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
        return v;
    }
    public void addPlaylist()
    {

        Rowitems.add(new RowItem ("Amr Diab",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Hamza Namira",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Mohamed Monir",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Maroons",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Adele",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem("Demi lovato",String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem ("Sharmofers", String.valueOf(R.drawable.flower) ));
        Rowitems.add(new RowItem ("Cairokee", String.valueOf(R.drawable.flower) ));
    }
}