package com.example.autoservice.service;
import com.example.autoservice.service.domain.ServiceDto;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ServiceController {
    private final ServiceService service;

    @GetMapping("/autoservice/services")
    @ResponseBody
    public List<ServiceDto> viewAllServices() {
        return service.getAll();
    }

    @GetMapping("/autoservice/customer/vehicle/{id}/services")
    public List<ServiceDto> viewServicesForVehicle(@PathVariable Long id) {
        return service.getAllServiceByVehicleId(id);
    }

    @GetMapping("/autoservice/customer/{id}/vehicle/services")
    public List<ServiceDto> viewServicesForCustomer(@PathVariable Long id) {
        return service.getAllServiceByCustomerId(id);
    }

    @GetMapping("/autoservice/services/earnings/month/{date}")
    public double viewEarningsForMonth(@PathVariable String date) {
        return service.earningsForMonth(date);
    }

    @GetMapping("/autoservice/services/earnings/year/{date}")
    public double viewEarningsForYear(@PathVariable String date) {
        return service.earningsForYear(date);
    }

    @PostMapping("/autoservice/customer/vehicle/{id}/services/add")
    public boolean addService(@RequestBody ServiceDto serviceDto, @PathVariable Long id) {
        return service.saveService(serviceDto, id);
    }

    @PutMapping("/autoservice/services/edit/{id}")
    public void editService(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        service.editServiceByid(serviceDto,id);
    }

    @GetMapping("/autoservice/customer/vehicle/{id}/pdf")
    public ResponseEntity<InputStreamResource> generatePDFField(@PathVariable Long id) throws IOException {
        return service.generatePdfServicesByVehicleId(id);
    }
}
