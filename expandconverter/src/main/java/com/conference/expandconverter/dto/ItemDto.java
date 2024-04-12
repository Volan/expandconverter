package com.conference.expandconverter.dto;

import lombok.Data;

@Data
public class ItemDto {

    private Long id;

    private String description;

    private CategoryDto category;

    private ProducerDto producer;

}