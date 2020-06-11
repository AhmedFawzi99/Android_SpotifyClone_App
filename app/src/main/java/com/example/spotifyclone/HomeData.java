package com.example.spotifyclone;

import java.util.List;

/**
 * @author shaimaa
 * it contains both the recently played songs and the other categories in home
 */
public class HomeData {
    /**
     * members of Homedata
     */
    List<Category> data;

    Category recentlyplayed;

    /**
     * setters and getters
     */
    public Category getRecentlyplayed() {
        return recentlyplayed;
    }

    public void setRecentlyplayed(Category recentlyplayed) {
        this.recentlyplayed = recentlyplayed;
    }


    public HomeData(List<Category> data) {
        this.data = data;
    }

    public List<Category> getData() {
        return data;
    }
}