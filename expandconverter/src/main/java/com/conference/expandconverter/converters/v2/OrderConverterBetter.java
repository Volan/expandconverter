package com.conference.expandconverter.converters.v2;

import com.conference.expandconverter.model.OrderPosition;
import com.conference.expandconverter.dto.DeliveryTypeDto;
import com.conference.expandconverter.dto.OrderDto;
import com.conference.expandconverter.dto.OrderPositionDto;
import com.conference.expandconverter.dto.PersonDto;
import com.conference.expandconverter.model.DeliveryType;
import com.conference.expandconverter.model.Order;
import com.conference.expandconverter.model.Person;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderConverterBetter extends BetterAbstractExpandConverter<Order, OrderDto> {

    private final ExpandConverter<Person, PersonDto> personDtoExpandConverter;

    private final ExpandConverter<OrderPosition, OrderPositionDto> orderPositionDtoExpandConverter;

    private final ExpandConverter<DeliveryType, DeliveryTypeDto> deliveryTypeDtoExpandConverter;

    @PostConstruct
    public void init() {
        Map<String, ExpandConverter> expandConverterMap = new HashMap<>();
        expandConverterMap.put("person", personDtoExpandConverter);
        expandConverterMap.put("positions", orderPositionDtoExpandConverter);
        expandConverterMap.put("deliveryType", deliveryTypeDtoExpandConverter);
        super.setExpandConverterMap(expandConverterMap);
    }

    @Override
    protected OrderDto convert(Order source) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(source.getId());
        return orderDto;
    }

}
