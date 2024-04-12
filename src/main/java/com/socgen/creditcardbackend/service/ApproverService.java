package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.dto.ApproverDto;
import com.socgen.creditcardbackend.exception.InvalidApproverDetails;
import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.repository.IApproverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ApproverService implements IApproverService {
    @Autowired
    IApproverRepository approverRepository;
    @Autowired
    IApplicationService applicationService;
    @Override
    public Approver addApprover(ApproverDto approverDto) throws InvalidApproverDetails {
        long count = CheckIfApproverExists(approverDto);

        if(count > 0)
        {
            throw new InvalidApproverDetails("Approver with same first and last name exists");
        }

        Approver approver = new Approver(approverDto.getFirstName(),approverDto.getLastName());
        return approverRepository.save(approver);
    }

    private long CheckIfApproverExists(ApproverDto approver) {

        //NOT WORKING
        
        Iterable<Approver> allApprovers = approverRepository.findAll();
        List<Approver> approverList = new ArrayList<Approver>();
        for (Approver approvers : allApprovers)
        {
            approverList.add(approvers);
        }
        return approverList
                .stream()
                .filter(approvers ->
                        approvers.getFirstName() == approvers.getFirstName()
                                && approvers.getLastName() == approver.getLastName())
                .count();
    }

    @Override
    public Approver getApprover(Integer id)
    {
        Optional<Approver> approver = approverRepository.findById(id);
        if(approver.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            return approver.orElseThrow();
        }
    }

    @Override
    public Boolean approveGivenApplication(Integer approverId, Integer applicationId)
    {
        try{
            Approver approver = getApprover(approverId);
        }
        catch(NoSuchElementException ex){
            return false;
        }

        return applicationService.approveApplication(applicationId);

    }
}
