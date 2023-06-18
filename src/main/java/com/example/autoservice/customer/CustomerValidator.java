package com.example.autoservice.customer;

import com.example.autoservice.customer.domain.Customer;
import com.example.autoservice.customer.domain.CustomerDto;
import com.example.autoservice.customer.domain.CustomerValidationError;
import com.example.autoservice.customer.domain.CustomerValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;


@Component
@AllArgsConstructor
public class CustomerValidator {
    private final CustomerRepository customerRepository;

    public CustomerValidationResult validCustomer(CustomerDto customerDto) {
        if (isNull(customerDto.getFirst_name())) {
            return new CustomerValidationResult(false, CustomerValidationError.NULL_VALUE_FIRST_NAME);
        } else if (validateLengthFirstName(customerDto.getFirst_name())) {
            return new CustomerValidationResult(false, CustomerValidationError.INCORRECT_LENGTH_FIRST_NAME);
        } else if (validateContainsCharacterFirstName(customerDto.getFirst_name())) {
            return new CustomerValidationResult(false, CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_FIRST_NAME);
        }

        if (isNull(customerDto.getLast_name())) {
            return new CustomerValidationResult(false, CustomerValidationError.NULL_VALUE_LAST_NAME);
        } else if (validateLengthLastName(customerDto.getLast_name())) {
            return new CustomerValidationResult(false, CustomerValidationError.INCORRECT_LENGTH_LAST_NAME);
        } else if (validateContainsCharacterLastName(customerDto.getLast_name())) {
            return new CustomerValidationResult(false, CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_LAST_NAME);
        }

        if (isNull(customerDto.getAddress())) {
            return new CustomerValidationResult(false, CustomerValidationError.NULL_VALUE_ADDRESS);
        } else if (validateLengthAddress(customerDto.getAddress())) {
            return new CustomerValidationResult(false, CustomerValidationError.INCORRECT_LENGTH_ADDRESS);
        } else if (validateContainsCharacterAddress(customerDto.getAddress())) {
            return new CustomerValidationResult(false, CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_ADDRESS);
        }

        if (isNull(customerDto.getMobile())) {
            return new CustomerValidationResult(false, CustomerValidationError.NULL_VALUE_MOBILE);
        } else if (validateLengthMobile(customerDto.getMobile())) {
            return new CustomerValidationResult(false, CustomerValidationError.INCORRECT_LENGTH_MOBILE);
        } else if (validateContainsCharacterMobile(customerDto.getMobile())) {
            return new CustomerValidationResult(false, CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_MOBILE);
        }






        if (isNull(customerDto.getEmail())) {
            return new CustomerValidationResult(false, CustomerValidationError.NULL_VALUE_EMAIL);
        } else if (validateLengthEmail(customerDto.getEmail())) {
            return new CustomerValidationResult(false, CustomerValidationError.INCORRECT_LENGTH_EMAIL);
        } else if (validateContainsCharacterEmail(customerDto.getEmail())) {
            return new CustomerValidationResult(false, CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_EMAIL);
        } else if (validateFormatEmail(customerDto.getEmail())) {
            return new CustomerValidationResult(false, CustomerValidationError.INCORRECT_FORMAT_EMAIL);
        } else if (validateIfEmailIsUsed(customerDto.getEmail(), customerDto.getId())) {
            return new CustomerValidationResult(false, CustomerValidationError.EMAIL_ALREADY_USED);
        }

        return new CustomerValidationResult(true, null);
    }


    private boolean validateLengthFirstName(String firstName) {
        return firstName.length() >= 50 || firstName.length() < 3;
    }

    private boolean validateLengthLastName(String lastName) {
        return lastName.length() >= 50 || lastName.length() < 3;
    }

    private boolean validateLengthEmail(String email) {
        return email.length() >= 50 || email.length() < 5;
    }

    private boolean validateLengthAddress(String address) {
        return address.length() >= 50 || address.length() < 3;
    }

    private boolean validateLengthMobile(String mobile) {
        return mobile.length() != 9;
    }

    private boolean validateContainsCharacterFirstName(String firstName) {
        return !Pattern.matches("[A-Za-zĄĆŹŻĘÓŁŃŚąćźżęółńś]+", firstName);
    }

    private boolean validateContainsCharacterLastName(String lastName) {
        return !Pattern.matches("[A-Za-zĄĆŹŻĘÓŁŃŚąćźżęółńś]+", lastName);
    }

    private boolean validateContainsCharacterMobile(String mobile) {
        return !Pattern.matches("[0-9]+", mobile);
    }

    private boolean validateContainsCharacterEmail(String email) {
        return !Pattern.matches("[A-Za-z0-9.@\\-_]+", email);
    }

    private boolean validateContainsCharacterAddress(String address) {
        return !Pattern.matches("[A-Za-zĄĆŹŻĘÓŁŃŚąćźżęółńś0-9.,/\\- ]+", address);
    }

    private boolean validateFormatEmail(String email) {
        return !Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }

    private boolean validateIfEmailIsUsed(String email, Long id) {
        List<Customer> customer = customerRepository.findAllCustomers();
        if (isNull(customer))
            return false;


        for (Customer c : customer) {
            if (c.getEmail().equals(email) && !Objects.equals(c.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}
