package com.conference.expandconverter.converters.v1;

import com.conference.expandconverter.dto.CategoryDto;
import com.conference.expandconverter.dto.ItemDto;
import com.conference.expandconverter.dto.ProducerDto;
import com.conference.expandconverter.model.Category;
import com.conference.expandconverter.model.Item;
import com.conference.expandconverter.model.Producer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ItemConverter extends AbstractExpandConverter<Item, ItemDto> {

    private final ExpandConverter<Category, CategoryDto> categoryDtoExpandConverter;

    private final ExpandConverter<Producer, ProducerDto> producerDtoExpandConverter;

    @PostConstruct
    public void init() {
        Map<String, ExpandConverter> expandConverterMap = new HashMap<>();
        expandConverterMap.put("category", categoryDtoExpandConverter);
        expandConverterMap.put("producer", producerDtoExpandConverter);
        super.setExpandConverterMap(expandConverterMap);
    }

    @Override
    protected ItemDto convert(Item source) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(source.getId());
        itemDto.setDescription(source.getDescription());
        return itemDto;
    }

}
