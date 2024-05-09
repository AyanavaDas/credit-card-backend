package com.socgen.creditcardbackend.repository;

import com.socgen.creditcardbackend.model.Application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IApplicationRepository extends CrudRepository<Application,Integer> {
    @Query("SELECT a FROM Application a WHERE a.applierId= :applierId")
    List<Application> findAllBy(@Param("applierId")Integer ApplierId);
}
