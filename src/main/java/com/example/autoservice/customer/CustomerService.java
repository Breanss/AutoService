package com.example.autoservice.customer;

import com.example.autoservice.customer.domain.Customer;
import com.example.autoservice.customer.domain.CustomerDto;
import com.example.autoservice.customer.domain.CustomerValidationResult;
import com.example.autoservice.nip.NipRepository;
import com.example.autoservice.nip.NipValidator;
import com.example.autoservice.nip.domain.NipValidationResult;
import com.example.autoservice.vehicle.VehicleRepository;
import com.example.autoservice.vehicle.domain.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final NipRepository nipRepository;
    private final CustomerMapper customerMapper;
    private final CustomerValidator customerValidator;
    private final NipValidator nipValidator;

    public List<CustomerDto> getAll() {
        List<Customer> customer = customerRepository.findAllCustomers();
        return customer.stream()
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (isNull(customer))
            return null;
        return customerMapper.toCustomerDto(customer);
    }

    public CustomerDto getCustomerByID(Long id) {
        Customer customer = customerRepository.findCustomerByCustomerID(id);
        if (isNull(customer))
            return null;
        return customerMapper.toCustomerDto(customer);
    }

    public void deleteCustomerByID(Long id) {
        Customer customer = customerRepository.findCustomerByCustomerID(id);

        if (!isNull(customer)) {
            List<Vehicle> vehicles = vehicleRepository.findAllVehiclesByCustomer(customer);
            for (Vehicle v : vehicles) {
                vehicleRepository.deleteById(v.getId());
            }
        }
        customerRepository.deleteById(id);
    }


    public boolean saveCustomer(CustomerDto customerDto) {
        CustomerValidationResult customerValidationResult = customerValidator.validCustomer(customerDto);

        if (!customerValidationResult.isCustomerValid()) {
            return false;
        }

        NipValidationResult nipValidationResult = nipValidator.validNip(customerDto.getNip());
        if (!nipValidationResult.isNipValid()) {
            return false;
        }
        try {
            customerRepository.save(customerMapper.toCustomer(customerDto));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean editCustomerByid(CustomerDto customerDtoEdit, Long id) {
        CustomerDto customerDto = this.getCustomerByID(id);

        if (isNull(customerDto) || isNull(customerDtoEdit)) return false;
        if (!isNull(customerDtoEdit.getEmail()))
            customerDto.setEmail(customerDtoEdit.getEmail());
        if (!isNull(customerDtoEdit.getMobile()))
            customerDto.setMobile(customerDtoEdit.getMobile());
        if (!isNull(customerDtoEdit.getFirst_name()))
            customerDto.setFirst_name(customerDtoEdit.getFirst_name());
        if (!isNull(customerDtoEdit.getLast_name()))
            customerDto.setLast_name(customerDtoEdit.getLast_name());
        if (!isNull(customerDtoEdit.getAddress()))
            customerDto.setAddress(customerDtoEdit.getAddress());
        if (!isNull(customerDtoEdit.getNip()) && !isNull(customerDtoEdit.getNip().getNip())) {
            customerDto.getNip().setNip(customerDtoEdit.getNip().getNip());
        }

        CustomerValidationResult customerValidationResult = customerValidator.validCustomer(customerDto);

        if (!customerValidationResult.isCustomerValid()) {
            return false;
        }



        NipValidationResult nipValidationResult = nipValidator.validNip(customerDto.getNip());

        if (isNull(customerDto.getNip()) || !nipValidationResult.isNipValid()) {
            return false;
        }

        try {
            nipRepository.editNip(customerDto.getNip().getNip(), customerDto.getNip().getId());
            customerRepository.editCustomer(customerDto.getEmail(), customerDto.getFirst_name(), customerDto.getLast_name(), customerDto.getMobile(), customerDto.getAddress(), id);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
