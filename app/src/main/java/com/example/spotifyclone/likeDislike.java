package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This Class is Mainly for the Like/Dislike Request it has only 1 parameters which is the Id and has setters and getters for it.
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class LikeDislike {

    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public LikeDislike(String id) {
        this.id = id;
    }


    }
