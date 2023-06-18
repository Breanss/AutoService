package com.example.autoservice

import com.example.autoservice.vehicle.VehicleRepository
import com.example.autoservice.vehicle.VehicleValidator
import com.example.autoservice.vehicle.domain.Vehicle
import com.example.autoservice.vehicle.domain.VehicleDto
import com.example.autoservice.vehicle.domain.VehicleType
import com.example.autoservice.vehicle.domain.VehicleValidationError
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


class VehicleValidatorTests extends Specification {
    @Subject
    VehicleValidator vehicleValidator

    VehicleRepository vehicleRepository = Mock(VehicleRepository)

    @Shared
    VehicleDto vehicleDto

    @Shared
    Vehicle vehicle

    def setup() {
        vehicleValidator = new VehicleValidator(vehicleRepository)
        vehicleDto = new VehicleDto(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, null, null)
        vehicle = new Vehicle(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, null, null)
    }

    def "Should return validation successfull when vehicle is correct"() {
        given:
        def v = vehicleDto
        vehicleRepository.findAllVehicles() >> [vehicle].toList()
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        result.vehicleValid
    }

    def "Should return validation failed when brand is null"() {
        given:
        def v = vehicleDto
        v.setBrand(null)
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.NULL_VALUE_BRAND
    }

    def "Should return validation failed when model is null"() {
        given:
        def v = vehicleDto
        v.setModel(null)
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.NULL_VALUE_MODEL
    }

    def "Should return validation failed when vehicle_type is null"() {
        given:
        def v = vehicleDto
        v.setVehicleType(null)
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.NULL_VALUE_VEHICLE_TYPE
    }

    def "Should return validation failed when vin is null"() {
        given:
        def v = vehicleDto
        v.setVin(null)
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.NULL_VALUE_VIN
    }

    def "Should return validation failed when brand is too short"() {
        given:
        def v = vehicleDto
        v.setBrand("A")
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.INCORRECT_LENGTH_BRAND
    }


    def "Should return validation failed when model is too short"() {
        given:
        def v = vehicleDto
        v.setModel("")
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.INCORRECT_LENGTH_MODEL
    }


    def "Should return validation failed when brand contains illegal characters"() {
        given:
        def v = vehicleDto
        v.setBrand("A<>")
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.CONTAINS_ILLEGAL_CHARACTERS_BRAND
    }

    def "Should return validation failed when model contains illegal characters"() {
        given:
        def v = vehicleDto
        v.setModel("A<>")
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.CONTAINS_ILLEGAL_CHARACTERS_MODEL
    }

    def "Should return validation failed when vin contains illegal characters"() {
        given:
        def v = vehicleDto
        v.setVin("YS3DF7?K527013335")
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.CONTAINS_ILLEGAL_CHARACTERS_VIN
    }

    def "Should return validation failed when vin is incorrect"() {
        given:
        def v = vehicleDto
        v.setVin("A<>")
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.INCORRECT_VIN
    }

    def "Should return validation failed when manufactured_year has incorrect value"() {
        given:
        def v = vehicleDto
        v.setManufactured_year(20000)
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.INCORRECT_VALUE_MANUFACTURED_YEAR
    }


    def "Should return validation failed when vin already exist"() {
        given:
        def v = vehicleDto
        def v2 = new Vehicle(2L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, null, null)
        vehicleRepository.findAllVehicles() >> [vehicle, v2].toList()
        when:
        def result = vehicleValidator.validVehicle(v)
        then:
        !result.vehicleValid
        result.vehicleValidationError == VehicleValidationError.VIN_ALREADY_EXISTS
    }

}
