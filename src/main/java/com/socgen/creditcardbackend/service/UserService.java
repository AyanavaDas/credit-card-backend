package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.dto.UserDto;
import com.socgen.creditcardbackend.enums.UserRoles;
import com.socgen.creditcardbackend.exception.InvalidUserDetails;
import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.LoginUser;
import com.socgen.creditcardbackend.model.User;
import com.socgen.creditcardbackend.repository.IApproverRepository;
import com.socgen.creditcardbackend.repository.ICustomerRepository;
import com.socgen.creditcardbackend.repository.IUserRepository;
import com.socgen.creditcardbackend.util.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IApproverRepository approverRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public User addUser(UserDto userDto)
    {
        User user = new User(userDto.getUserId(),userDto.getUserRole(), userDto.getHash(), userDto.getCorrespondingId());

        return userRepository.save(user);
    }

    @Override
    public Approver loginAsApprover(LoginUser user) throws InvalidUserDetails {
        User registeredUser = userRepository.findByUserIdAndUserRole(user.getUserId(), UserRoles.APPROVER.getValue());
        if(registeredUser == null)
        {
            throw new InvalidUserDetails("User doesnot exist");
        }
        else if(!PasswordValidation.isValidPassword(registeredUser.getHash(),user.getRawPassword()))
        {
            throw new InvalidUserDetails("Password doesnot match");
        }

        return approverRepository.findById(registeredUser.getCorrespondingId()).orElseThrow();
    }

    @Override
    public Customer loginAsCustomer(LoginUser user) throws InvalidUserDetails {
        User registeredUser = userRepository.findByUserIdAndUserRole(user.getUserId(), UserRoles.CUSTOMER.getValue());
        if(registeredUser == null)
        {
            throw new InvalidUserDetails("User doesnot exist");
        }
        else if(!PasswordValidation.isValidPassword(registeredUser.getHash(),user.getRawPassword()))
        {
            throw new InvalidUserDetails("Password doesnot match");
        }

        return customerRepository.findById(registeredUser.getCorrespondingId()).orElseThrow();
    }

    @Override
    public Iterable<User> allUsers()
    {
        return userRepository.findAll();
    }
}
