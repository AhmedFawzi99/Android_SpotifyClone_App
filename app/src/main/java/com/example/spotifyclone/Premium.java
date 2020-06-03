package com.example.spotifyclone;


import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Premium extends AppCompatActivity {
    Button bPremium;
    private static final String TAG = "Premium";

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);
        bPremium = findViewById(R.id.bPremium);
        models = new ArrayList<>();
        models.add(new Model(R.drawable.ad, "", ""));
        models.add(new Model(R.drawable.shuffle, "", ""));
        models.add(new Model(R.drawable.skips, "", ""));


        adapter = new Adapter(models, this);

        viewPager =findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0 ,130, 0);
        bPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }
    private void sendMail() {
        // This should be the email saved in the server
        String mail = "salmahazem310@yahoo.com";
        String message = "You are upgraded to Maestro";
        String subject = "Getting Premium";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(getBaseContext(),mail,subject,message);

        javaMailAPI.execute();



    }
}
