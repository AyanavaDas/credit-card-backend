package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;
import com.socgen.creditcardbackend.repository.IApplicationRepository;
import com.socgen.creditcardbackend.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    private IApplicationRepository applicationRepository;
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public Application applyForCreditCard(Customer customer)
    {
        Application newApplication = new Application(customer,false);
        return applicationRepository.save(newApplication);
    }

    @Override
    public Boolean isValidApplication(Integer Id)
    {
        Optional<Application> applicationId = applicationRepository.findById(Id);
        return applicationId.isPresent();
    }

    @Override
    public Boolean approveApplication(Integer Id)
    {
        if(!isValidApplication(Id))
            return false;
        Application application = applicationRepository.findById(Id).get();
        application.setApplicationStatus(true);
        applicationRepository.save(application);
        Notify(application);
        return true;
    }

    @Override
    public Boolean status(Integer Id)
    {
        return applicationRepository.findById(Id).orElseThrow().getApplicationStatus();
    }

    @Override
    public Notification Notify(Application application)
    {
        Notification newNotification = new Notification("Your Credit Card for application number #"+application.getId()+" is approved !",application.getCustomer().getId());
        return notificationRepository.save(newNotification);

    }

    @Override
    public Iterable<Application> getAllApplications()
    {
        return applicationRepository.findAll();
    }
}
