package com.example.autoservice

import com.example.autoservice.customer.CustomerMapper
import com.example.autoservice.customer.domain.Customer
import com.example.autoservice.customer.domain.CustomerDto
import com.example.autoservice.nip.NipMapper
import com.example.autoservice.nip.domain.Nip
import com.example.autoservice.nip.domain.NipDto
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class CustomerMapperTests extends Specification {
    @Subject
    CustomerMapper customerMapper

    NipMapper nipMapper = Mock(NipMapper)

    @Shared
    Customer customer

    @Shared
    CustomerDto customerDto

    @Shared
    Nip nip

    @Shared
    NipDto nipDto

    def setup() {
        customerMapper = new CustomerMapper(nipMapper)
        nip = new Nip(1L, "5352502246")
        nipDto = new NipDto(1L, "5352502246")
        customer = new Customer(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", nip)
        customerDto = new CustomerDto(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", nipDto)
    }

    def "Should return customerDto"() {
        given:
        nipMapper.toNipDto(nip) >> nipDto
        when:
        def result = customerMapper.toCustomerDto(customer)
        then:
        result.getNip().getId() == customer.getNip().getId()
        result.getId() == customer.getId()
        result.getFirst_name() == customer.getFirst_name()
        result.getLast_name() == customer.getLast_name()
        result.getAddress() == customer.getAddress()
        result.getMobile() == customer.getMobile()
        result.getEmail() == customer.getEmail()
    }

    def "Should return customer"() {
        given:
        nipMapper.toNip(nipDto) >> nip
        when:
        def result = customerMapper.toCustomer(customerDto)
        then:
        result.getNip().getId() == customerDto.getNip().getId()
        result.getId() == customerDto.getId()
        result.getFirst_name() == customerDto.getFirst_name()
        result.getLast_name() == customerDto.getLast_name()
        result.getAddress() == customerDto.getAddress()
        result.getMobile() == customerDto.getMobile()
        result.getEmail() == customerDto.getEmail()
    }
}
