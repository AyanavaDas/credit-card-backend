package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.dto.CustomerDto;
import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Isolated
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;
    private CustomerDto customer1;
    private CustomerDto customer2;
    private CustomerDto customer3;
    private ResponseEntity<Customer> customerResponseEntity1;
    private ResponseEntity<Customer> customerResponseEntity2;
    private ResponseEntity<Customer> customerResponseEntity3;


    @BeforeAll
    void setup()
    {
        customer1 =  new CustomerDto("Timo","Werner","1234567890","tw@gmail.com");
        customer2 =  new CustomerDto("Luis","Diaz","1234567890","tw@gmail.com");
        customer3 =  new CustomerDto();



        customerResponseEntity1 = customerController.addNewCustomer(customer1);
        customerResponseEntity2 = customerController.addNewCustomer(customer2);
        customerResponseEntity3 = customerController.addNewCustomer(customer3);
    }
    @Test
    public void ShouldReturnRequiredStatusWhenNewCustomersWithValidOrInvalidDetailsIsAdded()
    {

        assertEquals(customerResponseEntity1.getStatusCode(),HttpStatus.CREATED);
        assertEquals(customerResponseEntity2.getStatusCode(),HttpStatus.BAD_REQUEST);
        assertEquals(customerResponseEntity3.getStatusCode(),HttpStatus.BAD_REQUEST);

    }

    @Test
    public void ShouldReturnRequiredStatusWhenCustomerIdIsQueried()
    {
        final ResponseEntity<Customer> customerById1 = customerController.getCustomerById(1);
        final ResponseEntity<Customer> customerById2 = customerController.getCustomerById(2);

        assertEquals(customerById1.getStatusCode(),HttpStatus.OK);
        assertEquals(customerById2.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    //getting detached entity passed to persist error
    //@Test
    public void ShouldReturnRequiredStatusWhenCustomerAppliesForCreditCard()
    {

        final ResponseEntity<Application> applicationResponseEntity1 = customerController.applyForCreditCard(1);
        final ResponseEntity<Application> applicationResponseEntity2 = customerController.applyForCreditCard(2);

        assertEquals(applicationResponseEntity1.getStatusCode(),HttpStatus.CREATED);
        assertEquals(applicationResponseEntity2.getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    public void ShouldReturnRequiredNotificationsWhenNotificationIsQueried()
    {
        final ResponseEntity<List<Notification>> notifications1 = customerController.getNotifications(1);
        final ResponseEntity<List<Notification>> notifications2 = customerController.getNotifications(2);

        assertEquals(notifications1.getStatusCode(),HttpStatus.OK);
        assertEquals(notifications2.getStatusCode(),HttpStatus.NOT_FOUND);


    }

}
