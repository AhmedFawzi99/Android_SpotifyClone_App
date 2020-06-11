package com.example.spotifyclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Setters and Getters for SignUP data
 * @author Ahmed Mahmoud Fawzi <br>
 */
public class SignUP {
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("ID")
    @Expose
    private String iD;

    public SignUP(String email, String password, String gender, String date, String name, String type, String iD) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.date = date;
        this.name = name;
        this.type = type;
        this.iD = iD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }
}
