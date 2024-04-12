package com.conference.expandconverter.converters.v1;

import com.conference.expandconverter.dto.AddressDto;
import com.conference.expandconverter.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter extends AbstractExpandConverter<Address, AddressDto> {

    @Override
    protected AddressDto convert(Address source) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(source.getId());
        addressDto.setLocation(source.getLocation());
        return addressDto;
    }

}
