package com.example.spotifyclone;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Salma Hazem
 */
public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_preff";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    public SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }
    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if(mInstance == null){
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }
    public void saveUser(User user){
        SharedPreferences sharedPrefManager = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefManager.edit();

//        editor.putInt("id", user.getId());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.putString("name", user.getName());
        editor.putString("password", user.getPassword());
        editor.putString("gender", user.getEmail());

        editor.apply();
    }
    public  boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }
    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("birthdate", null),
                sharedPreferences.getString("gender", null)
        );
    }
    public void clear(){
        SharedPreferences sharedPrefManager = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefManager.edit();
        editor.clear();
        editor.apply();
    }
}
