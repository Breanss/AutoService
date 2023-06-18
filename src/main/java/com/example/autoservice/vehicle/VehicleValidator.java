package com.example.autoservice.vehicle;

import com.example.autoservice.vehicle.domain.Vehicle;
import com.example.autoservice.vehicle.domain.VehicleDto;
import com.example.autoservice.vehicle.domain.VehicleValidationError;
import com.example.autoservice.vehicle.domain.VehicleValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;


@Component
@AllArgsConstructor
public class VehicleValidator {
    private final VehicleRepository vehicleRepository;

    public VehicleValidationResult validVehicle(VehicleDto vehicleDto) {

        if (isNull(vehicleDto.getBrand())) {
            return new VehicleValidationResult(false, VehicleValidationError.NULL_VALUE_BRAND);
        } else if (validateBrandLength(vehicleDto.getBrand())) {
            return new VehicleValidationResult(false, VehicleValidationError.INCORRECT_LENGTH_BRAND);
        } else if (validateContainsCharacterBrande(vehicleDto.getBrand())) {
            return new VehicleValidationResult(false, VehicleValidationError.CONTAINS_ILLEGAL_CHARACTERS_BRAND);
        }

        if (validateManufacturedYearValue(vehicleDto.getManufactured_year())) {
            return new VehicleValidationResult(false, VehicleValidationError.INCORRECT_VALUE_MANUFACTURED_YEAR);
        }

        if (isNull(vehicleDto.getModel())) {
            return new VehicleValidationResult(false, VehicleValidationError.NULL_VALUE_MODEL);
        } else if (validateModelLength(vehicleDto.getModel())) {
            return new VehicleValidationResult(false, VehicleValidationError.INCORRECT_LENGTH_MODEL);
        } else if (validateContainsCharacterModel(vehicleDto.getModel())) {
            return new VehicleValidationResult(false, VehicleValidationError.CONTAINS_ILLEGAL_CHARACTERS_MODEL);
        }

        if (isNull(vehicleDto.getVehicleType())) {
            return new VehicleValidationResult(false, VehicleValidationError.NULL_VALUE_VEHICLE_TYPE);
        }

        if (isNull(vehicleDto.getVin())) {
            return new VehicleValidationResult(false, VehicleValidationError.NULL_VALUE_VIN);
        } else if (validateVinLength(vehicleDto.getVin())) {
            return new VehicleValidationResult(false, VehicleValidationError.INCORRECT_VIN);
        } else if (validateContainsCharacterVin(vehicleDto.getVin())) {
            return new VehicleValidationResult(false, VehicleValidationError.CONTAINS_ILLEGAL_CHARACTERS_VIN);
        } else if (validateVinExists(vehicleDto.getVin(), vehicleDto.getId())) {
            return new VehicleValidationResult(false, VehicleValidationError.VIN_ALREADY_EXISTS);
        }

        return new VehicleValidationResult(true, null);
    }

    private boolean validateContainsCharacterModel(String model) {
        return !Pattern.matches("[A-Za-z0-9]+", model);
    }

    private boolean validateModelLength(String model) {
        return model.length() >= 50 || model.length() < 1;
    }

    private boolean validateManufacturedYearValue(int manufactured_year) {
        if (manufactured_year >= 1900 && manufactured_year <= 2023) {
            return false;
        }
        return true;
    }

    private boolean validateContainsCharacterVin(String vin) {
        return !Pattern.matches("[A-Za-z0-9]+", vin);
    }

    private boolean validateVinLength(String vin) {
        return vin.length() != 17;
    }

    private boolean validateContainsCharacterBrande(String brand) {
        return !Pattern.matches("[A-Za-z]+", brand);
    }

    private boolean validateBrandLength(String brand) {
        return brand.length() >= 50 || brand.length() < 3;
    }

    private boolean validateVinExists(String vin, Long id) {
        List<Vehicle> vehicles = vehicleRepository.findAllVehicles();

        for (Vehicle v : vehicles) {
            if (v.getVin().equals(vin) && !Objects.equals(v.getId(), id)) {
                return true;
            }
        }
        return false;
    }

}
