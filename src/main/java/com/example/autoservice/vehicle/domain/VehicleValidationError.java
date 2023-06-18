package com.example.autoservice.vehicle.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VehicleValidationError {
    NULL_VALUE_BRAND("Brand is null!"),
    INCORRECT_LENGTH_BRAND("Brand has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS_BRAND("Brand contains illegal characters!"),

    NULL_VALUE_MODEL("Model is null!"),
    INCORRECT_LENGTH_MODEL("Model has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS_MODEL("Model contains illegal characters!"),

    INCORRECT_VALUE_MANUFACTURED_YEAR("Manufactured year has incorrect value!"),

    NULL_VALUE_VIN("VIN is null!"),
    INCORRECT_VIN("VIN has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS_VIN("VIN contains illegal characters!"),
    VIN_ALREADY_EXISTS("NIP already exists!"),

    NULL_VALUE_VEHICLE_TYPE("Vehicle type is null!");


    private final String message;
}
