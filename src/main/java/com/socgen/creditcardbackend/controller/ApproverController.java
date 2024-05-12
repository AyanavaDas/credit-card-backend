package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.dto.ApproverDto;
import com.socgen.creditcardbackend.exception.InvalidApproverDetails;
import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.service.IApproverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/approver")
public class ApproverController {
    @Autowired
    private IApproverService approverService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<Approver> addNewApprover(@RequestBody ApproverDto approverDto)
    {
        Approver savedApprover = null;
        try {
            savedApprover = approverService.addApprover(approverDto);
        } catch (InvalidApproverDetails e) {
            return new ResponseEntity<Approver>((Approver) null , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Approver>(savedApprover, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    public ResponseEntity<Approver> getApprover(@RequestParam Integer Id)
    {
        Approver approver = null;
        try {
            approver = approverService.getApprover(Id);
        }
        catch(NoSuchElementException ex){
            return new ResponseEntity<Approver>((Approver) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Approver>(approver,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/approve")
    public ResponseEntity<Boolean> approve(@RequestParam Integer approverId,
                                           @RequestParam Integer applicationId)
    {
        Boolean status = approverService.approveGivenApplication(approverId, applicationId);
        if(status) {
            return new ResponseEntity<Boolean>(status, HttpStatus.OK);
        }
        else
        {
            return  new ResponseEntity<Boolean>(status,HttpStatus.BAD_REQUEST);
        }
    }
}
