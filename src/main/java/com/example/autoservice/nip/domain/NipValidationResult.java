package com.example.autoservice.nip.domain;

import lombok.Getter;

@Getter
public class NipValidationResult {
    private final boolean isNipValid;
    private final NipValidationError nipValidationError;

    public NipValidationResult(boolean isNipValid, NipValidationError nipValidationError) {
        this.isNipValid = isNipValid;
        this.nipValidationError = nipValidationError;
    }
}
