package com.example.autoservice

import com.example.autoservice.customer.CustomerController
import com.example.autoservice.customer.CustomerService
import com.example.autoservice.customer.domain.CustomerDto
import org.junit.jupiter.api.BeforeEach
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


class CustomerControllerTests extends Specification {
    @Subject
    CustomerController customerController

    CustomerService customerService = Mock(CustomerService)

    @Shared
    CustomerDto customerDto

    @BeforeEach
    def setup() {
        customerDto = new CustomerDto(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", null)
        customerController = new CustomerController(customerService)
    }

    def "Should view all customers"() {
        given:
        def customerList = [customerDto, customerDto].toList()
        customerService.getAll() >> customerList
        when:
        def result = customerController.viewAllCustomers()
        then:
        result == customerList

    }

    def "Should view customers by email "() {
        given:
        def customer = customerDto
        customerService.getCustomerByEmail("jan@o2.pl") >> customer
        when:
        def result = customerController.viewCustomerByEmail("jan@o2.pl")
        then:
        result == customerDto
    }

    def "Should view customers by id "() {
        given:
        def customer = customerDto
        customerService.getCustomerByID(1L) >> customer
        when:
        def result = customerController.viewCustomerByID(1L)
        then:
        result == customerDto
    }

    def "Should return true when successfull add customers"() {
        given:
        def saveResult = true
        customerService.saveCustomer(customerDto) >> saveResult
        when:
        def result = customerController.addCustomer(customerDto)
        then:
        result
    }

    def "Should return true when successfull edit customers"() {
        given:
        def saveResult = true
        customerService.editCustomerByid(customerDto,1L) >> saveResult
        when:
        def result = customerController.editCustomerByID(customerDto,1L)
        then:
        result
    }
}
