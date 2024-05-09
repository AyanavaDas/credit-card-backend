package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;

import java.util.List;

public interface IApplicationService {
    Application applyForCreditCard(Customer customer);

    Boolean isValidApplication(Integer Id);

    Boolean approveApplication(Integer Id);

    Boolean status(Integer Id);

    Notification Notify(Application application);

    Iterable<Application> getAllApplications();

    List<Application> getAllApplicationsById(Integer id);
}
