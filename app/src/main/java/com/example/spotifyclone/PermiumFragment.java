package com.example.spotifyclone;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Premium Fragment in Home Layout
 */
public class PermiumFragment extends Fragment {
    Context context; //Declare the variable context
    Button bPremium;
    private static final String TAG = "Premium";

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Pass your layout xml to the inflater and assign it to rootView.
        View f = inflater.inflate(R.layout.activity_premium, container, false);
        context = f.getContext(); // Assign your rootView to context

        bPremium = f.findViewById(R.id.bPremium);
        models = new ArrayList<>();
        models.add(new Model(R.drawable.ad, "", ""));
        models.add(new Model(R.drawable.shuffle, "", ""));
        models.add(new Model(R.drawable.skips, "", ""));


        adapter = new Adapter(models, getContext());

        viewPager =f.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0 ,130, 0);
        bPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
        return f;
    }
    private void sendMail() {
        // This should be the email saved in the server
        String mail = "salmahazem310@yahoo.com";
        String message = "You are upgraded to Maestro";
        String subject = "Getting Premium";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(),mail,subject,message);

        javaMailAPI.execute();



    }



}