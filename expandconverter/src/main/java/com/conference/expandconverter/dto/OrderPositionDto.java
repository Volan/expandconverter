package com.conference.expandconverter.dto;

import lombok.Data;

@Data
public class OrderPositionDto {

    private Long id;

    private ItemDto item;

    private Long count;

    private Long orderId;

}
