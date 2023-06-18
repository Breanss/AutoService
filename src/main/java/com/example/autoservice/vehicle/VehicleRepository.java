package com.example.autoservice.vehicle;

import com.example.autoservice.customer.domain.Customer;
import com.example.autoservice.service.domain.Service;
import com.example.autoservice.vehicle.domain.Vehicle;
import com.example.autoservice.vehicle.domain.VehicleType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v")
    List<Vehicle> findAllVehicles();

    @Query("SELECT v FROM Vehicle v where v.customer=:customer")
    List<Vehicle> findAllVehiclesByCustomer(Customer customer);

    @Query("SELECT v.services FROM Vehicle v WHERE v.id=:id")
    List<Service> findServiceByVehicle(Long id);

    @Query("SELECT v.services FROM Vehicle v WHERE v.customer.id=:id")
    List<Service> findServiceByCustomer(Long id);

    @Query("SELECT v FROM Vehicle v where v.id=:id")
    Vehicle findVehicleById(Long id);

    @Query("SELECT v FROM Vehicle v WHERE v.vin=:vin")
    Vehicle findVehicleByVin(String vin);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Vehicle v SET v.vin=:vin, v.brand=:brand, v.manufactured_year=:manufactured_year, v.model=:model, v.vehicleType=:vehicleType where v.id = :id")
    int editVehicle(String vin, String brand, int manufactured_year, String model, VehicleType vehicleType, Long id);
}

