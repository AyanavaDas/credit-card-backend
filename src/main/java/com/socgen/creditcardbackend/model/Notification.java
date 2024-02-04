package com.socgen.creditcardbackend.model;

import jakarta.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="notification_id")
    private Integer Id;
    @Column(name = "description")
    private String Description;
    @Column(name = "customer_id")
    private Integer CustomerId;

    public Notification(String description, Integer customerId) {
        Description = description;
        CustomerId = customerId;
    }

    public Notification() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        CustomerId = customerId;
    }
}
