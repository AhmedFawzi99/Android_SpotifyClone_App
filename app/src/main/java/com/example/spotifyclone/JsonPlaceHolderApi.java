package com.example.spotifyclone;

import java.util.ArrayList;
import java.util.List;

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

/**
 * The Requests Are Defined in this Interface
 */
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



    @GET("songs")
    Call<ArrayList<RowItem>> getplaylists();

    @GET ("tracks")
    Call<ArrayList<Tracks>> gettracks();

    @GET("search")
    Call<List<SearchResponse>> searchList();

    /**
     * A request to Get the Plalists of the user using the usserassociated to that playlist
     * @param ID usserassociated to that playlist
     * @return
     */
    @GET("Playlist")
    Call<List<PlaylistResponse>> getplaylist(@Query("userassociated") String ID);

    /**
     * A request to Get the Plalists of the user using the usserassociated to that playlist
     * @param ID usserassociated to that playlist
     * @return
     */
    @GET("Data")
    Call<List<RowItem>> getplaylist2(@Query("userassociated") String ID);

    /**
     * A request to GET the ArtistData
     * @param ID usserassociated to that playlist
     * @return
     */
    @GET("ArtistD")
    Call<List<ArtistResponse>> artistdata(@Query("ID") String ID);

    /**
     * PUT request of the like Button
     * @param token  User Token as HEADER parameter
     * @param a  This parameter is the id of the song to be liked
     * @return
     */
    @PUT("likedislike")
    Call<likeDislike> putlike(@Header("Authorization") String token, @Body likeDislike a);

    @GET("likes")
    Call<List<Likes>> getLikesInfo(@Query("id") String id);

    @GET("listeners")
    Call<List<Listeners>> getListenersInfo(@Query("id") String id);

    /**
     * DELETE request of the like Button
     * @param token  User Token as HEADER parameter
     * @param a  This parameter is the id of the song to be liked
     * @return
     */
    @HTTP(method = "DELETE", path = "likedislike", hasBody = true)
    Call<getmessage> deletelike(@Header("Authorization") String token, @Body likeDislike a);

    /**
     * Get the unrealeased songs of the user from the server so that he can realease them on spotify at any time he wants
     * @param ID
     * @return
     */
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

    /**
     * PUT request for adding songs to albums
     * @param id
     * @return
     */
    @PUT("addsongtop")
    Call<String> putsongtop(@Query("sid") String id);


    /**
     * PUT request for adding albums
     * @param id
     * @return
     */
    @PUT("addplaylist")
    Call<String> putplaylist(@Query("pid") String id);

    /**
     * PUT request for adding songs
     * @param id
     * @return
     */
    @PUT("addsong")
    Call<String> putsong(@Query("sid") String id);

    @GET("blocks")
    Call<ArrayList<Type>> getlist();



}
