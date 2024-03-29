package com.example.spotifyclone;

import java.util.ArrayList;

/**
 *@author  shaimaa
 * songs class
 */
public class Tracks {
    /**
     *members of the class
     */
    private String name;
    private String type;
    private String image;
    private String url;
    private  String id;
    private boolean isliked;
    private String pname;
    private String aname;

    /**
     * setters and getters
     * @return
     */
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

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

    public boolean getIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
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