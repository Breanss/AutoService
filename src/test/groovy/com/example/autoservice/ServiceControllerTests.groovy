package com.example.autoservice

import com.example.autoservice.customer.domain.CustomerDto
import com.example.autoservice.service.ServiceController
import com.example.autoservice.service.ServiceService
import com.example.autoservice.service.domain.ServiceDto
import com.example.autoservice.servicetype.domain.ServiceType
import com.example.autoservice.vehicle.domain.VehicleDto
import com.example.autoservice.vehicle.domain.VehicleType
import org.junit.jupiter.api.BeforeEach
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.text.SimpleDateFormat

class ServiceControllerTests extends Specification {

    @Subject
    ServiceController serviceController

    ServiceService serviceService = Mock(ServiceService)

    @Shared
    ServiceDto serviceDto

    @BeforeEach
    def setup() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        serviceDto = new ServiceDto(1L, 424.99, dateFormat.parse("2023-01-12"), null, new ServiceType(1L, "Fix"));
        serviceController = new ServiceController(serviceService)
    }

    def "Should view all services"() {
        given:
        def servicesList = [serviceDto, serviceDto].toList()
        serviceService.getAll() >> servicesList
        when:
        def result = serviceController.viewAllServices()
        then:
        result == servicesList
    }

    def "Should view services by vehicle ID "() {
        given:
        def services = [serviceDto, serviceDto].toList()
        def customerDto = new CustomerDto(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", null)
        def vehicleDto = new VehicleDto(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, customerDto, services)
        serviceService.getAllServiceByVehicleId(1L) >> services
        when:
        def result = serviceController.viewServicesForVehicle(1L)
        then:
        result == vehicleDto.services
    }


    def "Should view services by customer ID "() {
        given:
        def services = [serviceDto, serviceDto].toList()
        def customerDto = new CustomerDto(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", null)
        def vehicleDto = new VehicleDto(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, customerDto, services)
        serviceService.getAllServiceByCustomerId(1L) >> services
        when:
        def result = serviceController.viewServicesForCustomer(1L)
        then:
        result == vehicleDto.services
    }

    def "Should return true when successfull add service"() {
        given:
        def saveResult = true
        serviceService.saveService(serviceDto, 1L) >> saveResult
        when:
        def result = serviceController.addService(serviceDto, 1L)
        then:
        result
    }


}
