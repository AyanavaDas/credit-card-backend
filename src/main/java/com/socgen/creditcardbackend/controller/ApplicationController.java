package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private IApplicationService applicationService;

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getApplicationStatus(@PathVariable("id") Integer Id)
    {
        try {
            Boolean status = applicationService.status(Id);
            if(status)
                return new ResponseEntity<String>("Approved", HttpStatus.OK);
            else
                return new ResponseEntity<String>("Not Approved", HttpStatus.OK);
        }
        catch (NoSuchElementException exception)
        {
            return new ResponseEntity<String>("Application doesnot exist",HttpStatus.OK);
        }
    }
}
