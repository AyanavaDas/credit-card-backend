package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.service.IApproverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/approver")
public class ApproverController {
    @Autowired
    private IApproverService approverService;
    @PostMapping("/add/{firstName}/{lastName}")
    public ResponseEntity<Integer> addNewApprover(@PathVariable("firstName") String FirstName,
                                                  @PathVariable("lastName") String LastName)
    {
        Approver approver = new Approver(FirstName,LastName);
        Integer id = approverService.addApprover(approver);
        return new ResponseEntity<Integer>(id, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Approver>> getApprover(@PathVariable("id") Integer Id)
    {
        Optional<Approver> approver = approverService.getApprover(Id);
        return new ResponseEntity<Optional<Approver>>(approver,HttpStatus.OK);
    }

    @PostMapping("/approve/{approverId}/{applicationId}")
    public ResponseEntity<Boolean> approve(@PathVariable("approverId") Integer approverId,
                                           @PathVariable("applicationId") Integer applicationId)
    {
        Boolean status = approverService.approveGivenApplication(approverId, applicationId);
        return new ResponseEntity<Boolean>(status,HttpStatus.OK);
    }
}
