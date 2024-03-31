package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.model.Application;
import com.socgen.creditcardbackend.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/allApplications")
    public ResponseEntity<Iterable> getAllApplications()
    {
        return new ResponseEntity<Iterable>(applicationService.getAllApplications(),HttpStatus.OK);
    }
}
