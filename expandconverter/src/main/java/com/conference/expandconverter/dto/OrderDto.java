package com.conference.expandconverter.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long id;

    private PersonDto person;

    private List<OrderPositionDto> positions;

    private DeliveryTypeDto deliveryType;

}
