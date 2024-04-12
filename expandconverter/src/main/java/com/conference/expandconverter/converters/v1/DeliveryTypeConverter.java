package com.conference.expandconverter.converters.v1;

import com.conference.expandconverter.dto.DeliveryTypeDto;
import com.conference.expandconverter.model.DeliveryType;
import org.springframework.stereotype.Component;

@Component
public class DeliveryTypeConverter extends AbstractExpandConverter<DeliveryType, DeliveryTypeDto> {

    @Override
    protected DeliveryTypeDto convert(DeliveryType source) {
        DeliveryTypeDto deliveryTypeDto = new DeliveryTypeDto();
        deliveryTypeDto.setId(source.getId());
        deliveryTypeDto.setType(source.getType());
        return deliveryTypeDto;
    }

}
