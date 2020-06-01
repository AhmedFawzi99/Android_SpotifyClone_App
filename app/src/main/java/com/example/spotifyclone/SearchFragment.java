package com.example.spotifyclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View s;

        s=inflater.inflate(R.layout.fragment_search,container,false);
        final Button button=(Button) s.findViewById(R.id.button3);
        Button button2=(Button) s.findViewById(R.id.button4);
       button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
           }
       });

        return inflater.inflate(R.layout.fragment_search,container,false);
    }
}
