package com.example.autoservice.nip;

import com.example.autoservice.nip.domain.Nip;
import com.example.autoservice.nip.domain.NipDto;
import org.springframework.stereotype.Component;

@Component
public class NipMapper {
    public NipDto toNipDto(Nip nip) {
        return new NipDto(nip.getId(), nip.getNip());
    }

    public Nip toNip(NipDto nipDto) {
        return new Nip(nipDto.getId(), nipDto.getNip());
    }
}

