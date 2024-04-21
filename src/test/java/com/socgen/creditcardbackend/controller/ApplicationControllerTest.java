package com.socgen.creditcardbackend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Isolated
@SpringBootTest
public class ApplicationControllerTest {
    @Autowired
    private ApplicationController applicationController;

    @Test
    public void ShouldReturnNotFoundWhenApplicationDoesnotExists()
    {
        final ResponseEntity<String> applicationStatus = applicationController.getApplicationStatus(-1);
        assertEquals(applicationStatus.getStatusCode(), HttpStatus.NOT_FOUND);

    }
}
