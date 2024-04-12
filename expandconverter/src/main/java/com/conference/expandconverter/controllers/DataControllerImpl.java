package com.conference.expandconverter.controllers;

import com.conference.expandconverter.services.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataControllerImpl implements DataController {

    private final int PERSONS = 100;

    private final int ITEMS = 100;

    private final int ORDERS = 2000;

    private final int ORDER_POSITIONS = 4;

    private final int CATEGORY = 10;

    private final int DELIVERY_TYPE = 10;

    private final int COUNTRY = 10;

    private final int PRODUCERS = 10;

    private final DataService dataService;

    @Override
    public ResponseEntity<Void> generateData() {
        dataService.generateData(PERSONS, ITEMS, ORDERS, ORDER_POSITIONS, CATEGORY, DELIVERY_TYPE, COUNTRY, PRODUCERS);
        return ResponseEntity.ok().build();
    }

}
