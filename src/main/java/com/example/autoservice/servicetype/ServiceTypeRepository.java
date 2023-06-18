package com.example.autoservice.servicetype;

import com.example.autoservice.servicetype.domain.ServiceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceTypeRepository extends CrudRepository<ServiceType, Long> {

    @Query("SELECT s FROM ServiceType s")
    List<ServiceType> findAllServiceTypes();
    @Query("SELECT s FROM ServiceType s WHERE s.id=:id ")
    ServiceType findServiceTypeById(Long id);
}
