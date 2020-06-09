package com.example.spotifyclone;

import java.util.ArrayList;

public class Category {

    /**
     * String name of the section
     */
    String name;
    /**
     * ArrayList of playlists
     */
<<<<<<< HEAD
    ArrayList<PlaylistResponse> playlists =new ArrayList<PlaylistResponse>();
=======
    ArrayList<RowItem> playlists =new ArrayList<RowItem>();
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692

    public ArrayList<Type> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Type> blocks) {
        this.blocks = blocks;
    }

    /**
     * ArrayList of albums
     */
    ArrayList<Album> albums = new ArrayList<>();
    ArrayList<Type> blocks = new ArrayList<>();
<<<<<<< HEAD
    ArrayList<Artist> artists=new ArrayList<>();
    public ArrayList<PlaylistResponse> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlaylistResponse> playlists) {
=======

    public ArrayList<RowItem> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<RowItem> playlists) {
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
        this.playlists = playlists;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    /**
     * gets the name
     * @return the name of the Section
     */

    public String getName() {
        return name;
    }

    /**
     * sets the name of the section
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the ArrayList of playlists
     * @return
     */
<<<<<<< HEAD
    public ArrayList<PlaylistResponse> getplaylist() {
=======
    public ArrayList<RowItem> getplaylist() {
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
        return playlists;
    }

    /**
     * sets the ArrayList of playlists
     * @param rowitems
     */
<<<<<<< HEAD
    public void setplaylists(ArrayList<PlaylistResponse> rowitems) {
=======
    public void setplaylists(ArrayList<RowItem> rowitems) {
>>>>>>> 88ead3f730e84fb8c66fc9b0e401913e4c378692
        this.playlists = rowitems;
    }
}
