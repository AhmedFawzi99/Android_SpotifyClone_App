package com.example.spotifyclone;
/**
 * @author shaimaa
 * class that contains the whole data of home
 */
public class HomeBodyResponse {
    /**
     * members of homebodyrespon
     */
    String status;
    HomeData data;

    public HomeBodyResponse(String status, HomeData data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public HomeData getData() {
        return data;
    }
}