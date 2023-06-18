package com.example.autoservice.service.domain;

import com.example.autoservice.servicetype.domain.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ServiceDto {
    private Long id;
    private double price;
    private Date date;
    private String details;
    private ServiceType serviceType;
}
