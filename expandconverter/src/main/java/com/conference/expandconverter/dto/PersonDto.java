package com.conference.expandconverter.dto;

import lombok.Data;

@Data
public class PersonDto {

    private Long id;

    private String firstName;

    private String lastName;

    private AddressDto address;

}