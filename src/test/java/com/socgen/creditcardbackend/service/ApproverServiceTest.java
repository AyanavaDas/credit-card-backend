package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.model.Approver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Isolated
@SpringBootTest
public class ApproverServiceTest {
    @Autowired
    IApproverService approverService;
    @Test
    public void ShouldBeAbleToAddAndGetNewApprovers()
    {
        Approver a1= new Approver("Dejan","Kulu");
        Integer id = approverService.addApprover(a1);
        Assertions.assertFalse(approverService.getApprover(id).isEmpty());
        Assertions.assertEquals(id,approverService.getApprover(id).orElseThrow().getId());
    }

    @Test
    public void ShouldNotApproveApplicationWhenApproverIsInvalid()
    {
        Integer approverId = Integer.MAX_VALUE;
        Integer applicationId = Integer.MIN_VALUE;
        Boolean b = approverService.approveGivenApplication(approverId, applicationId);
        Assertions.assertFalse(b);
    }
}
