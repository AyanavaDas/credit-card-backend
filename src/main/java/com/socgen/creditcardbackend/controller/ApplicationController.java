package com.socgen.creditcardbackend.controller;

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

    @GetMapping("/status")
    public ResponseEntity<String> getApplicationStatus(@RequestParam Integer Id)
    {
        try {
            Boolean status = applicationService.status(Id);
            if(status)
                return new ResponseEntity<String>("Approved", HttpStatus.OK);
            else
                return new ResponseEntity<String>("In progress", HttpStatus.OK);
        }
        catch (NoSuchElementException exception)
        {
            return new ResponseEntity<String>("Application doesnot exist",HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/allApplications")
    public ResponseEntity<Iterable> getAllApplications()
    {
        return new ResponseEntity<Iterable>(applicationService.getAllApplications(),HttpStatus.OK);
    }

    //add validations and exception handling later
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    public ResponseEntity<List> getApplicationsById(@RequestParam Integer id)
    {
        return new ResponseEntity<List>(applicationService.getAllApplicationsById(id),HttpStatus.OK);
    }
}
