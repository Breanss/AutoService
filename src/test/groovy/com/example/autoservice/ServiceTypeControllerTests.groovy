package com.example.autoservice

import com.example.autoservice.servicetype.ServiceTypeController
import com.example.autoservice.servicetype.ServiceTypeService
import com.example.autoservice.servicetype.domain.ServiceType
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

@WebMvcTest(ServiceTypeController)
class ServiceTypeControllerTests extends Specification {

    @Subject
    ServiceTypeController serviceTypeController

    ServiceTypeService serviceTypeService = Mock(ServiceTypeService)

    @Shared
    ServiceType serviceType

    @MockBean
    MockMvc mockMvc

    @BeforeEach
    def setup() {
        serviceType = new ServiceType(1L, "Fix")
        serviceTypeController = new ServiceTypeController(serviceTypeService)
    }

    def "Should view all service types"() {
        given:
        def serviceTypesList = [serviceType, serviceType].toList()
        serviceTypeService.getAll() >> serviceTypesList
        when:
        def result = serviceTypeController.viewAllServiceTypes()
        then:
        result == serviceTypesList
    }

}
