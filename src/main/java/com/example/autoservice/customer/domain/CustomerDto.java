package com.example.autoservice.customer.domain;

import com.example.autoservice.nip.domain.NipDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String first_name;
    private String last_name;
    private String address;
    private String mobile;
    private String email;
    private NipDto nip;
}
