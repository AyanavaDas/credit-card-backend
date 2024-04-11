package com.socgen.creditcardbackend.dto;

import com.socgen.creditcardbackend.model.Customer;

public class ApplicationDto {
    private Customer customer;
    private Boolean applicationStatus;

    public ApplicationDto() {
    }

    public ApplicationDto(Integer id, Customer customer, Boolean applicationStatus) {
        this.customer = customer;
        this.applicationStatus = applicationStatus;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Boolean applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
