package com.example.spotifyclone;
/**
 * Class to save and access data of the user
 * @author Salma Hazem
 * @version 1.0
 */
public class User {
    private int id;
    private String email, password, name, birthDate, gender,token;

    public User(String email, String password, String name, String birthDate, String gender) {
        //this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }

    public String getEmail() { return email; }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }


}