package com.example.autoservice.servicetype;

import com.example.autoservice.servicetype.domain.ServiceType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @GetMapping("/autoservice/servicetypes")
    @ResponseBody
    public List<ServiceType> viewAllServiceTypes(){
        return serviceTypeService.getAll();
    }

}
