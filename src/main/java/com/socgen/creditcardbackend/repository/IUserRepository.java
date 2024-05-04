package com.socgen.creditcardbackend.repository;

import com.socgen.creditcardbackend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IUserRepository extends CrudRepository<User, UUID> {

    User findByUserIdAndUserRole(String UserId,Integer UserRole);
}
