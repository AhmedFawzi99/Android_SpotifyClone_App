package com.example.spotifyclone;

/**
 * @author shaimaa
 */
public class Type {
    /**
     * members of type
     */
    String type;
    RowItem rowitem;
    Album album;
    Tracks track;

    /**
     * constructor
     * @param ttype
     * @param rrowitem
     * @param aalbum
     */
    Type(String ttype, RowItem rrowitem, Album aalbum)
    {
        album=aalbum;
        rowitem=rrowitem;
        type=ttype;
    }

    /**
     * setters and getters
     */
    public Tracks getTrack() {
        return track;
    }

    public void setTrack(Tracks track) {
        this.track = track;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RowItem getRowitem() {
        return rowitem;
    }

    public void setRowitem(RowItem rowitem) {
        this.rowitem = rowitem;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}