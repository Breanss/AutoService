package com.example.autoservice.nip;

import com.example.autoservice.nip.domain.Nip;
import com.example.autoservice.nip.domain.NipDto;
import com.example.autoservice.nip.domain.NipValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.example.autoservice.nip.domain.NipValidationError.*;
import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class NipValidator {

    private final NipRepository nipRepository;

    public NipValidationResult validNip(NipDto nipDto) {
        if (isNull(nipDto.getNip())) {
            return new NipValidationResult(true, null);
        } else if (validateLengthNip(nipDto.getNip())) {
            return new NipValidationResult(false, INCORRECT_LENGTH);
        } else if (validateContainsCharacterNip(nipDto.getNip())) {
            return new NipValidationResult(false, CONTAINS_ILLEGAL_CHARACTERS);
        } else if (validateCheckSum(nipDto.getNip())) {
            return new NipValidationResult(false, CHECKSUM_FAILED);
        } else if (validateIfNipExists(nipDto.getNip(), nipDto.getId())) {
            return new NipValidationResult(false, NIP_ALREADY_EXISTS);
        }
        return new NipValidationResult(true, null);
    }

    private boolean validateIfNipExists(String nipp, Long id) {
        List<Nip> nip = nipRepository.findAllNip();
        if (isNull(nip))
            return false;

        for (Nip n : nip) {
            if (!isNull(n.getNip()))
                if (n.getNip().equals(nipp) && !Objects.equals(n.getId(), id)) {
                    return true;
                }
        }
        return false;
    }

    private boolean validateLengthNip(String nip) {
        return nip.length() != 10;
    }

    private boolean validateContainsCharacterNip(String nipDto) {
        return !Pattern.matches("[\\d]+", nipDto);
    }

    private boolean validateCheckSum(String nipDto) {
        int[] weight = {6, 5, 7, 2, 3, 4, 5, 6, 7};

        int sum = 0;

        for (int i = 0; i < 9; i++) {
            int nipNumber = Integer.parseInt(String.valueOf(nipDto.charAt(i)));
            sum += nipNumber * weight[i];
        }


        return sum % 11 != Integer.parseInt(String.valueOf(nipDto.charAt(9)));
    }
}
