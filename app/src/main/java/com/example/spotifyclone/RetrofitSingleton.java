package com.example.spotifyclone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by enyason on 10/9/18.
 */

public class RetrofitSingleton {

<<<<<<< HEAD
    private String BASE_URL = "https://my-json-server.typicode.com/AhmedFawzi99/jasonfakeAPI/";
=======
    private String BASE_URL = "https://my-json-server.typicode.com/ShaimaaOsama999/SHServer/";
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

    private Retrofit retrofit;
    OkHttpClient.Builder okHttpClient;


    private static RetrofitSingleton retrofitSingletonInstance;
    private Gson gson;

    private RetrofitSingleton() {

        gson = new GsonBuilder()
                .setLenient()
                .create();

        okHttpClient = new OkHttpClient.Builder().readTimeout(3, TimeUnit.MINUTES);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


    }


    public static synchronized RetrofitSingleton getInstance() {

        if (retrofitSingletonInstance == null) {
            retrofitSingletonInstance = new RetrofitSingleton();
        }

        return retrofitSingletonInstance;


    }

    public JsonPlaceHolderApi getApi() {

        return retrofit.create(JsonPlaceHolderApi.class);

    }


}