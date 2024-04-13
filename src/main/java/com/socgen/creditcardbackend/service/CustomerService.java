package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.dto.CustomerDto;
import com.socgen.creditcardbackend.exception.InvalidCustomerDetails;
import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;
import com.socgen.creditcardbackend.repository.ICustomerRepository;
import com.socgen.creditcardbackend.util.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IApplicationService applicationService;
    @Override
    public Customer addNewCustomers(CustomerDto customerDto) throws InvalidCustomerDetails {
        if(!CustomerValidation.ValidateEmail(customerDto.getEmailAddress())
                || !CustomerValidation.ValidateNumber(customerDto.getContactNumber()))
        {
            throw new InvalidCustomerDetails("email or contact number is invalid");
        }

        long count = CheckIfCustomerExists(customerDto);

        if(count > 0)
        {
            throw new InvalidCustomerDetails("customer with same email and contact number already exists");
        }

        Customer customer = new Customer(
                                customerDto.getFirstName(),
                                customerDto.getLastName(),
                                customerDto.getContactNumber(),
                                customerDto.getEmailAddress());

        return customerRepository.save(customer);
    }

    private long CheckIfCustomerExists(CustomerDto customer) {

        Iterable<Customer> allCustomer = customerRepository.findAll();

        return StreamSupport
                .stream(allCustomer.spliterator(),false)
                .filter(customers ->
                            customers.getEmailAddress().equals(customer.getEmailAddress())
                                    && customers.getContactNumber().equals(customer.getContactNumber()))
                .count();
    }

    @Override
    public Customer getCustomer(Integer id)
    {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty())
        {
            throw new NoSuchElementException("Customer for id :"+ id +" does not exist !");
        }
        else
        {
            return customer.orElseThrow();
        }
    }

    @Override
    public Application applyForCreditCard(Integer Id)
    {
        Customer customer = null;
        try {
            customer = getCustomer(Id);
        }
        catch(NoSuchElementException ex){
            //throw exception here so that http responses can be differentiated at controller
            throw new NoSuchElementException(ex.getMessage());
        }

        return applicationService.applyForCreditCard(getCustomer(Id));
    }

    @Override
    public List<Notification> getNotifications(Integer Id)
    {
        Customer customer = null;
        try {
            customer = getCustomer(Id);
        }
        catch(NoSuchElementException ex){
           throw new NoSuchElementException(ex.getMessage());
        }
        return customer.getNotifications();
    }
}

