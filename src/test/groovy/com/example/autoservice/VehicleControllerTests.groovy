package com.example.autoservice

import com.example.autoservice.customer.domain.CustomerDto
import com.example.autoservice.service.domain.ServiceDto
import com.example.autoservice.vehicle.VehicleController
import com.example.autoservice.vehicle.VehicleService
import com.example.autoservice.vehicle.domain.VehicleDto
import com.example.autoservice.vehicle.domain.VehicleType
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


class VehicleControllerTests extends Specification {
    @Subject
    VehicleController vehicleController

    VehicleService vehicleService = Mock(VehicleService)

    @Shared
    VehicleDto vehicleDto

    @Shared
    CustomerDto customerDto

    @BeforeEach
    def setup() {
        customerDto = new CustomerDto(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", null)
        vehicleDto = new VehicleDto(1L, "BMW", "E36", 1994, "YS3DF78K527013335", VehicleType.CAR, customerDto, new ArrayList<ServiceDto>())
        vehicleController = new VehicleController(vehicleService);
    }

    def "Should view all vehicles"() {
        given:
        def vehicleList = [vehicleDto, vehicleDto].toList()
        vehicleService.getAll() >> vehicleList
        when:
        def result = vehicleController.viewAllVehicles()
        then:
        result == vehicleList

    }

    def "Should view vehicles by ID "() {
        given:
        def vehicle = vehicleDto
        vehicleService.getVehicleByID(1L) >> vehicle
        when:
        def result = vehicleController.viewVehicleByID(1L)
        then:
        result == vehicleDto
    }

    def "Should view vehicles by customer "() {
        given:
        def vehicleList = [vehicleDto, vehicleDto].toList()
        vehicleService.getAllVehiclesByCustomer(customerDto.getId()) >> vehicleList
        when:
        def result = vehicleController.viewAllVehicleByCustomer(customerDto.getId())
        then:
        result == vehicleList
    }

    def "Should view vehicle by VIN "() {
        given:
        def vehicle = vehicleDto
        def vin = "YS3DF78K527013335"
        vehicleService.getVehicleByVIN(vin) >> vehicle
        when:
        def result = vehicleController.viewVehicleByVIN(vin)
        then:
        result == vehicleDto
    }


    def "Should return true when successfull add vehicle"() {
        given:
        def saveResult = true
        vehicleService.saveVehicle(vehicleDto) >> saveResult
        when:
        def result = vehicleController.addVehicle(vehicleDto)
        then:
        result
    }

    def "Should return true when successfull edit vehicle"() {
        given:
        def saveResult = true
        vehicleService.editVehicleByID(vehicleDto, 1L) >> saveResult
        when:
        def result = vehicleController.editVehicleByID(vehicleDto, 1L)
        then:
        result
    }
}
