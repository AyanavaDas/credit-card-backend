package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.dto.ApproverDto;
import com.socgen.creditcardbackend.model.Approver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Isolated
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApproverControllerTest {

    @Autowired
    private ApproverController approverController;
    private ApproverDto approver1;
    private ApproverDto approver2;
    private ResponseEntity<Approver> approverResponse1;
    private ResponseEntity<Approver> approverResponse2;

    @BeforeAll
    void setup()
    {
        approver1 = new ApproverDto("Dejan","Kulu");
        approver2 = new ApproverDto("Dejan","Kulu");

        approverResponse1 = approverController.addNewApprover(approver1);
        approverResponse2 = approverController.addNewApprover(approver2);
    }

    @Test
    public void ShouldReturnRequiredStatusWhenNewApproverIsAdded()
    {
        assertEquals(approverResponse1.getStatusCode(), HttpStatus.CREATED);
        assertEquals(approverResponse2.getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    public void ShouldReturnRequiredStatusWhenApproverisQueried()
    {
        final ResponseEntity<Approver> result1 = approverController.getApprover(1);
        final ResponseEntity<Approver> result2 = approverController.getApprover(2);

        assertEquals(result1.getStatusCode(),HttpStatus.OK);
        assertEquals(result2.getStatusCode(),HttpStatus.NOT_FOUND);


    }

}
