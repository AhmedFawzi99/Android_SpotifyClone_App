package com.example.spotifyclone;

import java.util.List;

public class HomeData {
    List<Category> data;

    Category recentlyplayed;

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
