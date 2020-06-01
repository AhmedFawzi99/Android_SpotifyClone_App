package com.example.spotifyclone;

import java.util.ArrayList;

public class Tracks {
    private String name;
    private String type;
    private String image;
    private String url;
    private  String id;
private ArrayList<Artist> artists;

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String URL) {
        this.url = URL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Tracks(String name, String type, String imageid, String URL, String id, ArrayList<Artist> artists) {
        this.name = name;
        this.type = type;
        this.image = imageid;
        this.url = URL;
        this.id = id;
        this.artists = artists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageid(String imageid) {
        this.image = imageid;
    }





    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImageid() {
        return image;
    }




}
