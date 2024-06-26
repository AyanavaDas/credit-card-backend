package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.dto.ApproverDto;
import com.socgen.creditcardbackend.dto.UserDto;
import com.socgen.creditcardbackend.enums.UserRoles;
import com.socgen.creditcardbackend.exception.InvalidApproverDetails;
import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.repository.IApproverRepository;
import com.socgen.creditcardbackend.util.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ApproverService implements IApproverService {
    @Autowired
    IApproverRepository approverRepository;
    @Autowired
    IApplicationService applicationService;
    @Autowired
    IUserService userService;
    @Override
    public Approver addApprover(ApproverDto approverDto) throws InvalidApproverDetails {
        long count = CheckIfApproverExists(approverDto);

        if(count > 0)
        {
            throw new InvalidApproverDetails("Approver with same first and last name exists");
        }

        Approver approver = new Approver(approverDto.getFirstName(),approverDto.getLastName());
        Approver savedApprover = approverRepository.save(approver);
        String hash = PasswordValidation.encodePassword(approverDto.getPassword());
        UserDto newUser= new UserDto(approverDto.getFirstName(), UserRoles.APPROVER.getValue(), hash, savedApprover.getId());
        userService.addUser(newUser);
        System.out.println("added user");
        return savedApprover;
    }

    private long CheckIfApproverExists(ApproverDto approver) {

        Iterable<Approver> allApprovers = approverRepository.findAll();

        return StreamSupport
                .stream(allApprovers.spliterator(),false)
                .filter(approvers ->
                        approvers.getFirstName() .equals(approver.getFirstName())
                                || approvers.getLastName().equals(approver.getLastName()))
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
