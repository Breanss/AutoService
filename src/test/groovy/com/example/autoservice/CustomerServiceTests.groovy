package com.example.autoservice

import com.example.autoservice.customer.CustomerMapper
import com.example.autoservice.customer.CustomerRepository
import com.example.autoservice.customer.CustomerService
import com.example.autoservice.customer.CustomerValidator
import com.example.autoservice.customer.domain.Customer
import com.example.autoservice.customer.domain.CustomerDto
import com.example.autoservice.customer.domain.CustomerValidationError
import com.example.autoservice.customer.domain.CustomerValidationResult
import com.example.autoservice.nip.NipRepository
import com.example.autoservice.nip.NipValidator
import com.example.autoservice.nip.domain.Nip
import com.example.autoservice.nip.domain.NipDto
import com.example.autoservice.nip.domain.NipValidationError
import com.example.autoservice.nip.domain.NipValidationResult
import com.example.autoservice.vehicle.VehicleRepository
import com.example.autoservice.vehicle.domain.Vehicle
import com.example.autoservice.vehicle.domain.VehicleType
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class CustomerServiceTests extends Specification {
    @Subject
    CustomerService customerService

    CustomerRepository customerRepository = Mock(CustomerRepository)
    VehicleRepository vehicleRepository = Mock(VehicleRepository)
    NipRepository nipRepository = Mock(NipRepository)
    CustomerMapper customerMapper = Mock(CustomerMapper)
    CustomerValidator customerValidator = Mock(CustomerValidator)
    NipValidator nipValidator = Mock(NipValidator)

    @Shared
    Customer customer

    @Shared
    CustomerDto customerDto


    def setup() {
        customer = new Customer(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", new Nip(1L, null))
        customerDto = new CustomerDto(1L, "Jan", "Kowalski", "Lublin 5", "123456789", "jan@o2.pl", new NipDto(1L,null))
        customerService = new CustomerService(customerRepository, vehicleRepository,nipRepository,customerMapper, customerValidator, nipValidator)
    }

    def "Should return all customersDTO"() {
        given:
        def customerList = [customer, customer].toList()
        def customerDtoList = [customerDto, customerDto].toList()
        customerRepository.findAllCustomers() >> customerList
        customerMapper.toCustomerDto(_ as Customer) >> { Customer customer ->
            customerDto.find { it.id == customer.id }
        }
        when:
        def result = customerService.getAll()
        then:
        result == customerDtoList
    }

    def "Should return customerDTO by email"() {
        given:
        customerRepository.findCustomerByEmail("jan@o2.pl") >> customer
        customerMapper.toCustomerDto(customer) >> customerDto
        when:
        def result = customerService.getCustomerByEmail("jan@o2.pl")
        then:
        result == customerDto
    }

    def "Should return null when customer by email does not exist"() {
        given:
        customerRepository.findCustomerByEmail("jan@o2.pl") >> null
        when:
        def result = customerService.getCustomerByEmail("jan@o2.pl")
        then:
        result == null
    }

    def "Should return customerDTO by ID"() {
        given:
        customerRepository.findCustomerByCustomerID(1L) >> customer
        customerMapper.toCustomerDto(customer) >> customerDto
        when:
        def result = customerService.getCustomerByID(1L)
        then:
        result == customerDto
    }

    def "Should return null when add customer by id does not exist"() {
        given:
        customerRepository.findCustomerByCustomerID(1L) >> null
        when:
        def result = customerService.getCustomerByID(1L)
        then:
        result == null
    }


    def "Should return false when add customer is invalid"() {
        given:
        customerDto.setEmail("a")
        customerValidator.validCustomer(customerDto) >> new CustomerValidationResult(false, CustomerValidationError.INCORRECT_LENGTH_EMAIL)
        when:
        def result = customerService.saveCustomer(customerDto)
        then:
        !result
    }

    def "Should return false when add customer nip is invalid"() {
        given:
        customerDto.setNip(new NipDto(1L, "1248481900"))
        customerValidator.validCustomer(customerDto) >> new CustomerValidationResult(true, null)
        nipValidator.validNip(customerDto.getNip()) >> new NipValidationResult(false, NipValidationError.CHECKSUM_FAILED)
        when:
        def result = customerService.saveCustomer(customerDto)
        then:
        !result
    }

    def "Should return false when add customer exception occurs during saving"() {
        given:
        customerValidator.validCustomer(customerDto) >> new CustomerValidationResult(true, null)
        nipValidator.validNip(customerDto.getNip()) >> new NipValidationResult(true, null)
        customerMapper.toCustomer(customerDto) >> customer
        customerRepository.save(_ as Customer) >> { throw new Exception() }
        when:
        def result = customerService.saveCustomer(customerDto)
        then:
        !result
    }

    def "Should return true when add customer successfull"() {
        given:
        customerValidator.validCustomer(customerDto) >> new CustomerValidationResult(true, null)
        nipValidator.validNip(customerDto.getNip()) >> new NipValidationResult(true, null)
        customerMapper.toCustomer(customerDto) >> customer
        customerRepository.save(_ as Customer) >> true
        when:
        def result = customerService.saveCustomer(customerDto)
        then:
        result
    }

    def "Should delete customer by ID"() {
        given:
        def vehicles = [new Vehicle(1L, "BMW", "E36", 1999, "1G4CU5312N1625421", VehicleType.CAR, customer, new ArrayList<>())].toList()
        customerRepository.findCustomerByCustomerID(1L) >> customer
        vehicleRepository.findAllVehiclesByCustomer(customer) >> vehicles
        when:
        customerService.deleteCustomerByID(1L)
        then:
        1 * vehicleRepository.deleteById(1L)
        1 * customerRepository.deleteById(customer.getId())
    }

    def "Should not delete customer when customer does not exist"() {
        given:
        customerRepository.findCustomerByCustomerID(2L) >> null
        when:
        customerService.deleteCustomerByID(2L)
        then:
        0 * vehicleRepository.deleteById(2L)
        0 * customerRepository.deleteById(customer.getId())
    }


    def "Should return false edit customer when id is incorrect"() {
        given:
        customerRepository.findCustomerByCustomerID(2L) >> null
        when:
        def result = customerService.editCustomerByid(new CustomerDto(null, "Anna", null, null, null, null, null), 2L)
        then:
        !result
    }

    def "Should return true when edit customer successfull"() {
        given:
        def nip = new Nip(1L,"5729394296")
        customerRepository.findCustomerByCustomerID(1L) >> customer
        customerMapper.toCustomerDto(customer) >> customerDto
        customerValidator.validCustomer(customerDto) >> new CustomerValidationResult(true, null)
        nipValidator.validNip(customerDto.getNip()) >> new NipValidationResult(true, null)
        when:
        def result = customerService.editCustomerByid(new CustomerDto(null, "Anna", "Kowalska", "Lublin 2", "123456789", "anna@o2.pl", new NipDto(null, "5729394296")), 1L)
        then:
        result
    }

    def "Should return false when edit customer is not valid"() {
        given:
        customerRepository.findCustomerByCustomerID(1L) >> customer
        customerMapper.toCustomerDto(customer) >> customerDto
        customerValidator.validCustomer(customerDto) >> new CustomerValidationResult(false, null)
        nipValidator.validNip(customerDto.getNip()) >> new NipValidationResult(true, null)
        when:
        def result = customerService.editCustomerByid(new CustomerDto(null, "Anna", "Kowalska", "Lublin 2", "123456789", "anna@o2.pl", new NipDto(null ,"5729394296")), 1L)
        then:
        !result
    }

    def "Should return false when edit customer nip is not valid"() {
        given:
        customerRepository.findCustomerByCustomerID(1L) >> customer
        customerMapper.toCustomerDto(customer) >> customerDto
        customerValidator.validCustomer(customerDto) >> new CustomerValidationResult(true, null)
        nipValidator.validNip(customerDto.getNip()) >> new NipValidationResult(false, null)
        when:
        def result = customerService.editCustomerByid(new CustomerDto(null, "Anna", "Kowalska", "Lublin 2", "123456789", "anna@o2.pl", null), 1L)
        then:
        !result
    }


    def "Should return false when edit customer  exception occurs during saving"() {
        given:
        customerRepository.findCustomerByCustomerID(1L) >> customer
        customerMapper.toCustomerDto(customer) >> customerDto
        customerValidator.validCustomer(customerDto) >> new CustomerValidationResult(true, null)
        nipValidator.validNip(customerDto.getNip()) >> new NipValidationResult(true, null)
        customerRepository.editCustomer(_ as String, _ as String, _ as String as String, _ as String as String as String, _ as String as String as String, _ as Long) >> { throw new Exception() }
        when:
        def result = customerService.editCustomerByid(new CustomerDto(null, "Anna", "Kowalska", "Lublin 2", "123456789", "anna@o2.pl", null), 1L)
        then:
        !result
    }
}
