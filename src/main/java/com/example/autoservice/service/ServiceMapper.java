package com.example.autoservice.service;

import com.example.autoservice.service.domain.Service;
import com.example.autoservice.service.domain.ServiceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServiceMapper {
    public ServiceDto toServiceDto(Service service) {
        return new ServiceDto(service.getId(), service.getPrice(), service.getDate(), service.getDetails(), service.getServiceType());
    }

    public Service toService(ServiceDto serviceDto) {
        return new Service(serviceDto.getId(), serviceDto.getPrice(), serviceDto.getDate(), serviceDto.getDetails(), serviceDto.getServiceType());
    }
}
