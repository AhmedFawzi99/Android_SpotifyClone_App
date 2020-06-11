package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Response for Likes
 * @author Salma Hazem
 * @version 1.0
 */
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
     * Getter for Time
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
     * Getter for likes
     * @return
     */
    public int getLikes() {
        return likes;
    }
    /**
     * Setter for likes
     * @param likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

}