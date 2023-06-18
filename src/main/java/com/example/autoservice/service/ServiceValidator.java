package com.example.autoservice.service;

import com.example.autoservice.service.domain.ServiceDto;
import com.example.autoservice.service.domain.ServiceValidationResult;
import com.example.autoservice.servicetype.ServiceTypeRepository;
import com.example.autoservice.servicetype.domain.ServiceType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.example.autoservice.service.domain.ServiceValidationError.*;
import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class ServiceValidator {

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceValidationResult serviceValidationResult(ServiceDto serviceDto) {

        if (isNull(serviceDto.getDate())) {
            return new ServiceValidationResult(false, NULL_VALUE_DATE);
        } else if (validateDate(serviceDto.getDate())) {
            return new ServiceValidationResult(false, INCORRECT_DATE);
        }

        if (!isNull(serviceDto.getDetails()) && validateLengthDetails(serviceDto.getDetails())) {
            return new ServiceValidationResult(false, INCORRECT_LENGTH_DETAIL);
        }

        if (validateMinusPrice(serviceDto.getPrice())) {
            return new ServiceValidationResult(false, MINUS_PRICE);
        } else if (validatePrecissionPrice(serviceDto.getPrice())) {
            return new ServiceValidationResult(false, INCORRECT_PRECISION_PRICE);
        }

        if (isNull(serviceDto.getServiceType())) {
            return new ServiceValidationResult(false, NULL_SERVICE_TYPE);
        } else if (validateValueServiceType(serviceDto.getServiceType())) {
            return new ServiceValidationResult(false, INCORRECT_VALUE_SERVICE_TYPE);
        }

        return new ServiceValidationResult(true, null);
    }

    private boolean validateDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) > LocalDate.now().getYear()
                || calendar.get(Calendar.MONTH) > LocalDate.now().getMonthValue();
    }

    private boolean validateLengthDetails(String detail) {
        return detail.length() > 254;
    }

    private boolean validateValueServiceType(ServiceType serviceType) {
        ServiceType type = serviceTypeRepository.findServiceTypeById(serviceType.getId());
        return isNull(type);
    }

    private boolean validateMinusPrice(double price) {
        return price <= 0;
    }

    private boolean validatePrecissionPrice(double price) {
        String[] p = String.valueOf(price).split("\\.");

        if (p.length == 2) {
            return p[1].length() > 2;
        }

        return true;
    }
}
