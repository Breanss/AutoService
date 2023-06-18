package com.example.autoservice

import com.example.autoservice.service.ServiceValidator
import com.example.autoservice.service.domain.Service
import com.example.autoservice.service.domain.ServiceDto
import com.example.autoservice.service.domain.ServiceValidationError
import com.example.autoservice.servicetype.ServiceTypeRepository
import com.example.autoservice.servicetype.domain.ServiceType
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.text.SimpleDateFormat

class ServiceValidatorTests extends Specification {
    @Subject
    ServiceValidator serviceValidator

    ServiceTypeRepository serviceRepository = Mock(ServiceTypeRepository)

    @Shared
    ServiceDto serviceDto

    @Shared
    Service service

    def setup() {
        serviceValidator = new ServiceValidator(serviceRepository)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        serviceDto = new ServiceDto(1L, 424.99, dateFormat.parse("2023-01-12"), null, new ServiceType(1L, "Wymiana oleju i filtra oleju"));
        service = new Service(1L, 424.99, dateFormat.parse("2023-01-12"), null, new ServiceType(1L, "Wymiana oleju i filtra oleju"));
    }

    def "Should return validation successfull when service is correct"() {
        given:
        def s = serviceDto
        serviceRepository.findServiceTypeById(1L) >> new ServiceType(1L, "Wymiana oleju i filtra oleju")
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        result.serviceValid
    }

    def "Should return validation failed when date is null"() {
        given:
        def s = serviceDto
        s.setDate(null)
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        !result.serviceValid
        result.serviceValidationError == ServiceValidationError.NULL_VALUE_DATE
    }

    def "Should return validation failed when date is incorrect"() {
        given:
        def s = serviceDto
        s.setDate(new Date(3000, 02, 11))
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        !result.serviceValid
        result.serviceValidationError == ServiceValidationError.INCORRECT_DATE
    }

    def "Should return validation failed when length of detail is incorrect"() {
        given:
        def s = serviceDto
        s.setDetails("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest")
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        !result.serviceValid
        result.serviceValidationError == ServiceValidationError.INCORRECT_LENGTH_DETAIL
    }

    def "Should return validation failed when service type is null"() {
        given:
        def s = serviceDto
        s.setServiceType(null)
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        !result.serviceValid
        result.serviceValidationError == ServiceValidationError.NULL_SERVICE_TYPE
    }

    def "Should return validation failed when value of service type is incorrect"() {
        given:
        def s = serviceDto
        s.setServiceType(new ServiceType(1L, null))
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        !result.serviceValid
        result.serviceValidationError == ServiceValidationError.INCORRECT_VALUE_SERVICE_TYPE
    }

    def "Should return validation failed when price == 0"() {
        given:
        def s = serviceDto
        s.setPrice(0)
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        !result.serviceValid
        result.serviceValidationError == ServiceValidationError.MINUS_PRICE
    }

    def "Should return validation failed when price < 0"() {
        given:
        def s = serviceDto
        s.setPrice(-1)
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        !result.serviceValid
        result.serviceValidationError == ServiceValidationError.MINUS_PRICE
    }

    def "Should return validation failed when precision of price is incorrect"() {
        given:
        def s = serviceDto
        s.setPrice(123.123)
        when:
        def result = serviceValidator.serviceValidationResult(s)
        then:
        !result.serviceValid
        result.serviceValidationError == ServiceValidationError.INCORRECT_PRECISION_PRICE
    }

}
