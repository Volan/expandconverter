package com.conference.expandconverter.converters.v2;

import com.conference.expandconverter.model.Category;
import com.conference.expandconverter.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterBetter extends BetterAbstractExpandConverter<Category, CategoryDto> {

    @Override
    protected CategoryDto convert(Category source) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(source.getId());
        categoryDto.setDescription(source.getDescription());
        return categoryDto;
    }

}
