package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that has getters and setters for the data of each Playlist
 */
public class PlaylistResponse {
    @SerializedName("playid")
    @Expose
    private String playid;
    @SerializedName("userassociated")
    @Expose
    private String userassociated;
    @SerializedName("playname")
    @Expose
    private String playname;
    @SerializedName("artimg")
    @Expose
    private String artimg;
    @SerializedName("tracks")
    @Expose
    private ArrayList<Track> tracks = null;

    public PlaylistResponse(String playid, String userassociated, String playname, String artimg, ArrayList<Track> tracks) {
        this.playid = playid;
        this.userassociated = userassociated;
        this.playname = playname;
        this.artimg = artimg;
        this.tracks = tracks;
    }

    public String getPlayid() {
        return playid;
    }

    public void setPlayid(String playid) {
        this.playid = playid;
    }

    public String getUserassociated() {
        return userassociated;
    }

    public void setUserassociated(String userassociated) {
        this.userassociated = userassociated;
    }

    public String getPlayname() {
        return playname;
    }

    public String getArtimg() {
        return artimg;
    }

    public void setArtimg(String artimg) {
        this.artimg = artimg;
    }

    public void setPlayname(String playname) {
        this.playname = playname;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
