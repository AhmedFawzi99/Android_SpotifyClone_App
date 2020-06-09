package com.example.spotifyclone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("home")
    Call<HomeBodyResponse> getPlaylistsByCategory();
    @GET("home")
    Call<HomeBodyResponse> getrecentlyplayed();
<<<<<<< HEAD
    @POST ("Playlist")
    Call<PlaylistResponse> addplaylist(@Field("playid") String playid, @Field("userassociated") String userassociated, @Field("playname") String playname);
    @GET ("Playlist")
    Call<List<PlaylistResponse>>  getplaylist (@Query("userassociated")String id);
    @GET("Category")
    Call<Category> getchooseartists();
=======

>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
    @GET("Category")
    Call<Category> getplaylistsdetails(@Query("id") String ID);
    @GET("Category")
    Call<Category> getalbumsdetails(@Query("ID") String ID);
<<<<<<< HEAD

    @GET("login")
    Call<List<LoginResponse>> userLogin();
=======
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

    @GET("posts")
    Call<List<post>> getPosts();

    @GET("getid")
    Call<getid> getid();
    @PUT("likedislike")
    Call<likeDislike> putlike(@Header("Authorization") String token, @Body likeDislike a);

    @GET("songs")
    Call<ArrayList<PlaylistResponse>> getplaylists();

    @GET ("tracks")
    Call<ArrayList<Track>> gettracks();

    @GET("search")
    Call<List<SearchResponse>> searchList();

    @GET("get_growth_info")
    Call<List<Growth>> getGrowthInfo();

    @GET("ArtistD")
    Call<List<ArtistResponse>> artistdata(@Query("ID") String ID);




    @HTTP(method = "DELETE", path = "likedislike", hasBody = true)
    Call<getmessage> deletelike(@Header("Authorization") String token,@Body likeDislike a);

    @POST("SignUp")
    Call<SignUP> createuser(@Body SignUP Signup);



}
