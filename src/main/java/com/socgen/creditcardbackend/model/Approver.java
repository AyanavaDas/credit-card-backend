package com.socgen.creditcardbackend.model;

import jakarta.persistence.*;

@Entity
public class Approver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="approver_id")
    private Integer Id;

    @Column(name ="approver_firstname")
    private String FirstName;
    @Column(name ="approver_lastname")
    private String LastName;

    public Approver() {
    }

    public Approver(String firstName, String lastName) {

        FirstName = firstName;
        LastName = lastName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
