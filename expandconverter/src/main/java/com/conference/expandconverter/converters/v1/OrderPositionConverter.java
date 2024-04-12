package com.conference.expandconverter.converters.v1;

import com.conference.expandconverter.dto.ItemDto;
import com.conference.expandconverter.dto.OrderPositionDto;
import com.conference.expandconverter.model.Item;
import com.conference.expandconverter.model.OrderPosition;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderPositionConverter extends AbstractExpandConverter<OrderPosition, OrderPositionDto> {

    private final ExpandConverter<Item, ItemDto> itemDtoExpandConverter;

    @PostConstruct
    public void init() {
        Map<String, ExpandConverter> expandConverterMap = new HashMap<>();
        expandConverterMap.put("item", itemDtoExpandConverter);
        super.setExpandConverterMap(expandConverterMap);
    }


    @Override
    protected OrderPositionDto convert(OrderPosition source) {
        OrderPositionDto orderPositionDto = new OrderPositionDto();
        orderPositionDto.setId(source.getId());
        orderPositionDto.setCount(source.getCount());
        orderPositionDto.setOrderId(source.getOrderId());
        return orderPositionDto;
    }
}
