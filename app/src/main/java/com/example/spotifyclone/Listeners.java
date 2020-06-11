package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Response for Listeners
 * @author Salma Hazem
 * @version 1.0
 */
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

    /**
     * Getter for ID
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for ID
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for time
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     * Setter for time
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Getter for listeners
     * @return
     */
    public int getListeners() {
        return listeners;
    }

    /**
     * Setter for listeners
     * @param listeners
     */
    public void setListeners(int listeners) {
        this.listeners = listeners;
    }
}