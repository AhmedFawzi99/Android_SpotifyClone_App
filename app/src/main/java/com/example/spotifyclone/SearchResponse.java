package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Response to get the list to search from
 * @author Salma Hazem
 * @version 1.0
 */
public class SearchResponse {
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}