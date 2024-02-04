package com.socgen.creditcardbackend.model;

import jakarta.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="applicaton_id")
    private Integer Id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer Customer;
    @Column(name = "application_status")
    private Boolean ApplicationStatus;

    public Application() {
    }

    public Application( Customer customer, Boolean applicationStatus) {
        Customer = customer;
        ApplicationStatus = applicationStatus;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public com.socgen.creditcardbackend.model.Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(com.socgen.creditcardbackend.model.Customer customer) {
        Customer = customer;
    }

    public Boolean getApplicationStatus() {
        return ApplicationStatus;
    }

    public void setApplicationStatus(Boolean applicationStatus) {
        ApplicationStatus = applicationStatus;
    }
}
