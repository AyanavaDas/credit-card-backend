package com.socgen.creditcardbackend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer Id;
    @Column(name ="first_name",nullable = false)
    private String FirstName;
    @Column(name="last_name",nullable = false)
    private String LastName;
    @Column(name="contact_number",length = 10)
    private String ContactNumber;
    @Column(name="email")
    private String EmailAddress;

    @OneToMany
    @Column(name="notifications")
    @JoinColumn(name = "customer_id")
    private List<Notification> Notifications;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String contactNumber, String emailAddress) {
        FirstName = firstName;
        LastName = lastName;
        ContactNumber = contactNumber;
        EmailAddress = emailAddress;
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

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public List<Notification> getNotifications() {
        return Notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        Notifications = notifications;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + Id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", ContactNumber='" + ContactNumber + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", Notifications=" + Notifications +
                '}';
    }
}
