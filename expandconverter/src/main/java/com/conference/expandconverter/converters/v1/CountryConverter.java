package com.conference.expandconverter.converters.v1;

import com.conference.expandconverter.dto.CountryDto;
import com.conference.expandconverter.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter extends AbstractExpandConverter<Country, CountryDto> {

    @Override
    protected CountryDto convert(Country source) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(source.getId());
        countryDto.setName(source.getName());
        return countryDto;
    }

}
