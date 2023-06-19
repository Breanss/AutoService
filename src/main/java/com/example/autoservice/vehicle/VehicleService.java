package com.example.autoservice.vehicle;

import com.example.autoservice.customer.CustomerMapper;
import com.example.autoservice.customer.CustomerRepository;
import com.example.autoservice.customer.domain.Customer;
import com.example.autoservice.vehicle.domain.Vehicle;
import com.example.autoservice.vehicle.domain.VehicleDto;
import com.example.autoservice.vehicle.domain.VehicleValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleValidator vehicleValidator;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final CustomerMapper customerMapper;

    public List<VehicleDto> getAll() {
        List<Vehicle> vehicles = vehicleRepository.findAllVehicles();
        return vehicles.stream()
                .map(vehicleMapper::toVehicleDto)
                .collect(Collectors.toList());
    }

    public VehicleDto getVehicleByID(Long id) {
        Vehicle vehicle = vehicleRepository.findVehicleById(id);
        if (isNull(vehicle))
            return null;
        return vehicleMapper.toVehicleDto(vehicle);
    }

    public List<VehicleDto> getAllVehiclesByCustomer(Long idCustomer) {
        Customer customer = customerRepository.findCustomerByCustomerID(idCustomer);
        List<Vehicle> vehicles = vehicleRepository.findAllVehiclesByCustomer(customer);
        System.out.println(vehicles.size());
        return vehicles.stream()
                .map(vehicleMapper::toVehicleDto)
                .filter(vehicleDto -> Objects.equals(vehicleDto.getCustomer().getId(), customer.getId()))
                .collect(Collectors.toList());
    }

    public VehicleDto getVehicleByVIN(String vin) {
        Vehicle vehicle = vehicleRepository.findVehicleByVin(vin);
        if (isNull(vehicle))
            return null;
        return vehicleMapper.toVehicleDto(vehicle);
    }

    public void deleteVehicleByID(Long id) {
        vehicleRepository.deleteById(id);
    }

    public boolean saveVehicle(VehicleDto vehicleDto) {
        VehicleValidationResult vehicleValidationResult = vehicleValidator.validVehicle(vehicleDto);

        if (!vehicleValidationResult.isVehicleValid()) {
            return false;
        }

        if (isNull(vehicleDto.getCustomer()) || isNull(vehicleDto.getCustomer().getId())) {
            return false;
        }

        Customer customer = customerRepository.findCustomerByCustomerID(vehicleDto.getCustomer().getId());

        if (isNull(customer)) {
            return false;
        }

        vehicleDto.setCustomer(customerMapper.toCustomerDto(customer));
        vehicleDto.setServices(new ArrayList<>());

        vehicleRepository.save(vehicleMapper.toVehicle(vehicleDto));

        return true;
    }

    public boolean editVehicleByID(VehicleDto vehicleDtoDtoEdit, Long id) {
        VehicleDto vehicleDto = this.getVehicleByID(id);

        if (isNull(vehicleDto) || isNull(vehicleDtoDtoEdit)) return false;

        if (!isNull(vehicleDtoDtoEdit.getVehicleType()))
            vehicleDto.setVehicleType(vehicleDtoDtoEdit.getVehicleType());
        if (!isNull(vehicleDtoDtoEdit.getModel()))
            vehicleDto.setModel(vehicleDtoDtoEdit.getModel());
        if (!isNull(vehicleDtoDtoEdit.getVin()))
            vehicleDto.setVin(vehicleDtoDtoEdit.getVin());
        if (!isNull(vehicleDtoDtoEdit.getBrand()))
            vehicleDto.setBrand(vehicleDtoDtoEdit.getBrand());
        if (!isNull(vehicleDtoDtoEdit.getCustomer()))
            vehicleDto.getCustomer().setId(vehicleDtoDtoEdit.getId());
        if (vehicleDtoDtoEdit.getManufactured_year() > 1900)
            vehicleDto.setManufactured_year(vehicleDtoDtoEdit.getManufactured_year());

        VehicleValidationResult vehicleValidationResult = vehicleValidator.validVehicle(vehicleDto);

        if (!vehicleValidationResult.isVehicleValid()) {
            return false;
        }

        try {
            vehicleRepository.editVehicle(vehicleDto.getVin(), vehicleDto.getBrand(), vehicleDto.getManufactured_year(), vehicleDto.getModel(), vehicleDto.getVehicleType(), vehicleDto.getId());
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
