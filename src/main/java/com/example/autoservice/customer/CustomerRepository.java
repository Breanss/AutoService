package com.example.autoservice.customer;

import com.example.autoservice.customer.domain.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers();

    @Query("SELECT c FROM Customer c WHERE c.id=:id")
    Customer findCustomerByCustomerID(Long id);

    @Query("SELECT c FROM Customer c WHERE c.email=:email")
    Customer findCustomerByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Customer c SET c.email=:email, c.first_name = :first_name, c.last_name = :last_name, c.mobile=:mobile, c.address=:address where c.id = :id")
    int editCustomer(String email, String first_name, String last_name, String mobile, String address, Long id);
}
