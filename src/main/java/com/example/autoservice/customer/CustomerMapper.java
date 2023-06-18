package com.example.autoservice.customer;

import com.example.autoservice.customer.domain.Customer;
import com.example.autoservice.customer.domain.CustomerDto;
import com.example.autoservice.nip.NipMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private final NipMapper nipMapper;

    public CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getAddress(),
                customer.getMobile(),
                customer.getEmail(),
                nipMapper.toNipDto(customer.getNip())
        );
    }

    public Customer toCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getFirst_name(),
                customerDto.getLast_name(),
                customerDto.getAddress(),
                customerDto.getMobile(),
                customerDto.getEmail(),
                nipMapper.toNip(customerDto.getNip())
        );
    }

    public Customer toCustomerId(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
