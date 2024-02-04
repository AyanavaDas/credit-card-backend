package com.socgen.creditcardbackend.repository;

import com.socgen.creditcardbackend.model.Application;
import org.springframework.data.repository.CrudRepository;

public interface IApplicationRepository extends CrudRepository<Application,Integer> {
}
