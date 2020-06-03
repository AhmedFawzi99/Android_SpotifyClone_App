package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("usertype")
    @Expose
    private String usertype;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("gender")
    @Expose
    private String gender;


    public String getlID() {
        return iD;
    }

    public void setlID(String iD) {
        this.iD = iD;
    }

    public String getlEmail() {
        return email;
    }

    public void setlEmail(String email) {
        this.email = email;
    }

    public String getlPassword() {
        return password;
    }

    public void setlPassword(String password) {
        this.password = password;
    }

    public String getlName() {
        return name;
    }

    public void setlName(String name) {
        this.name = name;
    }

    public String getlBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getlGender() {
        return gender;
    }

    public void setlGender(String gender) {
        this.gender = gender;
    }

    public String getlUsertype() {
        return usertype;
    }

    public void setlUsertype(String usertype) {
        this.usertype = usertype;
    }

}
