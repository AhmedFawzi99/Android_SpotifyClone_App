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
    ArrayList<RowItem> playlists =new ArrayList<RowItem>();

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

    public ArrayList<RowItem> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<RowItem> playlists) {
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
    public ArrayList<RowItem> getplaylist() {
        return playlists;
    }

    /**
     * sets the ArrayList of playlists
     * @param rowitems
     */
    public void setplaylists(ArrayList<RowItem> rowitems) {
        this.playlists = rowitems;
    }
}
