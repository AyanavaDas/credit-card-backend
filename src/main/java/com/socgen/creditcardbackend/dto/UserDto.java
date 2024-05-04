package com.socgen.creditcardbackend.dto;

public class UserDto {

    private String UserId;
    private Integer UserRole;
    private String hash;
    private Integer correspondingId;

    public UserDto(String userId, Integer userRole, String hash,Integer correspondingId) {
        UserId = userId;
        UserRole = userRole;
        this.hash = hash;
        this.correspondingId = correspondingId;
    }

    public UserDto() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Integer getUserRole() {
        return UserRole;
    }

    public void setUserRole(Integer userRole) {
        UserRole = userRole;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getCorrespondingId() {
        return correspondingId;
    }

    public void setCorrespondingId(Integer correspondingId) {
        this.correspondingId = correspondingId;
    }
}
