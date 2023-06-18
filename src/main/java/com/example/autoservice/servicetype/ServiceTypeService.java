package com.example.autoservice.servicetype;

import com.example.autoservice.servicetype.domain.ServiceType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public List<ServiceType> getAll(){
        List<ServiceType> serviceTypes = serviceTypeRepository.findAllServiceTypes();
        return new ArrayList<>(serviceTypes);
    }

}
