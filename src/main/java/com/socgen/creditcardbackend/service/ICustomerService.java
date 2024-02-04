package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Integer addNewCustomers(Customer customer);
    Optional<Customer> getCustomer(Integer id);

    Integer applyForCreditCard(Integer Id);

    List<Notification> getNotifications(Integer Id);
}
