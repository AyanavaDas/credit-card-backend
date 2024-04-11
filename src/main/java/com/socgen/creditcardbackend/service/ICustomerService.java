package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.exception.InvalidCustomerDetails;
import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;

import java.util.List;

public interface ICustomerService {
    Customer addNewCustomers(Customer customer) throws InvalidCustomerDetails;
    Customer getCustomer(Integer id);

    Application applyForCreditCard(Integer Id);

    List<Notification> getNotifications(Integer Id);
}
