package com.example.spotifyclone;

import java.util.ArrayList;

/**
 * @author Shaimaa Osama
 */
public class RowItem {
    /**
     * each playlist contains of an image
     */
    private String image;
    /**
     * each playlist contains of a name
     */
    private String name;
    /**
     * each playlist contains of an id
     */
    private String id;
    /**
     * each playlist contains of a description
     */
    private String description;
    /**
     * each playlist contains of a type
     */
    private String type;
    /**
     * each playlist contains of an array of songs
     */
    private ArrayList<Tracks> songs;
    private boolean isliked=true;

    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

    public String getUserassociated() {
        return userassociated;
    }

    public void setUserassociated(String userassociated) {
        this.userassociated = userassociated;
    }

    private String userassociated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public RowItem(String image, String name, String id, ArrayList<Tracks> songs, String userassociated) {
        this.image = image;
        this.name = name;
        this.id = id;
        this.songs = songs;
        this.userassociated = userassociated;
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
        this.image =Imageid;
    }
    public String getName()
    {

        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage()
    {
        return image;
    }
}
