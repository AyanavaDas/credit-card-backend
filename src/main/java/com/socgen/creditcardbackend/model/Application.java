package com.socgen.creditcardbackend.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

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

    @Column(nullable = false)
    private Integer applierId;

    public Application() {
    }

    public Application( Customer customer, Boolean applicationStatus,Integer id) {
        Customer = customer;
        ApplicationStatus = applicationStatus;
        applierId = id;
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

    public Integer getApplierId() {
        return applierId;
    }

    public void setApplierId(Integer applierId) {
        this.applierId = applierId;
    }

}
