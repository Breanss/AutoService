package com.example.autoservice.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerValidationError {
    NULL_VALUE_FIRST_NAME("First name is null!"),
    INCORRECT_LENGTH_FIRST_NAME("First name has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS_FIRST_NAME("First name contains illegal characters!"),

    NULL_VALUE_LAST_NAME("Last name is null!"),
    INCORRECT_LENGTH_LAST_NAME("Last name has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS_LAST_NAME("Last name contains illegal characters!"),

    NULL_VALUE_ADDRESS("Address is null!"),
    INCORRECT_LENGTH_ADDRESS("Address has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS_ADDRESS("Address contains illegal characters!"),

    NULL_VALUE_MOBILE("Mobile is null!"),
    INCORRECT_LENGTH_MOBILE("Mobile has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS_MOBILE("Mobile contains illegal characters!"),

    NULL_VALUE_EMAIL("Email is null!"),
    INCORRECT_LENGTH_EMAIL("Email has incorrect length!"),
    CONTAINS_ILLEGAL_CHARACTERS_EMAIL("Email contains illegal characters!"),
    INCORRECT_FORMAT_EMAIL("Email has incorrect format!"),

    EMAIL_ALREADY_USED("Email is already used!");

    private final String message;
}
