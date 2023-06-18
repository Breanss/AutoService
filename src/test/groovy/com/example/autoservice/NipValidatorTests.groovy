package com.example.autoservice

import com.example.autoservice.nip.NipRepository
import com.example.autoservice.nip.NipValidator
import com.example.autoservice.nip.domain.Nip
import com.example.autoservice.nip.domain.NipDto
import com.example.autoservice.nip.domain.NipValidationError
import spock.lang.Specification
import spock.lang.Subject


class NipValidatorTests extends Specification {
    @Subject
    NipValidator nipValidator

    NipRepository nipRepository = Mock(NipRepository)


    def setup() {
        nipValidator = new NipValidator(nipRepository)
    }

    def "Should return validation successfull when nip is null"() {
        given:
        def nipDto = new NipDto(null, null)
        when:
        def result = nipValidator.validNip(nipDto)
        then:
        result.nipValid
    }

    def "Should return validation successfull when nip is correct"() {
        given:
        def nipDto = new NipDto(1L, "5090367763")
        when:
        def result = nipValidator.validNip(nipDto)
        then:
        result.nipValid
    }

    def "Should return validation failed when nip is too short"() {
        given:
        def nipDto = new NipDto(null, "509036776")
        when:
        def result = nipValidator.validNip(nipDto)
        then:
        !result.nipValid
        result.nipValidationError == NipValidationError.INCORRECT_LENGTH
    }

    def "Should return validation failed when nip is space"() {
        given:
        def nipDto = new NipDto(null, " ")
        when:
        def result = nipValidator.validNip(nipDto)
        then:
        !result.nipValid
        result.nipValidationError == NipValidationError.INCORRECT_LENGTH
    }

    def "Should return validation failed when nip contains letters"() {
        given:
        def nipDto = new NipDto(null, "509036776a")
        when:
        def result = nipValidator.validNip(nipDto)
        then:
        !result.nipValid
        result.nipValidationError == NipValidationError.CONTAINS_ILLEGAL_CHARACTERS
    }

    def "Should return failed validation when nip has incorrect checksum"() {
        given:
        def nipDto = new NipDto(null, "8127264002")
        when:
        def result = nipValidator.validNip(nipDto)
        then:
        !result.nipValid
        result.nipValidationError == NipValidationError.CHECKSUM_FAILED
    }

    def "Should return failed validation when nip already exist"() {
        given:
        Long id = 1L;

        String uniqueNip = "9876543210";
        NipDto nipDto = new NipDto(id, uniqueNip);
        List<Nip> existingNips = [new Nip(2L, "9876543210")];

        when:
        def result = nipValidator.validNip(nipDto)
        then:
        !result.nipValid
        result.nipValidationError == NipValidationError.NIP_ALREADY_EXISTS
        1 * nipRepository.findAllNip() >> existingNips
    }

    def "Should return successfull validation when nip no already exist"() {
        given:
        Long id = 1L;

        String uniqueNip = "9876543210";
        NipDto nipDto = new NipDto(id, uniqueNip);
        List<Nip> existingNips = [new Nip(2L, "9655816968")];

        when:
        def result = nipValidator.validNip(nipDto)
        then:
        result.nipValid
        1 * nipRepository.findAllNip() >> existingNips
    }
}
