package com.example.spotifyclone;

import java.util.ArrayList;

public class Artist {
    /**
     * String id of the Artist
     */
    private String ID;
    /**
     * Name of the artist
     */
    private  String Name;
    /**
     * image of the artist
     */
    private  String Image;
    /**
     array of popular releases
     */
    ArrayList<Type> popular_releases =null;
    /**
     array of fans also like
     */
    ArrayList<Artist> FANSALSOLIKE =null;
    boolean following =true;

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public ArrayList<Type> getPopular_releases() {
        return popular_releases;
    }

    public void setPopular_releases(ArrayList<Type> popular_releases) {
        this.popular_releases = popular_releases;
    }

    public ArrayList<Artist> getFANSALSOLIKE() {
        return FANSALSOLIKE;
    }

    public void setFANSALSOLIKE(ArrayList<Artist> FANSALSOLIKE) {
        this.FANSALSOLIKE = FANSALSOLIKE;
    }



    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }




    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Artist(String Id, String Name, String Image,boolean following)
    {
        ID =Id;
        this.Name =Name;
        this.Image =Image;
        this.following=following;
    }
}
