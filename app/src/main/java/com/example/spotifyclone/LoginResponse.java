package com.example.spotifyclone;

public class LoginResponse {
    private boolean error;
    private String message1 = "Successful";
    private String message2 = "Unsuccessful";
    private User user;

    public LoginResponse(boolean error, String message1, String message2, User user) {
        this.error = error;
        this.message1 = message1;
        this.message2 = message2;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage1() {
        return message1;
    }

    public String getMessage2() {
        return message2;
    }
}
