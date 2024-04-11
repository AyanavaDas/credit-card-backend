package com.socgen.creditcardbackend.controller;

import com.socgen.creditcardbackend.dto.ApproverDto;
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
    @PostMapping("/add")
    public ResponseEntity<Approver> addNewApprover(@RequestBody ApproverDto approverDto)
    {
        Approver approver = new Approver(approverDto.getFirstName(), approverDto.getLastName());
        return new ResponseEntity<Approver>(approverService.addApprover(approver), HttpStatus.CREATED);
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
