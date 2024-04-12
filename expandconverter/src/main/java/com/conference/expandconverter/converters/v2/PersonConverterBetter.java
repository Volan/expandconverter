package com.conference.expandconverter.converters.v2;

import com.conference.expandconverter.dto.AddressDto;
import com.conference.expandconverter.dto.PersonDto;
import com.conference.expandconverter.model.Address;
import com.conference.expandconverter.model.Person;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PersonConverterBetter extends BetterAbstractExpandConverter<Person, PersonDto> {

    private final ExpandConverter<Address, AddressDto> addressDtoExpandConverter;

    @PostConstruct
    public void init() {
        Map<String, ExpandConverter> expandConverterMap = new HashMap<>();
        expandConverterMap.put("address", addressDtoExpandConverter);
        super.setExpandConverterMap(expandConverterMap);
    }

    @Override
    protected PersonDto convert(Person source) {
        PersonDto personDto = new PersonDto();
        personDto.setId(source.getId());
        personDto.setFirstName(source.getFirstName());
        personDto.setLastName(source.getLastName());
        return personDto;
    }

}
