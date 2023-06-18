package com.example.autoservice

import com.example.autoservice.customer.CustomerMapper
import com.example.autoservice.customer.CustomerRepository
import com.example.autoservice.customer.domain.Customer
import com.example.autoservice.customer.domain.CustomerDto
import com.example.autoservice.vehicle.VehicleMapper
import com.example.autoservice.vehicle.VehicleRepository
import com.example.autoservice.vehicle.VehicleService
import com.example.autoservice.vehicle.VehicleValidator
import com.example.autoservice.vehicle.domain.Vehicle
import com.example.autoservice.vehicle.domain.VehicleDto
import com.example.autoservice.vehicle.domain.VehicleType
import com.example.autoservice.vehicle.domain.VehicleValidationResult
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class VehicleServiceTests extends Specification {
    @Subject
    VehicleService vehicleService

    VehicleValidator vehicleValidator = Mock(VehicleValidator)
    VehicleRepository vehicleRepository = Mock(VehicleRepository)
    CustomerRepository customerRepository = Mock(CustomerRepository)
    CustomerMapper customerMapper = Mock(CustomerMapper)
    VehicleMapper vehicleMapper = Mock(VehicleMapper)

    @Shared
    CustomerDto customerDto
    @Shared
    Customer customer
    @Shared
    VehicleDto vehicleDto
    @Shared
    Vehicle vehicle

    def setup() {
        vehicleService = new VehicleService(vehicleValidator, customerRepository, vehicleRepository, vehicleMapper, customerMapper)
        customerDto = new CustomerDto(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", null)
        customer = new Customer(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", null)
        vehicleDto = new VehicleDto(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, customerDto, null)
        vehicle = new Vehicle(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, customer, null)
    }

    def "Should return all vehiclesDTO"() {
        given:
        def vehicleList = [vehicle, vehicle].toList()
        def vehicleDtoList = [vehicleDto, vehicleDto].toList()
        vehicleRepository.findAllVehicles() >> vehicleList
        vehicleMapper.toVehicleDto(_ as Vehicle) >> { Vehicle vehicle1 ->
            vehicleDto.find { it.id == vehicle1.id }
        }
        when:
        def result = vehicleService.getAll()
        then:
        result == vehicleDtoList
    }

    def "Should return vehicleDTO by id"() {
        given:
        vehicleRepository.findVehicleById(1L) >> vehicle
        vehicleMapper.toVehicleDto(vehicle) >> vehicleDto
        when:
        def result = vehicleService.getVehicleByID(1L)
        then:
        result == vehicleDto
    }

    def "Should return null vehicleDTO by id when vehicle no exist"() {
        given:
        vehicleRepository.findVehicleById(1L) >> null
        vehicleMapper.toVehicleDto(null) >> null
        when:
        def result = vehicleService.getVehicleByID(1L)
        then:
        result == null
    }

    def "Should return vehicleDTO by vin"() {
        given:
        vehicleRepository.findVehicleByVin("YS3DF78K527013335") >> vehicle
        vehicleMapper.toVehicleDto(vehicle) >> vehicleDto
        when:
        def result = vehicleService.getVehicleByVIN("YS3DF78K527013335")
        then:
        result == vehicleDto
    }

    def "Should return null vehicleDTO by vin when vehicle no exist"() {
        given:
        vehicleRepository.findVehicleByVin("YS3DF78K527013335") >> null
        vehicleMapper.toVehicleDto(null) >> null
        when:
        def result = vehicleService.getVehicleByVIN("YS3DF78K527013335")
        then:
        result == null
    }

    def "Should delete customer by ID"() {
        given:
        vehicleRepository.deleteById(1L) >> vehicle
        when:
        vehicleService.deleteVehicleByID(1L)
        then:
        1 * vehicleRepository.deleteById(1L)
    }

    def "Should return true when add customer successfull"() {
        given:
        vehicleValidator.validVehicle(vehicleDto) >> new VehicleValidationResult(true, null)
        vehicleMapper.toVehicle(vehicleDto) >> vehicle
        vehicleRepository.save(_ as Vehicle) >> true
        customerRepository.findCustomerByCustomerID(1L) >> customer
        customerMapper.toCustomerDto(customer) >> customerDto
        when:
        def result = vehicleService.saveVehicle(vehicleDto)
        then:
        result
    }

    def "Should return false when add vehicle valid is false"() {
        given:
        vehicleValidator.validVehicle(vehicleDto) >> new VehicleValidationResult(false, null)
        when:
        def result = vehicleService.saveVehicle(vehicleDto)
        then:
        !result
    }

    def "Should return false when add vehicle customer is null"() {
        given:
        vehicleDto.setCustomer(null)
        vehicleValidator.validVehicle(vehicleDto) >> new VehicleValidationResult(true, null)
        when:
        def result = vehicleService.saveVehicle(vehicleDto)
        then:
        !result
    }

    def "Should return false when add vehicle customer not exist"() {
        given:
        vehicleValidator.validVehicle(vehicleDto) >> new VehicleValidationResult(true, null)
        customerRepository.findCustomerByCustomerID(2L) >> null
        when:
        def result = vehicleService.saveVehicle(vehicleDto)
        then:
        !result
    }

    def "Should return true when edit vehicle successfull"() {
        given:
        vehicleRepository.findVehicleById(1L) >> vehicle
        vehicleMapper.toVehicleDto(vehicle) >> vehicleDto
        vehicleValidator.validVehicle(vehicleDto) >> new VehicleValidationResult(true, null)
        when:
        def result = vehicleService.editVehicleByID(new VehicleDto(null, "Audi", "A3", 2005, "1HGEM21292L047875", VehicleType.CAR, null, null), 1L)
        then:
        result
    }

    def "Should return false when edit vehicle is null"() {
        given:
        vehicleRepository.findVehicleById(1L) >> vehicle
        when:
        def result = vehicleService.editVehicleByID(new VehicleDto(null, "Audi", "A3", 2005, "1HGEM21292L047875", VehicleType.CAR, null, null), 1L)
        then:
        !result
    }

    def "Should return false when edit vehicle is valid false"() {
        given:
        vehicleRepository.findVehicleById(1L) >> vehicle
        vehicleMapper.toVehicleDto(vehicle) >> vehicleDto
        vehicleValidator.validVehicle(vehicleDto) >> new VehicleValidationResult(false, null)
        when:
        def result = vehicleService.editVehicleByID(new VehicleDto(null, "Audi", "A3", 2005, "1HGEM212L047875", VehicleType.CAR, null, null), 1L)
        then:
        !result
    }

    def "Should return false when edit vehicle exception occurs during saving"() {
        given:
        vehicleRepository.findVehicleById(1L) >> vehicle
        vehicleMapper.toVehicleDto(vehicle) >> vehicleDto
        vehicleValidator.validVehicle(vehicleDto) >> new VehicleValidationResult(false, null)
        vehicleRepository.editVehicle(_ as String, _ as String, _ as int, _ as String, _ as VehicleType, _ as Long) >> { throw new Exception() }
        when:
        def result = vehicleService.editVehicleByID(new VehicleDto(null, "Audi", "A3", 2005, "1HGEM212L047875", VehicleType.CAR, null, null), 1L)
        then:
        !result

    }
}
