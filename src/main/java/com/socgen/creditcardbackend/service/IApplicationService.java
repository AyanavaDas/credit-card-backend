package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;

public interface IApplicationService {
    Integer applyForCreditCard(Customer customer);

    Boolean isValidApplication(Integer Id);

    Boolean approveApplication(Integer Id);

    Boolean status(Integer Id);

    Notification Notify(Application application);

    Iterable<Application> getAllApplications();
}
