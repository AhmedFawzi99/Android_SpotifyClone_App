package com.example.spotifyclone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * Premium Fragment in Home Layout
 */
public class PermiumFragment extends Fragment {
    Context context; //Declare the variable context

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Pass your layout xml to the inflater and assign it to rootView.
        View rootView = inflater.inflate(R.layout.activity_premium, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Button bPremium = (Button) rootView.findViewById(R.id.bPremium);
        bPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context, Premium.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}