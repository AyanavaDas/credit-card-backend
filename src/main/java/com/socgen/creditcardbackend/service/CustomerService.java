package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;
import com.socgen.creditcardbackend.repository.ICustomerRepository;
import com.socgen.creditcardbackend.util.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IApplicationService applicationService;
    @Override
    public Integer addNewCustomers(Customer customer)
    {
        if(!CustomerValidation.ValidateEmail(customer.getEmailAddress())
                || !CustomerValidation.ValidateNumber(customer.getContactNumber()))
        {
            return -1;
        }

        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer.getId();
    }

    @Override
    public Optional<Customer> getCustomer(Integer id)
    {
        return customerRepository.findById(id);
    }

    @Override
    public Integer applyForCreditCard(Integer Id)
    {
        if(getCustomer(Id).isEmpty())
        {
            return -1;
        }

        return applicationService.applyForCreditCard(getCustomer(Id).get());
    }

    @Override
    public List<Notification> getNotifications(Integer Id)
    {
        Optional<Customer> customer = getCustomer(Id);
        if(customer.isEmpty())
            return null;
        else
            return customer.orElseThrow().getNotifications();
    }
}

