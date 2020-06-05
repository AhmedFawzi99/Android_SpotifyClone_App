package com.example.spotifyclone;

public class Artist {
    /**
     * String id of the Artist
     */
    private String id;
    /**
     * Name of the artist
     */
    private  String name;
    /**
     * image of the artist
     */
    private  String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist(String Id, String Name, String Image)
    {
        id=Id;
        name=Name;
        image=Image;
    }
}
