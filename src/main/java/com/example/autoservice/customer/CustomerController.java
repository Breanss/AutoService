package com.example.autoservice.customer;

import com.example.autoservice.customer.domain.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping( "/autoservice/customers/add")
    @ResponseBody
    public boolean addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.saveCustomer(customerDto);
    }

    @GetMapping("/autoservice/customers")
    @ResponseBody
    public List<CustomerDto> viewAllCustomers(){
        return customerService.getAll();
    }

    @GetMapping("/autoservice/customers/email/{email}")
    @ResponseBody
    public CustomerDto viewCustomerByEmail(@PathVariable String email){
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/autoservice/customers/id/{id}")
    @ResponseBody
    public CustomerDto viewCustomerByID(@PathVariable Long id){
        return customerService.getCustomerByID(id);
    }

    @DeleteMapping("/autoservice/customers/delete/{id}")
    @ResponseBody
    public void deleteCustomerByID(@PathVariable Long id){
        customerService.deleteCustomerByID(id);
    }

    @PutMapping("/autoservice/customers/edit/{id}")
    @ResponseBody
    public boolean editCustomerByID(@RequestBody CustomerDto customerDto, @PathVariable Long id){
        return customerService.editCustomerByid(customerDto, id);
    }

}
