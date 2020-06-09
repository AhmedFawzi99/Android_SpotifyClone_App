package com.example.spotifyclone;

import java.util.ArrayList;

public class Album {
    String ID;
    String Album_name;
    String Image_id;
    ArrayList<Artist> artists ;
<<<<<<< HEAD
    ArrayList<Track> Songs;
=======
    ArrayList<Tracks> Songs;
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;
    /**
     * each playlist contains of a type
     */
    private String type;

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

<<<<<<< HEAD
    public ArrayList<Track> getSongs() {
        return Songs;
    }

    public void setSongs(ArrayList<Track> songs) {
=======
    public ArrayList<Tracks> getSongs() {
        return Songs;
    }

    public void setSongs(ArrayList<Tracks> songs) {
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
        Songs = songs;
    }
}
