package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.repository.IApproverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApproverService implements IApproverService {
    @Autowired
    IApproverRepository approverRepository;
    @Autowired
    IApplicationService applicationService;
    @Override
    public Approver addApprover(Approver approver)
    {
        return approverRepository.save(approver);
    }

    @Override
    public Optional<Approver> getApprover(Integer id)
    {
        return approverRepository.findById(id);
    }

    @Override
    public Boolean approveGivenApplication(Integer approverId, Integer applicationId)
    {
        if(getApprover(approverId).isEmpty())
            return false;
        return applicationService.approveApplication(applicationId);

    }
}
