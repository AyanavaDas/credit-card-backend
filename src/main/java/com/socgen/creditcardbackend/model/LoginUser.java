package com.socgen.creditcardbackend.model;

public class LoginUser {
    private String userId;
    private String rawPassword;

    public LoginUser(String userId, String rawPassword) {
        this.userId = userId;
        this.rawPassword = rawPassword;
    }

    public LoginUser() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
