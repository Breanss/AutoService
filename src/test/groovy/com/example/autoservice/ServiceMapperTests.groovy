package com.example.autoservice

import com.example.autoservice.service.ServiceMapper
import com.example.autoservice.service.domain.Service
import com.example.autoservice.service.domain.ServiceDto
import com.example.autoservice.servicetype.domain.ServiceType
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.text.SimpleDateFormat

class ServiceMapperTests extends Specification {

    @Subject
    ServiceMapper serviceMapper

    @Shared
    Service service

    @Shared
    ServiceDto serviceDto

    def setup() {
        serviceMapper = new ServiceMapper()
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        service = new Service(1L, 150.00, dateFormat.parse("2023-01-12"), null, new ServiceType(1L, "Wymiana amortyzatorów"))
        serviceDto = new ServiceDto(1L, 150.00, dateFormat.parse("2023-01-12"), null, new ServiceType(1L, "Wymiana amortyzatorów"))
    }

    def "Should return serviceDto"() {
        given:
        def serviceD = serviceDto
        when:
        def result = serviceMapper.toService(serviceD)
        then:
        result.getId() == serviceD.getId()
        result.getDate() == serviceD.getDate()
        result.getDetails() == serviceD.getDetails()
        result.getPrice() == serviceD.getPrice()
        result.getServiceType() == serviceD.getServiceType()
    }

    def "Should return service"() {
        given:
        def service = service
        when:
        def result = serviceMapper.toServiceDto(service)
        then:
        result.getId() == service.getId()
        result.getDate() == service.getDate()
        result.getDetails() == service.getDetails()
        result.getPrice() == service.getPrice()
        result.getServiceType() == service.getServiceType()
    }

}
