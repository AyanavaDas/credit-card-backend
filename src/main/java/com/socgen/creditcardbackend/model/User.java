package com.socgen.creditcardbackend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="User_roles")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "user_id",nullable = false)
    private String userId;

    @Column(name = "roles",nullable = false)
    private Integer userRole;

    @Column(name="correspondingId",nullable = false)
    private Integer CorrespondingId;

    @Column(name="hash",nullable = false)
    private String hash;

    public User(String userId, Integer userRole, String hash,Integer correspondingId) {
        this.userId = userId;
        this.userRole = userRole;
        this.hash = hash;
        this.CorrespondingId = correspondingId;
    }

    public User() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getCorrespondingId() {
        return CorrespondingId;
    }

    public void setCorrespondingId(Integer correspondingId) {
        CorrespondingId = correspondingId;
    }


}
