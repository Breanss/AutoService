package com.example.autoservice.service;

import com.example.autoservice.service.domain.Service;
import com.example.autoservice.servicetype.domain.ServiceType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
    @Query("SELECT s FROM Service s")
    List<Service> getAll();

    @Query("SELECT s FROM Service s WHERE s.id=:id")
    Service getServiceById(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Service s SET s.date=:date, s.details=:details, s.price=:price, s.serviceType=:serviceType where s.id = :id")
    int editServiceById(Date date, String details, double price, ServiceType serviceType, Long id);
}
