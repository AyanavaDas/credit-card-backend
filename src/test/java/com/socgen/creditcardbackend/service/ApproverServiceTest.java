package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.dto.ApproverDto;
import com.socgen.creditcardbackend.exception.InvalidApproverDetails;
import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.repository.IApplicationRepository;
import com.socgen.creditcardbackend.repository.IApproverRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Isolated;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApproverServiceTest {

    @InjectMocks
    ApproverService approverService;

    @Mock
    IApproverRepository approverRepository;

    @Mock
    IApplicationService applicationService;

    @BeforeAll
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ShouldThrowErrorWhenApproverExists()
    {
        String firstName = "Dejan";
        String lastName = "Kulusevski";

        ApproverDto approver1 = new ApproverDto(firstName,lastName);
        Approver approver2 = new Approver(firstName,lastName);
        Iterable<Approver>  allApprovers = Arrays.asList(approver2);
        when(approverRepository.findAll()).thenReturn(allApprovers);

        assertThrows(InvalidApproverDetails.class,()->{
            approverService.addApprover(approver1);
        });

    }

    @Test
    public void ShouldAddApproverWithValidDetails()
    {
        String firstName = "Lucas";
        String lastName = "Moura";

        ApproverDto approver1 = new ApproverDto(firstName,lastName);

        assertDoesNotThrow(()->{approverService.addApprover(approver1);});

    }

    @Test
    public void ShouldThrowExceptionWhenApproverDoesNotExists()
    {
        assertThrows(NoSuchElementException.class,
                ()->{
            approverService.getApprover(-1);
        });
    }

    @Test
    public void ShouldGetApproverIfItExists()
    {
        String firstName="A";
        String lastName="B";
        Optional<Approver> approver = Optional.of(new Approver(firstName,lastName));
        approver.orElseThrow().setId(1);

        when(approverRepository.findById(1)).thenReturn(approver);

        Approver result = approverService.getApprover(1);

        assertEquals(firstName,result.getFirstName());
        assertEquals(lastName,result.getLastName());
        assertEquals(1,result.getId());
    }

    @Test
    public void ShouldThrowExceptionWhenNotExistantApproverApprovesApplication()
    {
        assertThrows(NoSuchElementException.class,()->{
            approverService.getApprover(-1);
        });
    }


    @Test
    public void ShouldApproveGivenApplicationIfCorrectApproverIdIsGiven()
    {
        String firstName="C";
        String lastName="D";
        Optional<Approver> approver = Optional.of(new Approver(firstName,lastName));
        approver.orElseThrow().setId(2);
        when(approverRepository.findById(2)).thenReturn(approver);

        approverService.approveGivenApplication(2,1);

        //only verify if the method is called as it has already been tested in application service test
        verify(applicationService).approveApplication(1);

    }

}
