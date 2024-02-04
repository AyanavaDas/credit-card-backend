package com.socgen.creditcardbackend.repository;

import com.socgen.creditcardbackend.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer,Integer> {
}
