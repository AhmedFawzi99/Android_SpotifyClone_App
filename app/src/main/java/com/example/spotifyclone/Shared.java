package com.example.spotifyclone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.spotifyclone.Login;


public class Shared {
    // to create sharedPerefrences file
    SharedPreferences sharedPreferences;
    //to edit it
    SharedPreferences.Editor editor;
    //context pass the ref to another class
    Context context;
    int mode =0;
    String Filename = "sdfile";
    String Data="b";

    public Shared(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Filename,mode);
        editor = sharedPreferences.edit();
    }
    public void secondTime(){
        editor.putBoolean(Data, true);
        editor.commit();
    }
    public void firstTime(){
        if(!this.login()){
            Intent intent = new Intent(context, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

        }
    }

    private boolean login() {
        return sharedPreferences.getBoolean(Data, false);
    }
}

