package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Isolated
@SpringBootTest
public class ApplicationServiceTest {
    @Autowired
    IApplicationService applicationService;
    @Test
    public void ShouldAddNewApplicationWithFalseApprovalStatus()
    {
        Customer c1= new Customer("Timo","Werner","1234567890","tw@gmail.com");
        Integer i1 = applicationService.applyForCreditCard(c1);
        Boolean status = applicationService.status(i1);
        Assertions.assertFalse(status);

    }

    @Test
    public void ShouldApproveApplicationAndChangeApprovalStatusToTrue()
    {
        Customer c1= new Customer("Timo","Werner","1234567890","tw@gmail.com");
        Integer i1 = applicationService.applyForCreditCard(c1);
        Boolean b = applicationService.approveApplication(i1);
        Assertions.assertTrue(b);
    }
}
