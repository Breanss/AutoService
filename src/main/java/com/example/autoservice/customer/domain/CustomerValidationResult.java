package com.example.autoservice.customer.domain;

import lombok.Getter;

@Getter
public class CustomerValidationResult {
    private final boolean isCustomerValid;
    private final CustomerValidationError customerValidationError;

    public CustomerValidationResult(boolean isCustomerValid, CustomerValidationError customerValidationError) {
        this.isCustomerValid = isCustomerValid;
        this.customerValidationError = customerValidationError;
    }
}
