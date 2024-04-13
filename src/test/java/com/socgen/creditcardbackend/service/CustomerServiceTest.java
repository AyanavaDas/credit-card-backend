package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.dto.CustomerDto;
import com.socgen.creditcardbackend.exception.InvalidCustomerDetails;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.Notification;
import com.socgen.creditcardbackend.repository.ICustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private ICustomerRepository  customerRepository;

    @Mock
    private IApplicationService applicationService;

    @BeforeAll
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void ShouldAddNewCustomerWithValidDetails()
    {
        String email  = "test@gmail.com";
        String contact = "1234567890";
        CustomerDto customer = new CustomerDto("Timo","Werner",contact,email);

        assertDoesNotThrow(()->{
            customerService.addNewCustomers(customer);
        });
    }
    @Test
    public void ShouldThrowExceptionWhenTwoCustomersWithSameEmailAndContactNumberIsRegistered() {
        String email  = "test@gmail.com";
        String contact = "1234567890";
        Customer customer1 = new Customer("Timo","Werner",contact,email);
        CustomerDto customer2 = new CustomerDto("Pat","Jennings",contact,email);


        Iterable<Customer> allCustomers = Arrays.asList(customer1);
        //mocking findAll()
        when(customerRepository.findAll()).thenReturn(allCustomers);



        assertThrows(InvalidCustomerDetails.class,()->{
            customerService.addNewCustomers(customer2);
        });
    }

    @Test
    public void ShouldThrowExceptionWhenInvalidEmailAndContactNumberIsGiven()
    {
        String email  = "testgmail.com";
        String contact = "123456789";
        CustomerDto customer = new CustomerDto("Timo","Werner",contact,email);

        assertThrows(InvalidCustomerDetails.class,()->{
            customerService.addNewCustomers(customer);
        });


    }

    @Test
    public void ShouldThrowExceptionWhenInvalidCustomerIdIsQueried()
    {
        assertThrows(NoSuchElementException.class,()->{
            customerService.getCustomer(-1);
        });
    }

    @Test
    public void ShouldReturnQueriedCustomerWithId()
    {
        String email  = "test@gmail.com";
        String contact = "1234567890";
        Optional<Customer> customer = Optional.of(new Customer("Timo","Werner",contact,email));
        customer.orElseThrow().setId(1);

        when(customerRepository.findById(1)).thenReturn(customer);

        Customer result = customerService.getCustomer(1);

        assertEquals(contact,result.getContactNumber());
        assertEquals(email,result.getEmailAddress());
        assertEquals("Timo",result.getFirstName());
        assertEquals("Werner",result.getLastName());
    }

    @Test
    public void ShouldThrowExceptionWhenInvalidCustomerAppliesForCreditCard()
    {
        assertThrows(NoSuchElementException.class,()->{
            customerService.applyForCreditCard(-1);
        });
    }

    @Test
    public void ShouldApplyForCreditCardIfCustomerIsValid()
    {
        String email  = "test@gmail.com";
        String contact = "1234567890";
        Optional<Customer> customer = Optional.of(new Customer("Timo","Werner",contact,email));
        customer.orElseThrow().setId(1);
        when(customerRepository.findById(1)).thenReturn(customer);

        assertDoesNotThrow(()->{
            customerService.applyForCreditCard(1);
        });
    }

    @Test
    public void ShouldThrowExceptionWhenInvalidCustomerNotificationIsQueried()
    {
        assertThrows(NoSuchElementException.class,()->{
            customerService.getNotifications(-1);
        });
    }

    @Test
    public void ShouldGetNotificationsForValidCustomer()
    {
        String email  = "test@gmail.com";
        String contact = "1234567890";
        Optional<Customer> customer = Optional.of(new Customer("Timo","Werner",contact,email));
        customer.orElseThrow().setId(1);

        when(customerRepository.findById(1)).thenReturn(customer);

        List<Notification> result = customerService.getNotifications(1);

        assertEquals(customer.orElseThrow().getNotifications(),result);
    }
}
