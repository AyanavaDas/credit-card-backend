package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Approver;

import java.util.Optional;

public interface IApproverService {
    Approver addApprover(Approver approver);

    Optional<Approver> getApprover(Integer id);

    Boolean approveGivenApplication(Integer approverId, Integer applicationId);
}
