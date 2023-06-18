package com.example.autoservice.servicetype;

import com.example.autoservice.servicetype.domain.ServiceType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServiceTypeInitData {

    private final ServiceTypeRepository serviceTypeRepository;

    public void initServiceType() {
        serviceTypeRepository.save(new ServiceType(null, "Wymiana oleju i filtra oleju"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana świec zapłonowych"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana płynu chłodzącego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana tarcz hamulcowych"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana klocków hamulcowych"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana amortyzatorów"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana sprężyny zawieszenia"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana przewodów hamulcowych"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana paska rozrządu"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana pompy paliwa"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana filtra paliwa"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana filtra powietrza"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana filtra kabinowego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana rozrusznika"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana alternatora"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana akumulatora"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana chłodnicy"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana termostatu"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana przegubu napędowego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana łożyska koła"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana wahacza"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana stabilizatora"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana sworznia wahacza"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana końcówki drążka kierowniczego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana półosi napędowej"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana układu wydechowego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana sondy lambda"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana zacisku hamulcowego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana przewodu hamulcowego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana cylinderka hamulcowego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana przegubu homokinetycznego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana łożyska przód/tył"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana tulei stabilizatora"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana drążka kierowniczego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana wahacza tylnego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana filtra klimatyzacji"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana sondy lambda"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika temperatury"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika ciśnienia oleju"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika ABS"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika położenia wału"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika położenia wałka rozrządu"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika temperatury płynu chłodzącego"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika temperatury oleju"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika parkowania"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika ciśnienia powietrza w oponach"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika temperatury gazu"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika natężenia światła"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika położenia przepustnicy"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika prędkości pojazdu"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika czadu"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana skrzyni biegów"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana czujnika temperatury powietrza"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana pedału gazu"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana pedału hamulca"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana kierownicy"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana sprzęgła"));
        serviceTypeRepository.save(new ServiceType(null, "Wymiana zamków"));
        serviceTypeRepository.save(new ServiceType(null, "Inna usługa"));
    }
}
