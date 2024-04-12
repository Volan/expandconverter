package com.conference.expandconverter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/data")
public interface DataController {

    @PostMapping("/generateData")
    ResponseEntity<Void> generateData();

}
