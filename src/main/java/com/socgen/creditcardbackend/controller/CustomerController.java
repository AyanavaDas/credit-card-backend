package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;
import com.socgen.creditcardbackend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @PostMapping("/add/{firstName}/{lastName}/{number}/{email}")
    public ResponseEntity<Integer> addNewCustomer(@PathVariable("firstName") String firstName,
                                                  @PathVariable("lastName") String lastName,
                                                  @PathVariable("number") String number,
                                                  @PathVariable("email") String email){

        Customer customer = new Customer(firstName,lastName,number,email);
        Integer idOfAddedCustomer = customerService.addNewCustomers(customer);
        return new ResponseEntity<Integer>(idOfAddedCustomer, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable("id") Integer Id)
    {
        Optional<Customer> customer = customerService.getCustomer(Id);
        String response = "";
        if(customer.isEmpty())
        {
            response += "No such customer exists";
        }
        else
        {
            response += "Name :"+customer.orElseThrow().getFirstName()+" "+ customer.orElseThrow().getLastName();
        }

        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @PostMapping("/apply/{id}")
    public ResponseEntity<Integer> applyForCreditCard(@PathVariable("id") Integer Id)
    {
        Integer id = customerService.applyForCreditCard(Id);
        return new ResponseEntity<Integer>(id,HttpStatus.OK);
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable("id") Integer Id)
    {
        List<Notification> notifications = customerService.getNotifications(Id);
        return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
    }
}
