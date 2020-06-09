package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {
    @SerializedName("aid")
    @Expose
    private String aid;
    @SerializedName("aname")
    @Expose
    private String aname;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pname")
    @Expose
    private String pname;
    @SerializedName("isliked")
    @Expose
    private Boolean isliked;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
public Track(String name,String imageid,String URL,String id)
{
    this.name=name;
    this.previewUrl=imageid;
    this.url=URL;
    this.id =id;
}
    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String gettImage() {
        return image;
    }

    public void settImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String gettId() {
        return id;
    }

    public void settId(String id) {
        this.id = id;
    }

    public String gettName() {
        return name;
    }

    public void settName(String name) {
        this.name = name;
    }

    public String gettPreviewUrl() {
        return previewUrl;
    }

    public void settPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Boolean getIsliked() {
        return isliked;
    }

    public void setIsliked(Boolean isliked) {
        this.isliked = isliked;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }


    public String gettPname() {
        return pname;
    }

    public void settPname(String pname) {
        this.pname = pname;
    }
}