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
    @GET("home")
    Call<HomeBodyResponse> getPlaylistsByCategory();
    @GET("home")
    Call<HomeBodyResponse> getrecentlyplayed();

    @GET("Category")
    Call<Category> getplaylistsdetails(@Query("id") String ID);
    @GET("Category")
    Call<Category> getalbumsdetails(@Query("ID") String ID);

    @GET("login")
    Call<List<LoginResponse>> userLogin();

    @GET("posts")
    Call<List<post>> getPosts();

    @GET("getid")
    Call<getid> getid();

    @GET("songs")
    Call<ArrayList<RowItem>> getplaylists();

    @GET ("tracks")
    Call<ArrayList<Tracks>> gettracks();

    @GET("search")
    Call<List<SearchResponse>> searchList();

    @GET("get_growth_info")
    Call<List<Growth>> getGrowthInfo();

    @GET("Playlist")
    Call<List<PlaylistResponse>> getplaylist(@Query("userassociated") String ID);

    @GET("Data")
    Call<List<RowItem>> getplaylist2(@Query("userassociated") String ID);

    @GET("ArtistD")
    Call<List<ArtistResponse>> artistdata(@Query("ID") String ID);


    @PUT("likedislike")
    Call<likeDislike> putlike(@Header("Authorization") String token, @Body likeDislike a);

    @GET("likes")
    Call<List<Likes>> getLikesInfo(@Query("id") String id);

    @GET("listeners")
    Call<List<Listeners>> getListenersInfo(@Query("id") String id);

    @HTTP(method = "DELETE", path = "likedislike", hasBody = true)
    Call<getmessage> deletelike(@Header("Authorization") String token,@Body likeDislike a);

    @GET("unrealeased")
    Call<List<Track>> unrealeased(@Query("aid") String ID);

    @FormUrlEncoded
    @POST("login")
    Call<SignUP> createuser(
             @Field("email") String Email,
             @Field("password") String Password,
             @Field("birthDate") String Date,
             @Field("gender") String Gender,
             @Field("name") String Name,
             @Field("usertype") String Type,
             @Field("ID") String ID

    );


}
