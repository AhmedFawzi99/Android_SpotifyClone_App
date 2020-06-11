package com.example.spotifyclone;

import java.util.ArrayList;

/**
 * @author Shaimaa
 * album class
 */
public class Album {
    /**
     * string ID of the album
     */
    String ID;
    /**
     * string  the album name
     */
    String Album_name;
    /**
     * string  the album image
     */
    String Image_id;

    /**
     * Each album contains of a type
     */
    private String type;
    /**
     * description of album
     */
    String description;
    /**
     * Array of artists for each album
     */
    ArrayList<Artist> artists ;
    /**
     * Array of songs for each album
     */
    ArrayList<Tracks> Songs;
    /**
     * boolean isliked whether the album is liked or not
     */
    boolean isliked=false;

    /**
     * the setters and getters
     */
    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }


    public String getDescription() {
        return description;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAlbum_name() {
        return Album_name;
    }

    public void setAlbum_name(String album_name) {
        Album_name = album_name;
    }

    public String getImage_id() {
        return Image_id;
    }

    public void setImage_id(String image_id) {
        Image_id = image_id;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public ArrayList<Tracks> getSongs() {
        return Songs;
    }

    public void setSongs(ArrayList<Tracks> songs) {
        Songs = songs;
    }
}