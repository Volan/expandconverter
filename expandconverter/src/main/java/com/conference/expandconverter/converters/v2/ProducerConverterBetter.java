package com.conference.expandconverter.converters.v2;

import com.conference.expandconverter.dto.CountryDto;
import com.conference.expandconverter.dto.ProducerDto;
import com.conference.expandconverter.model.Country;
import com.conference.expandconverter.model.Producer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProducerConverterBetter extends BetterAbstractExpandConverter<Producer, ProducerDto> {

    private final ExpandConverter<Country, CountryDto> countryDtoExpandConverter;

    @PostConstruct
    public void init() {
        Map<String, ExpandConverter> expandConverterMap = new HashMap<>();
        expandConverterMap.put("country", countryDtoExpandConverter);
        super.setExpandConverterMap(expandConverterMap);
    }

    @Override
    protected ProducerDto convert(Producer source) {
        ProducerDto producerDto = new ProducerDto();
        producerDto.setId(source.getId());
        return producerDto;
    }

}
