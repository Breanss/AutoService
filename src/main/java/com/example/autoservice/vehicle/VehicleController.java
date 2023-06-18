package com.example.autoservice.vehicle;

import com.example.autoservice.vehicle.domain.VehicleDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("/autoservice/customer/vehicles/add")
    @ResponseBody
    public boolean addVehicle(@RequestBody VehicleDto vehicleDto) {
        return vehicleService.saveVehicle(vehicleDto);
    }

    @GetMapping("/autoservice/vehicles")
    @ResponseBody
    public List<VehicleDto> viewAllVehicles() {
        return vehicleService.getAll();
    }

    @GetMapping("/autoservice/vehicles/id/{id}")
    @ResponseBody
    public VehicleDto viewVehicleByID(@PathVariable Long id) {
        return vehicleService.getVehicleByID(id);
    }

    @GetMapping("/autoservice/vehicles/customer/id/{id}")
    @ResponseBody
    public List<VehicleDto> viewAllVehicleByCustomer(@PathVariable Long id) {
        return vehicleService.getAllVehiclesByCustomer(id);
    }

    @GetMapping("/autoservice/vehicles/vin/{vin}")
    @ResponseBody
    public VehicleDto viewVehicleByVIN(@PathVariable String vin) {
        return vehicleService.getVehicleByVIN(vin);
    }

    @DeleteMapping("/autoservice/vehicles/delete/{id}")
    @ResponseBody
    public void deleteCustomerByID(@PathVariable Long id) {
        vehicleService.deleteVehicleByID(id);
    }

    @PutMapping("/autoservice/vehicles/edit/{id}")
    @ResponseBody
    public boolean editVehicleByID(@RequestBody VehicleDto vehicleDto, @PathVariable Long id) {
        return vehicleService.editVehicleByID(vehicleDto, id);
    }

}
