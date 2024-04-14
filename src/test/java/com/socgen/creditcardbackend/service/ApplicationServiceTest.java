package com.socgen.creditcardbackend.service;


import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.repository.IApplicationRepository;
import com.socgen.creditcardbackend.repository.INotificationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationServiceTest {

    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private IApplicationRepository applicationRepository;

    @Mock
    private INotificationRepository notificationRepository;

    @BeforeAll
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ShouldReturnFalseIfApplicationNumberDoesNotExists()
    {
        assertEquals(false,applicationService.approveApplication(-1));
    }

    @Test
    public void ShouldReturnTrueIfApplicationNumberExists()
    {
        Optional<Application> application = Optional.of(new Application());
        Customer customer = new Customer();
        application.orElseThrow().setId(1);
        application.orElseThrow().setCustomer(customer); //so that notify method doesn't throw null exception
        when(applicationRepository.findById(1)).thenReturn(application);

        Boolean returnResult = applicationService.approveApplication(1);
        Boolean statusResult = applicationService.status(1);

        assertEquals(true,returnResult);
        assertEquals(true,statusResult);
    }

    @Test
    public void ShouldSendNotificationToCorrectCustomer()
    {
        //Application application = new Application();
        //Customer customer = new Customer();
        //customer.setId(1);
        //application.setId(1);
        //application.setCustomer(customer);
        //Notification newNotification = new Notification("Your Credit Card for application number #"+application.getId()+" is approved !",application.getCustomer().getId());
        //when(new Notification("Your Credit Card for application number #"+application.getId()+" is approved !",application.getCustomer().getId())).thenReturn(newNotification);

        //Notification notify = applicationService.Notify(application);

        //assertEquals("Your Credit Card for application number #1 is approved !",notify.getDescription());
        //assertEquals(1,notify.getCustomerId());
    }

}
