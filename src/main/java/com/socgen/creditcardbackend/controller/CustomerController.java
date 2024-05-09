package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.dto.CustomerDto;
import com.socgen.creditcardbackend.exception.InvalidCustomerDetails;
import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;
import com.socgen.creditcardbackend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody CustomerDto customerDto)
    {
        Customer savedCustomer = null;
        try {
            savedCustomer = customerService.addNewCustomers(customerDto);
        } catch (InvalidCustomerDetails e) {
            return new ResponseEntity<Customer>((Customer) null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    public ResponseEntity<Customer> getCustomerById(@RequestParam Integer Id) {
        Customer customer = null;
        try {
            customer = customerService.getCustomer(Id);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<Customer>((Customer) null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/apply")
    public ResponseEntity<Application> applyForCreditCard(@RequestParam Integer customerId)
    {
        Application application = null;
        try {
            application = customerService.applyForCreditCard(customerId);
        }
        catch(NoSuchElementException ex)
        {
            return new ResponseEntity<Application>((Application) null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Application>(application,HttpStatus.CREATED);
    }

    @CrossOrigin(origins ="http://localhost:4200")
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@RequestParam Integer customerId)
    {
        List<Notification> notifications = null;
        try{
            notifications = customerService.getNotifications(customerId);
        }
        catch(NoSuchElementException ex)
        {
            return new ResponseEntity<List<Notification>>((List<Notification>) null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
    }
}
