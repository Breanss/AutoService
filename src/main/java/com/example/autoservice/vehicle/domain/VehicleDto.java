package com.example.autoservice.vehicle.domain;

import com.example.autoservice.customer.domain.CustomerDto;
import com.example.autoservice.service.domain.ServiceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    private String brand;
    private String model;
    private int manufactured_year;
    private String vin;
    private VehicleType vehicleType;
    private CustomerDto customer;
    private List<ServiceDto> services;
}
