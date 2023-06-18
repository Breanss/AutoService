package com.example.autoservice.initdefaultdata;

import com.example.autoservice.customer.CustomerMapper;
import com.example.autoservice.customer.CustomerRepository;
import com.example.autoservice.customer.domain.Customer;
import com.example.autoservice.customer.domain.CustomerDto;
import com.example.autoservice.nip.domain.Nip;
import com.example.autoservice.service.domain.Service;
import com.example.autoservice.servicetype.ServiceTypeInitData;
import com.example.autoservice.servicetype.ServiceTypeRepository;
import com.example.autoservice.vehicle.VehicleMapper;
import com.example.autoservice.vehicle.VehicleRepository;
import com.example.autoservice.vehicle.domain.Vehicle;
import com.example.autoservice.vehicle.domain.VehicleType;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;



@Component
@AllArgsConstructor
public class InitData {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final VehicleRepository vehicleRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final VehicleMapper vehicleMapper;

    private final ServiceTypeInitData serviceTypeInitData;

    @PostConstruct
    public void initDefaultData() {
        serviceTypeInitData.initServiceType();
        initCustomers();
        initVehicles();
        initServices();
    }

    private void initCustomers() {
        customerRepository.save(new Customer(null, "Jan", "Kowalski", "Lublin 4", "518234123", "jkowal@o2.pl", new Nip()));
        customerRepository.save(new Customer(null, "Anna", "Nowak", "Warszawa 7", "512345678", "annanowak@gmail.com", new Nip(null, "5263876229")));
        customerRepository.save(new Customer(null, "Piotr", "Wiśniewski", "Kraków 12", "511223344", "piotr.wisniewski@wp.pl", new Nip()));
        customerRepository.save(new Customer(null, "Magdalena", "Kaczmarek", "Poznań 9", "510987654", "magda.kaczmarek@yahoo.com", new Nip(null, "5333113087")));
        customerRepository.save(new Customer(null, "Marek", "Nowicki", "Gdańsk 2", "515555555", "mnowicki@gmail.com", new Nip(null, "5358978990")));
    }

    private void initVehicles() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        vehicleRepository.save(new Vehicle(null, "BMW", "E36", 1999, "4T1SK12E1NU028452", VehicleType.CAR, customerMapper.toCustomerId(customerDto), new ArrayList<>()));
        customerDto.setId(1L);
        vehicleRepository.save(new Vehicle(null, "Renault", "Megane", 2010, "1GNDT13W012105424", VehicleType.CAR, customerMapper.toCustomerId(customerDto), new ArrayList<>()));
        customerDto.setId(2L);
        vehicleRepository.save(new Vehicle(null, "Ursus", "C-360", 1970, "JH4DB7550RS000461", VehicleType.TRACTOR, customerMapper.toCustomerId(customerDto), new ArrayList<>()));
        customerDto.setId(3L);
        vehicleRepository.save(new Vehicle(null, "Honda", "Cbr 125", 2012, "1G2JB12F047226515", VehicleType.MOTORBIKE, customerMapper.toCustomerId(customerDto), new ArrayList<>()));
        customerDto.setId(4L);
        vehicleRepository.save(new Vehicle(null, "Mercedes", "Sprinter", 2015, "2CNDL23F856093901", VehicleType.BUS, customerMapper.toCustomerId(customerDto), new ArrayList<>()));
        customerDto.setId(4L);
        vehicleRepository.save(new Vehicle(null, "Audi", "A5", 2009, "WAUYGAFC6CN174200", VehicleType.CAR, customerMapper.toCustomerId(customerDto), new ArrayList<>()));
        customerDto.setId(4L);
        vehicleRepository.save(new Vehicle(null, "Opel", "Insygnia", 2011, "WAUGC0896JA235262", VehicleType.CAR, customerMapper.toCustomerId(customerDto), new ArrayList<>()));
        customerDto.setId(5L);
        vehicleRepository.save(new Vehicle(null, "Claas", "Axion", 2006, "2FTHF26L7GCA92029", VehicleType.TRACTOR, customerMapper.toCustomerId(customerDto), new ArrayList<>()));
    }

    private void initServices() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Vehicle vehicle1 = vehicleRepository.findVehicleById(1L);
            vehicle1.getServices().add(new Service(null, 424.99, dateFormat.parse("2023-01-12"), null, serviceTypeRepository.findServiceTypeById(14L)));
            vehicle1.getServices().add(new Service(null, 250.00, dateFormat.parse("2023-01-23"), null, serviceTypeRepository.findServiceTypeById(12L)));
            vehicleRepository.save(vehicle1);

            Vehicle vehicle2 = vehicleRepository.findVehicleById(2L);
            vehicle2.getServices().add(new Service(null, 80.50, dateFormat.parse("2023-04-26"), null, serviceTypeRepository.findServiceTypeById(24L)));
            vehicleRepository.save(vehicle2);

            Vehicle vehicle3 = vehicleRepository.findVehicleById(3L);
            vehicle3.getServices().add(new Service(null, 1425.24, dateFormat.parse("2023-05-04"), null, serviceTypeRepository.findServiceTypeById(57L)));
            vehicle3.getServices().add(new Service(null, 3222.00, dateFormat.parse("2023-05-04"), "Remont silnika", serviceTypeRepository.findServiceTypeById(59L)));
            vehicle3.getServices().add(new Service(null, 124.23, dateFormat.parse("2023-05-04"), null, serviceTypeRepository.findServiceTypeById(39L)));
            vehicle3.getServices().add(new Service(null, 53.50, dateFormat.parse("2023-04-26"), null, serviceTypeRepository.findServiceTypeById(54L)));
            vehicleRepository.save(vehicle3);

            Vehicle vehicle4 = vehicleRepository.findVehicleById(4L);
            vehicle4.getServices().add(new Service(null, 150.00, dateFormat.parse("2023-02-14"), null, serviceTypeRepository.findServiceTypeById(10L)));
            vehicleRepository.save(vehicle4);

            Vehicle vehicle5 = vehicleRepository.findVehicleById(5L);
            vehicle5.getServices().add(new Service(null, 5400.00, dateFormat.parse("2023-03-04"), null, serviceTypeRepository.findServiceTypeById(25L)));
            vehicle5.getServices().add(new Service(null, 130.50, dateFormat.parse("2023-03-04"), null, serviceTypeRepository.findServiceTypeById(13L)));
            vehicleRepository.save(vehicle5);

            Vehicle vehicle6 = vehicleRepository.findVehicleById(6L);
            vehicle6.getServices().add(new Service(null, 400.00, dateFormat.parse("2023-04-26"), null, serviceTypeRepository.findServiceTypeById(30L)));
            vehicle6.getServices().add(new Service(null, 3500.00, dateFormat.parse("2023-06-01"), "Uszczelka pod głowicą", serviceTypeRepository.findServiceTypeById(59L)));
            vehicleRepository.save(vehicle6);

            Vehicle vehicle7 = vehicleRepository.findVehicleById(7L);
            vehicle7.getServices().add(new Service(null, 129.30, dateFormat.parse("2023-01-15"), null, serviceTypeRepository.findServiceTypeById(41L)));
            vehicleRepository.save(vehicle7);

            Vehicle vehicle8 = vehicleRepository.findVehicleById(8L);
            vehicle8.getServices().add(new Service(null, 200.00, dateFormat.parse("2023-05-12"), null, serviceTypeRepository.findServiceTypeById(31L)));
            vehicle8.getServices().add(new Service(null, 450.50, dateFormat.parse("2023-05-12"), null, serviceTypeRepository.findServiceTypeById(10L)));
            vehicle8.getServices().add(new Service(null, 230.50, dateFormat.parse("2023-05-12"), null, serviceTypeRepository.findServiceTypeById(55L)));
            vehicleRepository.save(vehicle8);

        } catch (Exception ignored) {
        }

    }


}
