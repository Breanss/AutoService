package com.example.autoservice

import com.example.autoservice.nip.NipMapper
import com.example.autoservice.nip.domain.Nip
import com.example.autoservice.nip.domain.NipDto
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class NipMapperTests extends Specification {
    @Subject
    NipMapper nipMapper

    @Shared
    Nip nip;

    @Shared
    NipDto nipDto

    def setup() {
        nipMapper = new NipMapper()
        nip = new Nip(1L, "5352502246")
        nipDto = new NipDto(1L, "5352502246")
    }

    def "Should return nip"() {
        given:
        def nipT = nipDto
        when:
        def result = nipMapper.toNip(nipT)
        then:
        result.getNip() == nipT.getNip()
        result.getId() == nipT.getId()
    }

    def "Should return nipDto"() {
        given:
        def nipT = nip
        when:
        def result = nipMapper.toNipDto(nipT)
        then:
        result.getNip() == nipT.getNip()
        result.getId() == nipT.getId()
    }
}
