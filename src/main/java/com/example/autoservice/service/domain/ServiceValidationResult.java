package com.example.autoservice.service.domain;

import lombok.Getter;

@Getter
public class ServiceValidationResult {
    private final boolean isServiceValid;
    private final ServiceValidationError serviceValidationError;

    public ServiceValidationResult(boolean isServiceValid, ServiceValidationError serviceValidationError) {
        this.isServiceValid = isServiceValid;
        this.serviceValidationError = serviceValidationError;
    }
}
