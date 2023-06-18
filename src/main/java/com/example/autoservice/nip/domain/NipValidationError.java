package com.example.autoservice.nip.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NipValidationError {
    INCORRECT_LENGTH("NIP has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS("NIP contains illegal characters!"),
    CHECKSUM_FAILED("NIP has invalid checksum!"),
    NIP_ALREADY_EXISTS("NIP already exists!");

    private final String message;
}
