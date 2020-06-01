package com.example.spotifyclone;

import java.util.ArrayList;

public class RowItem {
    private String imageid;
    private String name;
    private String id;
    private String description;
    private String type;
    private ArrayList<Tracks> songs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Tracks> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Tracks> songs) {
        this.songs = songs;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RowItem(String Name, String Imageid)
    {
        this.name=Name;
        this.imageid=Imageid;
    }
    public String getName()
    {

        return name;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageid()
    {
        return imageid;
    }
}
