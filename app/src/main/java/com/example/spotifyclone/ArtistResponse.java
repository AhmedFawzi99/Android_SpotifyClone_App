package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistResponse {
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Playlists")
    @Expose
    private Integer playlists;
    @SerializedName("Followers")
    @Expose
    private Integer followers;
    @SerializedName("Total Songs")
    @Expose
    private Integer totalSongs;
    @SerializedName("PS")
    @Expose
    private String pS;
    @SerializedName("Tlisteners")
    @Expose
    private Integer tlisteners;
    @SerializedName("TLikes")
    @Expose
    private Integer tLikes;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Integer playlists) {
        this.playlists = playlists;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getTotalSongs() {
        return totalSongs;
    }

    public void setTotalSongs(Integer totalSongs) {
        this.totalSongs = totalSongs;
    }

    public String getPS() {
        return pS;
    }

    public void setPS(String pS) {
        this.pS = pS;
    }

    public Integer getTlisteners() {
        return tlisteners;
    }

    public void setTlisteners(Integer tlisteners) {
        this.tlisteners = tlisteners;
    }

    public Integer getTLikes() {
        return tLikes;
    }

    public void setTLikes(Integer tLikes) {
        this.tLikes = tLikes;
    }
}
