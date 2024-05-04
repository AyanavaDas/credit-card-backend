package com.socgen.creditcardbackend.service;

import com.socgen.creditcardbackend.dto.UserDto;
import com.socgen.creditcardbackend.exception.InvalidUserDetails;
import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.LoginUser;
import com.socgen.creditcardbackend.model.User;

public interface IUserService {
    User addUser(UserDto userDto);


    Approver loginAsApprover(LoginUser user) throws InvalidUserDetails;

    Customer loginAsCustomer(LoginUser user) throws InvalidUserDetails;

    Iterable<User> allUsers();
}
