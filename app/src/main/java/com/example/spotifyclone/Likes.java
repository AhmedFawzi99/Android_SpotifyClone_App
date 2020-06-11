package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("likes")
    @Expose
    private int likes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
