package com.socgen.creditcardbackend.repository;

import com.socgen.creditcardbackend.model.Notification;
import org.springframework.data.repository.CrudRepository;

public interface INotificationRepository extends CrudRepository<Notification,Integer> {
}
