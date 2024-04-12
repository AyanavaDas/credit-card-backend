package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.dto.ApproverDto;
import com.socgen.creditcardbackend.exception.InvalidApproverDetails;
import com.socgen.creditcardbackend.model.Approver;

public interface IApproverService {
    Approver addApprover(ApproverDto approver) throws InvalidApproverDetails;

    Approver getApprover(Integer id);

    Boolean approveGivenApplication(Integer approverId, Integer applicationId);
}
