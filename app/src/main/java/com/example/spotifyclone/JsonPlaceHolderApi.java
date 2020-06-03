package com.example.spotifyclone;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<post>> getPosts();

    @GET("getid")
    Call<getid> getid();

    @GET("songs")
    Call<ArrayList<RowItem>> getplaylists();

    @GET ("tracks")
    Call<ArrayList<Tracks>> gettracks();


    @PUT("likedislike")
    Call<getmessage> putlike(@Header("Authorization") String token, @Body likeDislike a);


    @HTTP(method = "DELETE", path = "likedislike", hasBody = true)
    Call<getmessage> deletelike(@Header("Authorization") String token,@Body likeDislike a);

    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("birthdate") String birthDate,
            @Field("gender") String gender
    );



    @GET("login")
    Call<List<LoginResponse>> userLogin();

    @GET("ArtistD")
    Call<List<ArtistResponse>> artistdata(@Query("ID") String ID);




}
