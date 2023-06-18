package com.example.autoservice

import com.example.autoservice.customer.CustomerMapper
import com.example.autoservice.customer.domain.Customer
import com.example.autoservice.customer.domain.CustomerDto
import com.example.autoservice.nip.domain.Nip
import com.example.autoservice.nip.domain.NipDto
import com.example.autoservice.service.ServiceMapper
import com.example.autoservice.service.domain.Service
import com.example.autoservice.service.domain.ServiceDto
import com.example.autoservice.vehicle.VehicleMapper
import com.example.autoservice.vehicle.domain.Vehicle
import com.example.autoservice.vehicle.domain.VehicleDto
import com.example.autoservice.vehicle.domain.VehicleType
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class VehicleMapperTest extends Specification {
    @Subject
    VehicleMapper vehicleMapper

    ServiceMapper serviceMapper = Mock(ServiceMapper)
    CustomerMapper customerMapper = Mock(CustomerMapper)

    @Shared
    Customer customer

    @Shared
    CustomerDto customerDto

    @Shared
    Nip nip

    @Shared
    NipDto nipDto

    @Shared
    Vehicle vehicle

    @Shared
    VehicleDto vehicleDto

    def setup() {
        vehicleMapper = new VehicleMapper(customerMapper, serviceMapper)
        nip = new Nip(1L, "5352502246")
        nipDto = new NipDto(1L, "5352502246")
        customer = new Customer(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", nip)
        customerDto = new CustomerDto(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", nipDto)
        vehicleDto = new VehicleDto(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, customerDto, new ArrayList<ServiceDto>())
        vehicle = new Vehicle(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, customer, new ArrayList<Service>())

    }

    def "Should return vehicleDto"() {
        given:
        serviceMapper.toServiceDto(vehicle.getServices() as Service) >> vehicleDto.getServices()
        customerMapper.toCustomerDto(vehicle.getCustomer()) >> vehicleDto.getCustomer()
        when:
        def result = vehicleMapper.toVehicleDto(vehicle)
        then:
        result.getId() == vehicleDto.getId()
        result.getCustomer() == vehicleDto.getCustomer()
        result.getServices() == vehicleDto.getServices()
        result.getBrand() == vehicleDto.getBrand()
        result.getManufactured_year() == vehicleDto.getManufactured_year()
        result.getModel() == vehicleDto.getModel()
        result.getVehicleType() == vehicleDto.getVehicleType()
    }

    def "Should return vehicle"() {
        given:
        serviceMapper.toService(vehicleDto.getServices() as ServiceDto) >> vehicle.getServices()
        customerMapper.toCustomer(vehicleDto.getCustomer()) >> vehicle.getCustomer()
        when:
        def result = vehicleMapper.toVehicle(vehicleDto)
        then:
        result.getId() == vehicle.getId()
        result.getCustomer() == vehicle.getCustomer()
        result.getServices() == vehicle.getServices()
        result.getBrand() == vehicle.getBrand()
        result.getManufactured_year() == vehicle.getManufactured_year()
        result.getModel() == vehicle.getModel()
        result.getVehicleType() == vehicle.getVehicleType()
    }
}
