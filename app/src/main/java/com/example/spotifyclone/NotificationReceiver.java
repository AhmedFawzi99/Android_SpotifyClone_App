package com.example.spotifyclone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;



public class NotificationReceiver extends BroadcastReceiver {
    MusicActivity main = MusicActivity.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {

        String aTYPE = intent.getType();
        if(aTYPE.contentEquals("ACTION_PLAY")) {
            main.buttonPlayerAction();
        }else if(aTYPE.contentEquals("ACTION_NEXT")){
            main.next();
        }else if(aTYPE.contentEquals("ACTION_LIKE")){
            main.buttonLikeAction();
        }else  if(aTYPE.contentEquals("ACTION_PREV")) {
            main.prev();
        }

    }
}