package com.example.spotifyclone;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String AUTH = "Basic" + Base64.encodeToString(("").getBytes(), Base64.NO_WRAP);
    private static final String BASE_URL = "http://3.137.69.49:3000/user/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static RetrofitClient mInstance;
    private static Retrofit retrofit = null;

    private RetrofitClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Authorization", AUTH)
                                        .method(original.method(), original.body());
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                ).build();

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

        }
    }
    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public JsonPlaceHolderApi getApi(){
        return retrofit.create(JsonPlaceHolderApi.class);
        }

}
