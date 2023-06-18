package com.example.autoservice

import com.example.autoservice.customer.CustomerRepository
import com.example.autoservice.customer.CustomerValidator
import com.example.autoservice.customer.domain.Customer
import com.example.autoservice.customer.domain.CustomerDto
import com.example.autoservice.customer.domain.CustomerValidationError
import com.example.autoservice.nip.domain.Nip
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class CustomerValidatorTests extends Specification {
    @Subject
    CustomerValidator customerValidator

    CustomerRepository customerRepository = Mock(CustomerRepository)


    @Shared
    CustomerDto customerDto

    def setup() {
        customerValidator = new CustomerValidator(customerRepository)
        customerDto = new CustomerDto(null, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", null)
    }

    def "Should return validation successfull when customer is correct"() {
        given:
        def customer = customerDto
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        result.customerValid
    }

    def "Should return validation failed when last_name is null"() {
        given:
        def customer = customerDto
        customer.setLast_name(null)
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.NULL_VALUE_LAST_NAME
    }

    def "Should return validation failed when first_name is null"() {
        given:
        def customer = customerDto
        customer.setFirst_name(null)
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.NULL_VALUE_FIRST_NAME
    }

    def "Should return validation failed when mobile is null"() {
        given:
        def customer = customerDto
        customer.setMobile(null)
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.NULL_VALUE_MOBILE
    }

    def "Should return validation failed when email is null"() {
        given:
        def customer = customerDto
        customer.setEmail(null)
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.NULL_VALUE_EMAIL
    }

    def "Should return validation failed when address is null"() {
        given:
        def customer = customerDto
        customer.setAddress(null)
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.NULL_VALUE_ADDRESS
    }

    def "Should return validation failed when first_name is too short"() {
        given:
        def customer = customerDto
        customer.setFirst_name("A")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.INCORRECT_LENGTH_FIRST_NAME
    }

    def "Should return validation failed when last_name is too short"() {
        given:
        def customer = customerDto
        customer.setLast_name("A")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.INCORRECT_LENGTH_LAST_NAME
    }

    def "Should return validation failed when mobile is too short"() {
        given:
        def customer = customerDto
        customer.setMobile("12345678")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.INCORRECT_LENGTH_MOBILE
    }

    def "Should return validation failed when address is too short"() {
        given:
        def customer = customerDto
        customer.setAddress("1")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.INCORRECT_LENGTH_ADDRESS
    }

    def "Should return validation failed when email is too short"() {
        given:
        def customer = customerDto
        customer.setEmail("1")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.INCORRECT_LENGTH_EMAIL
    }

    def "Should return validation failed when first_name contains letters"() {
        given:
        def customer = customerDto
        customer.setFirst_name("Jan1")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_FIRST_NAME
    }

    def "Should return validation failed when last_name contains letters"() {
        given:
        def customer = customerDto
        customer.setLast_name("Kowalski1")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_LAST_NAME
    }

    def "Should return validation failed when address contains letters"() {
        given:
        def customer = customerDto
        customer.setAddress("Lublin @")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_ADDRESS
    }

    def "Should return validation failed when email contains letters"() {
        given:
        def customer = customerDto
        customer.setEmail("jan%@o2.pl")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_EMAIL
    }

    def "Should return validation failed when mobile contains letters"() {
        given:
        def customer = customerDto
        customer.setMobile("1234a6789")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.CONTAINS_ILLEGAL_CHARACTERS_MOBILE
    }

    def "Should return validation failed when email incorrect format"() {
        given:
        def customer = customerDto
        customer.setEmail("jan@o2")
        when:
        def result = customerValidator.validCustomer(customer)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.INCORRECT_FORMAT_EMAIL
    }

    def "Should return validation failed when customer already exist"() {
        given:
        def c1 = new Customer(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", new Nip(1L, "as"))
        def c2 = new Customer(2L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", new Nip(2L, "asd"))
        def customerList = [c1, c2].toList()
        customerRepository.findAllCustomers() >> customerList

        when:
        def result = customerValidator.validCustomer(customerDto)
        then:
        !result.customerValid
        result.customerValidationError == CustomerValidationError.EMAIL_ALREADY_USED
    }

    def "Should return validation failed when customer no already exist"() {
        given:
        def c1 = new Customer(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jann@o2.pl", new Nip(1L, "as"))
        def c2 = new Customer(2L, "Anna", "Nowak", "Lublin 5", "123456789", "anna@o2.pl", new Nip(2L, "asd"))
        def customerList = [c1, c2].toList()
        customerRepository.findAllCustomers() >> customerList

        when:
        def result = customerValidator.validCustomer(customerDto)
        then:
        result.customerValid
    }
}
