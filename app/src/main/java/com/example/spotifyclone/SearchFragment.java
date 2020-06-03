package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SearchFragment extends Fragment implements View.OnClickListener{
    Button bSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View s;

        s=inflater.inflate(R.layout.activity_search_page,container,false);
        bSearch = (Button)s.findViewById(R.id.bSearch);
        bSearch.setOnClickListener(this);


        return s;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSearch:
                Intent intent = new Intent(getActivity(), Search.class);
                startActivity(intent);
        }

    }
}