package com.example.autoservice.vehicle.domain;

import lombok.Getter;

@Getter
public class VehicleValidationResult {
    private final boolean isVehicleValid;
    private final VehicleValidationError vehicleValidationError;

    public VehicleValidationResult(boolean isVehicleValid, VehicleValidationError vehicleValidationError) {
        this.isVehicleValid = isVehicleValid;
        this.vehicleValidationError = vehicleValidationError;
    }
}
