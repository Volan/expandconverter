package com.conference.expandconverter.converters.v1;

import com.conference.expandconverter.dto.CategoryDto;
import com.conference.expandconverter.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends AbstractExpandConverter<Category, CategoryDto> {

    @Override
    protected CategoryDto convert(Category source) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(source.getId());
        categoryDto.setDescription(source.getDescription());
        return categoryDto;
    }

}
