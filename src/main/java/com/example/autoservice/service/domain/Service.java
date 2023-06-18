package com.example.autoservice.service.domain;

import com.example.autoservice.servicetype.domain.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String details;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private ServiceType serviceType;

}
