package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Listeners {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("listeners")
    @Expose
    private int listeners;

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

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }
}