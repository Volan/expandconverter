package com.conference.expandconverter.converters.v2;

import com.conference.expandconverter.dto.AddressDto;
import com.conference.expandconverter.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverterBetter extends BetterAbstractExpandConverter<Address, AddressDto> {

    @Override
    protected AddressDto convert(Address source) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(source.getId());
        addressDto.setLocation(source.getLocation());
        return addressDto;
    }

}
