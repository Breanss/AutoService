package com.example.autoservice.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceValidationError {

    NULL_VALUE_DATE("Date is null!"),
    INCORRECT_DATE("Date is ahead of the system date!"),

    INCORRECT_LENGTH_DETAIL("Detail has incorrect length!"),

    MINUS_PRICE("Price is minus!"),
    INCORRECT_PRECISION_PRICE("Price has incorrect precision!"),

    NULL_SERVICE_TYPE("Service type is null!"),
    INCORRECT_VALUE_SERVICE_TYPE("Service type has incorrect value!");


    private final String message;
}
