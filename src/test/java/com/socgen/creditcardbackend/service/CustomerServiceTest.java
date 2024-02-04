package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Isolated
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    ICustomerService customerService;

    @Test
    public void ShouldAddCustomersWithIncrementingIdHavingValidInformation()
    {
        Customer customer1= new Customer("Timo","","1234567890","tw@gmail.com");
        Integer i1 = customerService.addNewCustomers(customer1);
        Customer customer2 = new Customer("Richarlison","","1234567890","r@gmail.com");
        Integer i2 = customerService.addNewCustomers(customer2);
        Assertions.assertTrue(i1>0);
        Assertions.assertTrue(i2>0);
    }

    @Test
    public void ShouldNotAddCustomerWithInvalidInformation()
    {
        Customer c1 = new Customer("Timo","","123456789","tw@gmail.com");
        Customer c2 = new Customer("Richarlison","","1234567890","r.gmail.com");
        Integer i1 = customerService.addNewCustomers(c1);
        Integer i2 = customerService.addNewCustomers(c2);
        Assertions.assertEquals(-1,i1);
        Assertions.assertEquals(-1,i2);
    }

    @Test
    public void ShouldBeAbleToApplyCreditCardWhenCustomerExists()
    {
        Customer c1 = new Customer("Timo","","1234567890","tw@gmail.com");
        Integer i1 = customerService.addNewCustomers(c1);
        Integer applicationId1 = customerService.applyForCreditCard(i1);
        Integer applicationId2 = customerService.applyForCreditCard(i1);
        Assertions.assertEquals(1,applicationId1);
        Assertions.assertEquals(2,applicationId2);

    }

    @Test
    public void ShouldNotApplyForCreditCardWhenCustomerDoesnotExist()
    {
        Integer id= Integer.MAX_VALUE;
        Integer i = customerService.applyForCreditCard(id);
        Assertions.assertEquals(-1,i);
    }
}
