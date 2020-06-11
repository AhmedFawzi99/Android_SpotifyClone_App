package com.example.spotifyclone;

import android.animation.ArgbEvaluator;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import static com.example.spotifyclone.App.CHANNEL_1_ID;

/**
 * Premium Fragment in Home Layout
 * Helping Source: <a href="https://www.youtube.com/watch?v=UsXv6VRqZKs&t=431s">https://www.youtube.com/watch?v=UsXv6VRqZKs&t=431s</a> <br>
 * @author Salma Hazem
 * @version 1.0
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
    static String message = "No Recent Activities Yet";


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
                message = "You are Premium Now";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Activity Notification")
                        .setContentText(message)
                        .setAutoCancel(true);
                Intent i = new Intent(getContext(), PermiumFragment.class);
//                startActivity(i);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("message", message);

                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
        });
        return f;
    }

    /**
     * Sends the user email entered in the login page to let them know they got premium and sets its type to premium
     */
    private void sendMail() {
        // This should be the email saved in the server
        String mail = Profile_DATA.mail;
        String message = "You are upgraded to Maestro";
        String subject = "Getting Premium";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(),mail,subject,message);

        javaMailAPI.execute();
        Profile_DATA.Type="pr";

    }



}