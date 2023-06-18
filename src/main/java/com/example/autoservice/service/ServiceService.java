package com.example.autoservice.service;

import com.example.autoservice.pdf.TemplatePdf;
import com.example.autoservice.service.domain.ServiceDto;
import com.example.autoservice.service.domain.ServiceValidationResult;
import com.example.autoservice.servicetype.ServiceTypeRepository;
import com.example.autoservice.vehicle.VehicleMapper;
import com.example.autoservice.vehicle.VehicleRepository;
import com.example.autoservice.vehicle.domain.Vehicle;
import com.example.autoservice.vehicle.domain.VehicleDto;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final VehicleRepository vehicleRepository;
    private final ServiceMapper serviceMapper;
    private final VehicleMapper vehicleMapper;
    private final ServiceValidator serviceValidator;
    private final ServiceTypeRepository serviceTypeRepository;

    public List<ServiceDto> getAll() {
        List<com.example.autoservice.service.domain.Service> services = serviceRepository.getAll();
        return services.stream()
                .map(serviceMapper::toServiceDto)
                .collect(Collectors.toList());
    }

    public List<ServiceDto> getAllServiceByVehicleId(Long id) {
        List<com.example.autoservice.service.domain.Service> services = vehicleRepository.findServiceByVehicle(id);
        return services.stream()
                .map(serviceMapper::toServiceDto)
                .collect(Collectors.toList());
    }

    public List<ServiceDto> getAllServiceByCustomerId(Long id) {
        List<com.example.autoservice.service.domain.Service> services = vehicleRepository.findServiceByCustomer(id);
        return services.stream()
                .map(serviceMapper::toServiceDto)
                .collect(Collectors.toList());
    }

    public double earningsForMonth(String miesiac) {
        if (!Pattern.matches("[\\d]{2}\\.[\\d]{4}", miesiac))
            return -1;

        String[] date = miesiac.split("\\.");

        double suma = 0.00;
        List<com.example.autoservice.service.domain.Service> services = serviceRepository.getAll();

        Calendar calendar = new GregorianCalendar();
        Calendar calendar1 = new GregorianCalendar();

        for (com.example.autoservice.service.domain.Service s : services) {
            calendar.setTime(s.getDate());
            calendar1.set(Calendar.MONTH, Integer.parseInt(date[0]) - 1);

            if (calendar.get(Calendar.YEAR) == Integer.parseInt(date[1]))
                if (calendar.get(Calendar.MONTH) == calendar1.get(Calendar.MONTH))
                    suma += s.getPrice();
        }

        return suma;
    }

    public double earningsForYear(String year) {
        if (!Pattern.matches("[\\d]{4}", year))
            return -1;

        double suma = 0.00;
        List<com.example.autoservice.service.domain.Service> services = serviceRepository.getAll();

        Calendar calendar = new GregorianCalendar();

        for (com.example.autoservice.service.domain.Service s : services) {
            calendar.setTime(s.getDate());

            if (calendar.get(Calendar.YEAR) == Integer.parseInt(year))
                suma += s.getPrice();
        }

        return suma;
    }

    public boolean saveService(ServiceDto serviceDto, Long id) {
        Vehicle vehicle = vehicleRepository.findVehicleById(id);

        if (isNull(vehicle))
            return false;
        ServiceValidationResult serviceValidationResult = serviceValidator.serviceValidationResult(serviceDto);

        if (!serviceValidationResult.isServiceValid()) {
            return false;
        }

        serviceDto.setServiceType(serviceTypeRepository.findServiceTypeById(id));

        VehicleDto vehicleDto = vehicleMapper.toVehicleDto(vehicle);

        vehicleDto.getServices().add(serviceDto);
        try {
            vehicleRepository.save(vehicleMapper.toVehicle(vehicleDto));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public ResponseEntity<InputStreamResource> generatePdfServicesByVehicleId(Long id) throws IOException {
        Vehicle vehicle = vehicleRepository.findVehicleById(id);

        if(isNull(vehicle))
            return null;

        List<ServiceDto> serviceDtoList = this.getAllServiceByVehicleId(id);

        if(isNull(serviceDtoList))
            return null;

        TemplatePdf templatePdf = new ServicesPdfExporter(serviceDtoList,vehicleMapper.toVehicleDto(vehicle));
        ByteArrayInputStream pdf = templatePdf.export();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline;file=lcwd.pdf");

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }

    boolean editServiceByid(ServiceDto serviceDtoEdit, Long id) {
        com.example.autoservice.service.domain.Service service = serviceRepository.getServiceById(id);
        if (isNull(service)) return false;

        ServiceDto serviceDto= serviceMapper.toServiceDto(service);

        if (!isNull(serviceDtoEdit.getDate()))
            serviceDto.setDate(serviceDtoEdit.getDate());
        if (serviceDtoEdit.getPrice()>=0)
            serviceDto.setPrice(serviceDtoEdit.getPrice());
        if (!isNull(serviceDtoEdit.getDetails()))
            serviceDto.setDetails(serviceDtoEdit.getDetails());
        if (!isNull(serviceDtoEdit.getServiceType()) && !isNull(serviceDtoEdit.getServiceType().getName()))
            serviceDto.setServiceType(serviceDtoEdit.getServiceType());

        ServiceValidationResult serviceValidationResult = serviceValidator.serviceValidationResult(serviceDto);

        if (!serviceValidationResult.isServiceValid()) {
            return false;
        }

        try {
            serviceRepository.editServiceById(serviceDto.getDate(),serviceDto.getDetails(),serviceDto.getPrice(),serviceDto.getServiceType(), id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
