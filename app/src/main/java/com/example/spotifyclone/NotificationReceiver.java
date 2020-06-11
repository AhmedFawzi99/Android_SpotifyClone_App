package com.example.spotifyclone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/**
 * This Class Mainly focuses on the Actions of the Notification for each Action a type is set for it. <br>
 * Inside this extended BroadcastReceiver class the Type of each Action is CHeck and an function is called in response. <br>
 * @author Ahmed Mahmoud Fawzi <br>
 * @version 1.0
 */
public class NotificationReceiver extends BroadcastReceiver {
    MusicActivity main = MusicActivity.getInstance();

    /**
     * The onReceive of the Action Intents checking the types and calling their functions.
     * @param context
     * @param intent
     */
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