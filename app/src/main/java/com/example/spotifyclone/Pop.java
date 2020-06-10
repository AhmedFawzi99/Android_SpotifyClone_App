package com.example.spotifyclone;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Pop extends Activity {
    public Button subscribeadd;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.adds_pop);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        subscribeadd=findViewById(R.id.subscribeadd);
        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*0.95),(int)(height*0.7));

        subscribeadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = "salmahazem310@yahoo.com";
                String message = "You are upgraded to Maestro";
                String subject = "Getting Premium";
                JavaMailAPI javaMailAPI = new JavaMailAPI(Pop.this,mail,subject,message);
                javaMailAPI.execute();
                Profile_DATA.Type="pr";
            }
        });
    }


}
