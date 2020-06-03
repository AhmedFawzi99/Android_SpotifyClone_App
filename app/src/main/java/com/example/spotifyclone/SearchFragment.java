package com.example.spotifyclone;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SearchFragment extends Fragment {
    private Button bSearch;
    private ScrollView mainScrollView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View s;

        s=inflater.inflate(R.layout.activity_search_page,container,false);
        bSearch =  s.findViewById(R.id.bSearch);

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Search.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivityForResult(i,0);
                getActivity().overridePendingTransition(R.anim.slideseacrch, R.anim.hold);
            }
        });

        return s;
    }



}