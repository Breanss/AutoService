package com.example.autoservice.vehicle;

import com.example.autoservice.customer.CustomerMapper;
import com.example.autoservice.service.ServiceMapper;
import com.example.autoservice.vehicle.domain.Vehicle;
import com.example.autoservice.vehicle.domain.VehicleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VehicleMapper {

    private final CustomerMapper customerMapper;
    private final ServiceMapper serviceMapper;

    public VehicleDto toVehicleDto(Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getManufactured_year(),
                vehicle.getVin(),
                vehicle.getVehicleType(),
                customerMapper.toCustomerDto(vehicle.getCustomer()),
                vehicle.getServices().stream()
                        .map(serviceMapper::toServiceDto)
                        .collect(Collectors.toList())
        );
    }

    public Vehicle toVehicle(VehicleDto vehicleDto) {
        return new Vehicle(
                vehicleDto.getId(),
                vehicleDto.getBrand(),
                vehicleDto.getModel(),
                vehicleDto.getManufactured_year(),
                vehicleDto.getVin(),
                vehicleDto.getVehicleType(),
                customerMapper.toCustomer(vehicleDto.getCustomer()),
                vehicleDto.getServices().stream()
                        .map(serviceMapper::toService)
                        .collect(Collectors.toList())
        );
    }
}
